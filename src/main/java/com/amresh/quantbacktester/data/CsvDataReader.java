package com.amresh.quantbacktester.data;

import com.amresh.quantbacktester.model.MarketData;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class CsvDataReader {
    private DateTimeFormatter formatter;

    // Constructor accepts a date format pattern (e.g., "yyyy-MM-dd HH:mm:ss")
    public CsvDataReader(String datePattern) {
        this.formatter = DateTimeFormatter.ofPattern(datePattern);
    }

    public List<MarketData> readData(String filePath) throws IOException {
        List<MarketData> dataList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            // Read header line (if exists)
            br.readLine();
            while ((line = br.readLine()) != null) {
                String[] fields = line.split(",");
                MarketData data = parseLine(fields);
                dataList.add(data);
            }
        }
        return dataList;
    }

    private MarketData parseLine(String[] fields) {
        // Assumes CSV columns: timestamp, open, high, low, close, volume
        LocalDateTime timestamp = LocalDateTime.parse(fields[0], formatter);
        double open = Double.parseDouble(fields[1]);
        double high = Double.parseDouble(fields[2]);
        double low = Double.parseDouble(fields[3]);
        double close = Double.parseDouble(fields[4]);
        long volume = Long.parseLong(fields[5]);
        return new MarketData(timestamp, open, high, low, close, volume);
    }
}
