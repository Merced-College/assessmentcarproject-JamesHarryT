/*
Name: Harrison Tinley
Date: 3/7/26
Program: Main program with menu, loading csv, sorting by mileage, printing stats, and more
*/


import java.io.*;
import java.util.*;

public class Main {

    ArrayList<Car> cars = new ArrayList<>();

    public static void main(String[] args) {
        Main program = new Main();
        program.run();
    }

    public void run() {
        read_from_csv("Car_Data.csv");

        //create working list and print the size
        ArrayList<Car> working = new ArrayList<>(cars.subList(0, 2000));
        System.out.println("Working List Size: "+ working.size());

        Scanner scnr = new Scanner(System.in);

        boolean is_running = true;

        // set up a menu that will repeat options until the user exits the program
        while (is_running == true) {
            System.out.println("\nMenu:");
            System.out.println("1. Sort Cars by Mileage");
            System.out.println("2. Search by Mileage");
            System.out.println("3. Show Stats");
            System.out.println("4. Exit");

            System.out.print("\nChoose option (type number): ");
            int choice = scnr.nextInt();

            //logic for sorting the cars by mileage
            if (choice == 1) {

                sort_cars(working);
                System.out.println("\nFirst 10 cars after sorting:");
                for (int i = 0; i < 10; i++) {
                    System.out.println("mileage: " + working.get(i).getMileage());
                }
            }
            // logic for searching car by mileage
            else if (choice == 2) {
                System.out.print("Enter the mileage of the car you're looking for : ");
                double target_mileage = scnr.nextDouble();
                System.out.println("\n");
                Car found_car = search_cars(target_mileage, working);
                if (found_car == null) {
                    System.out.println("Car not found");
                }
                else {
                    System.out.println(found_car.toString());
                }
            }
            //logic for printing the stats of the cars list
            else if (choice == 3) {
                System.out.println("");
                print_stats(working);
            }
            //logic for ending the program and the menu options and all that
            else if (choice == 4) {
                is_running = false;
                System.out.println("Exiting Program . . . ");
            }
            //logic for if choice is out of bounds
            else {
                System.out.println("Invalid choice, Try Again");
            }
        }
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
                String color = parts[5];
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

        //fuel type variables
        int total_CNG = 0;
        int total_diesel = 0;
        int total_electric = 0;
        int total_hybrid = 0;
        int total_petrol = 0;

        for (Car c : arr) {
            total += c.getMileage();

            String fuel = c.getFuelType();

            if (fuel.equalsIgnoreCase("CNG")) {
                total_CNG++;
            }
            else if (fuel.equalsIgnoreCase("Diesel")) {
                total_diesel++;
            }
            else if (fuel.equalsIgnoreCase("Electric")) {
                total_electric++;
            }
            else if (fuel.equalsIgnoreCase("Hybrid")) {
                total_hybrid++;
            }
            else if (fuel.equalsIgnoreCase("Petrol")) {
                total_petrol++;
            }
        }

        double average = total / arr.size();

        System.out.println("Average Mileage: " + average);

        System.out.println("Fuel Counts: ");

        System.out.println("CNG: " + total_CNG);
        System.out.println("Diesel: " + total_diesel);
        System.out.println("Electric: " + total_electric);
        System.out.println("Hybrid: " + total_hybrid);
        System.out.println("Petrol: " + total_petrol);

    }


}
