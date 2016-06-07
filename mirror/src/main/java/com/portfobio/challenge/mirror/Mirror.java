package com.portfobio.challenge.mirror;

import java.io.IOException;

public class Mirror {

  public static void main(String[] args) throws IOException {
    MirrorCipher cipher= MirrorCipherLoader.loadFromFile("mirror.conf");
    MirrorCipherInterface.launch(cipher);
  }
  
}
