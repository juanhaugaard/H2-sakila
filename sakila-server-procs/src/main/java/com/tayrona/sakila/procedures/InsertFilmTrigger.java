package com.tayrona.sakila.procedures;

import org.h2.tools.TriggerAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InsertFilmTrigger extends TriggerAdapter {
    /**
     * This method is called for each triggered action by the default
     * fire(Connection conn, Object[] oldRow, Object[] newRow) method.
     * <p>
     * For "before" triggers, the new values of the new row may be changed
     * using the ResultSet.updateX methods.
     * </p>
     *
     * @param conn a connection to the database
     * @param oldRow the old row, or null if no old row is available (for INSERT)
     * @param newRow the new row, or null if no new row is available (for DELETE)
     * @throws SQLException if the operation must be undone
     */
    @Override
    public void fire(Connection connection, ResultSet oldRow, ResultSet newRow) throws SQLException {
        String sql = "INSERT INTO SAKILA.FILM_TEXT(film_id, title, description) VALUES(?,?,?)";
        long filmId = newRow.getLong("film_id");
        String title = newRow.getString("title");
        String description = newRow.getString("description");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, filmId);
            preparedStatement.setString(2, title);
            preparedStatement.setString(3, description);
            preparedStatement.executeUpdate();
        }
    }
}
