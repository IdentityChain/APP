package com.project.isc.iscdbserver.exception;

import java.io.IOException;

public class NormalException extends IOException {

	private static final long serialVersionUID = 1L; 
	 
    public NormalException(String message, Throwable cause) { 
        super(message, cause); 
    } 
     
    public NormalException(Throwable cause) { 
        super(cause); 
    } 
     
    public NormalException(String message) { 
        super(message); 
    } 

}
