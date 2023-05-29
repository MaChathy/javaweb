package com.fisher.myssm.basedao.exceptions;

/**
 *
 * @author fisher
 * @version 1.0.1 2023/5/30 - 22:39
 */
public class DaoException extends RuntimeException{
    public DaoException(String message){
        super(message);
    }
}
