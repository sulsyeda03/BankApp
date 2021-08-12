package Accounts;

public class AccountInfo {

    private Integer customerID;
    private Double currentBalance;
    private Integer accountNumber;
    private String account_type;
    private Double openingBalance;

    //default constructor
    public AccountInfo() {
        this.customerID = customerID;
        this.currentBalance = currentBalance;
        this.accountNumber = accountNumber;
        this.account_type = account_type;
        this.openingBalance = openingBalance;
    }
// parameterized constructor
    public AccountInfo(Integer cust_id, Double currentBalance, Integer accountNumber, String account_type) {
        this.customerID = customerID;
        this.currentBalance = currentBalance;
        this.accountNumber = accountNumber;
        this.account_type = account_type;
    }

    //getters and setters
    public Integer getCustomerID() {
        return customerID;
    }

    public void setCustomerID(Integer customerID) {
        this.customerID = customerID;
    }

    public Double getCurrentBalance() {
        return currentBalance;
    }

    public void setCurrentBalance(Double currentBalance) {
        this.currentBalance = currentBalance;
    }

    public Integer getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Integer accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getAccount_type() {
        return account_type;
    }

    public void setAccount_type(String account_type) {
        this.account_type = account_type;
    }

    public Double getOpeningBalance() {
        return openingBalance;
    }

    public void setOpeningBalance(double openingBalance) {
        this.openingBalance = openingBalance;
    }
}
