package com.amresh.quantbacktester.engine;

import com.amresh.quantbacktester.model.MarketData;
import com.amresh.quantbacktester.model.Portfolio;
import com.amresh.quantbacktester.risk.RiskManager;
import com.amresh.quantbacktester.strategy.TradingStrategy;
import java.util.List;

public class SimulationEngine {
    private List<MarketData> marketDataList;
    private Portfolio portfolio;
    private RiskManager riskManager;
    private List<TradingStrategy> strategies;

    // Constructor accepts market data, a portfolio, a risk manager, and a list of strategies
    public SimulationEngine(List<MarketData> marketDataList, Portfolio portfolio,
                            RiskManager riskManager, List<TradingStrategy> strategies) {
        this.marketDataList = marketDataList;
        this.portfolio = portfolio;
        this.riskManager = riskManager;
        this.strategies = strategies;
    }

    public void runSimulation() {
        for (MarketData data : marketDataList) {
            System.out.println("Processing data at " + data.getTimestamp() +
                    " | Close Price: " + data.getClose());
            for (TradingStrategy strategy : strategies) {
                // Before executing the strategy's signal, check risk conditions.
                // (For now, we assume strategies themselves call portfolio.addTrade.
                // In a more advanced version, you might have the strategy return a trade signal,
                // then the engine consults the RiskManager before executing the trade.)
                strategy.onData(data, portfolio);
            }
        }
        System.out.println("Simulation complete. Final portfolio state: ");
        System.out.println(portfolio);
    }
}
