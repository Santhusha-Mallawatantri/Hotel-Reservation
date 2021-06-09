package classes;

import java.io.Serializable;

public class Person implements Serializable {

    private String firstName;
    private String Surname;
    private String creditCardNumber;

    public Person() {
    }

    public Person(String firstName, String surname) {
        this.firstName = firstName;
        Surname = surname;
    }

    public Person(String firstName, String surname, String creditCardNumber) {
        this.firstName = firstName;
        Surname = surname;
        this.creditCardNumber = creditCardNumber;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSurname() {
        return Surname;
    }

    public void setSurname(String surname) {
        Surname = surname;
    }

    public String getCreditCardNumber() {
        return creditCardNumber;
    }

    public void setCreditCardNumber(String creditCardNumber) {
        this.creditCardNumber = creditCardNumber;
    }

    @Override
    public String toString() {
        return "firstName = " + firstName + " |"+
                " Surname = " + Surname + " |"+
                " Credit Card Number = " + creditCardNumber;
    }
}
