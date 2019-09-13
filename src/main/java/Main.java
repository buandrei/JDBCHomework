import databaseActioners.DatabaseConnector;
import databaseActioners.DatabaseReader;
import databaseActioners.DatabaseWriter;
import objectDefinition.Accomodation;
import objectDefinition.RoomFair;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) {
        DatabaseConnector databaseConnector = new DatabaseConnector(
                "postgresql",
                "localhost",
                5432,
                "booking",
                "~~your username here~~",
                "~~your password here~~"
        );

        Connection conn = databaseConnector.connectToDatabase();

        addRecordsToDB(conn);
        listRecords(conn);


    }

    private static void addRecordsToDB(Connection conn) {
        Accomodation accomodation1 = new Accomodation(1, "Room", "matrimonial", 2, "A room fit for a married couple");
        Accomodation accomodation2 = new Accomodation(2, "Big Room", "bunk-beds", 6, "A room fit for traveling people!");
        Accomodation accomodation3 = new Accomodation(3, "Single Room", "single", 1, "Feeling lonely?");

        RoomFair roomFair1 = new RoomFair(1, 20.39, "Winter");
        RoomFair roomFair2 = new RoomFair(2, 55.32, "All-Season");
        RoomFair roomFair3 = new RoomFair(3, 12.11, "All-Season");

        List<Accomodation> accomodations = Arrays.asList(accomodation1, accomodation2, accomodation3);
        List<RoomFair> roomFairs = Arrays.asList(roomFair1, roomFair2, roomFair3);

        writeDatabaseInfo(accomodations, roomFairs, conn);

    }

    private static void writeDatabaseInfo(final List<Accomodation> accomodations, final List<RoomFair> roomFairs, final Connection connection) {
        final DatabaseWriter dbWriter = new DatabaseWriter();

        for (Accomodation list : accomodations) {
            try {
                dbWriter.addAccomodationsToDatabase(list, connection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        for (RoomFair list1 : roomFairs) {
            try {
                dbWriter.addRoomFairs(list1, connection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < accomodations.size(); i++) {
            try {
                dbWriter.addRelation(accomodations.get(i), roomFairs.get(i), connection);
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static void listRecords(Connection connection) {

        DatabaseReader databaseReader = new DatabaseReader();
        try {
            databaseReader.listAccomodationPrices(connection);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

}
