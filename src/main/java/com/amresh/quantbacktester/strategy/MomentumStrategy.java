package com.amresh.quantbacktester.strategy;

import com.amresh.quantbacktester.model.MarketData;
import com.amresh.quantbacktester.model.Portfolio;

public class MomentumStrategy implements TradingStrategy {

    @Override
    public void onData(MarketData data, Portfolio portfolio) {
        // A simple example: if the close price is higher than the open, generate a "BUY" signal; otherwise, "SELL".
        if (data.getClose() > data.getOpen()) {
            if (portfolio.canBuy(data)) { // Ask portfolio if buying is allowed based on cash available.
                System.out.println("MomentumStrategy: BUY signal at " + data.getTimestamp());
                portfolio.addTrade("BUY", data);
            }
        } else {
            System.out.println("MomentumStrategy: SELL signal at " + data.getTimestamp());
            portfolio.addTrade("SELL", data);
        }
    }

    @Override
    public String getName() {
        return "Momentum Strategy";
    }
}
