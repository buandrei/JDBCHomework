package databaseActioners;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseReader {

    public void listAccomodationPrices(Connection conn) throws SQLException {

        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery( " SELECT COALESCE(accomodation.description, $$$$) AS desc," +
                                                    "        COALESCE(room_fair.value , 0) AS room_value" +
                                                    " FROM accomodation_fair_relation " +
                                                    "   LEFT JOIN accomodation ON accomodation.id = accomodation_fair_relation.id_accomodation " +
                                                    "   LEFT JOIN room_fair ON room_fair.id = accomodation_fair_relation.id_room_fair ")) {
            while (rs.next()) {
                System.out.println("Room description: " + rs.getString("desc") + " at the price of :  " + rs.getString("room_value"));
            }
        }
    }

}
