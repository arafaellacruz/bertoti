package com.rafaellacruz.lolbuildbuddy;

import org.apache.commons.csv.*;

import java.io.*;
import java.util.*;

public class CSVLoader {
    public List<Map<String, String>> loadChampionData(String fileName) throws IOException {
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream(fileName);

        if (inputStream == null) {
            throw new FileNotFoundException("Arquivo não encontrado: " + fileName);
        }

        Reader reader = new InputStreamReader(inputStream);
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT.withHeader().withIgnoreHeaderCase().withTrim());

        List<Map<String, String>> records = new ArrayList<>();

        for (CSVRecord csvRecord : csvParser) {
            Map<String, String> record = new HashMap<>();
            for (String header : csvParser.getHeaderMap().keySet()) {
                record.put(header, csvRecord.get(header));
            }
            records.add(record);
        }

        csvParser.close();
        return records;
    }
}