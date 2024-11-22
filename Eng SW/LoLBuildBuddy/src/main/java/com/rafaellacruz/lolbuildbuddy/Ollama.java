package com.rafaellacruz.lolbuildbuddy;

import io.github.ollama4j.OllamaAPI;
import io.github.ollama4j.exceptions.OllamaBaseException;
import io.github.ollama4j.models.response.OllamaResult;
import io.github.ollama4j.utils.OptionsBuilder;
import io.github.ollama4j.utils.PromptBuilder;

import java.io.IOException;

public class Ollama {
    private static final String HOST = "http://localhost:11434/";
    private final OllamaAPI ollamaAPI;

    public Ollama() {
        this.ollamaAPI = new OllamaAPI(HOST);
        ollamaAPI.setRequestTimeoutSeconds(200);
    }

    public String getOllamaResponse(String prompt) throws OllamaBaseException, IOException, InterruptedException {
        PromptBuilder promptBuilder = new PromptBuilder()
                .addLine("You are an expert in League of Legends builds and strategies, familiar with champions, items, and optimal strategies.")
                .addLine("Your task is to generate a short, optimal build for the given champion based on role, lane, and meta.")
                .addLine("For example: 'Build for Yasuo mid' should give me the best items, runes, and strategy for Yasuo in the mid lane.")
                .addLine("Please provide the following:")
                .addLine("- Key items: core and situational.")
                .addLine("- Recommended runes setup for the champion's role and match-up.")
                .addLine("- A brief strategy for the champion in the lane (1-2 sentences).")
                .addLine("Do not include excessive details or redundant information.")
                .addLine("Keep responses short and focused on the most important aspects.")
                .addLine("Answer only in the language of the prompt (English or Portuguese).")
                .addLine("When bringing in a response, add a header like something like 'Here's what I found about...' to add the context of the messageText.")
                .addLine("Você é um especialista em builds e estratégias de League of Legends, com conhecimento profundo de campeões, itens e estratégias ótimas.")
                .addLine("Sua tarefa é gerar uma build otimizada e curta para o campeão fornecido, com base na função, rota e meta atual.")
                .addLine("Por exemplo: 'Build para Yasuo mid' deve me dar os melhores itens, runas e estratégia para Yasuo na rota do meio.")
                .addLine("Por favor, forneça o seguinte:")
                .addLine("- Itens principais: itens essenciais e situacionais.")
                .addLine("- Configuração de runas recomendada para a função e match-up do campeão.")
                .addLine("- Estratégia breve para o campeão na rota (1-2 frases).")
                .addLine("Não inclua detalhes excessivos ou informações redundantes.")
                .addLine("Mantenha as respostas curtas e focadas nos aspectos mais importantes.")
                .addLine("Responda apenas no idioma da pergunta (se for português, responda em português).")
                .addLine("Ao trazer uma resposta, adicione um cabeçalho como algo pareciso com 'Aqui está o que encontrei sobre...' adicionar o contexto da messageText.")
                .addSeparator()
                .addSeparator()
                .add(prompt);

        OllamaResult result = ollamaAPI.generate("gemma:2b", promptBuilder.build(), true, new OptionsBuilder().build());
        return result.getResponse();
    }
}


