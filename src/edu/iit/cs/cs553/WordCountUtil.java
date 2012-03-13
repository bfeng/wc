package edu.iit.cs.cs553;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.Map.Entry;
import java.util.*;

public class WordCountUtil {

    private static final Integer ONE = new Integer(1);

    public static StringTokenizer getWordTokenizer(String line) {
        StringTokenizer st = new StringTokenizer(line, ",!.?;:\" \t\n\r\f");
        return st;
    }

    public static void addWord(Map<String, Integer> mp, String word) {
        Integer obj = mp.get(word);
        if (obj == null) {
            mp.put(word, ONE);
        } else {
            mp.put(word, obj + 1);
        }
    }

    public static void writeMap(Map<String, Integer> mp, String filepath) throws java.io.IOException {
        FileWriter fstream = new FileWriter(filepath);
        BufferedWriter out = new BufferedWriter(fstream);

        SortedMap<String, Integer> sm = new TreeMap<String, Integer>(mp);
        Set<Entry<String, Integer>> entries = sm.entrySet();

        for (Iterator<Entry<String, Integer>> iter = entries.iterator(); iter.hasNext();) {
            Map.Entry<String, Integer> me = iter.next();
            out.write(me.getKey() + " : " + me.getValue() + "\n");
        }
        out.close();
        fstream.close();
    }
}
