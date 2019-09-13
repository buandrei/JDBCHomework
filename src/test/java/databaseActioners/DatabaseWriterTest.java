package databaseActioners;

import objectDefinition.Accomodation;
import objectDefinition.RoomFair;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.runners.statements.ExpectException;
import org.junit.rules.ExpectedException;

import javax.xml.crypto.Data;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.*;

public class DatabaseWriterTest {
    DatabaseWriter databaseWriter = new DatabaseWriter();
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

    //Used to clear the database from previously inserted values
    public static void clearDatabase(Accomodation accomodation, RoomFair roomFair, Connection conn) {
        try (PreparedStatement stmt4 = conn
                .prepareStatement("DELETE FROM accomodation_fair_relation WHERE id_accomodation = " + accomodation.getId() +
                        " ; DELETE FROM accomodation WHERE id = " + accomodation.getId() +
                        " ; DELETE FROM room_fair WHERE id = " + roomFair.getId())) {
            stmt4.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Failed to insert relation: " + e.getMessage());
        }
    }

    @Test
    public void addDataToTable() throws SQLException {
        databaseWriter.addAccomodationsToDatabase(accomodation1, conn);
        databaseWriter.addAccomodationsToDatabase(accomodation2, conn);
        databaseWriter.addAccomodationsToDatabase(accomodation3, conn);
        databaseWriter.addRoomFairs(roomFair1, conn);
        databaseWriter.addRoomFairs(roomFair2, conn);
        databaseWriter.addRoomFairs(roomFair3, conn);
        databaseWriter.addRelation(accomodation1, roomFair1, conn);
        databaseWriter.addRelation(accomodation2, roomFair2, conn);
        databaseWriter.addRelation(accomodation3, roomFair3, conn);

        //to clear data just decomment
//        clearDatabase(accomodation1, roomFair1, conn);
//        clearDatabase(accomodation2, roomFair2, conn);
//        clearDatabase(accomodation3, roomFair3, conn);
    }

    @Test(expected = SQLException.class)
    public void testWithduplicateData() throws SQLException {
        databaseWriter.addAccomodationsToDatabase(accomodation1, conn);
        databaseWriter.addAccomodationsToDatabase(accomodation1, conn);
        fail("should have thrrown duplicate exception");
    }


}