import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement; // Allow the access to the DB.

public class Principal {
    public static void main(String[] args) {
        final String user = "yourusername";
        final String password = "yourpassword";
        final String url = "jdbc:postgresql://localhost:yourlocalhost/postgres";
        final String sql = "SELECT * FROM people";
        final String driverDB = "org.postgresql.Driver";

        try {
            Class.forName(driverDB);
            Connection connection = DriverManager.getConnection(url, user, password);

            Statement syntax = connection.createStatement();

            ResultSet rs = syntax.executeQuery(sql);

            while (rs.next()){
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("Id: " + id + " - Name: " + name);
            }


            System.out.println("Connected with postgresql");
        } catch (Exception exception){
            System.out.println("You can't reach the postgresql database.");
        }

    }

}
