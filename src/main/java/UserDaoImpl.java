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
        String sql = "insert into employee (name, email) values (?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getEmail());
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("employee saved");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public void updateUser(User user) throws SQLException {
        String sql = "Update employee set name = ?, email = ? where id = ?";
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
    public void deleteUser(int id) throws SQLException {
        String sql = "delete from employee where id = ?";
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setInt(1, id);
        int count = preparedStatement.executeUpdate();
        if (count > 0) {
            System.out.println("employee updated");
        } else {
            System.out.println("OOps!, something went wrong");
        }
    }

    @Override
    public List<User> getUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String sql = "select * from employee";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            User user = new User(id, name, email);
            users.add(user);
        }
        return users;
    }

    @Override
    public User getUserById(int empId) throws SQLException {
        User user = new User();
        String sql = "select * from employee where id = " + empId;
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        resultSet.next();
        if (resultSet != null) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            user = new User(id, name, email);
        }else{
            System.out.println("no rescord found");
        }
        return user;
    }
}
