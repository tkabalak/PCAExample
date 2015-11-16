package com.si.project01;

import java.util.Arrays;

public class MatrixUtils {
    
    /**
     * Method is prepared to calculate covariance matrix, shouldn't be used to normal multiplication
     * 
     * @param simpleMatrix - multiplicands matrix
     * @param transposeMatrix - multipliers matrix
     * @return new matrix object
     */
    public static Matrix multiplyMatrix(Matrix simpleMatrix, Matrix transposeMatrix) {
        double[][] result = new double[transposeMatrix.hight()][transposeMatrix.hight()];
        //transpose
        for (int i = 0; i < result.length; i++) {
            //normal
            for (int j = 0; j < result.length; j++) {
                result[i][j] = multiplyArrays(transposeMatrix.getRow(i), simpleMatrix.getColumn(j)) / simpleMatrix.hight();
            }
        }
        return new Matrix(result);
    }

    private static double multiplyArrays(double[] a, double[] b) {
        double result = 0;
        for (int i = 0; i < a.length; i++) {
            result += (a[i] * b[i]);
        }
        return result;
    }

    public static Matrix getTransposeMatrix(Matrix matrixToTranspose) {
        double[][] result = new double[matrixToTranspose.width()][matrixToTranspose.hight()];
        for (int i = 0; i < result.length; i++) {
            for (int j = 0; j < result[0].length; j++) {
                result[i][j] = matrixToTranspose.getElement(j, i);
            }
        }
        return new Matrix(result);
    }

    public static double[] getMeanMatrix(Matrix matrix) {
        return Arrays.stream(matrix.getMatrix())
                .map(x -> (Arrays.stream(x).sum() / x.length))
                .mapToDouble(Double::new)
                .toArray();
    }

    public static Matrix getDeviationsMatrix(Matrix matrix, double[] means) {
        double[][] result = new double[matrix.hight()][matrix.width()];
        for (int i = 0; i < matrix.hight(); i++) {
            for (int j = 0; j < matrix.width(); j++) {
                result[i][j] = matrix.getElement(i, j) - means[i];
            }
        }
        return new Matrix(result);
    }

    /**
     * Round matrix elements to specified decimal places
     * @param matrix - matrix to beautify
     * @param places - decimal places to the rigth
     * @return 
     */
    public static Matrix beautify(Matrix matrix, int places) {
        Matrix result = new Matrix(new double[matrix.hight()][matrix.width()]);
        for (int i = 0; i < matrix.hight(); i++) {
            for (int j = 0; j < matrix.width(); j++) {
                result.put(i, j, round(matrix.getElement(i, j), places));
            }
        }
        return result;
    }

    public static double round(double value, int places) {
        if (places < 0) {
            throw new IllegalArgumentException();
        }

        long factor = (long) Math.pow(10, places);
        value = value * factor;
        long tmp = Math.round(value);
        return (double) tmp / factor;
    }

}
