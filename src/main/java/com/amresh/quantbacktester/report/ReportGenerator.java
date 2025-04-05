package com.amresh.quantbacktester.report;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ReportGenerator {

    /**
     * Exports portfolio values over time to a CSV file.
     *
     * @param portfolioValues List of portfolio values.
     * @param outputFilePath  Output file path.
     * @throws IOException If an I/O error occurs.
     */
    public static void exportPortfolioValues(List<Double> portfolioValues, String outputFilePath) throws IOException {
        try (FileWriter writer = new FileWriter(outputFilePath)) {
            writer.write("TimeStep,PortfolioValue\n");
            for (int i = 0; i < portfolioValues.size(); i++) {
                writer.write(i + "," + portfolioValues.get(i) + "\n");
            }
        }
    }
}
