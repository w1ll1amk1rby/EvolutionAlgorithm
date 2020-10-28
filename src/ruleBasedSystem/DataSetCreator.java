/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ruleBasedSystem;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author William Kirby
 */
public class DataSetCreator {
    
    public static Data[] getDataSetFromFile(String fileName) {
        
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
        Data[] dataSet = new Data[extractedData.size()];
        for(int x = 0;x < dataSet.length;x++) {
            String line = extractedData.get(x);
            String[] splitLine = line.split(" ");
            int[] input = DataSetCreator.convertString(splitLine[0]);
            int[] output = DataSetCreator.convertString(splitLine[1]);
            dataSet[x] = new Data(input,output);
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
