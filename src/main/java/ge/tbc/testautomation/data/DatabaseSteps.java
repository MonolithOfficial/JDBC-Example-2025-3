package ge.tbc.testautomation.data;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DatabaseSteps {
    public List<UserModel> getAllUsers() {
        List<UserModel> users = new ArrayList<>();
        try (Connection connection = MSSQLConnection.getConnection()) {
            String SQL = "SELECT * FROM Users2";
            Statement statement = connection.createStatement();

            ResultSet resultSet = statement.executeQuery(SQL);
            while (resultSet.next()) {
                users.add(new UserModel(resultSet.getString("Username"), resultSet.getString("PasswordHash")));
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return users;
    }

    public void createUser() {
        try (Connection connection = MSSQLConnection.getConnection()) {
            String SQL = """
                    INSERT INTO Users2 (Username, PASSWORDHASH)
                    VALUES (?, ?);
                    """;

            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            String username = "some_kind_of_user";
            String password = "not_secret_sauce";

            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void updateUser() {
        try (Connection connection = MSSQLConnection.getConnection()) {
            String SQL = "UPDATE Users2\n" +
                    "SET Username = ?, PasswordHash = HASHBYTES('SHA2_256', ?)\n" +
                    "    WHERE Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            String oldUsername = "different_user";
            String newUsername = "very_different_user";
            String newPassword = "more_secret_sauce";
            preparedStatement.setString(1, newUsername);
            preparedStatement.setString(2, newPassword);
            preparedStatement.setString(3, oldUsername);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    public void deleteUser() {
        try (Connection connection = MSSQLConnection.getConnection()) {
            String SQL = "DELETE FROM Users2 WHERE Username = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(SQL);
            String username = "very_different_user";
            preparedStatement.setString(1, username);

            int affectedRows = preparedStatement.executeUpdate();
            System.out.println(affectedRows);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
