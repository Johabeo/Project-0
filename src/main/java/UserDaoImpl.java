import java.sql.*;
        import java.util.ArrayList;
        import java.util.List;

public class UserDaoImpl implements UserDao {

    Connection connection;

    public UserDaoImpl() {
        connection = ConnectionFactory.getConnection();
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "insert into user (username, email, password) values (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setString(3, user.getPassword());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Login Saved");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "Update user set username = ?, email = ? where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        preparedStatement.setInt(3, user.getId());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("employee updated");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void upgradeUser(String name, String email) throws SQLException {
        String sql = "Update user set loginType = 2 where email = ? and password = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("Welcome to the team!");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void deleteUser(int id) throws SQLException {
        String sql = "delete from user where userId = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("user updated");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from user";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            User user = new User(id, name, email, password);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserById(int empId) throws SQLException {
        User user = new User();
        String sql = "select * from user where userId = " + empId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            user = new User(id, name, email, password);
        }else{
            System.out.println("no record found");
        }
        return user;
    }

    @Override
    public int Login(String email, String password) throws SQLException {
        User user = new User();
        String sql = "select * from user where email = '" + email + "' and password = '" + password +"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int loginType = resultSet.getInt(5);
            if (loginType == 1) {
                return 1;
            } else if (loginType == 2) {
                return 2;
            }
        }
        return 3;
    }

    @Override
    public int getId(String email, String password) throws SQLException {
        User user = new User();
        String sql = "select * from user where email = '" + email + "' and password = '" + password +"'";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        int id = resultSet.getInt(1);
        return id;
    }
}
