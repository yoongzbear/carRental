/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;

/**
 *
 * @author User
 */
public class Car {
        private String id;
        private String model;
        private String type;
        private int numSeats;
        private String gearbox;
        private String color;
        private double pricePerDay;
        private String features;   

        // Constructor, getters, and setters
        public Car() {}

        public Car(String id, String model, String type, int numSeats, String gearbox, String color, double pricePerDay, String features) {
            this.id = id;
            this.model = model;
            this.type = type;
            this.numSeats = numSeats;
            this.gearbox = gearbox;
            this.color = color;
            this.pricePerDay = pricePerDay;
            this.features = features;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getModel() {
            return model;
        }

        public void setModel(String model) {
            this.model = model;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public int getNumSeats() {
            return numSeats;
        }

        public void setNumSeats(int numSeats) {
            this.numSeats = numSeats;
        }

        public String getGearbox() {
            return gearbox;
        }

        public void setGearbox(String gearbox) {
            this.gearbox = gearbox;
        }

        public String getColor() {
            return color;
        }

        public void setColor(String color) {
            this.color = color;
        }

        public double getPricePerDay() {
            return pricePerDay;
        }

        public void setPricePerDay(double pricePerDay) {
            this.pricePerDay = pricePerDay;
        }

        public String getFeatures() {
            return features;
        }

        public void setFeatures(String features) {
            this.features = features;
        }
        
        public static Map<String, String[]> loadCarInfo() {
            Map<String, String[]> carInfoMap = new HashMap<>();
            try (BufferedReader reader = new BufferedReader(new FileReader("car_info.txt"))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts.length > 7) {
                        StringBuilder features = new StringBuilder();
                        for (int i = 7; i < parts.length; i++) {
                            features.append(parts[i].trim());
                            if (i < parts.length - 1) {
                                features.append(", ");
                            }
                        }
                        String[] carDetails = {
                            parts[1].trim(), parts[2].trim(), parts[3].trim(), 
                            parts[4].trim(), parts[5].trim(), parts[6].trim(), 
                            features.toString()
                        };
                        carInfoMap.put(parts[0].trim(), carDetails);
                    }
                }
            } catch (IOException e) {
                JOptionPane.showMessageDialog(null, "Error reading car info file: " + e.getMessage());
            }
            return carInfoMap;
        }
        
        public static String[] getCarDetails(String carID, Map<String, String[]> carInfoMap) {
            return carInfoMap.getOrDefault(carID, new String[]{"Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "Unknown", "No features available"});
        }
}
