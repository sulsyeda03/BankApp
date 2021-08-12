package Accounts;

import Main.ConnectionFactory;
import java.sql.*;
import java.util.LinkedList;
import java.util.List;

public class AccountInfoDAOimpl implements AccountInfoDAO {

    private static Statement statement = null;
    Connection connection = null;

    // default constructor
    public AccountInfoDAOimpl() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    // gets current balance by passing account number a one parameter and return type is Double
    public Double getCurrentBalanceByAccNumber(Integer account_number, String email) throws SQLException {

        // using join as account number and email are present in different tables
        String sql = "select current_balance from accounts JOIN customers where accounts.cust_id = customers.cust_id " +
                "and account_number = " + account_number+" and email = '"+email+"'";
        try {
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            Double balance = resultSet.getDouble(1);
            return balance;
        } }catch (Exception e){
            System.out.println(" Wrong input...");
        }return 0.0;
    }

    // returns all fields of Account (cust_id, currentBalance, accountNumber, account_type) in form of List
    public List<AccountInfo> getAccount(String email, String password) throws SQLException {
        List<AccountInfo> accList = new LinkedList<>();
        String sql = "select * from accounts JOIN customers " +
                "where accounts.cust_id = customers.cust_id and " +
                "customers.email = '" + email + "' and customers.password = '" + password + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            Integer cust_id = resultSet.getInt(1);
            Double currentBalance = resultSet.getDouble(2);
            Integer accountNumber = resultSet.getInt(3);
            String account_type = resultSet.getString(4);
            AccountInfo accSummary = new AccountInfo(cust_id, currentBalance, accountNumber, account_type);
        }
        return accList;
    }


    // update balance to newBalance for account_num = accNum
    public Boolean updateBalance(Double newBalance, Integer accNum) throws SQLException {

        String sql = "update accounts a join customers c on a.cust_id = c.cust_id " +
                "set current_balance = " + newBalance + " where account_number = " + accNum;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            return true;
        else
            return false;
    }

    // returns if Account Number exists or not by taking acc-Num as parameter
    public Boolean getAccountByAccountNumber(Integer account_number) throws SQLException {

        String sql = "select cust_id from accounts where account_number = " + account_number;
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();
            resultSet.next();
            if (resultSet != null) {
                return true;
            } }catch (Exception e){
            System.out.println(" Wrong input...");
        }
        return false;
    }

    // inserts row everytime transfer takes place and stores in
    // table transactions (sender_ac_number, receiver_ac_number,amount_transferred, transfer_status)
    public void addTransfer(Integer sender_ac_number, Integer receiver_ac_number, Double amount_transferred, String transfer_status) throws SQLException {
        String sql = "insert into transactions (sender_ac_number, receiver_ac_number,amount_transferred" +
                ", transfer_status) values (" + sender_ac_number + "," + receiver_ac_number + "," + amount_transferred + ",'" + transfer_status + "')";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            System.out.println("Transaction saved");
        else
            System.out.println("Oops! something went wrong");
    }

    //
    public int pendingTransferUpdate(int account_num, int account_num_r) throws SQLException {
        String sql = "select * from transactions where transfer_status = 'Pending Accept' and " +
                "sender_ac_number = " + account_num + " and receiver_ac_number = " + account_num_r;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            return count;
        else
            return 0;
    }

    public int statusUpdateafterTransfer(int account_num, int account_num_r, String transfer_status) throws SQLException {
        String sql = "update transactions set transfer_status = "+transfer_status+" where " +
                "sender_ac_number = " + account_num + " and receiver_ac_number = " + account_num_r;
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0)
            return count;
        else
            return 0;
    }


}



