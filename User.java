/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

/**
 *
 * @author User
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

public class User {
    protected String email;
    protected String name;
    protected String password;

    public User(String email, String name, String password) {
        this.email = email;
        this.name = name;
        this.password = password;
    }

    // Getters
    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    // Setters
    public void setEmail(String email) {
        this.email = email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

class Customer extends User {
    // Attributes specific to the Customer class
    private final String phoneNumber; // Phone number of the customer
    private final String icNumber; // Identification number of the customer
    private final String driNumber; // Driver's license number of the customer

    // Constructor
    public Customer(String name, String email, String phoneNumber, String icNumber,
                    String driNumber, String password) {
        super(email, name, password); // Call the constructor of the superclass (User)
        this.phoneNumber = phoneNumber;
        this.icNumber = icNumber;
        this.driNumber = driNumber;
    }

    // Method to create an account for the customer
    public void createAccount() {
        // Validate input fields
        if (name.isEmpty() || phoneNumber.isEmpty() || icNumber.isEmpty()
                || driNumber.isEmpty()) {
            System.out.println("Please fill in all the fields.");
            return;
        }

        // Collect data
        String account = name + "," + email + "," + phoneNumber + "," + icNumber + "," + driNumber + ","
                + password;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("account.txt", true))) {
            writer.write(account);
            writer.newLine();
            System.out.println("Your account has been created");
            JOptionPane.showMessageDialog(null, "Your account has been created", "Success", JOptionPane.INFORMATION_MESSAGE);
            return;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            e.printStackTrace();
        }
    }
}
