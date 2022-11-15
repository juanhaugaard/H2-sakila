package com.tayrona.sakila.procedures;

import org.h2.tools.TriggerAdapter;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeforeUpdateFilmTrigger extends TriggerAdapter {
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
    public void fire(Connection conn, ResultSet oldRow, ResultSet newRow) throws SQLException {
        ResultSetMetaData metaData = newRow.getMetaData();
        int barcodeColumnIndex = newRow.findColumn("BARCODE");
        String barcode = newRow.getString(barcodeColumnIndex);
        int width = metaData.getPrecision(barcodeColumnIndex);
        String paddedBarcode = StringUtils.lPad(barcode.trim(), width, '0');
        newRow.updateString(barcodeColumnIndex, paddedBarcode);
    }
}
