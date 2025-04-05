package com.amresh.quantbacktester.strategy;

import com.amresh.quantbacktester.model.MarketData;
import com.amresh.quantbacktester.model.Portfolio;

public interface TradingStrategy {
    /**
     * Process new market data and update the portfolio.
     *
     * @param data      New market data.
     * @param portfolio The current portfolio state.
     */
    void onData(MarketData data, Portfolio portfolio);

    /**
     * Return the name of the strategy.
     */
    String getName();
}
