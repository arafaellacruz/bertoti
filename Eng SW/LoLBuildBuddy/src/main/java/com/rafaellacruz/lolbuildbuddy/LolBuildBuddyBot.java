package com.rafaellacruz.lolbuildbuddy;

import io.github.ollama4j.exceptions.OllamaBaseException;
import org.telegram.telegrambots.client.okhttp.OkHttpTelegramClient;
import org.telegram.telegrambots.longpolling.util.LongPollingSingleThreadUpdateConsumer;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.meta.generics.TelegramClient;

import java.io.IOException;
import java.util.regex.Pattern;

public class LolBuildBuddyBot implements LongPollingSingleThreadUpdateConsumer {
    private static final String ERROR_RESPONSE = "Desculpe, houve um erro ao processar sua solicitação.";
    private static final String RESOURCE_KEYWORD = "(recursos|resources)";
    private static final String DIFFICULTY_KEYWORDS = "(dificuldade|difficulty)";
    private static final String ROLE_KEYWORDS = "(role|lane|rota)";

    private final TelegramClient telegramClient;
    private final Ollama ollama = new Ollama();
    private final DataHandler dataHandler;

    public LolBuildBuddyBot(String botToken, String csvFilePath, CSVLoader csvLoader) throws IOException {
        this.telegramClient = new OkHttpTelegramClient(botToken);
        this.dataHandler = new DataHandler(csvFilePath, csvLoader);
    }

    @Override
    public void consume(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();

            String response = handleMessage(messageText);
            SendMessage message = SendMessage.builder().chatId(chatId).text(response).build();

            try {
                telegramClient.execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private String handleMessage(String messageText) {
        String response;

        if (messageText.toLowerCase().contains("informações") || messageText.toLowerCase().contains("info")) {
            String championName = extractChampionName(messageText);
            response = dataHandler.getChampionInfo(championName);
        } else if (messageText.toLowerCase().contains(RESOURCE_KEYWORD)) {
            response = dataHandler.getResourceType(messageText);
        } else if (Pattern.compile(DIFFICULTY_KEYWORDS).matcher(messageText.toLowerCase()).find()) {
            response = dataHandler.getDifficulty(messageText);
        } else if (Pattern.compile(ROLE_KEYWORDS).matcher(messageText.toLowerCase()).find()) {
            response = dataHandler.getRole(messageText);
        } else {
            response = handleOllamaQuery(messageText);
        }

        return formatResponse(response);
    }

    private String extractChampionName(String messageText) {
        String[] words = messageText.split("\\s+");
        for (String word : words) {
            if (dataHandler.getChampionInfo(word) != null) {
                return word;
            }
        }
        return null;
    }


    private String handleOllamaQuery(String messageText) {
        try {
            return ollama.getOllamaResponse(messageText);
        } catch (OllamaBaseException | IOException | InterruptedException e) {
            e.printStackTrace();
            return ERROR_RESPONSE;
        }
    }

    private String formatResponse(String response) {
        response = response.trim();
        response = response.replace("*", "");
        response = response.replaceAll("(?m)^[\\s:]*$\\n", "");

        return response;
    }
}
