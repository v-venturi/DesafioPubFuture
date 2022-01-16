package com.vventuri.desafiopubfuture.exceptions;

import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.io.Serial;

/**
 * The type Funds not avaliable exception.
 */
@Component
@ControllerAdvice
public class FundsNotAvaliableException extends Exception {
    @Serial
    private static final long serialVersionUID = 1L;

    /**
     * Instantiates a new Funds not avaliable exception.
     */
    public FundsNotAvaliableException(){
        super();
    }

    /**
     * Instantiates a new Funds not avaliable exception.
     *
     * @param msg the msg
     */

    public FundsNotAvaliableException(String msg){
        super(msg);

    }

    /**
     * Instantiates a new Funds not avaliable exception.
     *
     * @param msg   the msg
     * @param cause the cause
     */
    public FundsNotAvaliableException(String msg, Throwable cause){
        super(msg, cause);
    }

}

