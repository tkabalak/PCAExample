package com.si.project01;

import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Main {
//    http://httprover2.blogspot.com/2011/01/covariance-matrix-example.html

    public static void main(String[] args) {
        Matrix covariance = PCA.getCovarianceMatrix();
        System.out.println("COVARIANCE MATRIX");
        boolean flag = saveToFile(covariance);
        System.out.println("flag = " + flag);
        System.out.println(MatrixUtils.beautify(covariance, 2));
    }
    public static boolean saveToFile(Matrix matrix){
        try {
            Path path = Paths.get(System.getProperty("user.dir") + "\\result.txt");
            if(Files.notExists(path))
                path = Files.createFile(path);
            try (BufferedWriter writer = Files.newBufferedWriter(path)) {
                for (int i = 0; i < matrix.hight(); i++) {
                    writer.write(Arrays.toString(matrix.getColumn(i)));
                    writer.newLine();
                }
                writer.flush();
            }
            return true;
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
            return false;
        }
    }
}
