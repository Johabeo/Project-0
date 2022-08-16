import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
import java.util.ResourceBundle;

public class ConnectionFactory {
    private static Connection connection = null;

    private ConnectionFactory() {

    }

    public static Connection getConnection() {
//        if (connection == null) {
//            ResourceBundle resourceBundle = ResourceBundle.getBundle("dbConfig");
//            String url = resourceBundle.getString("url");
//            String username = resourceBundle.getString("username");
//            String password = resourceBundle.getString("password");
//            try {
//                connection = DriverManager.getConnection(url, username, password);
//            } catch (SQLException e) {
//                throw new RuntimeException(e);
//            }
//        }

        try(InputStream input = new FileInputStream("C:\\Users\\JohnM\\Documents\\Training\\Kotlin-IntelliJ\\Project_0\\src\\main\\java\\dbConfig.properties")){
        Properties prop = new Properties(); prop.load(input);
        String driver = prop.getProperty("driver");
        String url = prop.getProperty("url");
        String user = prop.getProperty("user");
        String password = prop.getProperty("password");
        connection = DriverManager.getConnection(url, user, password); } catch (Exception e) {
        e.printStackTrace();

    }

        return connection;
    }
}

