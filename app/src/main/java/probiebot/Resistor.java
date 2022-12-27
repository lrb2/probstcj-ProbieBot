/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package probiebot;

/** 
 * 
 * @author hellokitty
 */
public class Resistor {
    public static String[] colors = {"black","brown","red",
        "orange","yellow","green","blue","violet","gray","white","gold","silver"};
    public static int[] colorsValue = {0,1,2,3,4,5,6,7,8,9,-1,-2};
    public static double[] colorsTolerance = {-1, .01,.02,.0005,.0002,.005,.0025,.001,.0001, -1,.05,.10};
    public static int[] colorsTempCoeff = {250,100,50,15,25,20,10,5,1,-1,-1,-1}; //ppm/K
    private int firstDig, secondDig, thirdDig, multiplier, coeff;
    private double tolerance;
    public Resistor(int firstDig, int secondDig, int thirdDig,int multiplier, double tolerance, int coeff) throws IllegalArgumentException{
        if(firstDig >= 0 && secondDig >= 0 && thirdDig >= 0){
            this.firstDig = firstDig;
            this.secondDig = secondDig;
            this.thirdDig = thirdDig;
        }
        else{
            throw new IllegalArgumentException("Incorrect first and/or second digit");
        }
        this.multiplier = multiplier;
        if(tolerance > 0){
            this.tolerance = tolerance;
        }
        else{
            throw new IllegalArgumentException("Incorrect tolerance");
        }
        if(coeff>0){
            this.coeff = coeff;
        }
        else{
            throw new IllegalArgumentException("Incorrect Coefficient");
        }
    }
    public Resistor(String[] colors) throws IllegalArgumentException{
        int dig1=-1, dig2=-1, dig3=-1, mult=-10, tempCoeff = -1;
        double tol=-1;
        switch (colors.length) {
            case 3:
                for (int i = 0; i < colors.length; i++) {
                    switch (i) {
                        case 0:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig2 = colorsValue[j];
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig3 = colorsValue[j];
                                }
                            }
                            break;
                        case 2:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    mult = colorsValue[j];
                                }
                            }
                            break;
                    }
                }
                if(dig2<0 || dig3<0 || mult == -10){
                    throw new IllegalArgumentException("Incorrect colors");
                }
                else{
                    firstDig = 0;
                    secondDig = dig2;
                    thirdDig = dig3;
                    multiplier = mult;
                    tolerance = .20;
                    coeff = -1;
                }
                break;
            case 4:
                for (int i = 0; i < colors.length; i++) {
                    switch (i) {
                        case 0:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig2 = colorsValue[j];
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig3 = colorsValue[j];
                                }
                            }
                            break;
                        case 2:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    mult = colorsValue[j];
                                }
                            }
                            break;
                        case 3:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    tol = colorsTolerance[j];
                                }
                            }
                            break;
                    }
                }
                if(dig2<0 || dig3<0 || mult == -10 || tol<0){
                    throw new IllegalArgumentException("Incorrect colors");
                }
                else{
                    firstDig = 0;
                    secondDig = dig2;
                    thirdDig = dig3;
                    multiplier = mult;
                    tolerance = tol;
                    coeff = -1;
                }
                break;
            case 5:
                for (int i = 0; i < colors.length; i++) {
                    switch (i) {
                        case 0:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig1 = colorsValue[j];
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig2 = colorsValue[j];
                                }
                            }
                            break;
                        case 2:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig3 = colorsValue[j];
                                }
                            }
                            break;
                        case 3:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    mult = colorsValue[j];
                                }
                            }
                            break;
                        case 4:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    tol = colorsTolerance[j];
                                }
                            }
                            break;
                    }
                }
                if(dig1 < 0 || dig2<0 || dig3<0 || mult == -10 || tol<0){
                    throw new IllegalArgumentException("Incorrect colors");
                }
                else{
                    firstDig = dig1;
                    secondDig = dig2;
                    thirdDig = dig3;
                    multiplier = mult;
                    tolerance = tol;
                    coeff = -1;
                }
                break;
            case 6:
                for (int i = 0; i < colors.length; i++) {
                    switch (i) {
                        case 0:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig1 = colorsValue[j];
                                }
                            }
                            break;
                        case 1:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig2 = colorsValue[j];
                                }
                            }
                            break;
                        case 2:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    dig3 = colorsValue[j];
                                }
                            }
                            break;
                        case 3:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    mult = colorsValue[j];
                                }
                            }
                            break;
                        case 4:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    tol = colorsTolerance[j];
                                }
                            }
                            break;
                        case 5:
                            for (int j = 0; j < this.colors.length; j++) {
                                if(colors[i].equalsIgnoreCase(this.colors[j])){
                                    tempCoeff = colorsTempCoeff[j];
                                }
                            }
                            break;
                    }
                }
                if(dig1 < 0 || dig2<0 || dig3<0 || mult == -10 || tol<0 || tempCoeff<0){
                    throw new IllegalArgumentException("Incorrect colors");
                }
                else{
                    firstDig = dig1;
                    secondDig = dig2;
                    thirdDig = dig3;
                    multiplier = mult;
                    tolerance = tol;
                    coeff = tempCoeff;
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
    }
    public double resistanceCalc(){
        return ((firstDig*100) + (secondDig*10) + (thirdDig))*(Math.pow(10, multiplier));
    }
    public int getDigit1(){
        return firstDig;
    }
    public int getDigit2(){
        return secondDig;
    }
    public int getDigit3(){
        return thirdDig;
    }
    public int getMult(){
        return multiplier;
    }
    public double getTolerance(){
        return tolerance;
    }
    public int getTempCoeff(){
        return coeff;
    }
    public String colorFromValue(float res){
        return null;
    }
}
