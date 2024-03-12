import javax.swing.*; //Imports the Swing package for Java applications, to create windows, buttons, and text fields
import java.awt.*; //AWT (Abstract Window Toolkit) - GUI toolkit. 
import java.awt.event.*; //User actions, such as mouse clicks or keyboard inputs. ActionListener, MouseListener, KeyListener, WindowListener

public class CalculatorGUI extends JFrame {

    // Instance variables
    // JTextField - single-line text input field
    // JComboBox<String> - drop down
    private JTextField nameField;
    private JTextField num1Field;
    private JTextField num2Field;
    private JComboBox<String> operatorComboBox;

    // Constructor
    public CalculatorGUI() {
        setTitle("Calculator");
        setSize(300, 200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        Color backgroundColor = new Color(240, 248, 255); // Light blue background
        Color foregroundColor = new Color(30, 144, 255); // Dark blue foreground
        Color buttonColor = new Color(135, 206, 250); // Button color

        try {
            UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) { // Exceptions that might occur during the attempt to set the Look and Feel. If
                                // an exception occurs, it is caught, and the program continues to execute.
            e.printStackTrace(); // Prints the stack trace of the exception to the standard error stream
        }

        JPanel panel = new JPanel(new GridBagLayout()); // Arranges components in a grid
        GridBagConstraints gbc = new GridBagConstraints(); // New instance of the GridBagConstraints class, positioned
                                                           // and sized within a GridBagLayout
        gbc.insets = new Insets(5, 5, 5, 5); // Sets the insets property of the gbc object. Insets represent the space
                                             // (padding) around a component within its grid cell.
        gbc.anchor = GridBagConstraints.WEST; // Anchor - Relative position of the component within its grid cell.

        // BG color
        panel.setBackground(backgroundColor);

        // Name
        // Positions "Name:" in the first column (index 0) and first row (index 0)
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);

        // Text field with a width of 20 characters, positions it in the second column
        // (next to "Name:") of the first row within the panel using the
        // previously defined gbc object.
        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        // Number
        // Label "Number 1:" on the first column, second row, and a text field num1Field
        // next to it on the same row
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Number 1:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        num1Field = new JTextField(20);
        panel.add(num1Field, gbc);

        // Number 2
        // Label "Number 2:" on the first column, third row, and a text field num2Field
        // next to it on the same row
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Number 2:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        num2Field = new JTextField(20);
        panel.add(num2Field, gbc);

        // Operator
        // Label "Operator:" on the first column, fourth row, and a combo box
        // operatorComboBox next to it on the same row
        gbc.gridx = 0;
        gbc.gridy = 3;
        panel.add(new JLabel("Operator:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 3;
        String[] operators = { "+", "-", "*", "/" };
        operatorComboBox = new JComboBox<>(operators);
        panel.add(operatorComboBox, gbc);

        // Compute Button
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.gridwidth = 2; // Button should span two columns (0 and 1) in the grid.
        gbc.anchor = GridBagConstraints.CENTER; // Changes the anchor property to GridBagConstraints.CENTER. This will
                                                // center the button horizontally within the two columns it occupies.
        JButton computeButton = new JButton("Compute");
        computeButton.setBackground(buttonColor);
        computeButton.setForeground(foregroundColor);
        computeButton.setFocusPainted(false);
        computeButton.addActionListener(new ComputeListener()); // When the button is clicked, it will trigger an action
                                                                // defined in the ComputeListener class.
        panel.add(computeButton, gbc); // Adds the computeButton to the panel.

        add(panel); // Adds the previously defined panel (containing all the calculator elements) to
                    // the main window frame.
        setVisible(true); // Displays the entire calculator on the screen.
    }

    private class ComputeListener implements ActionListener { // Creates an inner class named ComputeListener
                                                              // for the CalculatorGUI class

        // ComputeListener class is responsible for listening to and handling action
        // events (like button clicks) from the "Compute" button in the
        // calculator.

        // When the button is clicked, the actionPerformed method of the ComputeListener
        // class is executed, which performs the operations based on user input.

        public void actionPerformed(ActionEvent e) {// This method will be called whenever an action event
                                                    // (like a button click) occurs on a component (like a button) that
                                                    // the listener is registered to.
            // Retrieves user input
            String name = nameField.getText();
            String num1Str = num1Field.getText();
            String num2Str = num2Field.getText();
            String operator = (String) operatorComboBox.getSelectedItem();

            try { // convert user input strings to numbers,
                  // Performs the calculation based on the selected operator, and
                  // Handles potential division by zero errors
                double num1 = Double.parseDouble(num1Str);
                double num2 = Double.parseDouble(num2Str);

                double result = 0;

                switch (operator) {
                    case "+":
                        result = num1 + num2;
                        break;
                    case "-":
                        result = num1 - num2;
                        break;
                    case "*":
                        result = num1 * num2;
                        break;
                    case "/":
                        if (num2 == 0) {
                            JOptionPane.showMessageDialog(null, "Cannot divide by zero!");
                            return;
                        }
                        result = num1 / num2;
                        break;
                    default:
                        JOptionPane.showMessageDialog(null, "Invalid operator!");
                        return;
                }

                JOptionPane.showMessageDialog(null, "Hello " + name + "! The result is: " + result);
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Please enter valid numbers!");
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new CalculatorGUI()); // invokeLater- schedule the creation of the
                                                               // CalculatorGUI instance to happen later on the event
    }
}
