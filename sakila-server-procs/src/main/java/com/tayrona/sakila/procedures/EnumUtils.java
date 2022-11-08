package com.tayrona.sakila.procedures;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class EnumUtils {
    public static Integer mpaaRatingToOrdinal(Connection connection, String valueName) throws SQLException {
        return valueToOrdinal(connection, "MPAA_RATING", "DOMAIN", "TYPE", valueName);
    }
    public static Integer specialFeaturesToOrdinal(Connection connection, String valueName) throws SQLException {
        return valueToOrdinal(connection, "SPECIAL_FEATURES_ENUM", "DOMAIN", "TYPE", valueName);
    }
    public static Integer valueToOrdinal(Connection connection, String objectName, String objectType,
                                         String enumIdentifier, String valueName) throws SQLException {
        StringBuilder sql = new StringBuilder("SELECT VALUE_ORDINAL");
        sql.append(" FROM INFORMATION_SCHEMA.ENUM_VALUES");
        sql.append(" WHERE OBJECT_NAME=?");
        sql.append(" AND OBJECT_TYPE=?");
        sql.append(" AND ENUM_IDENTIFIER=?");
        sql.append(" AND VALUE_NAME=?");
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql.toString())) {
            preparedStatement.setString(1, objectName);
            preparedStatement.setString(2, objectType);
            preparedStatement.setString(3, enumIdentifier);
            preparedStatement.setString(4, valueName);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.first()) {
                int result = resultSet.getInt(1);
                if (!resultSet.wasNull()) {
                    return result;
                }
            }
        }
        return null;
    }
}
