import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    void addAccount(int balance, int userId) throws SQLException;
    void updateAccount(Account account) throws SQLException;
    void deleteAccount(int id) throws SQLException;
    List<Account> getAccounts() throws SQLException;
    Account getAccountById(int id) throws SQLException;
    int getAccountBalanceById(int id) throws SQLException;
}
