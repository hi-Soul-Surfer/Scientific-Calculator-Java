package calcPac;

import java.util.Scanner;

public class CalcPhase3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;

        while (keepRunning) {
            System.out.println("---SCIENTIFIC CALCULATOR---");
            System.out.println("Operators supported : +, -, *, /, %, ^, r(square root), s(sin), c(cos), t(tan), l(log10), e(ln) ");

            System.out.println("Enter the operator : ");
            char op = input.next().charAt(0);

            double num1 = 0,num2 = 0, result = 0;

            if (op == '+' || op == '-' || op == '*' || op == '/' || op == '%' || op == '^'){
                System.out.println("Enter num1 : ");
                num1 = input.nextDouble();
                System.out.println("Enter num2 : ");
                num2 = input.nextDouble();
            }
            else if (op == 'r' || op == 's' || op == 'c' || op == 't' || op == 'l' || op == 'e') {
                System.out.println("Enter num : ");
                num1 = input.nextDouble();
            }else {
                System.out.println("Invalid operator.");
                continue;
            }

            switch(op){
                case '+':
                    result = num1 + num2;
                    break;
                case '-':
                    result = num1 - num2;
                    break;
                case '*':
                    result = num1 * num2;
                    break;
                case '/':
                    if (num2 == 0) {
                        System.out.println("Error: Division by zero.");
                        continue;
                    }
                    result = num1 / num2;
                    break;
                case '%':
                    if (num2 == 0){
                        System.out.println("Error : Modulo by zero.");
                    }
                    result = num1 % num2;
                    break;
                case '^':
                    result = Math.pow(num1, num2);
                    break;
                case 'r':
                    if (num1 < 0) {
                        System.out.println("Error: Negative number for square root.");
                        continue;
                    }
                    result = Math.sqrt(num1);
                    break;
                case 's':
                    result = Math.sin(Math.toRadians(num1));
                    break;
                case 'c':
                    result = Math.cos(Math.toRadians(num1));
                    break;
                case 't':
                    double angle = num1 % 180;
                    if (angle == 90) {
                        System.out.println("Error: Tangent undefined at " + num1 + "°.");
                        continue;
                    }
                    result = Math.tan(Math.toRadians(num1));
                    break;

                case 'l':
                    if (num1 <= 0) {
                        System.out.println("Error: Logarithm of non-positive number.");
                        continue;
                    }
                    result = Math.log10(num1);
                    break;
                case 'e':
                    if (num1 <= 0) {
                        System.out.println("Error: Natural log of non-positive number.");
                        continue;
                    }
                    result = Math.log(num1);
                    break;
                default:
                    System.out.println("Invalid Operator");
                    continue;
            }
            System.out.println("Result = " +result);

            System.out.println("Do you want to continue ? (yes/no)");
            String choice = input.next().trim();

            if (!choice.equalsIgnoreCase("yes")){
                keepRunning = false;
            }
        }
        System.out.println("Scientific Calculator Exited. GoodBye!");
    }
}