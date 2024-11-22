package com.rafaellacruz.lolbuildbuddy;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class DataHandler {
    private static final String DATA_NOT_AVAILABLE = "Dado não disponível (Data is not available).";
    private static final String CHAMPION_NOT_FOUND = "Campeão não encontrado (Champion not found).";

    private final Map<String, Map<String, String>> championMap;

    public DataHandler(String csvFilePath, CSVLoader csvLoader) throws IOException {
        this.championMap = loadChampionData(csvFilePath, csvLoader);
    }

    private Map<String, Map<String, String>> loadChampionData(String csvFilePath, CSVLoader csvLoader) throws IOException {
        List<Map<String, String>> championData = csvLoader.loadChampionData(csvFilePath);
        Map<String, Map<String, String>> map = new HashMap<>();
        for (Map<String, String> row : championData) {
            String name = row.get("Name").toLowerCase().trim();
            map.put(name, row);
        }
        return map;
    }

    private String getChampionAttribute(String championName, String attribute) {
        if (championName == null || championName.isBlank()) {
            return DATA_NOT_AVAILABLE;
        }
        Map<String, String> championInfo = championMap.get(championName.toLowerCase().trim());
        return Optional.ofNullable(championInfo)
                .map(info -> Optional.ofNullable(info.get(attribute)).orElse(DATA_NOT_AVAILABLE))
                .orElse(CHAMPION_NOT_FOUND);
    }

    public String getResourceType(String championName) {
        return getChampionAttribute(championName, "Resource type");
    }

    public String getDifficulty(String championName) {
        return getChampionAttribute(championName, "Difficulty");
    }

    public String getRole(String championName) {
        return getChampionAttribute(championName, "Role");
    }

    public String getChampionInfo(String championName) {
        if (championName == null || championName.isBlank()) {
            return "Nome de campeão inválido (Invalid champion name).";
        }
        Map<String, String> championInfo = championMap.get(championName.toLowerCase().trim());
        return Optional.ofNullable(championInfo)
                .map(info -> {
                    StringBuilder infoBuilder = new StringBuilder("Informações do campeão " + championName + ":\n");
                    info.forEach((key, value) -> infoBuilder.append(key).append(": ").append(value).append("\n"));
                    return infoBuilder.toString();
                })
                .orElse(CHAMPION_NOT_FOUND);
    }
}
