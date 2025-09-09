package Item;

public class RecordDVD extends Item {
    private String genres;

    public RecordDVD(){
        super();
        genres = null;
    }
    public RecordDVD(Item item){
        super(item);
        genres = null;
    }

    public RecordDVD(Item item, String genres) {
        super(item);
        this.genres = genres;
    }

    public String getGenres() {
        return genres;
    }

    public void setGenres(String genres) {
        this.genres = genres;
    }

    @Override
    public void displaying(){
        System.out.println("ID: " + this.id);
        System.out.println("Title: " + this.title);
        System.out.println("Rental type: " + this.rentalType);
        System.out.println("Loan type: " + this.loanType);
        System.out.println("Quantity: " + this.quantities);
        System.out.println("Fee: " + this.fee);
        System.out.println("Genre: " + this.genres);
        System.out.println("Status: " + (isAvailable() ? "Available" : "Not Available"));

    }

    @Override
    public String toString() {
        return "Item{" +
                "ID=" + this.getId() +
                ", title=" + this.getTitle() +
                ", rentalType=" + this.getRentalType() +
                ", loanType=" + this.getLoanType() +
                ", quantity=" + this.getQuantities() +
                ", fee=" + this.getFee() +
                ", genres=" + genres +
                "}";
    }
}
