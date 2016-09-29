package com.portfobio.challenge.fingers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Fingers {

  private final String dictionary;
  
  public Fingers(String dictionary) {
    this.dictionary = dictionary;
  }
  
  public Set<String> searchForWords(String input, int minLength) {
    Pattern pattern = buildPattern(input);
    return matchPatternInDictionary(pattern, input, minLength);
  }
  
  private Set<String> matchPatternInDictionary(Pattern pattern, String input, int minLength) {
    Set<String> result = new HashSet<String>();
    Matcher matcher = pattern.matcher(dictionary);
    
    while (matcher.find()) {
      String match = matcher.group();
      if (match.length() >= minLength) {
        result.add(match);
      }
    }
    
    return result;
  }
  
  private Pattern buildPattern(String input) {
    StringBuilder patternBuilder = new StringBuilder();
    patternBuilder
      .append("(\\b")
      .append(input.charAt(0));
    
    for (int i=1; i<input.length()-1; i++) {
      patternBuilder
        .append(input.charAt(i))
        .append("*");
    }
    
    patternBuilder
      .append(input.charAt(input.length()-1))
      .append("\\b)");
    
    return Pattern.compile(patternBuilder.toString());
  }
  
  public static void main(String[] args) throws IOException {  
    String dictionary = new String(Files.readAllBytes(Paths.get("enable1.txt")));
    String input = args[0];
    Integer length = Integer.valueOf(args[1]);
    
    Fingers fingers = new Fingers(dictionary);
    Set<String> words = fingers.searchForWords(input, length);
    
    System.out.println(words);
  }
}
