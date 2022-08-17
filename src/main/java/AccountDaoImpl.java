import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountDaoImpl implements AccountDao {
    Connection connection;

    public AccountDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addAccount(int balance, int userId) throws SQLException {
        String sql = "insert into accounts (balance, userId) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, balance);
        preparedStatement.setInt(2, userId);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account Made");
        } else {
            System.out.println("oops!, something went wrong");
        }
    }

    @Override
    public void updateAccountBalance(int id, int modifier) throws SQLException {
        String sql = "Update accounts set balance = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, modifier);
        preparedStatement.setInt(2, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account updated");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void updateAccountTransfer(int id, int modifier) throws SQLException {
        String sql = "Update accounts set transfer = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, modifier);
        preparedStatement.setInt(2, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Account updated");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void updateAccountStatus(int id) throws SQLException {
        String sql = "Update accounts set status = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
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
    public List<Account> getAccounts(int queryId) throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from accounts where userId = " + queryId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int userId = resultSet.getInt(2);
            int status = resultSet.getInt(3);
            int balance = resultSet.getInt(4);
            Account account = new Account(id, userId, status, balance);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public List<Account> getPendingAccounts() throws SQLException {
        List<Account> accounts = new ArrayList<>();
        String sql = "select * from accounts where status = 1";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            int userId = resultSet.getInt(2);
            int status = resultSet.getInt(3);
            int balance = resultSet.getInt(4);
            Account account = new Account(id, userId, status, balance);
            accounts.add(account);
        }
        return accounts;
    }

    @Override
    public Account getAccountById(int id) throws SQLException {
        Account account = new Account();
        String sql = "select * from accounts where userId = " + id;
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

    @Override
    public int getAccountBalanceById(int id) throws SQLException {
        Account account = new Account();
        String sql = "select * from accounts where id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(4);
    }

    @Override
    public int getAccountTransferById(int id) throws SQLException {
        Account account = new Account();
        String sql = "select * from accounts where id = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(5);
    }

    @Override
    public int getAccountStatusById(int id) throws SQLException {
        Account account = new Account();
        String sql = "select * from accounts where userId = " + id;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        return resultSet.getInt(3);
    }
}