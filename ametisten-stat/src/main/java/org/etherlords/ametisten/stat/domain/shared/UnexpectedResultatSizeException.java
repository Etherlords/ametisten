package org.etherlords.ametisten.stat.domain.shared;

public class UnexpectedResultatSizeException extends RuntimeException {
    
    private static final long serialVersionUID = 5163045435803709485L;
    
    public UnexpectedResultatSizeException(final String message, final Throwable cause) {
        super(message, cause);
    }
    
    public UnexpectedResultatSizeException(final String message) {
        super(message);
    }
    
}
