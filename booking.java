/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

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

}
