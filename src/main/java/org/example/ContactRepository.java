package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;


public class ContactRepository {
    private final String path;
    private final boolean initContacts;
    private final List<Contact> contacts;

    public ContactRepository(EnvProgram envProgram) {
        this.path = envProgram.getPath();
        this.initContacts = envProgram.getInitContacts();
        this.contacts = new ArrayList<>();
        loadContactsFromFile();
    }

    public void addContact(Contact contact) {
        contacts.add(contact);
    }

    public void deleteContactByEmail(String email) {
        contacts.removeIf(contact -> contact.email().equals(email));
    }

    public List<Contact> getAllContacts() {
        return contacts;
    }

    public void saveContactsToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(this.path + "contacts.txt"))) {
            for (Contact contact : contacts) {
                writer.println(contact.fullName() + ";" + contact.phoneNumber() + ";" + contact.email());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadContactsFromFile() {
        if (this.initContacts) {
            System.out.println("Contacts is load.");
            try (Scanner scanner = new Scanner(new File(this.path + "contacts.txt"))) {
                while (scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    String[] parts = line.split(";");
                    if (parts.length == 3) {
                        contacts.add(new Contact(parts[0], parts[1], parts[2]));
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Exception: " + e.getMessage());
            }
        }
    }
}
