package com.tayrona.sakila.procedures;

import org.h2.tools.TriggerAdapter;

import java.io.PrintStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.Objects;

public class TestTrigger extends TriggerAdapter {
    private final PrintStream out = System.out;

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
        log("fire() invoked");
        StringBuilder msg = new StringBuilder("connection:").append(Objects.nonNull(connection));
        msg.append(" oldRow:").append(Objects.nonNull(oldRow));
        msg.append(" newRow:").append(Objects.nonNull(newRow));
        log(msg.toString());
        if (Objects.nonNull(oldRow)) {
            msg = new StringBuilder("oldRow>");
            ResultSetMetaData metaData = oldRow.getMetaData();
            for (int i=1; i<=metaData.getColumnCount(); i++) {
                msg.append(" ").append(metaData.getColumnLabel(i)).append(":").append(oldRow.getString(i));
            }
            log(msg.toString());
        }
        if (Objects.nonNull(newRow)) {
            msg = new StringBuilder("newRow>");
            ResultSetMetaData metaData = newRow.getMetaData();
            for (int i=1; i<=metaData.getColumnCount(); i++) {
                msg.append(" ").append(metaData.getColumnLabel(i)).append(":").append(newRow.getString(i));
            }
            log(msg.toString());
        }
    }

    @Override
    public void close() throws SQLException {
        log("close() invoked");
        super.close();
    }

    @Override
    public void remove() throws SQLException {
        log("remove() invoked");
        super.remove();
    }

    private void log(final String msg) {
        String threadName = Thread.currentThread().getName();
        String className =  getClass().getSimpleName();
        out.println(threadName+" : "+ className+" => "+msg);
    }
}
