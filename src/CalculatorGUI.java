import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalculatorGUI extends JFrame {
    private JTextField nameField;
    private JTextField num1Field;
    private JTextField num2Field;
    private JComboBox<String> operatorComboBox;

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.anchor = GridBagConstraints.WEST;

        // BG color
        panel.setBackground(backgroundColor);

        // Name
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Name:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);

        // Number 1
        gbc.gridx = 0;
        gbc.gridy = 1;
        panel.add(new JLabel("Number 1:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        num1Field = new JTextField(20);
        panel.add(num1Field, gbc);

        // Number 2
        gbc.gridx = 0;
        gbc.gridy = 2;
        panel.add(new JLabel("Number 2:"), gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        num2Field = new JTextField(20);
        panel.add(num2Field, gbc);

        // Operator
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
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JButton computeButton = new JButton("Compute");
        computeButton.setBackground(buttonColor);
        computeButton.setForeground(foregroundColor);
        computeButton.setFocusPainted(false);
        computeButton.addActionListener(new ComputeListener());
        panel.add(computeButton, gbc);

        add(panel);
        setVisible(true);
    }

    private class ComputeListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String name = nameField.getText();
            String num1Str = num1Field.getText();
            String num2Str = num2Field.getText();
            String operator = (String) operatorComboBox.getSelectedItem();

            try {
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
        SwingUtilities.invokeLater(() -> new CalculatorGUI());
    }
}
