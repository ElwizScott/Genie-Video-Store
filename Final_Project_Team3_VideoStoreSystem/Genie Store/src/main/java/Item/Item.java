package Item;

public class Item {
    protected String id;
    protected String title;
    protected String rentalType;
    protected String loanType;
    protected int quantities;
    protected double fee;

    //----- Constructor -----
    public Item(){
        id = null;
        title = null;
        rentalType = null;
        loanType = null;
        quantities = 0;
        fee = 0;
    }

    public Item(String id, String title, String rentalType, String loanType, int quantities, double fee){
        this.id = id;
        this.title = title;
        this.setRentalType(rentalType);
        this.setLoanType(loanType);
        this.setNumOfCopies(quantities);
        this.setFee(fee);
    }

    public Item(Item other){
        this.setId(other.id);
        this.setTitle(other.title);
        this.setRentalType(other.rentalType);
        this.setLoanType(other.loanType);
        this.setNumOfCopies(other.quantities);
        this.setFee(other.fee);
    }

    //----- Getter and Setter -----
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle(){ return this.title; }

    public void setTitle(String title){ this.title = title; }

    public String getRentalType() {
        return rentalType;
    }

    public void setRentalType(String rentalType) {
        this.rentalType = rentalType;
    }

    public String getLoanType() {
        return loanType;
    }

    public void setLoanType(String loanType) {
        this.loanType = loanType;
    }

    public int getQuantities() {
        return quantities;
    }

    public void setNumOfCopies(int quantities) {
        this.quantities = quantities;
    }

    public double getFee() {
        return fee;
    }

    public void setFee(double fee) {
        this.fee = fee;
    }
    public static String[] rentalTypeList = {"Record", "DVD", "Game"};
    public static String[] loanTypeList = {"1-week", "2-days"};

    //----- Function -----
    public boolean isAvailable(){
        return (quantities > 0 ? true : false);
    }

    public boolean borrowing(){
        if(!this.isAvailable()) return false;
        this.quantities--;
        ManageItem.saveFile();
        return true;
    }

    public void returning(){
        this.quantities++;
        ManageItem.saveFile();
    }

    public void updating(Item other){
        this.id = other.id;
        this.title = other.title;
        this.setRentalType(other.rentalType);
        this.setLoanType(other.loanType);
        this.setNumOfCopies(other.quantities);
        this.setFee(other.fee);
    }

    public void displaying(){
        System.out.println("ID: " + this.id);
        System.out.println("Title: " + this.title);
        System.out.println("Rental type: " + this.rentalType);
        System.out.println("Loan type: " + this.loanType);
        System.out.println("Quantity: " + this.quantities);
        System.out.println("Fee: " + this.fee);
        System.out.println("Status: " + (isAvailable() ? "Available" : "Not Available"));
    }

    @Override
    public String toString() {
        return "Item{" +
                "ID=" + this.id +
                ", title=" + this.title +
                ", rentalType=" + this.rentalType +
                ", loanType=" + this.loanType +
                ", quantity=" + this.quantities +
                ", fee=" + this.fee +
                '}';
    }
}