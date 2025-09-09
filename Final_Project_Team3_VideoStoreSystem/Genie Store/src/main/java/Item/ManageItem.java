package Item;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Scanner;

public class ManageItem {
    public static ArrayList<Item> items = new ArrayList<Item>();

    private static String items_file_name = "items.txt";

    private static Item readItem(String s){
        String[] attribute = s.split(",");
        Item item = new Item(attribute[0], attribute[1], attribute[2], attribute[3],
                Integer.parseInt(attribute[4]), Double.parseDouble(attribute[5]));

        if(!item.getRentalType().equalsIgnoreCase("Game")){
            RecordDVD recordDVD = new RecordDVD(item);
            recordDVD.setGenres(attribute[6]);
            item = recordDVD;
        }
        return item;
    }
    public static void readFile(){
        File myFile = new File(items_file_name);

        try {
            Scanner readLine = new Scanner(myFile);
            while(readLine.hasNextLine()){
                Item item = readItem(readLine.nextLine());
                items.add(item);
            }

            readLine.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static void saveFile(){
        File myFile = new File(items_file_name);

        try {
            FileWriter writeFile = new FileWriter(myFile);
            for(Item item : items){
                writeFile.write(item.getId() + ",");
                writeFile.write(item.getTitle() + ",");
                writeFile.write(item.getRentalType() + ",");
                writeFile.write(item.getLoanType() + ",");
                writeFile.write(item.getQuantities() + ",");
                writeFile.write(Double.toString(item.getFee()));
                if(item instanceof RecordDVD){
                    RecordDVD recordDVD = (RecordDVD)item;
                    writeFile.write("," + recordDVD.getGenres());
                }
                writeFile.write("\n");
            }

            writeFile.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static boolean borrowItem(String id){
        if(items.size() == 0) return false;

        for(int i = 0; i < items.size(); i++){
            if(id.equals(items.get(i).getId())
                    && items.get(i).borrowing()){
                saveFile();
                return true;
            }
        }

        return false;
    }
    public static boolean returnItem(String id){
        if(items.size() == 0) return false;

        for(int i = 0; i < items.size(); i++){
            if(id.equals(items.get(i).getId())){
                items.get(i).returning();
                saveFile();
                return true;
            }
        }

        return false;
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
    public static boolean checkIdFormat(String id){

        //Format: Ixxx-yyyy
        if(id.charAt(0) != 'I' || id.charAt(4) != '-') {
            System.out.println("Wrong format id!");
            return false;
        }

        String uniqueCode = id.substring(1, 4);
        String year = id.substring(5,9);

        if(!isNumeric(uniqueCode) || !isNumeric(year)) {
            System.out.println("Wrong format id!");
            return false;
        }

        return true;
    }

    public static boolean checkIdExist(String newId, String existedId){
        String uniqueCodeA = newId.substring(1, 4);
        String uniqueCodeB = existedId.substring(1, 4);

        if(uniqueCodeA.equals(uniqueCodeB)) {
            System.out.println("Unique code should not be duplicated!");
            return true;
        }

        return false;
    }
    public static boolean addItem(Item newItem) {
        if(!checkIdFormat(newItem.getId())) return false;

        boolean exist = false;
        for(int i = 0; i < items.size(); i++){
            if(checkIdExist(newItem.getId(), items.get(i).getId())){
                exist = true;
                break;
            }
        }

        if(exist == false){
            items.add(newItem);
            System.out.println("Add new item success!");
            saveFile();
            return true;
        }

        return false;
    }
    public static boolean removeItem(String id){
        for(int i = 0; i < items.size(); i++){
            if(id.equalsIgnoreCase(items.get(i).getId())){
                items.remove(i);
                System.out.println("Remove success!");
                saveFile();
                return true;
            }
        }
        System.out.println("Item is not exist in the list");
        return false;
    }
    public static boolean updateItem(Item updateItem){
        for(int i = 0; i < items.size(); i++){
            if(updateItem.getId().equalsIgnoreCase(items.get(i).getId())){
                items.get(i).updating(updateItem);
                System.out.println("Update success!");
                saveFile();
                return true;
            }
        }
        System.out.println("Item is not existed!");
        return false;
    }

    public static void displayAllItems(){
        if(items.size() == 0) System.out.println("There's no item in the store");
        System.out.println(items.size());

        int i = 1;
        for(Item item : items){
            System.out.println("Item " + i);
            item.displaying();
            System.out.println();
            i++;
        }
    }

    public static int SORT_BY_TITLE = 1000;
    public static int SORT_BY_ID = 1001;
    public static void sort(int type){
        if(type == SORT_BY_TITLE)
            items.sort(new Comparator<Item>() {
                @Override
                public int compare(Item a, Item b) {
                    return a.getTitle().compareTo(b.getTitle());
                }
            });
        else
            items.sort(new Comparator<Item>() {
                @Override
                public int compare(Item a, Item b) {
                    return a.getId().compareTo(b.getId());
                }
            });
    }
    public static Item search(String Id) {
        for(Item i : items) {
            if(i.getId().equalsIgnoreCase(Id) || i.getTitle().equalsIgnoreCase(Id)) {
                i.displaying();
                return i;
            }
        }
        System.out.println("There is no item with that Id/Title");
        return null;
    }
    //get out of stock list
    public static ArrayList<Item> getOutOfStock() {
        ArrayList<Item> zeroCopyList = new ArrayList<>();
        for(Item i : items) {
            if(i.isAvailable() == false) {
                zeroCopyList.add(i);
            }
        }
        for(Item i : zeroCopyList) {
            i.displaying();
        }
        return zeroCopyList;
    }
}

