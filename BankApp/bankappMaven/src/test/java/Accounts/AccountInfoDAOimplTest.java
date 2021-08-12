package Accounts;

import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

class AccountInfoDAOimplTest {


    @Test
    public void getAccountTest() throws SQLException {

        AccountInfoDAOimpl acc = new AccountInfoDAOimpl();
        List<AccountInfo> actualResult = acc.getAccount("PATRICIA.JOHNSON@customer.org","jhklkl");
        List<AccountInfo> expectedResult = new LinkedList<>();
        AccountInfo ac = new AccountInfo();
        ac.setCustomerID(2);
        ac.setAccount_type("Savings Account");
        ac.setAccountNumber(789456);
        ac.setCurrentBalance(147.99);
    }


}