/*
Name: Harrison Tinley
Date: 3/7/26
Program: 
*/


import java.io.*;
import java.util.*;

public class Main {

    ArrayList<Car> cars = new ArrayList<>();

    public void main() {
        read_from_csv("Car_Data.csv");

        ArrayList<Car> working = new ArrayList<>(cars.subList(0, Math.min(2000, cars.size())));

        
        System.out.println("Unsorted :");
        for (int i = 0; i < 10; i++) {
            System.out.println("mileage: " + working.get(i).getMileage());
        }

        sort_cars(working);

        System.out.println("Sorted :");
        for (int i = 0; i < 10; i++) {
            System.out.println("mileage: " + working.get(i).getMileage());
        }
    }

    public void read_from_csv(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            br.readLine(); //skip the header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length != 7) {
                    continue; //skips malformed rows
                }
                String carID = parts[0];
                String brand = parts[1];
                String model = parts[2];
                int year = Integer.parseInt(parts[3]);
                String fuel_type = parts[4];
                String color = parts[5];;
                double mileage = Double.parseDouble(parts[6]);

                cars.add(new Car(carID, brand, model, year, fuel_type, color, mileage));
            }
        }
        catch (IOException e) {
            System.out.println("Error opening the file " + e.getMessage());
        }
        System.out.println("Total cars loaded: " + cars.size());
    }


    //sorts the list by mileage using selection sort
    public void sort_cars(ArrayList<Car> arr) {
        for (int i = 0; i < arr.size() - 1; i++) {
            int smallest_index = i;

            for (int j = i; j < arr.size(); j++) {
                if (arr.get(j).getMileage() < arr.get(smallest_index).getMileage()) {
                    smallest_index = j;
                }
            }

            Car temp = arr.get(i);
            arr.set(i, arr.get(smallest_index));
            arr.set(smallest_index, temp);
        }
    }

    public void search_cars(double searched_mileage) {
        ;// set this up later to search for cars off of the input.
    }

}
