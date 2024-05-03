/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class User {
    protected String email;
    protected String name;
    protected String password;
    protected String role;
    private Login loginInstance;

    public User(String email, String name, String password, String role) {
        this.email = email;
        this.name = name;
        this.password = password;        
        this.role = role;
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
    
    public String getRole() {
        return role;
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
    
    public void setRole(String role) {
        this.role = role;
    }
    
    public boolean loginProcess() {
        String username = null;
        String mail = null;
        String pw = null;
        
        String filePath = null; //check which file to read from 

        if (role.equals("Customer")) {
            filePath = "account.txt"; //path of the file
        } else if (role.equals("Admin")) {
            filePath = "admin.txt";
        } 

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");

                if (role.equals("Customer")) {
                    if (parts.length < 6) {
                        continue;
                    }
                    username = parts[0].trim();
                    mail = parts[1].trim();
                    pw = parts[5].trim();
                } else if (role.equals("Admin")) {
                    if (parts.length < 3) {
                        continue;
                    }
                    username = parts[0].trim();
                    mail = parts[1].trim();
                    pw = parts[2].trim();
                }   
                if (email.equals(mail) && password.equals(pw)) {
                    SessionManager.setUser(mail, role, username);
                    break;
                }
            }
            
        } catch (FileNotFoundException e) {
            // Error if file not found
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage());    
        } catch (Exception e) {
            // Other unexpected errors
            JOptionPane.showMessageDialog(null, "Error reading from file: " + e.getMessage());
        }
        return email.equals(mail) && password.equals(pw);
    }
}
