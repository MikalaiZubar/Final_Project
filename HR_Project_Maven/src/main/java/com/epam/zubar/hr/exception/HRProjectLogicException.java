package com.epam.zubar.hr.exception;

/**
 * represents a general Logic exception.
 * @author Mikalay Zubar
 *
 */
public class HRProjectLogicException extends Exception{

    private static final long serialVersionUID = -8396868242601955689L;

    public HRProjectLogicException(){
    }

    public HRProjectLogicException(String message){
        super(message);
    }

    public HRProjectLogicException(String message, Throwable exception){
        super(message, exception);
    }

    public HRProjectLogicException(Throwable exception){
        super(exception);
    }

}
