package org.example;

public record Contact(String fullName, String phoneNumber, String email) {

    @Override
    public String toString() {
        return fullName + " | " + phoneNumber + " | " + email;
    }
}
