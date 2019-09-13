package databaseActioners;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {

    private String dbType;
    private String domainAdress;
    private int port;
    private String database;
    private String user;
    private String userPassword;

    public DatabaseConnector(String dbType, String domainAdress, int port, String database, String user, String userPassword) {
        this.dbType = dbType;
        this.domainAdress = domainAdress;
        this.port = port;
        this.database = database;
        this.user = user;
        this.userPassword = userPassword;
    }

    public  Connection connectToDatabase() {
        try {
            Class.forName("org.postgresql.Driver").newInstance();
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException e) {
            System.err.println("Something went wrong.");
            System.err.println(e.getMessage());
        }

        Connection conn = null;
        DriverManager.setLoginTimeout(120);

        try {
            String url = new StringBuilder()
                    .append("jdbc:")
                    .append(dbType)
                    .append("://")
                    .append(domainAdress)
                    .append(":")
                    .append(port)
                    .append("/")
                    .append(database)
                    .append("?user=")
                    .append(user)
                    .append("&password=")
                    .append(userPassword).toString();
            conn = DriverManager.getConnection(url);

        } catch (SQLException e) {

            System.err.println("Cannot connect to the database: " + e.getMessage());
        }

        if (conn == null) {
            System.out.println("No connection to the database.");
        }
        return conn;
    }

}
