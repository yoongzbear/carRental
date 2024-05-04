/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SubangsCarRental;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class AdminBookingConfirm extends javax.swing.JFrame {

    private String email;
    private String carID;
    private int seatNum;
    private double price;
    private double totalRent;
    private String rentDate;
    private String returnDate;
    private String carType;
    private String carModel;
    private String features;
    private String carColor;
    private String gearbox;
    private String index; //booking id
    /**
     * Creates new form AdminBookingConfirm
     */
    public AdminBookingConfirm() {
        initComponents();
        loadTableData(bookingTable);
        disableTF();
    }

    private void disableTF() {
        emailTF.setEditable(false);
        plateTF.setEditable(false);
        carModelTF.setEditable(false);
        typeTF.setEditable(false);
        priceTF.setEditable(false);
        rentDateTF.setEditable(false);
        returnDateTF.setEditable(false);
        rentalFeeTF.setEditable(false);
        colorTF.setEditable(false);
        numSeatsTF.setEditable(false);
        featureTA.setEditable(false);
        gearboxTF.setEditable(false);
    }
    
    private void getDetail() {
        //retireve detail from booking.txt and car info txt        
        Map<String, String[]> carInfoMap = new HashMap<>();

        //get car info
        try (BufferedReader reader = new BufferedReader(new FileReader("car_info.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 7) {
                    StringBuilder features = new StringBuilder();
                    for (int i = 7; i < parts.length; i++) {
                        features.append(parts[i].trim());
                        if (i < parts.length - 1) {
                            features.append(", ");  // Append comma for all but the last feature
                        }
                    }
                    
                    String[] carDetails = new String[] {parts[1].trim(), parts[2].trim(), parts[3].trim(), parts[4].trim(), parts[5].trim(), parts[6].trim(), features.toString()};
                    carInfoMap.put(parts[0].trim(), carDetails);  // Store carID as key, and details array as value
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading car info file: " + e.getMessage());
        }
        
        //get booking info
        try (BufferedReader reader = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length > 7) { 
                    String fileIndex = parts[0].trim();
                    if (fileIndex.equals(this.index)) { 
                        this.email = parts[1].trim();
                        this.carID = parts[2].trim();
                        this.totalRent = Double.parseDouble(parts[5].trim());
                        this.rentDate = parts[6].trim();  
                        this.returnDate = parts[7].trim();  
                        break; 
                    }
                }
            }
        } catch (FileNotFoundException e) {
            //if file not found
            JOptionPane.showMessageDialog(null, "File not found: " + e.getMessage());    
        } catch (Exception e) {
            //unexpected errors
            JOptionPane.showMessageDialog(null, "Error reading from file: " + e.getMessage());
        }
        //set car info
        String[] carDetails = carInfoMap.getOrDefault(this.carID, new String[]{"Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "No features available"});
        this.carModel = carDetails[0];  // Car model
        this.carType = carDetails[1];  // Car type
        this.seatNum = Integer.parseInt(carDetails[2]);
        this.carColor = carDetails[3];
        this.gearbox = carDetails[4];
        this.price = Double.parseDouble(carDetails[5]);
        this.features = carDetails[6];  // Combined features string

    }
    
    private void printDetail() {
        getDetail();
        emailTF.setText(this.email);
        plateTF.setText(this.carID);
        carModelTF.setText(this.carModel);
        typeTF.setText(this.carType);
        priceTF.setText(Double.toString(this.price));
        rentDateTF.setText(this.rentDate);
        returnDateTF.setText(this.returnDate);
        rentalFeeTF.setText(Double.toString(this.totalRent));
        colorTF.setText(this.carColor);
        numSeatsTF.setText(Integer.toString(this.seatNum));
        featureTA.setText(this.features);
        gearboxTF.setText(this.gearbox);
    }
    
    private void approveBooking(String index) {
        boolean updated = false;
        //string build to rewrite status
        StringBuilder updatedContent = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                String bookingID = parts[0].trim();
                if (bookingID.equals(this.index)) {
                    //update the status at this line
                    //4,alya@gmail.com,mow 789,sedans,5,150.0,04/05/2024,10/12/2024,Paid
                    String updatedLine = bookingID + "," + parts[1].trim() + "," + parts[2].trim() + "," + parts[3].trim() + "," + parts[4].trim() + "," + parts[5].trim() + "," + parts[6].trim() + "," + parts[7].trim() + "," + "Approved";
                    updatedContent.append(updatedLine).append("\n");
                    updated = true;
                } else {
                    updatedContent.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to update the file: " + e.getMessage());
        }
        // Write updated content back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cus_book_car.txt"))) {
            writer.write(updatedContent.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to write to the file: " + e.getMessage());
        }
        if (updated) {
            JOptionPane.showMessageDialog(null, "Booking successfully approved!");
            dispose();
            AdminBookingConfirm newParent = new AdminBookingConfirm();
            newParent.setVisible(true);
        }
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
        menuButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingTable = new javax.swing.JTable();
        viewButton = new javax.swing.JButton();
        jLabel13 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        gearboxTF = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        approveButton = new javax.swing.JButton();
        typeTF = new javax.swing.JTextField();
        plateTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        carModelTF = new javax.swing.JTextField();
        rentDateTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        returnDateTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        rentalFeeTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        featureTA = new javax.swing.JTextArea();
        jLabel10 = new javax.swing.JLabel();
        colorTF = new javax.swing.JTextField();
        jLabel15 = new javax.swing.JLabel();
        numSeatsTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 51, 51));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("Customer Bookings Confirmation");

        menuButton.setText("Menu");
        menuButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                menuButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(menuButton)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(120, 120, 120))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(menuButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        bookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Customer Email", "Car Model", "Total Fee (RM)", "Rent Date", "Return Date"
            }
        ));
        jScrollPane1.setViewportView(bookingTable);
        if (bookingTable.getColumnModel().getColumnCount() > 0) {
            bookingTable.getColumnModel().getColumn(0).setResizable(false);
            bookingTable.getColumnModel().getColumn(0).setPreferredWidth(3);
            bookingTable.getColumnModel().getColumn(5).setResizable(false);
        }

        viewButton.setText("View Booking");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Gearbox:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Booking Detail");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Car Plate:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Car Type:");

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Price per day:");

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Rent Date:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Number of Seats:");

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Total Rental Fee:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Return Date:");

        approveButton.setText("Approve Booking");
        approveButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                approveButtonActionPerformed(evt);
            }
        });

        rentDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentDateTFActionPerformed(evt);
            }
        });

        jLabel16.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel16.setText("Customer Email:");

        returnDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnDateTFActionPerformed(evt);
            }
        });

        emailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFActionPerformed(evt);
            }
        });

        featureTA.setColumns(20);
        featureTA.setRows(5);
        jScrollPane2.setViewportView(featureTA);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Car Detail");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Features:");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setText("Car Color:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Car Model:");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(approveButton)
                        .addGap(18, 18, 18)
                        .addComponent(backButton))
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8)
                                .addComponent(jLabel3)
                                .addComponent(jLabel9)
                                .addComponent(jLabel6)
                                .addComponent(jLabel16))
                            .addGap(18, 18, 18)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(rentalFeeTF, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(returnDateTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addComponent(rentDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGap(110, 110, 110)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel10)
                                .addGroup(layout.createSequentialGroup()
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(jLabel12)
                                                    .addComponent(jLabel13)
                                                    .addComponent(jLabel14)
                                                    .addComponent(jLabel2)
                                                    .addGroup(layout.createSequentialGroup()
                                                        .addGap(1, 1, 1)
                                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                            .addComponent(jLabel4)
                                                            .addComponent(jLabel5))))
                                                .addGap(48, 48, 48))
                                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jLabel7)
                                                .addGap(18, 18, 18)))
                                        .addGroup(layout.createSequentialGroup()
                                            .addComponent(jLabel15)
                                            .addGap(82, 82, 82)))
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(gearboxTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(plateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(carModelTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(typeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(numSeatsTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(colorTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 726, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(viewButton))))
                .addContainerGap(24, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 122, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(viewButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel16)
                            .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(10, 10, 10)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel6)
                            .addComponent(rentDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel9)
                            .addComponent(returnDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel8)
                            .addComponent(rentalFeeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel4)
                                    .addComponent(plateTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel5)
                                    .addComponent(typeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(20, 20, 20)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel7)
                                    .addComponent(numSeatsTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel12)
                                    .addComponent(colorTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel13)
                                    .addComponent(gearboxTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(jLabel14)
                                    .addComponent(priceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addComponent(carModelTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(approveButton)
                    .addComponent(backButton))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        new AdminMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_menuButtonActionPerformed

    private void viewButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_viewButtonActionPerformed
        //get row index to check if row is selected
        int selectedRow = bookingTable.getSelectedRow();
        
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();
            
            //call view booking detail   
            this.index = (String) bookingTable.getModel().getValueAt(selectedRow, 0);
            printDetail();                   
        } else {
            // No row is selected
            JOptionPane.showMessageDialog(null, "Please select a row to view details.");
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    private void approveButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_approveButtonActionPerformed
        //get row index to check if row is selected
        int selectedRow = bookingTable.getSelectedRow();
        
        if (selectedRow >= 0) {
            DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();
            
            //call view booking detail   
            this.index = (String) bookingTable.getModel().getValueAt(selectedRow, 0);
            approveBooking(this.index);                
        } else {
            // No row is selected
            JOptionPane.showMessageDialog(null, "Please select a row to view details.");
        }
    }//GEN-LAST:event_approveButtonActionPerformed

    private void rentDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rentDateTFActionPerformed

    private void returnDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnDateTFActionPerformed

    private void emailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTFActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        dispose();
    }//GEN-LAST:event_backButtonActionPerformed

    public void loadTableData(javax.swing.JTable table) {        
        DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();        

        // Map to store car plate as key and model as value
        Map<String, String> carModels = new HashMap<>();

        // Load car info
        try (BufferedReader brCarInfo = new BufferedReader(new FileReader("car_info.txt"))) {
            String lineCar;
            while ((lineCar = brCarInfo.readLine()) != null) {
                String[] carData = lineCar.split(",");
                if (carData.length >= 8) {
                    carModels.put(carData[0].trim(), carData[1].trim());
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading car info file: " + e.getMessage());
        }

        // Read customer booking data and display table - display with status booked only
        try (BufferedReader br = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 8) {
                    if (data[8].equals("Booked")) {
                        String carPlate = data[2].trim();
                        String carModel = carModels.getOrDefault(carPlate, "Unknown");
                        //add row into table
                        model.addRow(new Object[]{                            
                            data[0],        //booking ID 
                            data[1],        //customer email
                            carModel,         // Car model
                            Double.parseDouble(data[5]), // Total Price
                            data[6],         // Use Date
                            data[7],         // Return Date
                            data[8]          // Status
                        });
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading customer booking file: " + e.getMessage());
        }

        // Set the model to the table
        table.setModel(model);
        table.revalidate();  // Refresh the table to display new data             
    }
    
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
            java.util.logging.Logger.getLogger(AdminBookingConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminBookingConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminBookingConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminBookingConfirm.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminBookingConfirm().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton approveButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTable bookingTable;
    private javax.swing.JTextField carModelTF;
    private javax.swing.JTextField colorTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextArea featureTA;
    private javax.swing.JTextField gearboxTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton menuButton;
    private javax.swing.JTextField numSeatsTF;
    private javax.swing.JTextField plateTF;
    private javax.swing.JTextField priceTF;
    private javax.swing.JTextField rentDateTF;
    private javax.swing.JTextField rentalFeeTF;
    private javax.swing.JTextField returnDateTF;
    private javax.swing.JTextField typeTF;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
