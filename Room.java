package classes;

import java.io.Serializable;

public class Room implements Serializable {
    private int roomNumber;
    private int numOfGuests;
    private Person payingGuest;

    public Room() {
    }

    public Room(int roomNumber, int numOfGuests, Person payingGuest) {
        this.roomNumber = roomNumber;
        this.numOfGuests = numOfGuests;
        this.payingGuest = payingGuest;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public int getNumOfGuests() {
        return numOfGuests;
    }

    public void setNumOfGuests(int numOfGuests) {
        this.numOfGuests = numOfGuests;
    }

    public Person getPayingGuest() {
        return payingGuest;
    }

    public void setPayingGuest(Person payingGuest) {
        this.payingGuest = payingGuest;
    }

    @Override
    public String toString() {
        return " RoomNumber = " + roomNumber +" |"+
                " NumOfGuests = " + numOfGuests +" |"+
                " PayingGuest :- " + payingGuest ;
    }
}
