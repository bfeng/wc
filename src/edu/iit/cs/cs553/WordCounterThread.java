package edu.iit.cs.cs553;

import java.io.File;

public class WordCounterThread extends Thread {

  private File inputFile;

  public WordCounterThread(File inputFile) {
    this.inputFile = inputFile;
  }

  @Override
  public void run() {
    // this is the actual code of counting words
    System.out.println("I'm going to process this file: "+this.inputFile.getName());

    try {
      Thread.sleep(3*1000);
    } catch (InterruptedException e) {
      System.err.println("I'm getting killed");
      return;
    }

    System.out.println("I'm done");
  }
}
