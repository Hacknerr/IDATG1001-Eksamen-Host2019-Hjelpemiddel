package me.ntnu.folk.candidatenumber.project;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * This class is used to handle input from the user.
 * @Author: André Gärtner
 */
public class InputHandler {

    private Scanner sc;

    /**
     * Initializes Scanner
     */
    public InputHandler(){
        sc = new Scanner(System.in);
    }

    /**
     * Takes int from user and makes sure its within the number-range of the desired parameters
     * @param min
     * @param max
     * @return input from user.
     */
    public int getIntInput(int min, int max){

        boolean validInput = false;
        int inputFromUser = 0;

        while(!validInput){
            try{
                inputFromUser = sc.nextInt();
                if(checkValidInteger(inputFromUser, min, max)){
                    validInput = true;
                }
            }catch(InputMismatchException e){
                System.out.println("Please enter an integer!");
                sc.next();
            }
        }
        return inputFromUser;
    }

    /**
     * Checks if int-parameter is within the number-range of the desired min & max parameters
     * @param toBeChecked
     * @param minimumValue
     * @param maximumValue
     * @return boolean
     */
    public boolean checkValidInteger(int toBeChecked, int minimumValue, int maximumValue){
        boolean validInteger = false;
        if(toBeChecked <= maximumValue && toBeChecked >= minimumValue){
            validInteger = true;
        }else{
            System.out.println("Invalid input. Please enter an integer between " + minimumValue + " and " + maximumValue);
        }
        return validInteger;
    }

    /**
     * Checks if string is more than 0 letters in length.
     * @param string
     * @return
     */
    public boolean isStringValid(String string){
        return string.length() != 0;
    }

}
