/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Customer extends User {
    // Attributes specific to the Customer class
    private final String phoneNumber; // Phone number of the customer
    private final String icNumber; // Identification number of the customer
    private final String driNumber; // Driver's license number of the customer

    // Constructor
    public Customer(String name, String email, String phoneNumber, String icNumber,
                    String driNumber, String password, String role) {
        super(email, name, password, role); // Call the constructor of the superclass (User)
        this.phoneNumber = phoneNumber;
        this.icNumber = icNumber;
        this.driNumber = driNumber;
    }
    
    //construct overloading
    public Customer(String email) {
        super(email, "", "","");
        this.phoneNumber = null;
        this.icNumber = null;
        this.driNumber = null;
    }

    // Method to create an account for the customer
    public boolean createAccount() {
        // Validate input fields
        if (name.isEmpty() || phoneNumber.isEmpty() || icNumber.isEmpty()
                || driNumber.isEmpty()) {
            System.out.println("Please fill in all the fields.");
            return false;
        }

        // Collect data
        String account = name + "," + email + "," + phoneNumber + "," + icNumber + "," + driNumber + ","
                + password;

        try (BufferedWriter writer = new BufferedWriter(new FileWriter("account.txt", true))) {
            writer.write(account);
            writer.newLine();
            JOptionPane.showMessageDialog(null, "Your account has been created", "Success", JOptionPane.INFORMATION_MESSAGE);

            return true;
        } catch (IOException e) {
            System.out.println("An error occurred while writing to the file.");
            return false; // Account creation failed
        }
    }
    
    public static String[] getCusInfo(String email) {
        //retireve info from account.txt
        try (BufferedReader reader = new BufferedReader(new FileReader("account.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 4) { 
                    String fileMail = parts[1].trim();
                    if (fileMail.equals(email)) { 
                        return parts;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //if file not found
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage());    
        } catch (Exception e) {
            //unexpected errors
            JOptionPane.showMessageDialog(null, "Error reading from file: " + e.getMessage());
        }
        return null;
    }
}
