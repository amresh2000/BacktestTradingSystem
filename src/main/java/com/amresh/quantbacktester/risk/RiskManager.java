package com.amresh.quantbacktester.risk;

import com.amresh.quantbacktester.model.MarketData;
import com.amresh.quantbacktester.model.Portfolio;

public class RiskManager {
    private double maxPositionSize; // Example: maximum allowed trade amount

    public RiskManager(double maxPositionSize) {
        this.maxPositionSize = maxPositionSize;
    }

    /**
     * Checks if a trade is allowed based on risk parameters.
     * (For now, this is a simple check.)
     *
     * @param tradeType The type of trade ("BUY" or "SELL").
     * @param data      The current market data.
     * @param portfolio The current portfolio.
     * @return True if trade is allowed, false otherwise.
     */
    public boolean isTradeAllowed(String tradeType, MarketData data, Portfolio portfolio) {
        // Example: if trade is BUY and the price is higher than max allowed, block it.
        if ("BUY".equalsIgnoreCase(tradeType) && data.getClose() > maxPositionSize) {
            System.out.println("RiskManager: Trade blocked due to price exceeding max position size.");
            return false;
        }
        return true;
    }
}
