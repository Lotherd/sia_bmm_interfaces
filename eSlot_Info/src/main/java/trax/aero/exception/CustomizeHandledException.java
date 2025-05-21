package trax.aero.exception;

public class CustomizeHandledException extends Exception 
{

	private static final long serialVersionUID = 7718828512143293558L;

	public CustomizeHandledException() {
		super();
	}

	public CustomizeHandledException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) 
	{
		super(message, cause, enableSuppression, writableStackTrace);
	}

	public CustomizeHandledException(String message, Throwable cause) 
	{
		super(message, cause);
	}

	public CustomizeHandledException(String message) 
	{
		super(message);
	}

	public CustomizeHandledException(Throwable cause) 
	{
		super(cause);
	}
	
	
}
