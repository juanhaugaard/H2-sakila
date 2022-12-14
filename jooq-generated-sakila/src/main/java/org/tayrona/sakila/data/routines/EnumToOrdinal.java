/*
 * This file is generated by jOOQ.
 */
package org.tayrona.sakila.data.routines;


import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;
import org.tayrona.sakila.data.Public;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class EnumToOrdinal extends AbstractRoutine<Integer> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>PUBLIC.ENUM_TO_ORDINAL.RETURN_VALUE</code>.
     */
    public static final Parameter<Integer> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", SQLDataType.INTEGER, false, false);

    /**
     * The parameter <code>PUBLIC.ENUM_TO_ORDINAL.P1</code>.
     */
    public static final Parameter<String> P1 = Internal.createParameter("P1", SQLDataType.VARCHAR(1000000000), false, false);

    /**
     * The parameter <code>PUBLIC.ENUM_TO_ORDINAL.P2</code>.
     */
    public static final Parameter<String> P2 = Internal.createParameter("P2", SQLDataType.VARCHAR(1000000000), false, false);

    /**
     * The parameter <code>PUBLIC.ENUM_TO_ORDINAL.P3</code>.
     */
    public static final Parameter<String> P3 = Internal.createParameter("P3", SQLDataType.VARCHAR(1000000000), false, false);

    /**
     * The parameter <code>PUBLIC.ENUM_TO_ORDINAL.P4</code>.
     */
    public static final Parameter<String> P4 = Internal.createParameter("P4", SQLDataType.VARCHAR(1000000000), false, false);

    /**
     * Create a new routine call instance
     */
    public EnumToOrdinal() {
        super("ENUM_TO_ORDINAL", Public.PUBLIC, SQLDataType.INTEGER);

        setReturnParameter(RETURN_VALUE);
        addInParameter(P1);
        addInParameter(P2);
        addInParameter(P3);
        addInParameter(P4);
    }

    /**
     * Set the <code>P1</code> parameter IN value to the routine
     */
    public void setP1(String value) {
        setValue(P1, value);
    }

    /**
     * Set the <code>P1</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    public void setP1(Field<String> field) {
        setField(P1, field);
    }

    /**
     * Set the <code>P2</code> parameter IN value to the routine
     */
    public void setP2(String value) {
        setValue(P2, value);
    }

    /**
     * Set the <code>P2</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    public void setP2(Field<String> field) {
        setField(P2, field);
    }

    /**
     * Set the <code>P3</code> parameter IN value to the routine
     */
    public void setP3(String value) {
        setValue(P3, value);
    }

    /**
     * Set the <code>P3</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    public void setP3(Field<String> field) {
        setField(P3, field);
    }

    /**
     * Set the <code>P4</code> parameter IN value to the routine
     */
    public void setP4(String value) {
        setValue(P4, value);
    }

    /**
     * Set the <code>P4</code> parameter to the function to be used with a
     * {@link org.jooq.Select} statement
     */
    public void setP4(Field<String> field) {
        setField(P4, field);
    }
}
