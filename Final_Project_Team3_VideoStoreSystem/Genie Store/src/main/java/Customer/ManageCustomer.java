package Customer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ManageCustomer {
    public static ArrayList<Customer> userList = new ArrayList<Customer>();

    private static String user_file_name = "customer.txt";

    private static Customer readUser(String[] attribute, ArrayList<String> listRental){

        //ID,Name,Address,Phone,Number of rentals,customer type, username, password, list of items
        Customer user = new Customer(attribute[0], attribute[1], attribute[2], attribute[3],
                Integer.parseInt(attribute[4]), attribute[5], attribute[6], attribute[7], listRental);

        return user;
    }
    public static void readFile(){
        File myFile = new File(user_file_name);

        try {
            Scanner readLine = new Scanner(myFile);
            String line = readLine.nextLine();

            //Find the first customer information
            String[] customerInfo = line.split(",");
            while(readLine.hasNextLine() && customerInfo.length != 8) {
                line = readLine.nextLine();
                customerInfo = line.split(",");
            }

            //If there's no customer information in the file
            if(customerInfo.length != 8) return;

            while(readLine.hasNextLine()){
                customerInfo = line.split(",");

                line = readLine.nextLine();
                ArrayList <String> listRental = new ArrayList<>();

                //Read the list item until the next customer
                while(readLine.hasNextLine() && line.split(",").length == 1){
                    listRental.add(line);
                    line = readLine.nextLine();
                }

                if(!readLine.hasNextLine() && line.split(",").length == 1){
                    listRental.add(line);
                }

                Customer user = readUser(customerInfo, listRental);
                userList.add(user);
            }

            readLine.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile(){
        File myFile = new File(user_file_name);
        //ID,Name,Address,Phone,Number of rentals,customer type, username, password, list of items
        try {
            FileWriter writeFile = new FileWriter(myFile);
            for(Customer user : userList){
                writeFile.write(user.getId() + ",");
                writeFile.write(user.getName() + ",");
                writeFile.write(user.getAddress() + ",");
                writeFile.write(user.getPhone() + ",");
                writeFile.write(Integer.toString(user.getNumOfRentals()) + ",");
                writeFile.write(user.getCustomerType() + ",");
                writeFile.write(user.getUsername() + ",");
                writeFile.write(user.getPassword() + "\n");
                if(user.getNumOfRentals() > 0) {
                    for (int i = 0; i < user.getListRental().size(); i++) {
                        writeFile.write(user.getListRental().get(i) + "\n");
                    }
                } else {
                    writeFile.write("\n");
                }

            }
            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        try {
            double d = Double.parseDouble(strNum);
        } catch (NumberFormatException nfe) {
            return false;
        }
        return true;
    }
    public static boolean checkIdFormat2(String id){
        //Format: Cxxx
        if(id.charAt(0) != 'C') {
            System.out.println("Wrong format id!");
            return false;
        }

        String uniqueCode = id.substring(1, 4);
        //String year = id.substring(5,9);

        if(!isNumeric(uniqueCode) /*|| !isNumeric(year)*/) {
            System.out.println("Wrong format id!");
            return false;
        }

        return true;
    }

    public static boolean checkIdExist2(String newId, String existedId){
        String uniqueCodeA = newId.substring(1, 4);
        String uniqueCodeB = existedId.substring(1, 4);

        if(uniqueCodeA.equals(uniqueCodeB)) {
            System.out.println("Unique code should not be duplicated!");
            return true;
        }

        return false;
    }
    public static boolean addUser(Customer newUser) {
        if(!checkIdFormat2(newUser.getId())) return false;

        boolean exist = false;
        for(int i = 0; i < userList.size(); i++){
            if(checkIdExist2(newUser.getId(), userList.get(i).getId())){
                exist = true;
                break;
            }
        }

        if(exist == false){
            userList.add(newUser);
            System.out.println("Add new user success!");
            saveFile();
            return true;
        }

        return false;
    }
    public static boolean removeUser(String id){
        for(int i = 0; i < userList.size(); i++){
            if(id.equalsIgnoreCase(userList.get(i).getId())){
                userList.remove(i);
                System.out.println("Remove success!");
                saveFile();
                return true;
            }
        }
        System.out.println("User is not exist in the list");
        return false;
    }
    public static boolean updateUser(Customer updateUser){
        for(int i = 0; i < userList.size(); i++){
            if(updateUser.getId().equalsIgnoreCase(userList.get(i).getId())){
                userList.get(i).updating(updateUser);
                System.out.println("Update success!");
                saveFile();
                return true;
            }
        }
        System.out.println("User is not existed!");
        return false;
    }

    public static void displayAllUser(){
        if(userList.size() == 0) System.out.println("There's no customer!");
        System.out.println("Total number of user: " + userList.size());

        int i = 1;
        for(Customer user : userList){
            System.out.println("Customer " + i);
            user.displaying();
            System.out.println();
            i++;
        }
    }

    public static String createNewCustomerID() {
        String cID = "C";
        if (userList.size() < 9) {
            String lastCustomerId = userList.get(userList.size() - 1).getId();
            int lastIdNum = Integer.parseInt(String.valueOf(lastCustomerId.charAt(3)));
            if (lastIdNum != userList.size()) {
                for (int i = 0; i < userList.size(); i++) {
                    String currentCustomerId = userList.get(i).getId();
                    int currentIdNum = Integer.parseInt(String.valueOf(currentCustomerId.charAt(3)));
                    if (currentIdNum != i + 1) {
                        cID = cID + "00" + (i + 1);
                        return cID;
                    }
                }
            } else {
                cID = cID + "00" + (userList.size() + 1);
            }
        }

        if (userList.size() >= 9 && userList.size() < 99 ) {
            String lastCustomerId = userList.get(userList.size() - 1).getId();
            String lastTwoLetters = lastCustomerId.substring(lastCustomerId.length() - 2);
            int lastIdNum = Integer.parseInt(lastTwoLetters);
            if (lastIdNum != userList.size()) {
                for (int i = 0; i < userList.size(); i++) {
                    String currentCustomerId = userList.get(i).getId();
                    String currentTwoLetters = currentCustomerId.substring(currentCustomerId.length() - 2);
                    int currentIdNum = Integer.parseInt(currentTwoLetters);
                    if (currentIdNum != i + 1) {
                        cID = cID + "0" + (i + 1);
                        return cID;
                    }
                }
            } else {
                cID = cID + "0" + (userList.size() + 1);
            }

        }
        if (userList.size() >= 99) {
            String lastCustomerId = userList.get(userList.size() - 1).getId();
            String lastThreeLetters = lastCustomerId.substring(lastCustomerId.length() - 3);
            int lastIdNum = Integer.parseInt(lastThreeLetters);
            if (lastIdNum != userList.size()) {
                for (int i = 0; i < userList.size(); i++) {
                    String currentCustomerId = userList.get(i).getId();
                    String currentThreeLetters = currentCustomerId.substring(currentCustomerId.length() - 3);
                    int currentIdNum = Integer.parseInt(currentThreeLetters);
                    if (currentIdNum != i + 1) {
                        cID = cID + (i + 1);
                        return cID;
                    }
                }
            } else {
                cID = cID  + (userList.size() + 1);
            }
        }
        return cID;
    }

    public static int SORT_BY_NAME = 1000;
    public static int SORT_BY_ID = 1001;
    public static void sort(int type){
        if(type == SORT_BY_NAME)
            userList.sort(new Comparator<Customer>() {
                @Override
                public int compare(Customer a, Customer b) {
                    return a.getName().compareTo(b.getName());
                }
            });
        else
            userList.sort(new Comparator<Customer>() {
                @Override
                public int compare(Customer a, Customer b) {
                    return a.getId().compareTo(b.getId());
                }
            });
    }
    public static Customer search(String Id) {
        for(Customer i : userList) {
            if(i.getId().equalsIgnoreCase(Id) || i.getName().equalsIgnoreCase(Id)) {
                i.displaying();
                return i;
            }
        }
        System.out.println("There is no customer with that Id/Name");
        return null;
    }
    //get list of guest customers
    public static ArrayList<Customer> getListGuest() {
        ArrayList<Customer> guestList = new ArrayList<>();
        for(Customer i : userList) {
            if(i.getCustomerType().equalsIgnoreCase("Guest")) {
                guestList.add(i);
            }
        }
        for(Customer i : guestList) {
            i.displaying();
        }
        return guestList;
    }
    //get list of regular customers
    public static ArrayList<Customer> getListRegular() {
        ArrayList<Customer> regularList = new ArrayList<>();
        for(Customer i : userList) {
            if(i.getCustomerType().equalsIgnoreCase("Regular")) {
                regularList.add(i);
            }
        }
        for(Customer i : regularList) {
            i.displaying();
        }
        return regularList;
    }
    //get list of vip customers
    public static ArrayList<Customer> getListVip() {
        ArrayList<Customer> vipList = new ArrayList<>();
        for(Customer i : userList) {
            if(i.getCustomerType().equalsIgnoreCase("VIP")) {
                vipList.add(i);
            }
        }
        for(Customer i : vipList) {
            i.displaying();
        }
        return vipList;
    }
}
