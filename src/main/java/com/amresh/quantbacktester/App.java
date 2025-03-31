package com.amresh.quantbacktester;

import com.amresh.quantbacktester.data.CsvDataReader;
import com.amresh.quantbacktester.engine.SimulationEngine;
import com.amresh.quantbacktester.model.MarketData;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Define the date pattern matching your CSV timestamp format (e.g., "yyyy-MM-dd HH:mm:ss")
        CsvDataReader reader = new CsvDataReader("yyyy-MM-dd HH:mm:ss");
        try {
            // Update the path to your CSV file accordingly. For example, you might store it in src/main/resources/data/
            List<MarketData> marketData = reader.readData("src/main/resources/data/historical_data.csv");
            SimulationEngine engine = new SimulationEngine(marketData);
            engine.runSimulation();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
