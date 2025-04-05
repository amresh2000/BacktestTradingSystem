package com.amresh.quantbacktester;

import com.amresh.quantbacktester.data.CsvDataReader;
import com.amresh.quantbacktester.engine.SimulationEngine;
import com.amresh.quantbacktester.model.MarketData;
import com.amresh.quantbacktester.model.Portfolio;
import com.amresh.quantbacktester.risk.RiskManager;
import com.amresh.quantbacktester.strategy.MomentumStrategy;
import com.amresh.quantbacktester.strategy.TradingStrategy;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        // Define the date format that matches your CSV (adjust if necessary)
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        CsvDataReader reader = new CsvDataReader(datePattern);
        // Path to your CSV file (ensure this file exists and is correctly formatted)
        String filePath = "src/main/resources/data/historical_data.csv";

        try {
            List<MarketData> marketData = reader.readData(filePath);
            // Create a portfolio with initial cash
            Portfolio portfolio = new Portfolio(100000.0);
            // Create a risk manager with a simple risk rule (e.g., max trade price)
            RiskManager riskManager = new RiskManager(150.0); // Example: block trades if price > 150
            // Create a list of trading strategies
            List<TradingStrategy> strategies = Arrays.asList(new MomentumStrategy());
            // Initialize and run the simulation engine
            SimulationEngine engine = new SimulationEngine(marketData, portfolio, riskManager, strategies);
            engine.runSimulation();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
