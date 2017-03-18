package com.epam.zubar.hr.exception;

/**
 * Represents a general DAO exception.
 * @author Mikalay Zubar
 *
 */
public class HRProjectDAOException extends Exception{

    private static final long serialVersionUID = 9036006766027405554L;

    public HRProjectDAOException(){
    }

    public HRProjectDAOException(String message){
        super(message);
    }

    public HRProjectDAOException(String message, Throwable exception){
        super(message, exception);
    }

    public HRProjectDAOException(Throwable exception){
        super(exception);
    }

}
