package com.amresh.quantbacktester.model;

import java.util.ArrayList;
import java.util.List;

public class Portfolio {
    private double cash;
    private List<String> tradeHistory;

    public Portfolio(double initialCash) {
        this.cash = initialCash;
        this.tradeHistory = new ArrayList<>();
    }

    public double getCash() {
        return cash;
    }

    /**
     * Checks if there is enough cash to buy one unit of asset at the given price.
     * This is a simplified check.
     */
    public boolean canBuy(MarketData data) {
        return cash >= data.getClose();
    }

    /**
     * Adds a trade to the portfolio.
     * For simplicity, a "BUY" trade subtracts the price and "SELL" adds the price.
     */
    public void addTrade(String tradeType, MarketData data) {
        double price = data.getClose();
        if ("BUY".equalsIgnoreCase(tradeType)) {
            cash -= price;
        } else if ("SELL".equalsIgnoreCase(tradeType)) {
            cash += price;
        }
        tradeHistory.add(tradeType + " at " + data.getTimestamp() + " @ " + price);
    }

    public List<String> getTradeHistory() {
        return tradeHistory;
    }

    @Override
    public String toString() {
        return "Portfolio{" +
                "cash=" + cash +
                ", tradeHistory=" + tradeHistory +
                '}';
    }
}
