package Return;

import Customer.Customer;
import Item.Item;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class ManageRentCount {
    public static ArrayList<RentCount> countList = new ArrayList<RentCount>();

    private static String return_file_name = "return.txt";


    public static RentCount readRentCount(String s) {
        String[] attribute = s.split(",");
        RentCount rent = new RentCount(attribute[0], Integer.parseInt(attribute[1]), Integer.parseInt(attribute[2]));
        return rent;
    }

    public static void readFile() {
        File myFile = new File(return_file_name);

        try {
            Scanner readLine = new Scanner(myFile);
            while(readLine.hasNextLine()){
                RentCount rentCount = readRentCount(readLine.nextLine());
                countList.add(rentCount);
            }

            readLine.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }



    public static void saveFile() {
        File myFile = new File(return_file_name);

        try {
            FileWriter writeFile = new FileWriter(myFile);
            for(RentCount rentCount : countList){
                writeFile.write(rentCount.getCustomerId() + ",");
                writeFile.write(Integer.toString(rentCount.getReturnCount()) + ",");
                writeFile.write(Integer.toString(rentCount.getRewardPoint()));

                writeFile.write("\n");
            }

            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean addRentCount(RentCount newRentCount) {

        countList.add(newRentCount);
        System.out.println("Add new item success!");
        saveFile();
        return true;
    }

    public static boolean removeRentCount(String id){
        for(int i = 0; i < countList.size(); i++){
            if(id.equalsIgnoreCase(countList.get(i).getCustomerId())){
                countList.remove(i);
                System.out.println("Remove success!");
                saveFile();
                return true;
            }
        }
        System.out.println("RentCount is not exist in the list");
        return false;
    }

    public static boolean updateRentCount(RentCount updateRentCount){
        for(int i = 0; i < countList.size(); i++){
            if(updateRentCount.getCustomerId().equalsIgnoreCase(countList.get(i).getCustomerId())){
                countList.get(i).updating(updateRentCount);
                System.out.println("Update success!");
                saveFile();
                return true;
            }
        }
        System.out.println("User is not existed!");
        return false;
    }

    public static void displayAllRentCount() {
        if(countList.size() == 0) System.out.println("There's no data");
        System.out.println(countList.size());

        int i = 1;
        for(RentCount rentCount : countList){
            System.out.println("Rent count " + i);
            rentCount.displaying();
            System.out.println();
            i++;
        }
    }


}
