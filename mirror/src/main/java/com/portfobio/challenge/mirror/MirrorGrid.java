package com.portfobio.challenge.mirror;

public class MirrorGrid {
  private int width;
  private int height;
  
  private byte[][] cells;
  private int[] substitution;
  
  public MirrorGrid(int width, int height) {
    this.width= width;
    this.height = height;
    
    substitution = new int[2*(width+height)];
    cells = new byte[height][width];
    
    buildSubstitution();
  }
  // mirror \ =  1
  // mirror / = -1
  public void setMirrors(byte[] mirrors) {
    for (int i=0; i<mirrors.length; i++) {
      int row = i / width;
      int col = i % width;
      
      setCell(col, row, mirrors[i]);
    }
    
    buildSubstitution();
  }
  
  private void setCell(int x, int y, byte type) {
    cells[y][x] = type;
  }
  
  private int[] buildSubstitution() {
    for (int i=0; i<height; i++) {
      substitution[i] = findPath(0, i, 1, 0);
      substitution[i+height] = findPath(width-1, i, -1, 0);
    }
    
    for (int i=0; i<width; i++) {
      substitution[2*height+i] = findPath(i, 0, 0, 1);
      substitution[2*height+i+width] = findPath(i, height-1, 0, -1);
    }
    
    return substitution;
  }
  
  private int findPath(int x, int y, int dx, int dy) {
    while (true) {
      if (x  == width) {
        return height+y;
      }
      
      if (x == -1) {
        return y;
      }
      
      if (y == height) {
        return 2*height + width + x;
      }
      
      if (y == -1) {
        return 2*height + x;
      }

      int value = cells[y][x];
      if (value != 0) {
        int tmp = 0;
        tmp=dy*value; 
        dy = dx*value; 
        dx = tmp;
      }
      
      x+=dx;
      y+=dy;
    }
  }
  
  public int getOutput(int input) {
    return substitution[input];
  }
}
