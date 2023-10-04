package deas.app;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import static deas.utilities.sql.Account.insertAccount;
import deas.utilities.sql.Account.AccountType;
import deas.parser.*;

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



        Parser parser = new Parser();

        ASTNode expected = new ASTNode(new Token(Integer.MAX_VALUE));
        ASTNode actual = parser.parse("2147483647");

        ASTNode expected2 = new ASTNode(new Token(1117));
        ASTNode actual2 = parser.parse("1117");


        ASTNode expected3 = new ASTNode(new Token("\"cool string\""));
        ASTNode actual3 = parser.parse("\"cool string\"");

        System.out.println(expected.getValue());
        System.out.println(actual.getValue());
        System.out.println(expected2.getValue());
        System.out.println(actual2.getValue());
        System.out.println(expected3.getValue());
        System.out.println(actual3.getValue());

    }


}
