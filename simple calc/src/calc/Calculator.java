package calc;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Calculator extends JFrame implements ActionListener {
    private JTextField display;
    private String currentOperator;
    private double firstOperand;
    private boolean isNewInput;

    public Calculator() {
        setTitle("Calculator");
        setSize(300, 400);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        display = new JTextField("0");
        display.setFont(new Font("Arial", Font.BOLD, 24));
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setEditable(false);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);
        add(display, BorderLayout.NORTH);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(4, 4, 5, 5));
        buttonPanel.setBackground(Color.DARK_GRAY);

        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "C", "0", "=", "+"
        };

        for (String label : buttons) {
            JButton button = new JButton(label);
            button.setFont(new Font("Arial", Font.BOLD, 18));
            button.setBackground(Color.BLACK);
            button.setForeground(Color.WHITE);
            button.setFocusPainted(false);
            button.addActionListener(this);
            buttonPanel.add(button);
        }

        add(buttonPanel, BorderLayout.CENTER);

        currentOperator = "";
        firstOperand = 0;
        isNewInput = true;

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if ("0123456789".contains(command)) {
            if (isNewInput) {
                display.setText(command);
                isNewInput = false;
            } else {
                display.setText(display.getText() + command);
            }
        } else if ("/*-+".contains(command)) {
            currentOperator = command;
            firstOperand = Double.parseDouble(display.getText());
            isNewInput = true;
        } else if ("=".equals(command)) {
            double secondOperand = Double.parseDouble(display.getText());
            double result = switch (currentOperator) {
                case "+" -> firstOperand + secondOperand;
                case "-" -> firstOperand - secondOperand;
                case "*" -> firstOperand * secondOperand;
                case "/" -> secondOperand != 0 ? firstOperand / secondOperand : 0;
                default -> secondOperand;
            };
            display.setText(String.valueOf(result));
            isNewInput = true;
        } else if ("C".equals(command)) {
            display.setText("0");
            currentOperator = "";
            firstOperand = 0;
            isNewInput = true;
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(Calculator::new);
    }
}
