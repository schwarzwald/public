package com.portfobio.challenge.fingers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Set;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class FingersTest  {
  
  private static Fingers testee;
  
  @BeforeClass
  public static void createTestee() throws IOException {
    String dictionary = new String(Files.readAllBytes(Paths.get("enable1.txt")));    
    testee = new Fingers(dictionary);
  }
  
  @Test
  public void testFirstAndLastLettersMatchInput() {
    String input = "qwertyuytresdftyuioknn";
    Set<String> words = testee.searchForWords(input, 5);
    for (String word : words) {
      Assert.assertEquals("q", String.valueOf(word.charAt(0)));
      Assert.assertEquals("n", String.valueOf(word.charAt(word.length()-1)));
    }
  }
  
  @Test
  public void testDesiredWordLength() {
    String input = "qwertyuytresdftyuioknn";
    int length = 5;
    Set<String> words = testee.searchForWords(input, length);
    for (String word : words) {
      Assert.assertTrue(word.length() >= length);
    }
  }
  
  @Test
  public void testWordsConsistOfInputLetters() {
    String input = "qwertyuytresdftyuioknn";
    Set<String> words = testee.searchForWords(input, 5);

    for (String word : words) {
      for (int i=0; i<word.length(); i++) {
        Assert.assertTrue(input.contains(String.valueOf(word.charAt(i))));
      }      
    }
  }
}
