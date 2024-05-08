/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package SubangsCarRental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.Comparator;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author User
 */
public class AdminCusReview extends javax.swing.JFrame {

    private String index; //booking id
    private String carID;
    private String email;
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
    private String rating;
    private String feedback;
    
    /**
     * Creates new form AdminCusReview
     */
    public AdminCusReview() {
        initComponents();
        disableTF();
        loadTableData(bookingTable);
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
        ratingTF.setEditable(false);
        feedbackTA.setEditable(false);
    }        
    
    private void getDetail() {
        //load all car info into a map
        Map<String, String[]> carInfoMap = Car.loadCarInfo();
        
        //retrieve booking info using index
        String[] bookingInfo = booking.getBookingInfo(this.index);
        
        if (bookingInfo != null) {
            this.email = bookingInfo[1].trim();
            this.carID = bookingInfo[2].trim();
            this.totalRent = Double.parseDouble(bookingInfo[5].trim());
            this.rentDate = bookingInfo[6].trim();
            this.returnDate = bookingInfo[7].trim();
            this.rating = bookingInfo[9].trim();
            this.feedback = bookingInfo[10].trim();

            //retrieve and set car details from carInfoMap
            String[] carDetails = Car.getCarDetails(this.carID, carInfoMap);
            this.carModel = carDetails[0];
            this.carType = carDetails[1];
            this.seatNum = Integer.parseInt(carDetails[2]);
            this.carColor = carDetails[3];
            this.gearbox = carDetails[4];
            this.price = Double.parseDouble(carDetails[5]);
            this.features = carDetails[7];
        } else {
            JOptionPane.showMessageDialog(null, "No booking found with Booking ID: " + index, "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }
    
    //display detail
    private void printDetail() {
        getDetail();
        emailTF.setText(this.email);
        plateTF.setText(this.carID);
        carModelTF.setText(this.carModel);
        typeTF.setText(this.carType);
        priceTF.setText("RM"+Double.toString(this.price));
        rentDateTF.setText(this.rentDate);
        returnDateTF.setText(this.returnDate);
        rentalFeeTF.setText("RM"+Double.toString(this.totalRent));
        colorTF.setText(this.carColor);
        numSeatsTF.setText(Integer.toString(this.seatNum));
        featureTA.setText(this.features);
        gearboxTF.setText(this.gearbox);
        ratingTF.setText(this.rating + "⭐");
        feedbackTA.setText(this.feedback);
    }
    
    private void loadTableData(javax.swing.JTable table) {        
        DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();        

        //load all car info into a map
        Map<String, String[]> carInfoMap = Car.loadCarInfo();           

        //read customer booking data and display table
        try (BufferedReader br = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                //booking has more than 10
                if (data.length >= 10) {
                        String carPlate = data[2].trim();
                        String[] carDetails = Car.getCarDetails(carPlate, carInfoMap);
                        //add row into table
                        model.addRow(new Object[]{                            
                            data[0],        //booking ID 
                            data[1],        //email
                            carDetails[0],         //car model
                            Double.valueOf(data[5]), //total fee
                            data[6],         //rent date
                            data[7],         //return date
                            data[9]         //rating
                    });
                }
            } 
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading customer booking file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }

        //set the model to the table
        table.setModel(model);
        table.revalidate();  //refresh the table to display new data             
    }
    
    //method overloading
    private void loadTableData(javax.swing.JTable table, String sortOrder) {
        DefaultTableModel model = (DefaultTableModel) bookingTable.getModel();    
        model.setRowCount(0); //clear table
        List<Object[]> rowDataList = new ArrayList<>();
        
         //load all car info into a map
        Map<String, String[]> carInfoMap = Car.loadCarInfo(); 

        //read customer booking data and display table
        try (BufferedReader br = new BufferedReader(new FileReader("cus_book_car.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length >= 10) {
                    String carPlate = data[2].trim();
                    String[] carDetails = Car.getCarDetails(carPlate, carInfoMap);
                    rowDataList.add(new Object[]{
                            data[0],        // booking ID
                            data[1],        // email
                            carDetails[0],  // Car model
                            Double.valueOf(data[5]), // total fee
                            data[6],        // Use Date
                            data[7],        // Return Date
                            data[9]         // rating
                    });
                }
            }
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, "Error reading customer booking file: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
        //sort the list based on rating
        Collections.sort(rowDataList, new Comparator<Object[]>() {
            @Override
            public int compare(Object[] row1, Object[] row2) {
                Double rating1 = Double.valueOf((String) row1[6]); 
                Double rating2 = Double.valueOf((String) row2[6]); 
                //sort based on selected order
                return sortOrder.equals("Highest to Lowest") ? rating2.compareTo(rating1) : rating1.compareTo(rating2);
            }
        });

        //add sorted data to the table model
        for (Object[] row : rowDataList) {
            model.addRow(row);
        }
        
        //set the model to the table
        table.setModel(model);
        table.revalidate();  //refresh the table to display new data
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
        colorTF = new javax.swing.JTextField();
        numSeatsTF = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel13 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookingTable = new javax.swing.JTable();
        jLabel6 = new javax.swing.JLabel();
        gearboxTF = new javax.swing.JTextField();
        plateTF = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        viewButton = new javax.swing.JButton();
        carModelTF = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        featureTA = new javax.swing.JTextArea();
        jLabel16 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        typeTF = new javax.swing.JTextField();
        priceTF = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        rentDateTF = new javax.swing.JTextField();
        returnDateTF = new javax.swing.JTextField();
        jLabel14 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jScrollPane3 = new javax.swing.JScrollPane();
        feedbackTA = new javax.swing.JTextArea();
        rentalFeeTF = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        sortBox = new javax.swing.JComboBox<>();
        sortButton = new javax.swing.JButton();
        emailTF = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        ratingTF = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 153));

        jLabel1.setFont(new java.awt.Font("Century Gothic", 1, 36)); // NOI18N
        jLabel1.setText("Customer Reviews");

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
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(212, 212, 212))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(menuButton)
                        .addGap(16, 16, 16))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(menuButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addContainerGap(26, Short.MAX_VALUE))
        );

        jLabel12.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel12.setText("Car Color:");

        jLabel2.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel2.setText("Car Model:");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel3.setText("Booking Detail");

        jLabel13.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel13.setText("Gearbox:");

        bookingTable.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        bookingTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Booking ID", "Customer  Email", "Car Model", "Total Price (RM)", "Rent Date", "Return Date", "Rating"
            }
        ));
        jScrollPane1.setViewportView(bookingTable);

        jLabel6.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel6.setText("Rent Date:");

        jLabel4.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel4.setText("Car Plate:");

        jLabel7.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel7.setText("Number of Seats:");

        viewButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        viewButton.setText("View Details");
        viewButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                viewButtonActionPerformed(evt);
            }
        });

        jLabel8.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel8.setText("Total Rental Fee:");

        featureTA.setColumns(20);
        featureTA.setRows(5);
        jScrollPane2.setViewportView(featureTA);

        jLabel16.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel16.setText("Review");

        jLabel17.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel17.setText("Rating:");

        jLabel19.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel19.setText("Feedback:");

        rentDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rentDateTFActionPerformed(evt);
            }
        });

        returnDateTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                returnDateTFActionPerformed(evt);
            }
        });

        jLabel14.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel14.setText("Price per day:");

        jLabel5.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel5.setText("Car Type:");

        jLabel9.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel9.setText("Return Date:");

        jLabel15.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel15.setText("Features:");

        feedbackTA.setColumns(20);
        feedbackTA.setRows(5);
        jScrollPane3.setViewportView(feedbackTA);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 1, 18)); // NOI18N
        jLabel10.setText("Car Detail");

        jLabel18.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel18.setText("Sort by:");

        sortBox.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Sort Rating Order", "Highest to Lowest", "Lowest to Highest" }));
        sortBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortBoxActionPerformed(evt);
            }
        });

        sortButton.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        sortButton.setText("Sort");
        sortButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sortButtonActionPerformed(evt);
            }
        });

        emailTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailTFActionPerformed(evt);
            }
        });

        jLabel20.setFont(new java.awt.Font("Arial", 0, 18)); // NOI18N
        jLabel20.setText("Customer Email:");

        ratingTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ratingTFActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel3)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel8)
                                            .addComponent(jLabel9)
                                            .addComponent(jLabel6)
                                            .addComponent(jLabel20))
                                        .addGap(19, 19, 19)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(rentalFeeTF, javax.swing.GroupLayout.Alignment.LEADING)
                                                .addComponent(returnDateTF, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                                            .addComponent(rentDateTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(emailTF, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addGap(0, 96, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel16)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jLabel17))
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 294, javax.swing.GroupLayout.PREFERRED_SIZE)
                                            .addComponent(ratingTF, javax.swing.GroupLayout.PREFERRED_SIZE, 83, javax.swing.GroupLayout.PREFERRED_SIZE))))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
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
                        .addGap(20, 20, 20))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(jScrollPane1)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(viewButton)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jLabel18)
                                .addGap(18, 18, 18)
                                .addComponent(sortBox, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(sortButton)))
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel18)
                    .addComponent(sortBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(sortButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(viewButton)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(emailTF, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
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
                            .addComponent(rentalFeeTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(185, 185, 185))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel10)
                        .addGap(12, 12, 12)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
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
                                .addGap(165, 165, 165)
                                .addComponent(jLabel16)
                                .addGap(12, 12, 12)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel17)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(jLabel19)
                                            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                    .addComponent(ratingTF, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addContainerGap())))
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
            //view booking detail
            this.index = (String) bookingTable.getModel().getValueAt(selectedRow, 0);
            printDetail();
        } else {
            JOptionPane.showMessageDialog(null, "Please select a row to view details.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_viewButtonActionPerformed

    private void rentDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rentDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_rentDateTFActionPerformed

    private void returnDateTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_returnDateTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_returnDateTFActionPerformed

    private void sortBoxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortBoxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_sortBoxActionPerformed

    private void sortButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sortButtonActionPerformed
        //check if order is selected
        String sortSelected = (String) sortBox.getSelectedItem();
        if (!sortSelected.equals("Sort Rating Order")) {
            //sort table
            loadTableData(bookingTable, sortSelected);
        } else {
            JOptionPane.showMessageDialog(null, "Please select an order to sort.", "Alert", JOptionPane.WARNING_MESSAGE);
        }
    }//GEN-LAST:event_sortButtonActionPerformed

    private void emailTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emailTFActionPerformed

    private void ratingTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ratingTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_ratingTFActionPerformed

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
            java.util.logging.Logger.getLogger(AdminCusReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminCusReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminCusReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminCusReview.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminCusReview().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable bookingTable;
    private javax.swing.JTextField carModelTF;
    private javax.swing.JTextField colorTF;
    private javax.swing.JTextField emailTF;
    private javax.swing.JTextArea featureTA;
    private javax.swing.JTextArea feedbackTA;
    private javax.swing.JTextField gearboxTF;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
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
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JButton menuButton;
    private javax.swing.JTextField numSeatsTF;
    private javax.swing.JTextField plateTF;
    private javax.swing.JTextField priceTF;
    private javax.swing.JTextField ratingTF;
    private javax.swing.JTextField rentDateTF;
    private javax.swing.JTextField rentalFeeTF;
    private javax.swing.JTextField returnDateTF;
    private javax.swing.JComboBox<String> sortBox;
    private javax.swing.JButton sortButton;
    private javax.swing.JTextField typeTF;
    private javax.swing.JButton viewButton;
    // End of variables declaration//GEN-END:variables
}
