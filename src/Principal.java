import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

//This code is to simulate a data persistence on database, CRUD.

public class Principal {
    public static void main(String[] args) {
        final String user = "yourusername";
        final String password = "yourpassword";
        final String url = "jdbc:postgresql://localhost:yourlocalhost/postgres";
        final String firstSql = "SELECT * FROM people";
        final String secondSql = "INSERT INTO PEOPLE(id,name) VALUES(?,?)";
        final String thirdSql = "UPDATE PEOPLE set name=? WHERE ID=?";
        final String fourthSql = "DELETE FROM PEOPLE WHERE ID = ?";
        final String driverDB = "org.postgresql.Driver";

        try {

            Class.forName(driverDB);
            Connection connection = DriverManager.getConnection(url, user, password);

            //Create
            PreparedStatement create = connection.prepareStatement(secondSql);
            create.setInt(1, 4); // Set the first ? on secondSql with 4
            create.setString(2, "Rodrigues"); // Set the second ? on secondSql with "Rodrigues"
            create.executeUpdate(); // Execute the insert
            System.out.println("Included name");


            //Read
            Statement syntax = connection.createStatement();
            ResultSet rs = syntax.executeQuery(firstSql);
            while (rs.next()) {
                int id = rs.getInt("id");
                String name = rs.getString("name");
                System.out.println("Id: " + id + " - Name: " + name);
            }


            //Update
            PreparedStatement update = connection.prepareStatement(thirdSql);
            update.setString(1, "Mateus");
            update.setInt(2, 1);
            update.executeUpdate();
            System.out.println("Update done");

            //Delete
            PreparedStatement delete = connection.prepareStatement(fourthSql);
            delete.setInt(1, 4);
            delete.executeUpdate();
            System.out.println("Name deleted from DB");


            System.out.println("Connected with postgreSQL");
        } catch (Exception exception) {
            exception.printStackTrace();
            //System.out.println("You can't reach the postgresql database.");
        }

    }

}
