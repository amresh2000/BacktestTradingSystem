package com.amresh.quantbacktester.engine;

import com.amresh.quantbacktester.model.MarketData;
import com.amresh.quantbacktester.model.Portfolio;
import com.amresh.quantbacktester.risk.RiskManager;
import com.amresh.quantbacktester.strategy.TradingStrategy;
import com.amresh.quantbacktester.metrics.PerformanceMetrics;
import java.util.ArrayList;
import java.util.List;

public class SimulationEngine {
    private List<MarketData> marketDataList;
    private Portfolio portfolio;
    private RiskManager riskManager;
    private List<TradingStrategy> strategies;

    // To track portfolio performance over time
    private List<Double> portfolioValues;

    public SimulationEngine(List<MarketData> marketDataList, Portfolio portfolio,
                            RiskManager riskManager, List<TradingStrategy> strategies) {
        this.marketDataList = marketDataList;
        this.portfolio = portfolio;
        this.riskManager = riskManager;
        this.strategies = strategies;
        this.portfolioValues = new ArrayList<>();
        // Record initial portfolio value
        this.portfolioValues.add(portfolio.getCash());
    }

    public void runSimulation() {
        for (MarketData data : marketDataList) {
            System.out.println("Processing data at " + data.getTimestamp() +
                    " | Close Price: " + data.getClose());
            for (TradingStrategy strategy : strategies) {
                // Let the strategy process data (you may add risk checks inside strategies)
                strategy.onData(data, portfolio);
            }
            // Record the portfolio value after processing the current data point
            portfolioValues.add(portfolio.getCash());
        }
        System.out.println("Simulation complete. Final portfolio state:");
        System.out.println(portfolio);
        System.out.println("Performance Metrics:");
        // Calculate and print metrics
        printPerformanceMetrics();
    }

    private void printPerformanceMetrics() {
        double initialValue = portfolioValues.get(0);
        double finalValue = portfolioValues.get(portfolioValues.size() - 1);
        double cumulativeReturn = (finalValue - initialValue) / initialValue;
        double maxDrawdown = PerformanceMetrics.calculateMaxDrawdown(portfolioValues);
        double sharpeRatio = PerformanceMetrics.calculateSharpeRatio(portfolioValues, 0.0); // assume risk-free rate is 0

        System.out.printf("Cumulative Return: %.2f%%%n", cumulativeReturn * 100);
        System.out.printf("Maximum Drawdown: %.2f%%%n", maxDrawdown * 100);
        System.out.printf("Sharpe Ratio: %.2f%n", sharpeRatio);
    }

    // Optionally, provide a getter for portfolioValues for further use
    public List<Double> getPortfolioValues() {
        return portfolioValues;
    }
}
