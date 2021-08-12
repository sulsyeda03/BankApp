package Customer;

import Accounts.AccountInfo;
import Main.ConnectionFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class CustomerDAOimpl implements CustomerDAO {

    private static Statement statement = null;
    Connection connection = null;
    Customer customer = new Customer();

    public CustomerDAOimpl() {
        try {
            this.connection = ConnectionFactory.getConnection();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    @Override
    public int getmobile_banking(String lastName, String ssn) throws SQLException {
        String sql = "SELECT mobile_banking from customers where last_name = '" + lastName + "' and ssn = '" + ssn + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();

        try {
            if (resultSet != null) {
                String mobile_banking = resultSet.getString(1);
                if (mobile_banking.equals("Y")) {
                    return 1;
                }else {
                    return 2;
                }
            }
        } catch (SQLException e) {
            System.out.println("Loading.........");
        }
        return 3;
    }

    @Override
    public Boolean updatePassword(String email, String password, String ssn) throws SQLException {
        String sql = "update customers set password = '" + password + "',mobile_banking = 'Y' where email = '" + email + "' and SSN = '" + ssn + "'";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            return true;
        } else {
            return false;
        }
    }

    public String ifLoginSuccessGetLastName(String email, String password) throws SQLException {
        String sql = "SELECT last_name from customers where email = '" + email + "' and password = '" + password + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        // email and password exist - validation passsed
        try {
            if (resultSet != null) {
                String lastName = resultSet.getString(1);
                return lastName;
            }
        } catch (SQLException e) {
            System.out.println("Loading.........");
        }
        return null;
    }

    @Override
    public Boolean addCustomer(Customer c, AccountInfo account) throws SQLException {
        String sql = "INSERT INTO pending_customers ( first_name , last_name , ssn , date_of_birth ," +
                " email , contact_number , street_addr , city , state , country , zip , password, opening_balance,account_type ) " +
                "VALUES (? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ? , ?, ? , ?)";

        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, c.getFirstName());
            preparedStatement.setString(2, c.getLastName());
            preparedStatement.setString(3, c.getSsn());
            preparedStatement.setDate(4, (Date) c.getDateOfBirth());
            preparedStatement.setString(5, c.getEmail());
            preparedStatement.setString(6, c.getContactNo());
            preparedStatement.setString(7, c.getStreet());
            preparedStatement.setString(8, c.getCity());
            preparedStatement.setString(9, c.getState());
            preparedStatement.setString(10, c.getCountry());
            preparedStatement.setInt(11, c.getZip());
            preparedStatement.setString(12, c.getPassword());
            preparedStatement.setDouble(13, account.getOpeningBalance());
            preparedStatement.setString(14, account.getAccount_type());

            int count = preparedStatement.executeUpdate();
            if (count > 0)
                return true;

        } catch (Exception e) {
            System.out.println("OOOps! ");
        }
        return false;

    }

    public Customer getAccount(String last_name, String ssn) throws SQLException {
        Customer custInfo = new Customer();

        try {
            String sql = "select * from customers where last_name = '" + last_name + "' and ssn = '" + ssn + "'";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {

                int cust_id = resultSet.getInt(1);
                String email = resultSet.getString(2);
                String password = resultSet.getString(3);
                String firstName = resultSet.getString(4);
                String lastName = resultSet.getString(5);
                String ssN = resultSet.getString(6);
                java.util.Date dateOfBirth = resultSet.getDate(7);
                String contactNo = resultSet.getString(8);
                String street = resultSet.getString(9);
                String city = resultSet.getString(10);
                String state = resultSet.getString(11);
                int zip = resultSet.getInt(12);
                String country = resultSet.getString(13);
                String mobile_banking = resultSet.getString(14);
            }

        } catch (Exception e) {
            e.printStackTrace();
        }return custInfo;
    }

    public Boolean employeeLogin(String eemail, String password) throws SQLException {
        String sql = "SELECT * from employee where username = '" + eemail + "' and " +
                "password = '" + password + "'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);

        if (resultSet != null)
            return true;
        else
            return false;
    }
    public List<Customer> printcustomers(String echeck) throws SQLException {
        List<Customer> cust = new LinkedList<>();


            String sql = "select * from customers where email = '"+echeck+"'";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);

                while (resultSet.next()) {

                    int cust_id = resultSet.getInt(1);
                    String email = resultSet.getString(2);
                    String password = resultSet.getString(3);
                    String firstName = resultSet.getString(4);
                    String lastName = resultSet.getString(5);
                    String ssN = resultSet.getString(6);
                    java.util.Date dateOfBirth = resultSet.getDate(7);
                    String contactNo = resultSet.getString(8);
                    String street = resultSet.getString(9);
                    String city = resultSet.getString(10);
                    String state = resultSet.getString(11);
                    int zip = resultSet.getInt(12);
                    String country = resultSet.getString(13);
                    String mobile_banking = resultSet.getString(14);

                    Customer custt = new Customer(cust_id,email,password,firstName,lastName,ssN,
                            dateOfBirth,contactNo,street,city,state,zip,country,mobile_banking);
                    cust.add(custt);

            }return cust;
    }


}
