import me.ntnu.folk.candidatenumber.project.Aid;
import me.ntnu.folk.candidatenumber.project.AidCentral;
import me.ntnu.folk.candidatenumber.project.InputHandler;

import java.util.Scanner;

/**
 * This class is used to manage a text-based user interface for an aid-registry application.
 * @Author: André Gärtner
 * @version 1.0
 */
public class App {

    private final int REG_HEARING_AID = 1;
    private final int LIST_ALL_HEARING_AIDS = 2;
    private final int REG_NEW_RENTER = 3;
    private final int END_RENTAL_OF_AID = 4;
    private final int EXIT = 5;

    Scanner scn;
    AidCentral aidCentral;
    InputHandler inputHandler;

    /**
     * Constructor that initializes a Scanner and objects of classes.
     * Also takes the user to the start-menu.
     */
    public App(){
        scn = new Scanner(System.in);
        aidCentral = new AidCentral();
        inputHandler = new InputHandler();
        aidCentral.initializeAid();
        start();
    }

    /**
     * Creates an object of the App class.
     * Is the first function that runs when the application starts.
     * @param args
     */
    public static void main(String[] args) {
        App app = new App();
    }

    /**
     * Switch that takes input from user.
     */
    public void start(){

        boolean finished = false;

        while(!finished){
            int menuChoice = showMenu();
            switch(menuChoice){
                case REG_HEARING_AID:
                    regNewAidMenu();
                    break;
                case LIST_ALL_HEARING_AIDS:
                    listAllAid();
                    break;
                case REG_NEW_RENTER:
                    regNewRenterMenu();
                    break;
                case END_RENTAL_OF_AID:
                    endRentalMenu();
                    break;
                case EXIT:
                    finished = true;
                    break;
                default:
                    System.out.println("Unrecognized menu selected..");
                    break;
            }
        }
    }

    /**
     * Method that displays information and also takes the actual input from the user.
     * @return input from user.
     */
    public int showMenu(){
        System.out.println("\n***** Aid Central *****\n");
        System.out.println("1. Register new aid");
        System.out.println("2. List all aid");
        System.out.println("3. Register new renter");
        System.out.println("4. End rental of an aid");
        System.out.println("5. Quit");
        System.out.println("\nPlease select from the menu.\n");

        int menuChoice = 0;

        Scanner sc = new Scanner(System.in);

        if(sc.hasNextInt()){
            menuChoice = sc.nextInt();
        }else{
            System.out.println("You must enter an integer.");
        }
        return menuChoice;
    }

    /**
     * This method lets the user input information to register a new aid. Done with a Scanner.
     * The aid-ID has to be an integer, and the aid-type/description can't be 0 letters long.
     */
    public void regNewAidMenu(){

        System.out.println("-----------Register new aid-----------");
        System.out.println("Please enter a uniq ID: ");

        int inputID = inputHandler.getIntInput(1001, 9999);

        while(!aidCentral.checkUniqID(inputID)){
            inputID = inputHandler.getIntInput(1001, 9999);
        }

        System.out.println("Please enter name of the aid: ");

        String aidDescription = scn.nextLine();
        boolean validDescription = false;

        while(!validDescription){
            if(inputHandler.isStringValid(aidDescription)){
                validDescription = true;
            }else{
                System.out.println("You have not specified the type. Please try again");
                scn.next();
            }
        }

        if(aidCentral.registerNewAid(new Aid(inputID, aidDescription))){
            System.out.println("The aid was successfully added.");
        }else{
            System.out.println("The aid was not added.");
        }

    }

    /**
     * Prints a list to the user of all aid within the aid register.
     */
    private void listAllAid(){
        System.out.println(aidCentral.getCentralName());
        System.out.println("-----------All registered aid-----------");
        aidCentral.printResult(aidCentral.listAllAid());
    }

    /**
     * This method lets the user input information to register a new rent. Done with a Scanner.
     * The aid-ID has to be an integer, the aid has to exist, and the aid has to be available for rent.
     */
    public void regNewRenterMenu(){
        System.out.println("-----------Register new renter-----------");
        System.out.println("Please enter the ID of the aid you want to rent:");

        int inputID = inputHandler.getIntInput(1001, 9999);

        Aid newlyRentedAid = null;

        boolean recognizableID = false;
        while(!recognizableID){
            newlyRentedAid = aidCentral.getAidByID(inputID);
            if(newlyRentedAid == null || !newlyRentedAid.isAvailable()){
                System.out.println("This aid is not available or does not exist. Please enter another ID.");
                inputID = inputHandler.getIntInput(1001, 9999);
            }else{
                recognizableID = true;
            }
        }

        String renter = scn.nextLine();

        while(!inputHandler.isStringValid(renter)){
            System.out.println("Enter the name of the new renter.");
            renter = scn.nextLine();
        }

        System.out.println(newlyRentedAid.getAidDescription() + " has now been successfully rented out to " + renter);
        aidCentral.registerNewRenter(newlyRentedAid, renter);
    }

    /**
     * This method lets the user input information to end a rented aid. Done with a Scanner.
     * The aid-ID has to be an integer, the aid has to exist, and the aid has to be unavailable for rent.
     */
    private void endRentalMenu(){
        System.out.println("Please enter the ID of the aid you wish to make available");

        int inputID = inputHandler.getIntInput(1001, 9999);

        Aid newlyEndedRental;

        boolean recognizableID = false;
        while(!recognizableID){
            newlyEndedRental = aidCentral.getAidByID(inputID);
            if(newlyEndedRental == null || newlyEndedRental.isAvailable()){
                System.out.println("This aid can't be ended or does not exist. Please enter another ID.");
                inputID = inputHandler.getIntInput(1001, 9999);
            }else{
                recognizableID = true;
            }
        }

        if(aidCentral.endRentalOfAid(inputID)){
            System.out.println("The aid is now available!");
        }else{
            System.out.println("Error.");
        }

    }

}
