package Customer;

import Accounts.AccountInfo;

import java.sql.SQLException;
import java.util.List;

public interface CustomerDAO {

    public int getmobile_banking(String lastName, String SSN) throws SQLException;
    public Boolean updatePassword(String email, String password, String ssn) throws SQLException;
    public String ifLoginSuccessGetLastName(String email, String password) throws SQLException;
    public Boolean addCustomer(Customer c, AccountInfo account) throws SQLException ;
    public Customer getAccount(String last_name, String ssn) throws SQLException;
    public Boolean employeeLogin(String eemail, String password) throws SQLException;
    public List<Customer> printcustomers(String email) throws SQLException;


}
