/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class booking {
    private String bookingID;
    private String email;
    private String carID;
    private String carType;
    private int seatNum;
    private double rentFee;
    private String rentDate;
    private String returnDate;
    private String status;
    private int rating;
    private String feedback;

    public void setBookingID(String bookingID) {
        this.bookingID = bookingID;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setCarID(String carID) {
        this.carID = carID;
    }

    public void setCarType(String carType) {
        this.carType = carType;
    }

    public void setSeatNum(int seatNum) {
        this.seatNum = seatNum;
    }

    public void setRentFee(double rentFee) {
        this.rentFee = rentFee;
    }

    public void setRentDate(String rentDate) {
        this.rentDate = rentDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public void setRating(int rating) {
        this.rating = rating;
    }
    
    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    public String getBookingID() {
        return bookingID;
    }

    public String getEmail() {
        return email;
    }

    public String getCarID() {
        return carID;
    }

    public String getCarType() {
        return carType;
    }

    public int getSeatNum() {
        return seatNum;
    }

    public double getRentFee() {
        return rentFee;
    }

    public String getRentDate() {
        return rentDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public String getStatus() {
        return status;
    }
    
    public int getRating() {
        return rating;
    }
    public String getFeedback() {
        return feedback;
    }
     
    public static String[] getBookingInfo(String index) {
        try (BufferedReader reader = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 7 && parts[0].trim().equals(index)) {
                    return parts;
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading booking file: " + e.getMessage());
        }
        return null;  // Return null if no booking is found
    }
    
       // Method to calculate total fee
    public static double calculateTotalFee(LocalDate useDate, LocalDate returnDate, double pricePerDay) {
        // Calculate the number of days between use date and return date
        long numberOfDays = ChronoUnit.DAYS.between(useDate, returnDate);
        // Calculate total fee
        double totalFee = pricePerDay * numberOfDays;
        return totalFee;
    }
    
    public static void updateMissingBooking() {
        //update booking status to missing when customer did not come to pay on the date
        LocalDate currentDate = LocalDate.now(); //current date
        //string build to rewrite status
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                LocalDate rentDate = LocalDate.parse(parts[6].trim(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
                String status = parts[8].trim();
                if (ChronoUnit.DAYS.between(currentDate, rentDate) <= -1 && (status.equals("Booked")|| status.equals("Approved"))) {
                    //update the status at this line
                    String updatedLine = parts[0].trim() + "," + parts[1].trim() + "," + parts[2].trim() + "," + parts[3].trim() + "," + parts[4].trim() + "," + parts[5].trim() + "," + parts[6].trim() + "," + parts[7].trim() + "," + "Missing";
                    updatedContent.append(updatedLine).append("\n");
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
    }
}
