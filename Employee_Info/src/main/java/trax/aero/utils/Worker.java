package trax.aero.utils;

import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;

import trax.aero.data.PersonalInfoData;
import trax.aero.logger.LogManager;
import trax.aero.pojo.EmployeeInfo;

public class Worker implements Runnable {

	PersonalInfoData data = null;
	Logger logger = LogManager.getLogger("EmployeeInfo_I01");
	public Worker(EntityManagerFactory factory) {
		data = new PersonalInfoData(factory);
	}
	
	private EmployeeInfo input =null;
	
	private String exceuted = "";
	
	public void run() 
	{
		setExceuted("OK");
		
			try {
				String ouput = data.insertEmployee(input);
	    	if(ouput == null || !ouput.equalsIgnoreCase("OK")) {
	    		RunAble.employeesFailure.add(input); 
	    	}
		}	
		catch(Exception e)
		{	
			e.printStackTrace();
			logger.severe(e.toString());
		}finally {
			if(data.em != null && data.em .isOpen())
				data.em .close();
		}
	}

	public EmployeeInfo getInput() {
		return input;
	}

	public void setInput(EmployeeInfo inputs) {
		this.input = inputs;
	}
	
	public String getExceuted() {
		return exceuted;
	}

	public void setExceuted(String exceuted) {
		this.exceuted = exceuted;
	}

}
