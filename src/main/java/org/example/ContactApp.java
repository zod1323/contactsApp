package org.example;

import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class ContactApp {
    private final ContactService contactService;

    public ContactApp(ContactService contactService) {
        this.contactService = contactService;
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("Choose an action:");
            System.out.println("1. Print all contacts");
            System.out.println("2. Add new contact");
            System.out.println("3. Delete contact by email");
            System.out.println("4. Save contacts to file");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> contactService.printAllContacts();
                case 2 -> {
                    System.out.println("Enter new contact details (FullName;PhoneNumber;Email:");
                    String input = scanner.nextLine();
                    contactService.addNewContact(input);
                }
                case 3 -> {
                    System.out.println("Enter email of the contact to delete:");
                    String email = scanner.nextLine();
                    contactService.deleteContactByEmail(email);
                }
                case 4 -> contactService.saveContactsToFile();
                case 5 -> {
                    System.out.println("Exiting...");
                    return;
                }
                default -> System.out.println("Invalid choice. Please enter a number between 1 and 5.");
            }
        }
    }
}
