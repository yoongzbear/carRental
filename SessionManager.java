/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

/**
 *
 * @author User
 */

//utility class to set session
public class SessionManager {
    private static String email;
    private static String name;
    private static String role;
    
    private SessionManager() {
        //private constructor to prevent instantiation
    }
    
    //set the session after logging in
    public static void setUser(String email, String role, String name) {
        SessionManager.email = email;
        SessionManager.role = role;
        SessionManager.name = name; 
    }
    
    public static String getEmail() {
        return email;
    }

    public static String getRole() {
        return role;
    }

    public static String getName() {
        return name;
    }

    //clear session when logging out
    public static void clearSession() {
        email = null;
        role = null;
        name = null;
    }
}

/*
how to use:
public class SomeOtherClass {
    public void doSomething() {
        // Setting user session data
        Session.setUser("user@example.com", "admin", "John Doe");

        // Accessing session data
        String email = Session.getEmail();
        String role = Session.getRole();
        String name = Session.getName();

        // Displaying session data
        System.out.println("Logged in user: " + name + " (" + email + ", " + role + ")");
        
        // Clearing session data
        Session.clearSession();
    }
}
*/