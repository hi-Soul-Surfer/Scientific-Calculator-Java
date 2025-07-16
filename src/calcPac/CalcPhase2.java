package calcPac;

import java.util.Scanner;

public class CalcPhase2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        boolean keepRunning = true;

        while(keepRunning){
            System.out.println("---CALCULATOR---");

            System.out.println("Enter First number : ");
            double num1 = input.nextDouble();

            System.out.println("Enter operator (+,-,*,/,%) : ");
            char op = input.next().charAt(0);

            System.out.println("Enter Second number : ");
            double num2 = input.nextDouble();

            double result;

            switch (op) {
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
                    if (num2 == 0){
                        System.out.println("Error : Division by 0.");
                        continue;
                    }
                    result = num1/ num2;
                    break;
                case '%':
                    result = num1 % num2;
                    break;
                default:
                    System.out.println("Invalid Operator");
                    continue;
            }

            System.out.println("Result = "+result);

            System.out.println("Do you want to perform another calculation ? (yes/no)");
            String choice = input.next();

            if(!choice.equalsIgnoreCase("yes")){
                keepRunning = false;
            }
        }

        System.out.println("Calculator exited. GoodBye!");
    }
}