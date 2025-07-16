package calcPac;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Stack;

public class CalcGUI extends JFrame {
    private JTextField display;

    public CalcGUI() {
        setTitle("Scientific Calculator");
        setSize(400, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center on screen

        // --- Display ---
        display = new JTextField();
        display.setFont(new Font("Arial", Font.BOLD, 28));
        display.setPreferredSize(new Dimension(0, 60)); // Increase height
        display.setHorizontalAlignment(SwingConstants.RIGHT);
        display.setEditable(false);
        display.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(display, BorderLayout.NORTH);

        // --- Buttons ---
        JPanel buttonPanel = new JPanel(new GridLayout(6, 4, 5, 5));
        String[] buttons = {
                "7", "8", "9", "/",
                "4", "5", "6", "*",
                "1", "2", "3", "-",
                "0", ".", "=", "+",
                "√", "^", "C", "DEL",
                "sin", "cos", "tan", "ln"
        };

        for (String text : buttons) {
            JButton btn = new JButton(text);
            btn.setFont(new Font("Arial", Font.PLAIN, 16));
            btn.setPreferredSize(new Dimension(80, 60));
            btn.addActionListener(new ButtonHandler());
            buttonPanel.add(btn);
        }

        // --- Padding around button panel ---
        JPanel paddedPanel = new JPanel(new BorderLayout());
        paddedPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        paddedPanel.add(buttonPanel, BorderLayout.CENTER);
        add(paddedPanel, BorderLayout.CENTER);
    }

    private class ButtonHandler implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String cmd = e.getActionCommand();

            switch (cmd) {
                case "=":
                    try {
                        String expr = display.getText();
                        double result = evaluateExpression(expr);
                        display.setText(String.valueOf(result));
                    } catch (Exception ex) {
                        display.setText("Error");
                    }
                    break;
                case "C":
                    display.setText("");
                    break;
                case "DEL":
                    String text = display.getText();
                    if (!text.isEmpty()) {
                        display.setText(text.substring(0, text.length() - 1));
                    }
                    break;
                case "√":
                    try {
                        double val = Double.parseDouble(display.getText());
                        display.setText(String.valueOf(Math.sqrt(val)));
                    } catch (Exception ex) {
                        display.setText("Error");
                    }
                    break;
                case "sin":
                    trigFunction(Math::sin);
                    break;
                case "cos":
                    trigFunction(Math::cos);
                    break;
                case "tan":
                    trigFunction(Math::tan);
                    break;
                case "ln":
                    try {
                        double val = Double.parseDouble(display.getText());
                        display.setText(String.valueOf(Math.log(val)));
                    } catch (Exception ex) {
                        display.setText("Error");
                    }
                    break;
                default:
                    if ("+-*/^".contains(cmd)) {
                        display.setText(display.getText() + " " + cmd + " ");
                    } else {
                        display.setText(display.getText() + cmd);
                    }
            }
        }

        private void trigFunction(java.util.function.DoubleUnaryOperator func) {
            try {
                double val = Double.parseDouble(display.getText());
                double radians = Math.toRadians(val);
                display.setText(String.valueOf(func.applyAsDouble(radians)));
            } catch (Exception ex) {
                display.setText("Error");
            }
        }

        private double evaluateExpression(String expr) {
            String postfix = infixToPostfix(expr);
            return evaluatePostfix(postfix);
        }

        private String infixToPostfix(String infix) {
            StringBuilder output = new StringBuilder();
            Stack<Character> stack = new Stack<>();
            for (int i = 0; i < infix.length(); i++) {
                char ch = infix.charAt(i);
                if (Character.isDigit(ch) || ch == '.') {
                    output.append(ch);
                } else if ("+-*/^".indexOf(ch) != -1) {
                    output.append(" ");
                    while (!stack.isEmpty() && precedence(stack.peek()) >= precedence(ch)) {
                        output.append(stack.pop()).append(" ");
                    }
                    stack.push(ch);
                } else if (ch == ' ') {
                    output.append(" ");
                }
            }
            output.append(" ");
            while (!stack.isEmpty()) {
                output.append(stack.pop()).append(" ");
            }
            return output.toString();
        }

        private int precedence(char op) {
            return switch (op) {
                case '+', '-' -> 1;
                case '*', '/' -> 2;
                case '^' -> 3;
                default -> -1;
            };
        }

        private double evaluatePostfix(String postfix) {
            Stack<Double> stack = new Stack<>();
            for (String token : postfix.trim().split("\\s+")) {
                if ("+-*/^".contains(token)) {
                    if (stack.size() < 2) throw new IllegalArgumentException("Invalid expression");
                    double b = stack.pop();
                    double a = stack.pop();
                    double res = switch (token.charAt(0)) {
                        case '+' -> a + b;
                        case '-' -> a - b;
                        case '*' -> a * b;
                        case '/' -> a / b;
                        case '^' -> Math.pow(a, b);
                        default -> throw new IllegalArgumentException();
                    };
                    stack.push(res);
                } else {
                    stack.push(Double.parseDouble(token));
                }
            }
            return stack.pop();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            CalcGUI gui = new CalcGUI();
            gui.setVisible(true);
        });
    }
}
