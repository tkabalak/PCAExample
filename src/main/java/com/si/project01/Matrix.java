package com.si.project01;

import java.util.Arrays;

public class Matrix {

    private final double matrix[][];

    public Matrix(double[][] matrix) {
        this.matrix = matrix;
    }

    public double[] getColumn(int column) {
        return Arrays.stream(matrix)
                .map(e -> Arrays.stream(e)
                        .limit(column + 1)
                        .skip(column)
                        .sum())
                .mapToDouble(Double::new)
                .toArray();
    }

    public double[] getRow(int row) {
        double[] result = new double[matrix.length];
        return Arrays.stream(matrix)
                .skip(row)
                .limit(row + 1)
                .findFirst()
                .get();
    }
    public double getElement(int x, int y){
        return matrix[x][y];
    }
    public double[][] getMatrix() {
        return matrix;
    }
    
    public int getDementions(){
        if (this.hight() == this.width()) {
            return this.hight();
        }
        return 0;
    }
    public void put(int x, int y, double value){
        matrix[x][y] = value;
    }
    public int width() {
        if (matrix != null && matrix.length > 1) {
            return matrix[0].length;
        } else {
            return 0;
        }
    }
    public int hight() {
        if (matrix != null) {
            return matrix.length;
        } else {
            return 0;
        }
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder("MATRIX = {\n");

        Arrays.stream(matrix)
                .map(x -> Arrays.toString(x) + '\n')
                .forEach(x -> builder.append(x));
        
        return builder.append('\n').append('}').toString();
    }
}
