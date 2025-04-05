package com.amresh.quantbacktester;

import com.amresh.quantbacktester.data.CsvDataReader;
import com.amresh.quantbacktester.engine.SimulationEngine;
import com.amresh.quantbacktester.metrics.PerformanceMetrics;
import com.amresh.quantbacktester.model.MarketData;
import com.amresh.quantbacktester.model.Portfolio;
import com.amresh.quantbacktester.risk.RiskManager;
import com.amresh.quantbacktester.report.ReportGenerator;
import com.amresh.quantbacktester.strategy.MomentumStrategy;
import com.amresh.quantbacktester.strategy.TradingStrategy;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class App {
    public static void main(String[] args) {
        String datePattern = "yyyy-MM-dd HH:mm:ss";
        CsvDataReader reader = new CsvDataReader(datePattern);
        String filePath = "src/main/resources/data/historical_data.csv";

        try {
            List<MarketData> marketData = reader.readData(filePath);
            Portfolio portfolio = new Portfolio(100000.0);
            RiskManager riskManager = new RiskManager(150.0); // Example risk limit
            List<TradingStrategy> strategies = Arrays.asList(new MomentumStrategy());
            SimulationEngine engine = new SimulationEngine(marketData, portfolio, riskManager, strategies);
            engine.runSimulation();

            // Optionally export portfolio values to CSV for visualization or further analysis
            ReportGenerator.exportPortfolioValues(engine.getPortfolioValues(), "portfolio_performance.csv");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
