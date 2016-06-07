package com.portfobio.challenge.mirror;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.event.CaretListener;
import javax.swing.event.CaretEvent;

public class MirrorCipherInterface extends JFrame {

  private static final long serialVersionUID = 1L;

  /**
   * Launch the application.
   */
  public static void launch(MirrorCipher cipher) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          MirrorCipherInterface frame = new MirrorCipherInterface(cipher);
          frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the frame.
   */
  public MirrorCipherInterface(MirrorCipher cipher) {
    setTitle("Mirror Cipher");
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    setBounds(100, 100, 450, 219);
    JPanel contentPane = new JPanel();
    contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
    setContentPane(contentPane);
    contentPane.setLayout(null);
    
    JLabel lblInput = new JLabel("Input");
    lblInput.setBounds(10, 11, 46, 14);
    contentPane.add(lblInput);
    
    JLabel lblOutput = new JLabel("Output");
    lblOutput.setBounds(10, 92, 46, 14);
    contentPane.add(lblOutput);
    
    JTextArea input = new JTextArea();
    input.setBounds(5, 32, 419, 49);
    contentPane.add(input);
    
    JTextArea output = new JTextArea();
    output.setEditable(false);
    output.setBounds(5, 117, 419, 49);
    contentPane.add(output);
    
    input.addCaretListener(new CaretListener() {
      public void caretUpdate(CaretEvent e) {
       output.setText(cipher.cipherOutput(input.getText()));
      }
    });
  }
}
