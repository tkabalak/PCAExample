package com.si.project01;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class PCA {

    public static Matrix getCovarianceMatrix() {
        Path path = Paths.get("data.txt");

        double[][] arr = readFileAndParseResults(path); 
        Matrix matrix = new Matrix(arr);
        
        double[] means = MatrixUtils.getMeanMatrix(matrix);
        
        matrix = MatrixUtils.getDeviationsMatrix(matrix, means);
        Matrix transposeMatrix = MatrixUtils.getTransposeMatrix(matrix);
        Matrix result = MatrixUtils.multiplyMatrix(matrix, transposeMatrix);
        
        return result;
    }

    public static double[][] readFileAndParseResults(Path path) {
        try {
            return Files.lines(path)
                    .skip(1)
                    .map(e -> Stream.of(e.split(";"))
                            .skip(2)
                            .mapToDouble(x -> PCA.toDouble(x))
                            .toArray())
                    .toArray(double[][]::new);
        } catch (IOException ex) {
            throw new RuntimeException(ex);
        }
//                    .limit(maxSize)
    }

    private static double toDouble(String numberValue) {
        double parseVal;
        try {
            parseVal = Double.parseDouble(numberValue);
        } catch (NumberFormatException ex) {
            parseVal = getSignalType(numberValue);
        }
        return parseVal;
    }

    private static double getSignalType(String signal) {
        switch (signal.toUpperCase()) {
            case "SELL":
                return -1;
            case "WAIT":
                return 0;
            case "BUY":
                return 1;
            default:
                throw new IllegalArgumentException();
        }
    }
}
