package databaseActioners;


import objectDefinition.Accomodation;
import objectDefinition.RoomFair;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseReaderTest {
    DatabaseReader databaseReader = new DatabaseReader();
    Accomodation accomodation1, accomodation2, accomodation3;
    RoomFair roomFair1, roomFair2, roomFair3;
    Connection conn;

    @Before
    public void setUp() throws Exception {
        DatabaseConnector databaseConnector = new DatabaseConnector(
                "postgresql",
                "localhost",
                5432,
                "booking",
                "~~your username here~~",
                "~~your password here~~"
        );
        conn = databaseConnector.connectToDatabase();
        accomodation1 = new Accomodation(99, "Medium Room", "double ", 2, "A room fit for two people");
        accomodation2 = new Accomodation(100, "Big Room", "bunk-beds", 6, "A room fit for traveling people!");
        accomodation3 = new Accomodation(101, "Big Room", "bunk-beds", 6, "A room fit for traveling people!");
        roomFair1 = new RoomFair(99, 20.39, "Winter");
        roomFair2 = new RoomFair(100, 55.32, "All-Season");
        roomFair3 = new RoomFair(101, 55.32, "All-Season");

    }

    @Test
    public void listDataFromDb() {
        try {
            databaseReader.listAccomodationPrices(conn);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }


}