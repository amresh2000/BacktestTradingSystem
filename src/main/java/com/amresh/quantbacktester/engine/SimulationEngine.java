package com.amresh.quantbacktester.engine;

import com.amresh.quantbacktester.model.MarketData;
import java.util.List;

public class SimulationEngine {
    private List<MarketData> marketDataList;
    private double cashBalance;

    public SimulationEngine(List<MarketData> marketDataList) {
        this.marketDataList = marketDataList;
        this.cashBalance = 100000; // Starting cash balance, adjust as needed
    }

    public void runSimulation() {
        for (MarketData data : marketDataList) {
            // Currently, just output the market data. Later, you will integrate strategies and portfolio updates.
            System.out.println("Processing data at " + data.getTimestamp() + " with close price: " + data.getClose());
        }
        System.out.println("Simulation complete. Final cash balance: " + cashBalance);
    }
}
