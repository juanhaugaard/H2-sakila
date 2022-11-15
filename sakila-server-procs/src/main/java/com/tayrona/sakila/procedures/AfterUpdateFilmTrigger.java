package com.tayrona.sakila.procedures;

import org.h2.tools.TriggerAdapter;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Objects;

public class AfterUpdateFilmTrigger extends TriggerAdapter {
    /**
     * This method is called for each triggered action by the default
     * fire(Connection conn, Object[] oldRow, Object[] newRow) method.
     * <p>
     * For "before" triggers, the new values of the new row may be changed
     * using the ResultSet.updateX methods.
     * </p>
     *
     * @param connection a connection to the database
     * @param oldRow the old row, or null if no old row is available (for INSERT)
     * @param newRow the new row, or null if no new row is available (for DELETE)
     * @throws SQLException if the operation must be undone
     */
    @Override
    public void fire(Connection connection, ResultSet oldRow, ResultSet newRow) throws SQLException {
        long filmId = newRow.getLong("film_id");
        String title = newRow.getString("title");
        String description = newRow.getString("description");
        if (!isEqual(title,oldRow.getString("title")) ||
                !isEqual(description,oldRow.getString("description"))) {


            String sql = "UPDATE FILM_TEXT SET title=?, description=? WHERE film_id=?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setString(1, title);
                preparedStatement.setString(2, description);
                preparedStatement.setLong(3, filmId);
                preparedStatement.executeUpdate();
            }
        }
    }
    private boolean isEqual(String left, String right) {
        if (Objects.isNull(left) || Objects.isNull(right)) {
            return false;
        }
        return left.equals(right);
    }
}
