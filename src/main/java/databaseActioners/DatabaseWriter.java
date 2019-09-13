package databaseActioners;

import objectDefinition.Accomodation;
import objectDefinition.RoomFair;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DatabaseWriter {
    public void addAccomodationsToDatabase(Accomodation accomodation, Connection conn) throws SQLException {


        try (PreparedStatement stmt1 = conn
                .prepareStatement("INSERT INTO accomodation(id, type, bed_type, max_guests, description) VALUES (?, ?, ?, ?, ?)")) {
            stmt1.setInt(1, accomodation.getId());
            stmt1.setString(2, accomodation.getType());
            stmt1.setString(3, accomodation.getBed_type());
            stmt1.setInt(4, accomodation.getMax_guests());
            stmt1.setString(5, accomodation.getDescription());
            stmt1.executeUpdate();
        }
    }

    public void addRoomFairs(RoomFair roomFair, Connection conn) throws SQLException {

        try (PreparedStatement stmt2 = conn
                .prepareStatement("INSERT INTO room_fair(id, value, season) VALUES(?, ?, ?)")) {
            stmt2.setInt(1, roomFair.getId());
            stmt2.setDouble(2, roomFair.getValue());
            stmt2.setString(3, roomFair.getSeason());
            stmt2.executeUpdate();
        }
    }

    public void addRelation(Accomodation accomodation, RoomFair roomFair, Connection conn) throws SQLException {
        try (PreparedStatement stmt3 = conn
                .prepareStatement("INSERT INTO accomodation_fair_relation(id_accomodation, id_room_fair) VALUES(?, ?)")) {
            stmt3.setInt(1, accomodation.getId());
            stmt3.setInt(2, roomFair.getId());
            stmt3.executeUpdate();
        }
    }


}
