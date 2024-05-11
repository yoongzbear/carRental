/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SubangsCarRental;

/**
 *
 * @author User
 */
import java.awt.Color;
import javax.swing.JOptionPane;



public class CusRegister extends javax.swing.JFrame { 
    private final String role;
    /**
     * Creates new form CusRegister
     */
    public CusRegister() {        
        initComponents();
        this.role = SessionManager.getRole();
        addPlaceholderListeners();
    }

    
    
private boolean validateFields() {
    String fullName = Name.getText();
    String emailAddress = Email.getText();
    String phoneNumber = PhoneNum.getText();
    String icNumber = ICnum.getText();
    String driNumber = DriNum.getText();
    String password = Pass.getText();
    String conPassword = ConPass.getText();

    // Check if any field is empty
    if (fullName.isEmpty() || emailAddress.isEmpty() || phoneNumber.isEmpty() || icNumber.isEmpty() ||
            driNumber.isEmpty() || password.isEmpty() || conPassword.isEmpty()||fullName.isEmpty() || fullName.trim().equals("Enter your full name") ||
            emailAddress.isEmpty() || emailAddress.trim().equals("Enter a valid email address") ||
            phoneNumber.isEmpty() || phoneNumber.trim().equals("XXX-XXXXXXX") ||
            icNumber.isEmpty() || icNumber.trim().equals("XXXXXX-XX-XXXX") ||
            driNumber.isEmpty() || driNumber.trim().equals("eg. 0110051 U3OVqjEe") ||
            password.isEmpty() || password.trim().equals("At least 6 character including 1 special symbol/digit") ||
            conPassword.isEmpty() || conPassword.trim().equals("Confirm your password")) {
        JOptionPane.showMessageDialog(null, "Please fill in all the information", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Check email format
    String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    if (!emailAddress.matches(emailPattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.","Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Check phone number format
    String phonePattern = "\\d{3}-\\d{7,8}";
    if (!phoneNumber.matches(phonePattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid phone number in the format XXX-XXXXXXX.","Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Check IC number format
    String icPattern = "\\d{6}-\\d{2}-\\d{4}";
    if (!icNumber.matches(icPattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid IC number in the format XXXXXX-XX-XXXX.","Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Check driving license number format
    String driNumPattern = "\\d{7}\\s[a-zA-Z]{8}";
    if (!driNumber.matches(driNumPattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid driving license number in the format 'XXXXXXXX XXXXXXXX'.","Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

     // Check password format and length
    if (password.length() < 6 && !password.matches(".*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?].*")) {
        JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long and with a special symbol or digit.","Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

    // Check if passwords match
    if (!password.equals(conPassword)) {
        JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
        return false;
    }

// If all conditions pass, the password is valid
return true;
}
// Add focus listeners for placeholder functionality
    private void addPlaceholderListeners() {
        Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (Name.getText().equals("Enter your full name")) {
                    Name.setText("");
                    Name.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (Name.getText().isEmpty()) {
                    Name.setText("Enter your full name");
                    Name.setForeground(new Color(204, 204, 204)); // Change font color to gray
                }
            }
        });

        Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (Email.getText().equals("Enter a valid email address")) {
                    Email.setText("");
                    Email.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (Email.getText().isEmpty()) {
                    Email.setText("Enter a valid email address");
                    Email.setForeground(new Color(204, 204, 204)); // Change font color to gray
                }
            }
        });

        PhoneNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (PhoneNum.getText().equals("XXX-XXXXXXX")) {
                    PhoneNum.setText("");
                    PhoneNum.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (PhoneNum.getText().isEmpty()) {
                    PhoneNum.setText("XXX-XXXXXXX");
                    PhoneNum.setForeground(new Color(204, 204, 204)); // Change font color to gray
                }
            }
        });

        ICnum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (ICnum.getText().equals("XXXXXX-XX-XXXX")) {
                    ICnum.setText("");
                    ICnum.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (ICnum.getText().isEmpty()) {
                    ICnum.setText("XXXXXX-XX-XXXX");
                    ICnum.setForeground(new Color(204, 204, 204)); // Change font color to gray
                }
            }
        });

        DriNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (DriNum.getText().equals("eg. 0110051 U3OVqjEe")) {
                    DriNum.setText("");
                    DriNum.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (DriNum.getText().isEmpty()) {
                    DriNum.setText("eg. 0110051 U3OVqjEe");
                    DriNum.setForeground(new Color(204, 204, 204)); // Change font color to gray
                }
            }
        });

        Pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (Pass.getText().equals("At least 6 character including 1 special symbol/digit")) {
                    Pass.setText("");
                    Pass.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (Pass.getText().isEmpty()) {
                    Pass.setText("At least 6 character including 1 special symbol/digit");
                    Pass.setForeground(new Color(204, 204, 204)); // Change font color to gray
                }
            }
        });

        ConPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (ConPass.getText().equals("Confirm your password")) {
                    ConPass.setText("");
                    ConPass.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (ConPass.getText().isEmpty()) {
                    ConPass.setText("Confirm your password");
                    ConPass.setForeground(new Color(204, 204, 204)); // Change font color to gray
                }
            }
        });
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        Name = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Email = new javax.swing.JTextField();
        PhoneNum = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        ICnum = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        DriNum = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        Pass = new javax.swing.JTextField();
        ConPass = new javax.swing.JTextField();
        createButton = new javax.swing.JButton();
        back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(102, 204, 0));

        jLabel1.setFont(new java.awt.Font("Segoe Print", 1, 24)); // NOI18N
        jLabel1.setText("-- REGISTER ACCOUNT --");

        jLabel9.setFont(new java.awt.Font("Georgia", 1, 36)); // NOI18N
        jLabel9.setText("Subangs Car Rental");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(190, 190, 190)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Full Name: ");

        Name.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Name.setForeground(new java.awt.Color(204, 204, 204));
        Name.setText("Enter your full name");
        Name.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                NameFocusLost(evt);
            }
        });
        Name.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                NameActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setText("Email:");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setText("Phone No:");

        Email.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Email.setForeground(new java.awt.Color(204, 204, 204));
        Email.setText("Enter a valid email address");

        PhoneNum.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        PhoneNum.setForeground(new java.awt.Color(204, 204, 204));
        PhoneNum.setText("XXX-XXXXXXX");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("IC Number:");

        ICnum.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ICnum.setForeground(new java.awt.Color(204, 204, 204));
        ICnum.setText("XXXXXX-XX-XXXX");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Driving License No:");

        DriNum.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        DriNum.setForeground(new java.awt.Color(204, 204, 204));
        DriNum.setText("eg. 0110051 U3OVqjEe");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Password:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("Confirm Password: ");

        Pass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Pass.setForeground(new java.awt.Color(204, 204, 204));
        Pass.setText("At least 6 character including 1 special symbol/digit");

        ConPass.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        ConPass.setForeground(new java.awt.Color(204, 204, 204));
        ConPass.setText("Confirm your password");

        createButton.setBackground(new java.awt.Color(204, 255, 153));
        createButton.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        createButton.setText("Create");
        createButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                createButtonActionPerformed(evt);
            }
        });

        back.setBackground(new java.awt.Color(204, 255, 153));
        back.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        back.setText("Back");
        back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(41, 41, 41)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel8)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2)
                            .addComponent(jLabel4)
                            .addComponent(jLabel5)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(Pass, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DriNum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ICnum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(PhoneNum, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Email, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(Name, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(ConPass))))
                .addContainerGap(46, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(Name, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(Email, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(PhoneNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(ICnum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(DriNum, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(Pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(ConPass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 59, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
     // Validate fields
    if (!validateFields()) {
        return; // Exit method if validation fails
    }

    // Collect data from GUI components
    String fullName = Name.getText();
    String emailAddress = Email.getText();
    String phoneNumber = PhoneNum.getText();
    String icNumber = ICnum.getText();
    String driNumber = DriNum.getText();
    String password = Pass.getText();
    String conPassword = ConPass.getText();
    String Role = SessionManager.getRole();

    // Call the method in the Customer class to create the account
    Customer customer = new Customer(fullName, emailAddress, phoneNumber, icNumber, driNumber, password, Role);
    
    boolean registrationSuccessful = customer.createAccount();
        
    if (registrationSuccessful) {
        // Close the current registration window
        this.dispose();

        // Open the login window
        Login loginWindow = new Login();
        loginWindow.setVisible(true);
    }
    }//GEN-LAST:event_createButtonActionPerformed

    private void NameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_NameActionPerformed

    }//GEN-LAST:event_NameActionPerformed

    private void NameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_NameFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_NameFocusLost

    private void backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backActionPerformed
            Login login = new Login();
            // Set the frame visible
            login.setVisible(true);
            // Close the current frame (AdminMenu frame)
            dispose();
    }//GEN-LAST:event_backActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(CusRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CusRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CusRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CusRegister.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CusRegister().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField ConPass;
    private javax.swing.JTextField DriNum;
    private javax.swing.JTextField Email;
    private javax.swing.JTextField ICnum;
    private javax.swing.JTextField Name;
    private javax.swing.JTextField Pass;
    private javax.swing.JTextField PhoneNum;
    private javax.swing.JButton back;
    private javax.swing.JButton createButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
