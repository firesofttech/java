import java.awt.event.*;
import javax.swing.*;
import java.awt.*;

class Calculator extends JFrame implements ActionListener {
    static JFrame frame;
    static JTextField textField;

    String operand1, operator, operand2;

    Calculator() {
        operand1 = operator = operand2 = "";
    }

    public static void main(String args[]) {
        frame = new JFrame("Calculator");

        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

        Calculator calculator = new Calculator();

        textField = new JTextField(16);
        textField.setEditable(false);

        JButton[] numberButtons = new JButton[10];
        for (int i = 0; i < 10; i++) {
            numberButtons[i] = new JButton(String.valueOf(i));
            numberButtons[i].addActionListener(calculator);
        }

        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("-");
        JButton multiplyButton = new JButton("*");
        JButton divideButton = new JButton("/");
        JButton decimalButton = new JButton(".");
        JButton equalButton = new JButton("=");
        JButton clearButton = new JButton("C");

        JButton[] operatorButtons = { addButton, subtractButton, multiplyButton, divideButton, decimalButton, equalButton, clearButton };

        JPanel panel = new JPanel();
        panel.setBackground(Color.blue);

        for (JButton button : operatorButtons) {
            button.addActionListener(calculator);
            panel.add(button);
        }

        for (JButton button : numberButtons) {
            panel.add(button);
        }

        panel.add(textField);
        frame.add(panel);
        frame.setSize(200, 300);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ((command.charAt(0) >= '0' && command.charAt(0) <= '9') || command.charAt(0) == '.') {
            if (!operand2.equals(""))
                operand2 = operand2 + command;
            else
                operand1 = operand1 + command;

            textField.setText(operand1 + operator + operand2);
        } else if (command.charAt(0) == 'C') {
            operand1 = operator = operand2 = "";
            textField.setText(operand1 + operator + operand2);
        } else if (command.charAt(0) == '=') {
            double result;

            if (operator.equals("+"))
                result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
            else if (operator.equals("-"))
                result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
            else if (operator.equals("/"))
                result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
            else
                result = Double.parseDouble(operand1) * Double.parseDouble(operand2);

            textField.setText(operand1 + operator + operand2 + "=" + result);

            operand1 = Double.toString(result);
            operand2 = "";
        } else {
            if (operator.equals("") || operand2.equals(""))
                operator = command;
            else {
                double result;

                if (operator.equals("+"))
                    result = Double.parseDouble(operand1) + Double.parseDouble(operand2);
                else if (operator.equals("-"))
                    result = Double.parseDouble(operand1) - Double.parseDouble(operand2);
                else if (operator.equals("/"))
                    result = Double.parseDouble(operand1) / Double.parseDouble(operand2);
                else
                    result = Double.parseDouble(operand1) * Double.parseDouble(operand2);

                operand1 = Double.toString(result);
                operator = command;
                operand2 = "";
            }

            textField.setText(operand1 + operator + operand2);
        }
    }
}
