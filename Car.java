/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SubangsCarRental;

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
}

