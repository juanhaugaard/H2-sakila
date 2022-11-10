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
public class Reverse extends AbstractRoutine<String> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>PUBLIC.REVERSE.RETURN_VALUE</code>.
     */
    public static final Parameter<String> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", SQLDataType.VARCHAR, false, false);

    /**
     * The parameter <code>PUBLIC.REVERSE.P1</code>.
     */
    public static final Parameter<String> P1 = Internal.createParameter("P1", SQLDataType.VARCHAR(1000000000), false, false);

    /**
     * Create a new routine call instance
     */
    public Reverse() {
        super("REVERSE", Public.PUBLIC, SQLDataType.VARCHAR);

        setReturnParameter(RETURN_VALUE);
        addInParameter(P1);
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
}