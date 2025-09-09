package Return;

import Customer.Customer;

public class RentCount {
    private String customerId;
    private int returnCount;
    private int rewardPoint;

    //----- Constructor -----

    public RentCount() {
        this.customerId = null;
        this.returnCount = 0;
        this.rewardPoint = 0;
    }

    public RentCount(String customerId, int returnCount, int rewardPoint) {
        this.customerId = customerId;
        this.returnCount = returnCount;
        this.rewardPoint = rewardPoint;
    }

    public RentCount(RentCount other) {
        this.customerId = other.customerId;
        this.returnCount = other.returnCount;
        this.rewardPoint = other.rewardPoint;
    }

    //----- Getter and Setter -----
    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public int getReturnCount() {
        return returnCount;
    }

    public void setReturnCount(int returnCount) {
        this.returnCount = returnCount;
    }

    public int getRewardPoint() {
        return rewardPoint;
    }

    public void setRewardPoint(int rewardPoint) {
        this.rewardPoint = rewardPoint;
    }

    //----- Function -----
    public void add10RewardPoint() {
        rewardPoint = rewardPoint + 10;
    }
    public void countReturn() {
        returnCount++;
    }
    public void updating(RentCount other) {
        this.customerId = other.customerId;
        this.returnCount = other.returnCount;
        this.rewardPoint = other.rewardPoint;
    }

    public void displaying() {
        System.out.println("Customer ID: " + getCustomerId());
        System.out.println("Return time: " + getReturnCount());
        System.out.println("Reward point: " + getRewardPoint());
    }

}
