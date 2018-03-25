import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * A graphical binary to decimal converter.
 * <p>
 * The UI has two text fields. One to receive the user's input binary string
 * and one to display the decimal string or an error message if an exception
 * occurs.
 * 
 * @author Anton G Neuhold Jr
 * @version 1.0, Date: 03/20/2018
 */
public class Assignment2 extends JFrame {
  
  /** 
   * The {@link JTextField} instance representing the input from the user for
   * the binary conversion
   */
  private JTextField textFieldBinaryString;
  
  /** 
   * The {@link JTextField} instance representing the output of the binary
   * conversion 
   */
  private JTextField textFieldDecimalString;
  
  /** 
   * The {@link JLabel} instance representing the label for the 
   * {@link #textFieldDecimalString}.
   */
  private JLabel labelBinaryString;
  
  /** 
   * The {@link JLabel} instance representing the label for the 
   * {@link #textFieldDecimalString}
   */
  private JLabel labelDecimalString;
  
  /** 
   * The {@link JPanel} instance representing the container for the top of the
   * UI.
   */
  private JPanel panelTop;
  
  /** 
   * The {@link JButton} instance representing the trigger to convert the 
   * binary value to a decimal value.
   */
  private JButton buttonConvert;
  
  /** 
   * The default frame width for the UI.
   */
  private final int FRAME_WIDTH = 500;
  
  /** 
   * The default frame height for the UI.
   */
  private final int FRAME_HEIGHT = 150;
  
  /** 
   * The <code>String</code> instance representing the window title for 
   * {@link Assignment2}.
   */
  private final String FRAME_TITLE = "Convert Binary to Decimal";

  /**
   * Creates a new instance of {@link Assignment2}.
   * 
   * @param args the <code>String[]</code> command line arguments. These 
   * are not used. 
   */
  public static void main(String[] args) {
    new Assignment2();
  }
  
  /**
   * Builds the UI for the this application and sets it to visible.
   */
  public Assignment2() {
    setDefaultLookAndFeelDecorated(true);
    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setSize(FRAME_WIDTH, FRAME_HEIGHT);
    setTitle(FRAME_TITLE);
    
    buildPanel();
    add(panelTop, BorderLayout.NORTH);
    buildButton();
    add(buttonConvert, BorderLayout.SOUTH);
    
    setVisible(true);
  }

  /**
   * Builds the {@link #panelTop} and set's its properties then builds and adds 
   * it's sub components.
   */
  private void buildPanel() {
    int gridRows = 2;
    int gridCols = 2;
    int hGap = 2;
    int vGap = 2;
    panelTop = new JPanel(new GridLayout(gridRows, gridCols, hGap, vGap));
    
    buildTextFields();
    buildLabels();
    panelTop.add(labelBinaryString);
    panelTop.add(textFieldBinaryString);
    panelTop.add(labelDecimalString);
    panelTop.add(textFieldDecimalString);
  }
  
  /**
   * Builds the text fields and sets their properties.
   */
  private void buildTextFields() {
    textFieldBinaryString = new JTextField();
    textFieldBinaryString.setEditable(true);
    
    textFieldDecimalString = new JTextField();
    textFieldDecimalString.setEditable(false);
  }
  
  /**
   * Builds the labels and sets their properties.
   */
  private void buildLabels() {
    labelBinaryString = new JLabel("Binary String");
    
    labelDecimalString = new JLabel("Decimal String");
  }

  /**
   * Builds {@link #buttonConvert} and sets it's properties.
   */
  private void buildButton() {
    buttonConvert = new JButton("Convert To Decimal");
    buttonConvert.addActionListener(new ButtonListener());
  }

  /**
   * Attempts to convert the provided binary value <code>String</code>
   * to an <code>int</code> and throws an exception if it is in the incorrect
   * format. If an error is thrown, the exception message will contain details
   * on what caused the error in the string. 
   * 
   * @param binaryString the <code>String</code> containing the supposed binary
   * value
   * @return An <code>int</code> representing the decimal value of the 
   * binary string
   * @throws NumberFormatException If the string is not a binary string
   */
  private int parseBinary(String binaryString) throws NumberFormatException {
    binaryString = binaryString.trim();
    int decimalValue;
    
    // Throws an exception if the string is incorrect and describes why.
    if (binaryString.equals("")) {
      decimalValue = 0;
    } else if (!binaryString.matches("([10])+")) {
      HashSet<Character> invalidChars = new HashSet<Character>();
      for (int i = 0; i < binaryString.length(); i++) {
        if (binaryString.charAt(i) != '0' &&
            binaryString.charAt(i) != '1') {
          invalidChars.add(binaryString.charAt(i));
        }
      }
      String errorString = "Invalid format for a Binary String - "
          + "Illegal Character(s): ";
      for (char c : invalidChars) {
        errorString += c;
      }
      throw new NumberFormatException(errorString);
    } else {
      decimalValue = Integer.parseInt(binaryString, 2);
    }
    return decimalValue;
  }
  
  /**
   * An {@link ActionListener} that attempts to convert the binary string into
   * a decimal string and reports the output to the 
   * {@link Assignment2#textFieldDecimalString}.
   */
  private class ButtonListener implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
        
      // Attempts to convert the value
      try {
        int decimalValue = parseBinary(textFieldBinaryString.getText());
        textFieldDecimalString.setText("" + decimalValue);
      } catch (NumberFormatException exception) {
        textFieldDecimalString.setText(exception.getMessage());
      }
    }
    
  }

}
