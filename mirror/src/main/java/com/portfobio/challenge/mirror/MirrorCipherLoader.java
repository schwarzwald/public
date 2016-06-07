package com.portfobio.challenge.mirror;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class MirrorCipherLoader {
  
  public static MirrorCipher loadFromFile(String filename) throws IOException {
    BufferedReader reader = Files.newBufferedReader(Paths.get(filename));
    char[] alphabet = reader.readLine().toCharArray();
    List<Byte> key = new ArrayList<>();
    String line = reader.readLine();
    int gridHeight = 0;
    int gridWidth = line.length();
    
    while (line != null) {
      for (char c: line.toCharArray()) {
        key.add(getByteForChar(c));
      }
      
      gridHeight++;
      
      line = reader.readLine();
    }
    
    MirrorCipher cipher = new MirrorCipher(gridWidth, gridHeight);
    cipher.setAlphabet(alphabet);
    cipher.setKey(toByteArray(key));
    return cipher;
  }
  
  private static byte getByteForChar(char c) {
    switch (c) {
      case '\\': return 1;
      case '/': return -1;
      default: return 0;
    }
  }
  
  private static byte[] toByteArray(List<Byte> byteList) {
    byte[] array = new byte[byteList.size()];
    for (int i=0; i<array.length; i++) {
      array[i] = byteList.get(i);
    }
    
    return array;
  }
  
}
