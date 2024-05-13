/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Admin extends User{

    public Admin(String email, String name, String password, String role) {
        super(email, name, password, role);
    }
    //idk
    
    public Admin() {
        super("", "", "", "");
    }
    
    //admin confirm booking
    public void approveBooking(String index) {
        boolean updated = false;
        //string builder to rewrite status
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String bookingID = parts[0].trim();
                if (bookingID.equals(index)) {
                    //update the status at this line
                    String updatedLine = bookingID + "," + parts[1].trim() + "," + parts[2].trim() + "," + parts[3].trim() + "," + parts[4].trim() + "," + parts[5].trim() + "," + parts[6].trim() + "," + parts[7].trim() + "," + "Approved";
                    updatedContent.append(updatedLine).append("\n");
                    updated = true;
                } else {
                    updatedContent.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to update the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Write updated content back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cus_book_car.txt"))) {
            writer.write(updatedContent.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to write to the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (updated) {
            JOptionPane.showMessageDialog(null, "Booking successfully approved!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    
    //admin receive returned car
    public void returnCar(String index) {
        boolean updated = false;
        //string build to rewrite status
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String bookingID = parts[0].trim();
                if (bookingID.equals(index)) {
                    //update the status at this line
                    String updatedLine = bookingID + "," + parts[1].trim() + "," + parts[2].trim() + "," + parts[3].trim() + "," + parts[4].trim() + "," + parts[5].trim() + "," + parts[6].trim() + "," + parts[7].trim() + "," + "Returned";
                    updatedContent.append(updatedLine).append("\n");
                    updated = true;
                } else {
                    updatedContent.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to update the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //write updated content back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cus_book_car.txt"))) {
            writer.write(updatedContent.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to write to the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (updated) {
            JOptionPane.showMessageDialog(null, "Car successfully returned!", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }        
}
