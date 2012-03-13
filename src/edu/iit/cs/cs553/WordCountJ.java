package edu.iit.cs.cs553;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

public class WordCountJ {

    private static void print_usuage() {
        System.out.println("Usuage: <javaClass> <input> <output>");
    }

    private static void mergeWord(Map<String, Integer> result, String word, Integer times) {
        Integer t = result.get(word);
        if (t != null) {
            t += times;
        } else {
            t = times;
        }
        result.put(word, t);
    }

    private static void mergeResult(Map<String, Integer> map, File input) throws IOException {
        FileReader fr = new FileReader(input);
        BufferedReader br = new BufferedReader(fr);

        String line;
        while ((line = br.readLine()) != null) {
            String[] result = line.split(" : ", 2);
            String word = result[0];
            Integer times;
            try {
                times = new Integer(result[1]);
            } catch (NumberFormatException e) {
                times = new Integer(0);
            }
            mergeWord(map, word, times);
        }

        br.close();
        fr.close();
    }

    public static void main(String[] args) throws IOException {
        if (args.length != 2) {
            print_usuage();
            return;
        }

        File input = new File(args[0]);
        File output = new File(args[1]);

        if (!input.exists() || !input.isDirectory()) {
            print_usuage();
            return;
        }
        if (!output.exists()) {
            output.mkdir();
        }

        File[] inputFiles = input.listFiles();
        Thread[] workers = new Thread[inputFiles.length];

        for (int i = 0; i < inputFiles.length; i++) {
            workers[i] = new WordCounterThread(inputFiles[i], output.getPath());
            workers[i].start();
        }

        for (int i = 0; i < inputFiles.length; i++) {
            try {
                workers[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(WordCountJ.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        File[] outputFiles = output.listFiles();
        Map<String, Integer> result = new HashMap<String, Integer>();
        for (int i = 0; i < outputFiles.length; i++) {
            // read the result of each file & merge them here
            mergeResult(result, outputFiles[i]);
        }

        WordCountUtil.writeMap(result, "_result_trimmed.txt");
    }
}
