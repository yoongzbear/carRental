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

        Email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateEmail();
            }
        });
        PhoneNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validatePhoneNum();
            }
        });
        ICnum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateICnum();
            }
        });
        DriNum.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateDriNum();
            }
        });
        Pass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validatePass();
            }
        });
        ConPass.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                validateConPass();
            }
        });
        
        addPlaceholderListeners();
    }

    
    
    
// Validation methods
private void validateEmail() {
    String emailPattern = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";
    String email = Email.getText().trim(); // Remove leading and trailing whitespace
    if (!email.isEmpty() && !email.matches(emailPattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid email address.","Error", JOptionPane.ERROR_MESSAGE);
        Email.requestFocusInWindow();// Prompt user back to the field
    }
}

private void validatePhoneNum() {
    String phonePattern = "\\d{3}-\\d{7,8}";
    String phone = PhoneNum.getText().trim(); // Remove leading and trailing whitespace
    if (!phone.isEmpty() && !phone.matches(phonePattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid phone number in the format XXX-XXXXXXX(X).","Error", JOptionPane.ERROR_MESSAGE);
        PhoneNum.requestFocusInWindow();// Prompt user back to the field
    }
}

private void validateICnum() {
    String icPattern = "\\d{6}-\\d{2}-\\d{4}";
    String icNum = ICnum.getText().trim(); // Remove leading and trailing whitespace
    if (!icNum.isEmpty() && !icNum.matches(icPattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid IC number in the format XXXXXX-XX-XXXX.","Error", JOptionPane.ERROR_MESSAGE);
        ICnum.requestFocusInWindow();// Prompt user back to the field
    }
}

private void validateDriNum() {
    String driNum = DriNum.getText().trim(); // Remove leading and trailing whitespace
    // Define the pattern for the driving license number
    String driNumPattern = "\\d{7}\\s\\w{8}";
    // Check if the input matches the pattern
    if (!driNum.isEmpty() && !driNum.matches(driNumPattern)) {
        JOptionPane.showMessageDialog(this, "Please enter a valid driving license number in the format 'XXXXXXXX XXXXXXXX'.","Error", JOptionPane.ERROR_MESSAGE);
        DriNum.requestFocusInWindow();// Prompt user back to the field
    }
}


private void validatePass() {
    String password = Pass.getText().trim(); // Remove leading and trailing whitespace
    // Check if password length is at least 6 characters
    if (!password.isEmpty() && password.length() < 6) {
        JOptionPane.showMessageDialog(this, "Password must be at least 6 characters long.","Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Check if password contains at least one special symbol
    if (!password.isEmpty() && !password.matches(".*[!@#$%^&*()-_=+\\\\|\\[{\\]};:'\",<.>/?].*")) {
        JOptionPane.showMessageDialog(this, "Password must contain at least one special symbol.","Error", JOptionPane.ERROR_MESSAGE);
    }
}

private void validateConPass() {
    String password = Pass.getText().trim();
    String confirmPassword = ConPass.getText().trim();
    if (!password.isEmpty() && !confirmPassword.isEmpty() && !password.equals(confirmPassword)) {
        JOptionPane.showMessageDialog(this, "Passwords do not match.","Error", JOptionPane.ERROR_MESSAGE);
    }
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
                if (Pass.getText().equals("At least 6 character including 1 special symbol")) {
                    Pass.setText("");
                    Pass.setForeground(Color.BLACK); // Change font color to black
                }
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (Pass.getText().isEmpty()) {
                    Pass.setText("At least 6 character including 1 special symbol");
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
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel9)
                    .addComponent(jLabel1))
                .addGap(151, 151, 151))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(23, 23, 23)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(18, Short.MAX_VALUE))
        );

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setText("Full Name: ");

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
        jLabel4.setText("Phone Number:");

        Email.setForeground(new java.awt.Color(204, 204, 204));
        Email.setText("Enter a valid email address");
        Email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                EmailActionPerformed(evt);
            }
        });

        PhoneNum.setForeground(new java.awt.Color(204, 204, 204));
        PhoneNum.setText("XXX-XXXXXXX");
        PhoneNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PhoneNumActionPerformed(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setText("IC number:");

        ICnum.setForeground(new java.awt.Color(204, 204, 204));
        ICnum.setText("XXXXXX-XX-XXXX");
        ICnum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ICnumActionPerformed(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setText("Driving lisence num:");

        DriNum.setForeground(new java.awt.Color(204, 204, 204));
        DriNum.setText("eg. 0110051 U3OVqjEe");
        DriNum.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DriNumActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel7.setText("Password:");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel8.setText("Confirm Password: ");

        Pass.setForeground(new java.awt.Color(204, 204, 204));
        Pass.setText("At least 6 character including 1 special symbol");
        Pass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                PassActionPerformed(evt);
            }
        });

        ConPass.setForeground(new java.awt.Color(204, 204, 204));
        ConPass.setText("Confirm your password");
        ConPass.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ConPassActionPerformed(evt);
            }
        });

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
                            .addComponent(ConPass, javax.swing.GroupLayout.PREFERRED_SIZE, 397, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(26, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(createButton, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(back, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(21, 21, 21))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void EmailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_EmailActionPerformed

    }//GEN-LAST:event_EmailActionPerformed

    private void PhoneNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PhoneNumActionPerformed

    }//GEN-LAST:event_PhoneNumActionPerformed

    private void ICnumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ICnumActionPerformed

    }//GEN-LAST:event_ICnumActionPerformed

    private void PassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_PassActionPerformed

    }//GEN-LAST:event_PassActionPerformed

    private void createButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_createButtonActionPerformed
 // Collect data from GUI components
    String fullName = Name.getText();
    String emailAddress = Email.getText();
    String phoneNumber = PhoneNum.getText();
    String icNumber = ICnum.getText();
    String driNumber = DriNum.getText();
    String password = Pass.getText();
    String conPassword = ConPass.getText();
    String Role = SessionManager.getRole();
    // Check if any of the fields contain the placeholder text
    if (fullName.equals("Enter your full name") || emailAddress.equals("Enter a valid email address") ||
        phoneNumber.equals("XXX-XXXXXXX") || icNumber.equals("XXXXXX-XX-XXXX") ||
        driNumber.equals("eg. 0110051 U3OVqjEe") || password.equals("At least 6 character including 1 special symbol") ||
        conPassword.equals("Confirm your password")) {
        JOptionPane.showMessageDialog(null, "Please fill in all the information", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
        // Check if passwords match
    if (!password.equals(conPassword)) {
        JOptionPane.showMessageDialog(null, "Passwords do not match.", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }
    // Check if all information is entered
    if (fullName.isEmpty() || emailAddress.isEmpty() || phoneNumber.isEmpty() || icNumber.isEmpty() || driNumber.isEmpty() ||
            password.isEmpty() || conPassword.isEmpty()) {
        JOptionPane.showMessageDialog(null, "Please fill in all the information", "Error", JOptionPane.ERROR_MESSAGE);
        return;
    }

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

    private void ConPassActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ConPassActionPerformed

    }//GEN-LAST:event_ConPassActionPerformed

    private void DriNumActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DriNumActionPerformed

    }//GEN-LAST:event_DriNumActionPerformed

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
