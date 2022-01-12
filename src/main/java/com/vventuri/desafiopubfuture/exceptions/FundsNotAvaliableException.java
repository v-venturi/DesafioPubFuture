package com.vventuri.desafiopubfuture.exceptions;

import java.io.Serial;

public class FundsNotAvaliableException extends Exception{
    @Serial
    private static final long serialVersionUID = 1L;

    public FundsNotAvaliableException(){
        super();
    }

    public FundsNotAvaliableException(String msg){
        super(msg);
    }

public FundsNotAvaliableException(String msg, Throwable cause){
        super(msg, cause);
    }

}

