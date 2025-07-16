package calcPac;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Enter First number");
        double num1 = input.nextDouble();

        System.out.println("Enter operator (+,-,*,/,%)");
        char op = input.next().charAt(0);

        System.out.println("Enter Second number");
        double num2 = input.nextDouble();

        double result;

        switch (op){
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
                if(num2 == 0){
                    System.out.println("Error : Division by 0");
                    return;
                }
                result = num1 / num2;
                break;
            case '%':
                result = num1 % num2;
                break;
            default:
                System.out.println("Invalid operator.");
                return;
            }
        System.out.println("Result = "+ result);
    }
}