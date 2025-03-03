import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Calculator implements ActionListener {

    private JFrame frame;
    private JTextField displayField;
    private JButton[] numberButtons;
    private JButton[] operatorButtons;
    private JButton clearButton, equalButton, decimalButton;
    private String currentInput = "";
    private String operator = "";
    private double operand1 = 0;

    public Calculator() {
        frame = new JFrame("Calculator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 500);

        displayField = new JTextField(20);
        displayField.setEditable(false);
        displayField.setFont(new Font("Arial", Font.PLAIN, 24));

        JPanel buttonPanel = new JPanel(new GridLayout(4, 4));

        numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(this);
            buttonPanel.add(numberButtons[i]);
        }

        operatorButtons = new JButton[4];
        operatorButtons[0] = new JButton("+");
        operatorButtons[1] = new JButton("-");
        operatorButtons[2] = new JButton("*");
        operatorButtons[3] = new JButton("/");
        for (JButton button : operatorButtons) {
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        clearButton = new JButton("C");
        clearButton.addActionListener(this);
        buttonPanel.add(clearButton);

        equalButton = new JButton("=");
        equalButton.addActionListener(this);
        buttonPanel.add(equalButton);

        decimalButton = new JButton(".");
        decimalButton.addActionListener(this);
        buttonPanel.add(decimalButton);

        frame.add(displayField, BorderLayout.NORTH);
        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (Character.isDigit(command.charAt(0))) {
            currentInput += command;
            displayField.setText(currentInput);
        } else if (command.equals(".")) {
            if (!currentInput.contains(".")) {
                currentInput += command;
                displayField.setText(currentInput);
            }
        } else if (command.equals("+") || command.equals("-") || command.equals("*") || command.equals("/")) {
            if (!currentInput.isEmpty()) {
                operand1 = Double.parseDouble(currentInput);
                operator = command;
                currentInput = "";
            }
        } else if (command.equals("=")) {
            if (!currentInput.isEmpty() && !operator.isEmpty()) {
                double operand2 = Double.parseDouble(currentInput);
                double result = 0;
                switch (operator) {
                    case "+":
                        result = operand1 + operand2;
                        break;
                    case "-":
                        result = operand1 - operand2;
                        break;
                    case "*":
                        result = operand1 * operand2;
                        break;
                    case "/":
                        if (operand2 != 0) {
                            result = operand1 / operand2;
                        } else {
                            displayField.setText("Error: Division by zero");
                            return;
                        }
                        break;
                }
                currentInput = String.valueOf(result);
                displayField.setText(currentInput);
                operator = "";
            }
        } else if (command.equals("C")) {
            currentInput = "";
            operator = "";
            operand1 = 0;
            displayField.setText("");
        }
    }

    public static void main(String[] args) {
        new Calculator();
    }
}
