package me.ntnu.folk.candidatenumber.project;

/**
 * This class is used to describe aid.
 * @Author: André Gärtner
 */
public class Aid {

    private int aidID;
    private String aidDescription;
    private String personRenting;
    private boolean available;

    /**
     * This is a constructor that takes 3 parameters.
     * @param aidID Parameter for ID.
     * @param aidDescription Parameter for aid-type.
     * @param personRenting Parameter for name of aid-renter.
     */
    public Aid(int aidID, String aidDescription, String personRenting) {
        this.aidID = aidID;
        this.aidDescription = aidDescription;
        this.personRenting = personRenting;
        this.available = false;
    }

    /**
     * This is a constructor that takes 2 parameters.
     * @param aidID Parameter for ID.
     * @param aidDescription Parameter for aid-type.
     */
    public Aid(int aidID, String aidDescription) {
        this.aidID = aidID;
        this.aidDescription = aidDescription;
        this.personRenting = "";
        this.available = true;
    }

    /**
     * This function returns the aid identification number.
     * @return aid ID.
     */
    public int getAidID() {
        return aidID;
    }

    /**
     * This function returns the aid type.
     * @return aid type/aid description.
     */
    public String getAidDescription() {
        return aidDescription;
    }

    /**
     * This function returns the name of the person renting the aid.
     * @return Name of person
     */
    public String getPersonRenting() {
        return personRenting;
    }

    /**
     * This function sets the name of the person renting the aid.
     * @param personRenting
     */
    public void setPersonRenting(String personRenting) {
        this.personRenting = personRenting;
    }

    /**
     * This functions returns info about the availability of the aid.
     * @return Availability.
     */
    public boolean isAvailable() {
        return available;
    }

    /**
     * This function sets the availability of a aid.
     * @param availability
     */
    public void setAvailability(boolean availability) {
        this.available = availability;
    }

    /**
     * This function compares two aids based on the aid IDs.
     * @param aid
     * @return info regarding if aids are identical or not.
     */
    public boolean compareAid(Aid aid){
        return aid.getAidID() == this.getAidID();
    }

    /**
     * This function returns information about the aid.
     * @return String
     */
    @Override
    public String toString(){

        String info = this.getAidID() + " " + this.getAidDescription() + " ";

        if(!isAvailable()){
            info += "rented to " + this.getPersonRenting() + ".";
        }else{
            info += "available.";
        }

        return info;

    }
}
