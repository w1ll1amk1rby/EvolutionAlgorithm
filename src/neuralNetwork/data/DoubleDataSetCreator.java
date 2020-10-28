/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neuralNetwork.data;

import neuralNetwork.data.DoubleData;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author William Kirby
 */
public class DoubleDataSetCreator {

    public static DoubleData[] getDataSetFromFile(String fileName) {
        // pass the path to the file as a parameter 
        File file = new File("D:\\Documents - HDD\\NetBeansProjects\\EvolutionAlgorithm\\src\\mains\\" + fileName); 
        Scanner sc;
        // try to get a scanner
        try {
            sc = new Scanner(file);
        }
        catch(FileNotFoundException e) {
            return null;
        }
        ArrayList<String> extractedData = new ArrayList<>();
        while (sc.hasNextLine()) {
            extractedData.add(sc.nextLine());
        }
        // create data
        DoubleData[] dataSet = new DoubleData[extractedData.size()];
        for(int x = 0;x < dataSet.length;x++) {
            
            
            String line = extractedData.get(x);
            String[] splitLine = line.split(" ");
            
            
            double[] test = new double[splitLine.length - 1];
            int[] answer = new int[1];
            answer[0] = Integer.parseInt(splitLine[splitLine.length - 1]);
            for(int i = 0;i < test.length;i++) {
                test[i] = Double.parseDouble(splitLine[i]);
            }
            dataSet[x] = new DoubleData(test,answer);
        }
        return dataSet;
    }
    
    private static int[] convertString(String string) {
        int[] converted = new int[string.length()];
        for(int x = 0;x < converted.length;x++) {
            char c = string.charAt(x);
            converted[x] = Character.getNumericValue(c);
        }
        return converted;
    }
}
    

