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

        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));

        
        System.out.println("Unsorted :");
        for (int i = 0; i < 10; i++) {
            System.out.println("mileage: " + working.get(i).getMileage());
        }

        sort_cars(working);

        System.out.println("Sorted :");
        for (int i = 0; i < 10; i++) {
            System.out.println("mileage: " + working.get(i).getMileage());
        }

        System.out.println("Enter the mileage of the car you're looking for : ");
        Scanner scnr = new Scanner(System.in);
        double target_mileage = scnr.nextDouble();
        Car found_car = search_cars(target_mileage, working);
        if (found_car == null) {
            System.out.println("Car not found");
        }
        else {
            System.out.println(found_car.toString());
        }

        print_stats(working);


    }

    public void read_from_csv(String file) {
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            br.readLine(); //skip the header

            while ((line = br.readLine()) != null) {
                String[] parts = line.split(","); // splits data into sections divided by ','
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

                // adds car object to the ArrayList cars with all the data found in the csv
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

    // finds car with matching mileage the user typed using binary search
    public Car search_cars(double target_mileage, ArrayList<Car> arr) {
        int low = 0;
        int high = arr.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;

            double mileage = arr.get(mid).getMileage();

            if (mileage == target_mileage) {
                return arr.get(mid);
            }

            if (mileage < target_mileage) {
                low = mid + 1;
            }

            else {
                high = mid - 1;
            }
        }

        return null;
    }

    public void print_stats(ArrayList<Car> arr) {
        double total = 0;
        //note: still need to get stats for fuel_types!!! ! ! ! ! 
        for (Car c : arr) {
            total += c.getMileage();
        }

        double average = total / arr.size();

        System.out.println("Average Mileage: " + average);
    }


}
