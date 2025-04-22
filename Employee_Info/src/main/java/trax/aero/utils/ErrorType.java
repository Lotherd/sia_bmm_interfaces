package trax.aero.utils;

public enum ErrorType 
{
	OK(200),
	MULTIPLE_CHOICES(300),
	BAD_REQUEST(400),
	INTERNAL_SERVER_ERROR(500);
	
	private int value;
	private ErrorType(int value)
	{
		this.value = value;
	}
	
	public int getValue()
	{
		return value;
	}
}
