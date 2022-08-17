import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    Connection connection;

    public AccountDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addAccount(int balance) throws SQLException {
        String sql = "insert into accounts (balance) values (?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account Made");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void updateAccount(Account account) throws SQLException {
        String sql = "Update user set balance = ?, status = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, account.getBalance());
        preparedStatement.setInt(2, account.getStatus());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account updated");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void deleteAccount(int id) throws SQLException {
        String sql = "delete from accounts where userId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("account deleted");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public List<Account> getAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int name = resultSet.getInt(2);
            int email = resultSet.getInt(3);
            int password = resultSet.getInt(4);
            Account account = new Account(id, name, email, password);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int id) throws SQLException {
        Account account = new Account();
        String sql = "select * from user where userId = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int Id = resultSet.getInt(1);
            int name = resultSet.getInt(2);
            int email = resultSet.getInt(3);
            int password = resultSet.getInt(4);
            account = new Account(id, name, email, password);
        }else{
            System.out.println("no record found");
        }
        return account;
    }
}