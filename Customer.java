/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

/**
 *
 * @author User
 */

//encapsulate customer information
public class Customer extends User {
    private String phoneNum;
    private String ICNum;
    private String licenseNum;

    public Customer(String email, String name, String password, String phoneNum, String ICNum, String licenseNum) {
        super(email, name, password); // Call the superclass (User) constructor
        this.phoneNum = phoneNum;
        this.ICNum = ICNum;
        this.licenseNum = licenseNum;
    }

    // Getters
    public String getPhoneNum() {
        return phoneNum;
    }

    public String getICNum() {
        return ICNum;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    // Setters
    public void setPhoneNum(String phoneNum) {
        this.phoneNum = phoneNum;
    }

    public void setICNum(String ICNum) {
        this.ICNum = ICNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }
    
}
