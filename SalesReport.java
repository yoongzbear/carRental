/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SubangsCarRental;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author User
 */
public class SalesReport extends javax.swing.JFrame {
    
    private Map<String, Integer> modelCounts = new HashMap<>();
    private Map<String, Double> modelTotalRent = new HashMap<>();
    
    public class DateUtils {
    public static boolean isDateWithinMonth(String dateString, int inputMonth) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        try {
            // Parse the input date string into a Date object using the SimpleDateFormat
        Date date = sdf.parse(dateString); 
        // Create a Calendar object and set its time to the parsed date
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);  
        // Get the month from the Calendar instance. 
        // Note: Calendar.MONTH returns a zero-based index, so add 1 to make it one-based
        int month = cal.get(Calendar.MONTH) + 1; // Adding 1 to make it one-based       
        // Check if the extracted month matches the input month
        return month == inputMonth;
        } catch (ParseException e) {
            e.printStackTrace();
            return false; // Return false if unable to parse date
        }
    }
}
    
    private void processSalesData(int inputMonth) {
        boolean foundResults = false; // Flag to track if any results are found
        try (BufferedReader br = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 9) {
                    continue;
                }
                String status = parts[8].trim(); 
                String usedDate = parts[6].trim(); 

                 // Check if status is "Paid" and return date is within the input month
                if ((status.equals("Paid")|| status.equals("Returned")) && DateUtils.isDateWithinMonth(usedDate, inputMonth)) {
                    String model = parts[3].trim(); 
                    double totalPrice = Double.parseDouble(parts[5].trim()); 

                    // Update model counts
                    modelCounts.put(model, modelCounts.getOrDefault(model, 0) + 1);

                    // Update model total rent
                double currentTotalRent = modelTotalRent.getOrDefault(model, 0.0);
                modelTotalRent.put(model, currentTotalRent + totalPrice);
                foundResults = true; // Set flag to true if sales are found
                } 
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Display alert if no results found
    if (!foundResults) {
        JOptionPane.showMessageDialog(this, "No sales found for the selected month", "Information", JOptionPane.INFORMATION_MESSAGE);
    }
}


    private void displayResults(JTable Report) {
        DefaultTableModel model = (DefaultTableModel) Report.getModel();
        model.setRowCount(0); // Clear previous data

        int rowNum = 1;
        double totalRentForMonth = 0.0; // Variable to store total rent for the month
        for (Map.Entry<String, Integer> entry : modelCounts.entrySet()) {
            String Carmodel = entry.getKey();
            int bookedNumber = entry.getValue();
            double totalRent = modelTotalRent.getOrDefault(Carmodel, 0.0);
            model.addRow(new Object[]{rowNum++, Carmodel, bookedNumber, totalRent});
            totalRentForMonth += totalRent; // Accumulate total rent for the month
        }
        // Add a merged cell for displaying total rent for the month
        int rowCount = model.getRowCount();
        model.addRow(new Object[]{"Total:", "", "", totalRentForMonth});
        // Highlight the total row
        Report.getSelectionModel().addSelectionInterval(rowCount, rowCount);
        Color darkGray = new Color(64, 64, 64);
        Report.setSelectionBackground(Color.darkGray);
        // Adjust row heights for merged cell and hide the next row
        Report.setRowHeight(rowCount, 20); // Adjust row height for merged cell
        Report.setRowHeight(rowCount + 1, 0); // Hide the next row
    }
    
    /**
     * Creates new form SalesReport
     */
    public SalesReport() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        generate = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Report = new javax.swing.JTable();
        Back = new javax.swing.JButton();
        MONTH = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jLabel2.setText("Month of sales :");

        generate.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        generate.setText("Genreate");
        generate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                generateActionPerformed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(0, 204, 204));

        jLabel1.setFont(new java.awt.Font("Times New Roman", 1, 36)); // NOI18N
        jLabel1.setText("-- Sales Report --");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(309, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(290, 290, 290))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        Report.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "No","Car Type", "Booked Number", "Total rent (RM)"
            }
        ));
        jScrollPane1.setViewportView(Report);

        Back.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Back.setText("Back");
        Back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackActionPerformed(evt);
            }
        });

        MONTH.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        MONTH.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "January", "February", "March", "April","May","June", "July", "August", "September","October","November", "December" }));
        MONTH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MONTHActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(MONTH, javax.swing.GroupLayout.PREFERRED_SIZE, 125, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addComponent(generate, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(0, 0, Short.MAX_VALUE)
                                        .addComponent(Back)
                                        .addGap(10, 10, 10)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(generate)
                    .addComponent(MONTH, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(33, 33, 33)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 253, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Back)
                .addContainerGap(8, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void generateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_generateActionPerformed
    String selectedMonth = (String) MONTH.getSelectedItem();
    
    // Determine the month index based on the selected month string
    int inputMonth = -1; // Default value if month is not found
    String[] months = { "January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December" };
    for (int i = 0; i < months.length; i++) {
        if (selectedMonth.equals(months[i])) {
            inputMonth = i + 1; // Add 1 to make it one-based index
            break;
        }
    }
    
    // Check if input month is valid
    if (inputMonth >= 1 && inputMonth <= 12) {
        modelCounts.clear(); // Clear existing data
        modelTotalRent.clear(); // Clear existing data
        processSalesData(inputMonth);
        displayResults(Report);
        System.out.println("Input month is valid: " + selectedMonth);
    } 
    }//GEN-LAST:event_generateActionPerformed

    private void BackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackActionPerformed
        AdminMenu Menu = new AdminMenu();
        Menu.setVisible(true);
        dispose();
    }//GEN-LAST:event_BackActionPerformed

    private void MONTHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MONTHActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_MONTHActionPerformed

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
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SalesReport.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SalesReport().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Back;
    private javax.swing.JComboBox<String> MONTH;
    private javax.swing.JTable Report;
    private javax.swing.JButton generate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
