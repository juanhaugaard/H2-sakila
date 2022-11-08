/*
 * This file is generated by jOOQ.
 */
package com.tayrona.sakila.data.generated.routines;


import com.tayrona.sakila.data.generated.Sakila;

import org.jooq.Field;
import org.jooq.Parameter;
import org.jooq.impl.AbstractRoutine;
import org.jooq.impl.Internal;
import org.jooq.impl.SQLDataType;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class _GroupConcat extends AbstractRoutine<String> {

    private static final long serialVersionUID = 1L;

    /**
     * The parameter <code>SAKILA._GROUP_CONCAT.RETURN_VALUE</code>.
     */
    public static final Parameter<String> RETURN_VALUE = Internal.createParameter("RETURN_VALUE", SQLDataType.VARCHAR, false, false);

    /**
     * The parameter <code>SAKILA._GROUP_CONCAT.P1</code>.
     */
    public static final Parameter<String> P1 = Internal.createParameter("P1", SQLDataType.VARCHAR(1000000000), false, false);

    /**
     * The parameter <code>SAKILA._GROUP_CONCAT.P2</code>.
     */
    public static final Parameter<String> P2 = Internal.createParameter("P2", SQLDataType.VARCHAR(1000000000), false, false);

    /**
     * Create a new routine call instance
     */
    public _GroupConcat() {
        super("_GROUP_CONCAT", Sakila.SAKILA, SQLDataType.VARCHAR);

        setReturnParameter(RETURN_VALUE);
        addInParameter(P1);
        addInParameter(P2);
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
}