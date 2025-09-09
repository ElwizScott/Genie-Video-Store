package Customer;

import Item.ManageItem;

import java.util.ArrayList;

public class Customer {
    private String ID;
    private String name;
    private String address;
    private String phone;
    private int numOfRentals;
    private String customerType;
    private String username;
    private String password;
    private ArrayList<String> listRental;

    //----- Constructor -----
    public Customer() {
        ID = null;
        name = null;
        address = null;
        phone = null;
        numOfRentals = 0;
        customerType = null;
        username = null;
        password = null;
        listRental = new ArrayList<>();

    }
    public Customer(String ID, String name, String address, String phone, int numOfRentals, String customerType, String username, String password, ArrayList<String> listRental) {
        this.ID = ID;
        this.name = name;
        this.address = address;
        this.phone = phone;
        this.numOfRentals = numOfRentals;
        this.customerType = customerType;
        this.username = username;
        this.password = password;
        this.listRental = listRental;

    }
    public Customer(Customer other) {
        this.ID = other.ID;
        this.name = other.name;
        this.address = other.address;
        this.phone = other.phone;
        this.numOfRentals = other.numOfRentals;
        this.customerType = other.customerType;
        this.username = other.username;
        this.password = other.password;
        this.listRental = other.listRental;
    }

    //----- Getter and Setter -----

    public String getId() {
        return ID;
    }

    public void setID(String ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public ArrayList<String> getListRental() {
        return listRental;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getNumOfRentals() {
        return numOfRentals;
    }

    public void setNumOfRentals(int numOfRentals) {
        this.numOfRentals = numOfRentals;
    }

    public String getCustomerType() {
        return customerType;
    }

    public void setCustomerType(String customerType) {
        this.customerType = customerType;
    }

    public void setListRental(ArrayList<String> listRental) {
        this.listRental = listRental;
    }

    public static String[] customerTypeList = {"Guest", "Regular", "VIP"};

    //----- Function -----
    public String getRentalListComponents() {
        String rentalList = "";
        for (int i = 0; i < listRental.size(); i++) {
            rentalList = rentalList + listRental.get(i) + "\n";
        }
        return rentalList;
    }
    public boolean addRentalItem(String itemId) {
        if(this.getCustomerType().equalsIgnoreCase("Guest") && this.numOfRentals == 2) {
            System.out.println("Guest can only borrow max 2 items");
            return false;
        }
        if(ManageItem.borrowItem(itemId) == true) {
            this.listRental.add(itemId);
            ManageCustomer.saveFile();
            return true;
        } else {
            return false;
        }
    }

    public boolean returnItem(String itemId) {
        ManageItem.returnItem(itemId);
        listRental.remove(itemId);
        ManageCustomer.saveFile();
        return true;
    }

    public void updating(Customer other) {
        this.ID = other.ID;
        this.name = other.name;
        this.address = other.address;
        this.phone = other.phone;
        this.numOfRentals = other.numOfRentals;
        this.customerType = other.customerType;
        this.username = other.username;
        this.password = other.password;
        this.listRental = other.listRental;
    }


    public void displaying() {
        //ID,Name,Address,Phone,Number of rentals,customer type, username, password, list of items
        System.out.println("ID: " + ID
                + "\nName: " + name
                + "\nAddress: " + address
                + "\nPhone: " + phone
                + "\nNumber of rentals: " + numOfRentals
                + "\nCustomer type: " + customerType
                + "\nUsername: " + username
                + "\nPassword: " + password
                + "\nList of items: " + listRental
                + "\n");
    }
}
