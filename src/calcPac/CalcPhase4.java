package calcPac;

import java.util.*;
import java.io.FileWriter;
import java.io.IOException;

public class CalcPhase4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;
        List<String> history = new ArrayList<>();

        while (keepRunning) {
            System.out.println("\n--- SCIENTIFIC CALCULATOR ---");
            System.out.println("Operators: +, -, *, /, %, ^, r (sqrt), s (sin), c (cos), t (tan), l (log10), e (ln)");
            System.out.print("Enter operator: ");

            String operatorInput = input.next().trim();
            if (operatorInput.length() != 1){
                System.out.println("Invalid operator. Enter a single-character operator.");
                continue;
            }

            char op = operatorInput.charAt(0);
            double num1 = 0, num2 = 0, result = 0;
            String operation = "";

            if ("+-*/%^".indexOf(op) != -1) {
                System.out.println("Enter num1 : ");
                num1 = input.nextDouble();
                System.out.println("Enter num2 : ");
                num2 = input.nextDouble();
            } else if ("rsctle".indexOf(op) != -1) {
                System.out.println("Enter num : ");
                num1 = input.nextDouble();
            } else {
                System.out.println("Invalid operator.");
                continue;
            }

            switch(op) {
                case '+':
                    result = num1 + num2;
                    operation = num1 + " + " + num2 + " = " + result;
                    break;
                case '-':
                    result = num1 - num2;
                    operation = num1 + " - " + num2 + " = " + result;
                    break;
                case '*':
                    result = num1 * num2;
                    operation = num1 + " * " + num2 + " = " + result;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero.");
                        continue;
                    }
                    result = num1 / num2;
                    operation = num1 + " / " + num2 + " = " + result;
                    break;
                case '%':
                    if (num2 == 0){
                        System.out.println("Error : Modulo by zero.");
                        continue;
                    }
                    result = num1 % num2;
                    operation = num1 + " % " + num2 + " = " +result;
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    operation = num1 + " ^ " + num2 + " = " + result;
                    break;
                case 'r':
                    if (num1 < 0) {
                        System.out.println("Error: Negative number for square root.");
                        continue;
                    }
                    result = Math.sqrt(num1);
                    operation = "√" + num1 + " = " + result;
                    break;
                case 's':
                    result = Math.sin(Math.toRadians(num1));
                    operation = "sin(" + num1 + "°) = " + result;
                    break;
                case 'c':
                    result = Math.cos(Math.toRadians(num1));
                    operation = "cos(" + num1 + "°) = " + result;
                    break;
                case 't':
                    if ((num1 % 180) == 90) {
                        System.out.println("Error: Tangent undefined at " + num1 + "°.");
                        continue;
                    }
                    result = Math.tan(Math.toRadians(num1));
                    operation = "tan(" + num1 + "°) = " + result;
                    break;
                case 'l':
                    if (num1 <= 0) {
                        System.out.println("Error: Logarithm of non-positive number.");
                        continue;
                    }
                    result = Math.log10(num1);
                    operation = "log10(" + num1 + ") = " + result;
                    break;
                case 'e':
                    if (num1 <= 0) {
                        System.out.println("Error: Natural log of non-positive number.");
                        continue;
                    }
                    result = Math.log(num1);
                    operation = "ln(" + num1 + ") = " + result;
                    break;
            }

            System.out.println("/n-------------------------------");
            System.out.println("Result = " +result);
            history.add(operation);

            System.out.println("Do you want to continue ? (yes/no) : ");
            String choice = input.next().trim();

            if(!choice.equalsIgnoreCase("yes")) {
                keepRunning = false;
            }
        }

        System.out.println("\n----CALCULATION HISTORY----");
        for (String entry : history) {
            System.out.println(entry);
        }

        System.out.println("\nDo you want to save the history to a file? (yes/no) : ");
        String saveChoice = input.next().trim();

        if(saveChoice.equalsIgnoreCase("yes")) {
            try {
                FileWriter writer = new FileWriter("calc_history.txt");
                for (String entry : history){
                    writer.write(entry + "\n");
                }
                writer.close();
                System.out.println("History saved to 'calc_history.txt'");
            }catch (IOException e) {
                System.out.println("Error writing to file: " + e.getMessage());
            }
        }
        System.out.println("Calculator exited. GoodBye!");
        input.close();
    }
}