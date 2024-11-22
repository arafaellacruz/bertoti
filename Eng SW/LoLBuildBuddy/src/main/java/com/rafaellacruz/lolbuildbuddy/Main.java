package com.rafaellacruz.lolbuildbuddy;

import org.telegram.telegrambots.longpolling.TelegramBotsLongPollingApplication;

public class Main {
    public static void main(String[] args) {
        String botToken = "7844778549:AAFDyfgn1AGLuaYQqeSxknmzKw4Id2Ztrk0";
        String csvFilePath = "LeagueOfLegendChampions2024.csv";
        CSVLoader csvLoader = new CSVLoader();

        try (TelegramBotsLongPollingApplication botsApplication = new TelegramBotsLongPollingApplication()) {
            LolBuildBuddyBot bot = new LolBuildBuddyBot(botToken, csvFilePath, csvLoader);
            botsApplication.registerBot(botToken, bot);
            System.out.println("LolBuildBuddyBot successfully started!");
            Thread.currentThread().join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
