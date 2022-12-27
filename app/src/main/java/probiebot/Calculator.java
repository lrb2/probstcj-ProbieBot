/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package probiebot;

import java.util.Scanner;
import java.util.Stack;

/**
 *
 * @author probs
 */
public class Calculator {
    public Calculator(){
        
    }
    public static double evalPostFix(String post) throws IllegalArgumentException{
        Stack<Double> stack = new Stack<>();
        double leftOp = 0, rightOp = 0;
        Scanner text = new Scanner(post); 
        String data = null;
            while(text.hasNext()){
                data = text.next();
                try{
                    stack.push(Double.parseDouble(data));
                }
                
                catch(NumberFormatException e){
                    if(stack.size()<2){
                        throw new IllegalArgumentException("String is not in postfix notation.");
                    }
                    rightOp = stack.pop();
                    leftOp = stack.pop();
                    switch(data){
                        case "+":
                            stack.push(leftOp+rightOp);
                            break;
                        case "-":
                            stack.push(leftOp-rightOp);
                            break;
                        case "*":
                            stack.push(leftOp*rightOp);
                            break;
                        case "/":
                            stack.push(leftOp/rightOp);
                            break;
                        case "^":
                            stack.push(Math.pow(leftOp, rightOp));
                            break;
                        case "%":
                            stack.push(leftOp%rightOp);
                            break;
                        default:
                            throw new IllegalArgumentException("String is not in PostFix notation");
                            
                    }
                }
            }
            if(stack.size() != 1){
                throw new IllegalArgumentException("String is not in postfix notation.");
            }
            return stack.peek();
    }
    public static String infixToPostfix(String s) throws IllegalArgumentException{
        s = s.replaceAll("\\(", " ( ");
        s = s.replaceAll("\\)", " ) ");
        s = s.replaceAll("\\+", " + ");
        s = s.replaceAll("-", " - ");
        s = s.replaceAll("\\*", " * ");
        s = s.replaceAll("/", " / ");
        s = s.replaceAll("\\^", " ^ ");
        s = s.replaceAll("%", " % ");
        
        Stack<String> ops = new Stack<>();
        StringBuilder postFix = new StringBuilder();
        String data = null;
        Scanner input = new Scanner(s);
        while(input.hasNext()){
            if(input.hasNextDouble()){
                postFix.append(input.next()).append(" ");
            }
            else{
                data = input.next();
                if(ops.isEmpty() || data.equals("(")){
                    ops.push(data);
                }
                else if(data.equals(")")){
                    while(!ops.isEmpty() && !ops.peek().equals("(")){
                        postFix.append(ops.pop()).append(" ");
                    }
                    if(ops.isEmpty()){
                        throw new IllegalArgumentException("Incorrect infix notation");
                    }
                    else{
                        ops.pop();
                    }
                }
                //else if(precedence(ops.peek()) >= precedence(data)){
                else{
                    while(!ops.isEmpty() && precedence(ops.peek()) >= precedence(data)){
                        postFix.append(ops.pop()).append(" ");
                    }
                    ops.push(data);
                }
            }
        }
        while(!ops.isEmpty()){
            postFix.append(ops.pop()).append(" ");
        }
        return postFix.toString();
    }
    private static int precedence(String s){
        int plevel = 0;
        switch(s){
            case "^": plevel++;
            case "*":
            case "/":
            case "%": plevel++;
            case "+": 
            case "-": plevel++;
        }
        return plevel;
    }
}
