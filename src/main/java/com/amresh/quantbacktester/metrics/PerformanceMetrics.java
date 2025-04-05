package com.amresh.quantbacktester.metrics;

import java.util.List;

public class PerformanceMetrics {

    /**
     * Calculates the maximum drawdown given a list of portfolio values.
     *
     * @param portfolioValues List of portfolio values over time.
     * @return Maximum drawdown as a decimal (e.g., 0.15 for 15%).
     */
    public static double calculateMaxDrawdown(List<Double> portfolioValues) {
        double peak = portfolioValues.get(0);
        double maxDrawdown = 0.0;
        for (double value : portfolioValues) {
            if (value > peak) {
                peak = value;
            }
            double drawdown = (peak - value) / peak;
            if (drawdown > maxDrawdown) {
                maxDrawdown = drawdown;
            }
        }
        return maxDrawdown;
    }

    /**
     * Calculates the Sharpe ratio based on portfolio returns.
     * This simple implementation assumes equally spaced time intervals.
     *
     * @param portfolioValues List of portfolio values over time.
     * @param riskFreeRate    The risk-free rate (annualized, as a decimal). Use 0 if unknown.
     * @return The Sharpe ratio.
     */
    public static double calculateSharpeRatio(List<Double> portfolioValues, double riskFreeRate) {
        // First, compute periodic returns
        int n = portfolioValues.size();
        if (n < 2) return 0.0;

        double[] returns = new double[n - 1];
        for (int i = 1; i < n; i++) {
            returns[i - 1] = (portfolioValues.get(i) - portfolioValues.get(i - 1)) / portfolioValues.get(i - 1);
        }

        // Calculate average return and standard deviation of returns
        double avgReturn = 0.0;
        for (double r : returns) {
            avgReturn += r;
        }
        avgReturn /= returns.length;

        double sumSquaredDiffs = 0.0;
        for (double r : returns) {
            sumSquaredDiffs += Math.pow(r - avgReturn, 2);
        }
        double stdDev = Math.sqrt(sumSquaredDiffs / returns.length);

        // Avoid division by zero
        if (stdDev == 0) return 0.0;

        // Compute Sharpe Ratio (here we assume returns are periodic returns; adjust scaling if needed)
        return (avgReturn - riskFreeRate) / stdDev;
    }
}
