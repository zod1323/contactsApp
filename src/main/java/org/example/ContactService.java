package org.example;

import org.springframework.beans.factory.annotation.Value;

import java.util.List;
import java.util.regex.Pattern;

public class ContactService {
    private final ContactRepository contactRepository;
    @Value("${app.email.regexp}")
    private String regexEmail;
    @Value("${app.name.regexp}")
    private String regexName;
    @Value("${app.phone.regexp}")
    private String regexPhone;

    public ContactService(ContactRepository contactRepository) {
        this.contactRepository = contactRepository;
    }

    public void printAllContacts() {
        List<Contact> contacts = contactRepository.getAllContacts();
        for (Contact contact : contacts) {
            System.out.println(contact);
        }
    }

    public void addNewContact(String input) {
        String[] parts = input.split(";");
        if (parts.length == 3) {

            Contact contact = new Contact(parts[0], parts[1], parts[2]);
            if (isValidate(parts)) {
                contactRepository.addContact(contact);
                System.out.println("Contact added in cache successfully.");
            }

        } else {
            System.out.println("Invalid input format. Please use format: FullName;PhoneNumber;Email");
        }
    }

    private boolean isValidate(String[] parts) {
        boolean isNotError = true;
        Pattern patternEmail = Pattern.compile(regexEmail);
        Pattern patternName = Pattern.compile(regexName);
        Pattern patternPhone = Pattern.compile(regexPhone);

        if (!patternEmail.matcher(parts[2]).matches()) {
            System.out.println("Email not correct. Format: [login]@[domain].[zone]");
            isNotError = false;
        }

        if (!patternName.matcher(parts[0]).matches()) {
            System.out.println("FullName not correct. Format: [last name] [first name] [middle name]");
            isNotError = false;
        }
        if (!patternPhone.matcher(parts[1]).matches()) {
            System.out.println("Phone not correct. Format: +7|8|+996[phone]");
            isNotError = false;
        }
        return isNotError;
    }

    public void deleteContactByEmail(String email) {
        contactRepository.deleteContactByEmail(email);
        System.out.println("Contact deleted successfully.");
    }

    public void saveContactsToFile() {
        contactRepository.saveContactsToFile();
        System.out.println("Contacts saved to file.");
    }
}
