package edu.iit.cs.cs553;

import java.io.File;
import java.io.FileReader;
import java.io.BufferedReader;
import java.util.Map;
import java.util.HashMap;
import java.util.StringTokenizer;

public class WordCounterThread extends Thread {

  private File inputFile;
  private String output;

  public WordCounterThread(File inputFile, String output) {
    this.inputFile = inputFile;
    this.output = output;
  }

  @Override
  public void run() {
    try {
      Map<String,Integer> mp=new HashMap<String, Integer>();
      FileReader fr = new FileReader(inputFile);
      BufferedReader br = new BufferedReader(fr);
      String line;
      while ((line = br.readLine()) != null) {
        processLine(line, mp);
      }
      br.close();
      fr.close();

      WordCountUtil.writeMap(mp, this.output + "/result_"+this.getId()+".txt");
    } catch (java.io.IOException e) {
      return;
    }
  }

  private void processLine(String line, Map<String, Integer> map) {
    StringTokenizer st = new StringTokenizer(line, "?;\" \t\n\r\f");
    while (st.hasMoreTokens()) {
      WordCountUtil.addWord(map, st.nextToken());
    }
  }
}
