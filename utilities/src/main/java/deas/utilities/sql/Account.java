package deas.utilities.sql;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class Account {
    
    public enum AccountType {
        ASSET,
        LIABILITY,
        EQUITY,
        INCOME,
        EXPENSE
    }

    public static int insertAccount(Connection connection, String name, AccountType type) throws SQLException {
        Statement stmt = connection.createStatement();

        String accountTypeString = type.toString().substring(0, 1).toUpperCase() + type.toString().substring(1).toLowerCase();

        String sqlInsert = String.format("""
                    INSERT INTO account (type, name)
                    VALUES (
                        '%s',
                        '%s'
                    );
                """, 
                accountTypeString,
                name);
        return stmt.executeUpdate(sqlInsert);
    }
}