package trax.aero.utils;

/**
 * Enum for HTTP error types used in the application
 */
public enum ErrorType {
    OK(200),
    MULTIPLE_CHOICES(300),
    BAD_REQUEST(400),
    UNAUTHORIZED(401),
    FORBIDDEN(403),
    NOT_FOUND(404),
    CONFLICT(409),
    INTERNAL_SERVER_ERROR(500),
    SERVICE_UNAVAILABLE(503);

    private int value;

    private ErrorType(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
    
    /**
     * Get error type from HTTP status code
     * 
     * @param statusCode HTTP status code
     * @return ErrorType matching the status code, or INTERNAL_SERVER_ERROR if no match
     */
    public static ErrorType fromStatusCode(int statusCode) {
        for (ErrorType type : ErrorType.values()) {
            if (type.getValue() == statusCode) {
                return type;
            }
        }
        return INTERNAL_SERVER_ERROR;
    }
    
    /**
     * Check if status code represents a success
     * 
     * @param statusCode HTTP status code
     * @return true if the status code is in the 2xx range
     */
    public static boolean isSuccess(int statusCode) {
        return statusCode >= 200 && statusCode < 300;
    }
    
    /**
     * Check if status code represents an error
     * 
     * @param statusCode HTTP status code
     * @return true if the status code is in the 4xx or 5xx range
     */
    public static boolean isError(int statusCode) {
        return statusCode >= 400;
    }
}