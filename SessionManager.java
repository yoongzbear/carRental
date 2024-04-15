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
    private String email;
    private String name;
    private String role;
    
    private SessionManager() {
        //idk what this is for, apparently its to prevent instantiation
    }
    
    public static SessionManager getInstance() {
        if (instance == null) {
            instance = new SessionManager(); //idk what is this            
        } return instance;
    }
    
    public void setUser(String email, String role, String name) {
        this.email = email;
        this.role = role;
        this.name = name;
    }
    
    public String getEmail() {
        return email;
    }
    
    public String getRole() {
        return role;
    }
    
    public String getName() {
        return name;
    }
    
    public void clearSession() {
        email = null;
        role = null;
        name = null;
    }
}


/*how to use session
public class SomeOtherClass {
    public void doSomething() {
        SessionManager session = SessionManager.getInstance();
        String email = session.getEmail();
        String role = session.getRole();
        String name = session.getName();
    }
}
*/