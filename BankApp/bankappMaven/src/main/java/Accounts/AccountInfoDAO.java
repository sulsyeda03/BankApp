package Accounts;

import java.sql.SQLException;
import java.util.List;

public interface AccountInfoDAO {

    // all the methods are implemented in AccountInfoDAOimpl

    public List<AccountInfo> getAccount(String email, String password) throws SQLException;
    public Double getCurrentBalanceByAccNumber(Integer account_number, String email) throws SQLException;
    public Boolean updateBalance(Double newBalance, Integer accNum) throws SQLException;
    public Boolean getAccountByAccountNumber(Integer account_number) throws SQLException;
    public void addTransfer(Integer sender_ac_number, Integer receiver_ac_number,Double amount_transferred, String transfer_status) throws SQLException;
    public int pendingTransferUpdate(int account_num, int account_num_r) throws SQLException;
    public int statusUpdateafterTransfer(int account_num, int account_num_r, String transfer_status) throws SQLException;

}
