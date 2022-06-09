package com.company.exp;

import org.springframework.jdbc.core.SqlReturnType;

public class InformationNotFound extends RuntimeException{
    public InformationNotFound(String message){
        super(message);
    }
}
