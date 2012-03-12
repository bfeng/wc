package edu.iit.cs.cs553;

import java.util.Map;
import java.io.FileWriter;
import java.io.BufferedWriter;

public class WordCountUtil {

  private static final Integer ONE = new Integer(1);

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
    for(String key : mp.keySet()) {
      out.write(key + " : " + mp.get(key)+"\n");
    }
    out.close();
    fstream.close();
  }
}
