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

/**
 *
 * @author User
 */
public class payment extends javax.swing.JFrame {

    private String index;
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
    private double amountPaid;
    
    /**
     * Creates new form payment
     */
    public payment(String index) {
        this.index = index;
        initComponents();
        printDetail();
        disableTF();
        receiptButton.setEnabled(false);
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
        // Load all car info into a map
        Map<String, String[]> carInfoMap = Car.loadCarInfo();
        
        // Retrieve booking info using index
        String[] bookingInfo = booking.getBookingInfo(this.index);
        
        if (bookingInfo != null) {
            this.email = bookingInfo[1].trim();
            this.carID = bookingInfo[2].trim();
            this.totalRent = Double.parseDouble(bookingInfo[5].trim());
            this.rentDate = bookingInfo[6].trim();
            this.returnDate = bookingInfo[7].trim();

            // Retrieve and set car details from carInfoMap
            String[] carDetails = Car.getCarDetails(this.carID, carInfoMap);
            this.carModel = carDetails[0];
            this.carType = carDetails[1];
            this.seatNum = Integer.parseInt(carDetails[2]);
            this.carColor = carDetails[3];
            this.gearbox = carDetails[4];
            this.price = Double.parseDouble(carDetails[5]);
            this.features = carDetails[6];
        } else {
            JOptionPane.showMessageDialog(null, "No booking found with Booking ID: " + index, "Alert", JOptionPane.WARNING_MESSAGE);
        }
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
    
    private void calculateBalance() {
        if (paymentTF.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Please fill in the amount paid.", "Alert", JOptionPane.WARNING_MESSAGE);
            paymentTF.requestFocusInWindow();
        } else {
            this.amountPaid = Double.parseDouble(paymentTF.getText());
            double balance = this.amountPaid - this.totalRent;
            if (balance < 0) {
                JOptionPane.showMessageDialog(null, "The amount paid is lesser than the total rental fee.", "Alert", JOptionPane.WARNING_MESSAGE);
                paymentTF.requestFocusInWindow();
            } else {
                balanceTF.setText(Double.toString(balance));
                receiptButton.setEnabled(true);
            }
        }
    }
    
    private void updateStatus() {
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
                    String updatedLine = bookingID + "," + parts[1].trim() + "," + parts[2].trim() + "," + parts[3].trim() + "," + parts[4].trim() + "," + parts[5].trim() + "," + parts[6].trim() + "," + parts[7].trim() + "," + "Paid";
                    updatedContent.append(updatedLine).append("\n");
                    updated = true;
                } else {
                    updatedContent.append(line).append("\n");
                }
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to update the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        // Write updated content back to file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("cus_book_car.txt"))) {
            writer.write(updatedContent.toString());
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Failed to write to the file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        if (updated) {
            JOptionPane.showMessageDialog(null, "Booking successfully paid!");
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
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        calculateButton = new javax.swing.JButton();
        plateTF = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        carModelTF = new javax.swing.JTextField();
        jScrollPane2 = new javax.swing.JScrollPane();
        featureTA = new javax.swing.JTextArea();
        typeTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        colorTF = new javax.swing.JTextField();
        numSeatsTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        gearboxTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        rentDateTF = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        returnDateTF = new javax.swing.JTextField();
        emailTF = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        paymentTF = new javax.swing.JTextField();
        rentalFeeTF = new javax.swing.JTextField();
        balanceTF = new javax.swing.JTextField();
        receiptButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(51, 255, 0));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("Rental Payment");

        menuButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
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
                .addComponent(menuButton)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(246, 246, 246)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(12, 12, 12)
                .addComponent(menuButton)
                .addGap(5, 5, 5)
                .addComponent(jLabel1)
                .addContainerGap(23, Short.MAX_VALUE))
        );

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Price per day:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Car Type:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Features:");

        calculateButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        calculateButton.setText("Calculate Payment");
        calculateButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                calculateButtonActionPerformed(evt);
            }
        });

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Number of Seats:");

        featureTA.setColumns(20);
        featureTA.setRows(5);
        jScrollPane2.setViewportView(featureTA);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Car Detail");

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setText("Car Color:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Car Model:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Booking Detail");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Gearbox:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Car Plate:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Return Date:");

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

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Rent Date:");

        jLabel17.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel17.setText("Payment");

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("Total Rent Fee:");

        jLabel21.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel21.setText("Balance:");

        jLabel24.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel24.setText("Amount Paid:");

        receiptButton.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        receiptButton.setText("Payment Receipt");
        receiptButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                receiptButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(32, 32, 32)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel16))
                        .addGap(23, 23, 23)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(emailTF)
                            .addComponent(returnDateTF, javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(rentDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jLabel10)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel12)
                                    .addComponent(jLabel13)
                                    .addComponent(jLabel14)
                                    .addComponent(jLabel15)
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(gearboxTF)
                            .addComponent(priceTF)
                            .addComponent(plateTF)
                            .addComponent(carModelTF)
                            .addComponent(typeTF)
                            .addComponent(numSeatsTF)
                            .addComponent(colorTF)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 42, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel17)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(calculateButton)
                        .addGroup(layout.createSequentialGroup()
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel20)
                                .addGroup(layout.createSequentialGroup()
                                    .addGap(1, 1, 1)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel24)
                                        .addComponent(jLabel21))))
                            .addGap(48, 48, 48)
                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(paymentTF, javax.swing.GroupLayout.DEFAULT_SIZE, 150, Short.MAX_VALUE)
                                .addComponent(rentalFeeTF)
                                .addComponent(balanceTF)))
                        .addComponent(receiptButton)))
                .addGap(40, 40, 40))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(32, 32, 32)
                    .addComponent(jLabel3)
                    .addContainerGap(586, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(60, 60, 60)
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
                        .addGap(23, 23, 23)
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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel15)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel17)
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel20)
                            .addComponent(rentalFeeTF, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(paymentTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel21)
                            .addComponent(balanceTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(45, 45, 45)
                        .addComponent(calculateButton)
                        .addGap(18, 18, 18)
                        .addComponent(receiptButton)))
                .addContainerGap(11, Short.MAX_VALUE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(123, 123, 123)
                    .addComponent(jLabel3)
                    .addContainerGap(513, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void menuButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_menuButtonActionPerformed
        new AdminMenu().setVisible(true);
        dispose();
    }//GEN-LAST:event_menuButtonActionPerformed

    private void calculateButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_calculateButtonActionPerformed
        calculateBalance();
    }//GEN-LAST:event_calculateButtonActionPerformed

    private void rentDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rentDateTFActionPerformed

    private void returnDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnDateTFActionPerformed

    private void emailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTFActionPerformed

    private void receiptButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_receiptButtonActionPerformed
        //show receipt, update booking status to paid
        updateStatus();
        //send booking id and amount paid to receipt
        dispose();
        CusReceipt receipt = new CusReceipt(this.index, this.amountPaid);
        receipt.setVisible(true);
    }//GEN-LAST:event_receiptButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField balanceTF;
    private javax.swing.JButton calculateButton;
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
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton menuButton;
    private javax.swing.JTextField numSeatsTF;
    private javax.swing.JTextField paymentTF;
    private javax.swing.JTextField plateTF;
    private javax.swing.JTextField priceTF;
    private javax.swing.JButton receiptButton;
    private javax.swing.JTextField rentDateTF;
    private javax.swing.JTextField rentalFeeTF;
    private javax.swing.JTextField returnDateTF;
    private javax.swing.JTextField typeTF;
    // End of variables declaration//GEN-END:variables
}
