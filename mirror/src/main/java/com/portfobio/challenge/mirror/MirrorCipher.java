package com.portfobio.challenge.mirror;

public class MirrorCipher {
  
  private MirrorGrid grid;
  private char[] alphabet;
  
  public MirrorCipher(int width, int height) {
    grid = new MirrorGrid(width, height);
  }
  
  public void setKey(byte[] key) {
    grid.setMirrors(key);
  }
  
  public void setAlphabet(char[] alphabet) {
    this.alphabet = alphabet;
  }
  
  public String cipherOutput(String input) {
   return getGridOutput(input);
  }
  
  public char cipherOutput(char input) {
    return getGridOutput(input);
  }
  
  private String getGridOutput(String input) {
    char[] output = new char[input.length()];
    int i = 0;
    for (char c: input.toCharArray()) {
      output[i] = getGridOutput(c);
      i++;
    }
    
    return new String(output);
  }
  
  private char getGridOutput(char input) {
    if (input == ' ') {
      return ' ';
    }
    
    int encryptedOrdinal = grid.getOutput(getCharOrdinal(input));
    return alphabet[encryptedOrdinal];
  }
  
  private int getCharOrdinal(char input) {
    for (int i=0; i<alphabet.length; i++) {
      if (alphabet[i] == input) {
        return i;
      }
    }
     
    throw new IllegalArgumentException(input+" is not in the alphabet");
  }
}
