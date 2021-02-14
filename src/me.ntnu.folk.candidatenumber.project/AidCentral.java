package me.ntnu.folk.candidatenumber.project;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

/**
 * This class is used to manage and run an aid-registry application.
 * @Author: André Gärtner
 */
public class AidCentral {

    private String centralName;
    private HashSet<Aid> aidRegister;

    /**
     * This is a constructor
     */
    public AidCentral() {
        centralName = "";
        aidRegister = new HashSet<>();
    }

    /**
     * This function returns the name of the central..
     * @return
     */
    public String getCentralName() {
        return centralName;
    }

    /**
     * Helper method for initializing objects of class Aid.
     */
    public void initializeAid(){
        aidRegister.add(new Aid(1001, "Høreapparat", "André"));
        aidRegister.add(new Aid(1002, "Samtaleforsterker"));
        aidRegister.add(new Aid(1003, "Varslingsutstyr", "Einar"));
    }

    /**
     * Adds a new Aid-object to the HashSet aidRegister
     * @param aidToBeAdded
     * @return boolean with info if object was added or not.
     */
    public boolean registerNewAid(Aid aidToBeAdded){
        for(Aid aid : aidRegister){
            if(aid.compareAid(aidToBeAdded)){
                return false;
            }
        }
        aidRegister.add(aidToBeAdded);
        return true;
    }

    /**
     * Adds a new renter/user to a registered aid.
     * @param aidToBeRentedOut
     * @param renterName
     * @return boolean with info if aid was rented out or not.
     */
    public boolean registerNewRenter(Aid aidToBeRentedOut, String renterName){
        if(aidToBeRentedOut.isAvailable()){
            aidToBeRentedOut.setPersonRenting(renterName);
            aidToBeRentedOut.setAvailability(false);
            return true;
        }else{
            return false;
        }
    }

    //version using iterator
    public boolean endRentalOfAid(int aidIDToBecomeAvailable){
        boolean complete = false;
        Iterator<Aid> it = aidRegister.iterator();
        while(it.hasNext()){
            Aid aid = it.next();
            if(aid.getAidID() == aidIDToBecomeAvailable){
                aid.setAvailability(true);
                complete = true;
            }
        }
        return complete;
    }

    //version using foreach
    public boolean endRentalOfAid2(int aidIDToBecomeAvailable){
        boolean complete = false;
        for(Aid list : aidRegister){
            if(list.getAidID() == aidIDToBecomeAvailable){
                list.setAvailability(true);
                complete = true;
            }
        }
        return complete;
    }

    /**
     * Creates a list with all the books within the library.
     * @return ArrayList with all aid-objects.
     */
    public ArrayList listAllAid(){

        ArrayList<Aid> aidArrayList = new ArrayList<>();
        for(Aid list : aidRegister){
            aidArrayList.add(list);
        }

        return aidArrayList;
    }

    /**
     * Creates a list with all books of a given aid type.
     * @param aidTypeToBeListed
     * @return ArrayList with all aid-objects of a specific type.
     */
    public ArrayList listAllAidOfGivenType(String aidTypeToBeListed){
        ArrayList<Aid> aidArrayList = new ArrayList<>();
        for(Aid list : aidRegister){
            if(list.getAidDescription().equalsIgnoreCase(aidTypeToBeListed) && list.isAvailable()){
                aidArrayList.add(list);
            }
        }
        return aidArrayList;
    }

    /**
     * Converts the contents of an ArrayList to a string.
     * @param listToPrint ArrayList with results from a search.
     */
    public void printResult(ArrayList<Aid> listToPrint){
        if(listToPrint.isEmpty()){
            System.out.println("No aid were found.");
        }else{
            for(Aid list : listToPrint){
                System.out.println(list.toString());
            }
        }
    }

    /**
     * Compares input-ID with the ID of all aid-objects.
     * @param id
     * @return aid-object with identical ID.
     */
    public Aid getAidByID(int id){
        for(Aid list : aidRegister){
            if(id == list.getAidID()){
                return list;
            }
        }
        return null;
    }

    /**
     * Checks if input-ID already exists in the aidRegister HashSet.
     * @param input
     * @return boolean
     */
    public boolean checkUniqID(int input){

        Aid tempAid = null;
        boolean uniq = false;

        while(!uniq){
            tempAid = getAidByID(input);
            if(tempAid == null){
                uniq = true;
            }else{
                System.out.println("Aid with same ID already exists. Please enter another ID.");
                return uniq;
            }
        }
        return uniq;
    }

}
