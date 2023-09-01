package deas.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static deas.utilities.sql.Account.insertAccount;
import deas.utilities.sql.Account.AccountType;

public class App {

    public static void main(String[] args) {


        String userName = "postgres";
        String password = "mysecretpassword";
        String url = new StringBuilder("jdbc:postgresql://localhost:5432/postgres").toString();

        try(Connection conn = DriverManager.getConnection(url, userName, password)){

           int inserted = insertAccount(conn, "Barclays", AccountType.INCOME);
            System.out.println(inserted);

            
        } catch (SQLException e){
            System.out.println(e);
        }

        System.out.println("Hello!");
    }

}
