/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package carrental;

/**
 *
 * @author User
 */

//setting session for username
public class SessionManager {
    private static SessionManager instance;
    private String username;
    private String role;
    
    private SessionManager() {
        //idk what this is for, apparently its to prevent instantiation
    }
    
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager(); //idk what is this            
        } return instance;
    }
    
    public void setUser(String username, String role) {
        this.username = username;
        this.role = role;
    }
    
    public String getUsername() {
        return username;
    }
    
    public String getRole() {
        return role;
    }
    
    public void clearSession() {
        username = null;
        role = null;
    }
}


/*how to use session
public class SomeOtherClass {
    public void doSomething() {
        SessionManager session = SessionManager.getInstance();
        String username = session.getUsername();
        String role = session.getRole();

        System.out.println("Current User: " + username + " with role: " + role);
        // Add more logic based on user role or username
    }
}
*/
