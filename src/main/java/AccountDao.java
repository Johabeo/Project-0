import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    void addAccount(int balance, int userId) throws SQLException;
    void updateAccountBalance(int id, int modifier) throws SQLException;
    void updateAccountTransfer(int id, int modifier) throws SQLException;
    void updateAccountStatus(int id) throws SQLException;
    void deleteAccount(int id) throws SQLException;
    List<Account> getAccounts(int queryId) throws SQLException;
    List<Account> getPendingAccounts() throws SQLException;
    Account getAccountById(int id) throws SQLException;
    int getAccountBalanceById(int id) throws SQLException;
    int getAccountTransferById(int id) throws SQLException;
    int getAccountStatusById(int id) throws SQLException;
}
