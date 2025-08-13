package trax.aero.utils;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.FilenameFilter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.xml.bind.JAXBException;

import org.apache.commons.io.FileUtils;

import com.opencsv.CSVParser;
import com.opencsv.CSVParserBuilder;
import com.opencsv.CSVReader;
import com.opencsv.CSVReaderBuilder;
import com.opencsv.CSVWriter;

import trax.aero.Encryption.PGPEncryption;
import trax.aero.controller.EmployeeInfoController;
import trax.aero.data.EmployeeInfoData;
import trax.aero.logger.LogManager;
import trax.aero.pojo.EmployeeInfo;



public class RunAble implements Runnable {
	
	//Variables
	Logger logger = LogManager.getLogger("EmployeeInfo_I01");
	EmployeeInfoData data = null;
	EntityManagerFactory factory;
	private static File inputFiles[],inputFolder  ;
	public static List<EmployeeInfo> employeesFailure  = null;
	
	private static FilenameFilter filter = new FilenameFilter() 
	{		 
		public boolean accept(File dir, String name) 
		{
			return (name.toLowerCase().endsWith(".csv"));
		}
	};
	
	private static FilenameFilter filterEN = new FilenameFilter() 
	{		 
		public boolean accept(File dir, String name) 
		{
			return (name.toLowerCase().endsWith(".pgp"));
		}
	};
	
	public RunAble() {
		factory = Persistence.createEntityManagerFactory("TraxStandaloneDS");
		data = new EmployeeInfoData();
	}
	
	private String insertFile(File file, String outcome) 
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime  currentDateTime = LocalDateTime.now();
		
		File todayFolder = new File(System.getProperty("EmployeeInfo_compFiles")+ File.separator + dtf.format(currentDateTime));
		if (!todayFolder.isDirectory())			
			todayFolder.mkdir();
		
		
		
		File output =  new File(todayFolder + File.separator
			+ outcome + Calendar.getInstance().getTimeInMillis() + "_" + file.getName());
		
		try {
			FileUtils.copyFile(file, output);
		} catch (IOException e) {
			
		}
		file.delete();
		
		
		logger.info("DONE processing file " + file.getName() );
		
		return output.getName();
	}
	
	
	
	private String insertFileFailed(ArrayList<EmployeeInfo> employeeFailure, String outcome, String fileName) throws JAXBException, IOException
	{
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd");
		LocalDateTime currentDateTime = LocalDateTime.now();
		

		
		
        List<String[]> data = new ArrayList<String[]>();
        String[] header = {"EMPLOYEE_ID","RELATION_CODE","FULL_NAME","FIRST_NAME","LAST_NAME","RELATED_LOCATION",
                          "POSITION_CODE","POSITION","DATE_OF_BIRTH","DEPARTMENT","DEPARTMENT_DESCRIPTION",
                          "DIVISION","DIVISION_DESCRIPTION","MAIL_PHONE","MAIL_EMAIL",
                          "DATE_HIRED","DATE_TERMINATED","PROFILE","COMPANY_NAME","COST_CODE",
                          "SKILL","SKILL_DESCIPTION","GRADE_CODE","STATUS"};
        data.add(header);
        
        for(EmployeeInfo e : employeeFailure) {
        	String[] arr = new String[header.length];
            for(int i = 0; i < arr.length; i++) {
                arr[i] = "";
            }
            
        	if(e.getEmployeeId() != null && !e.getEmployeeId().isEmpty()) {
        		arr[0] = e.getEmployeeId();
        	}
        	if(e.getRelationCode() != null && !e.getRelationCode().isEmpty()) {
        		arr[1] = e.getRelationCode();
        	}
        	if(e.getFullName() != null && !e.getFullName().isEmpty()) {
        		arr[2] = e.getFullName();
        	}
        	if(e.getFirstName() != null && !e.getFirstName().isEmpty()) {
        		arr[3] = e.getFirstName();
        	}
        	if(e.getLastName() != null && !e.getLastName().isEmpty()) {
        		arr[4] = e.getLastName();
        	}
        	if(e.getRelatedLocation() != null && !e.getRelatedLocation().isEmpty()) {
        		arr[5] = e.getRelatedLocation();
        	}
        	if(e.getPositionCode() != null && !e.getPositionCode().isEmpty()) {
        		arr[6] = e.getPositionCode();
        	}
        	if(e.getPosition() != null && !e.getPosition().isEmpty()) {
        		arr[7] = e.getPosition();
        	}
        	if(e.getDateOfBirth() != null && !e.getDateOfBirth().isEmpty()) {
        		arr[8] = e.getDateOfBirth();
        	}
        	if(e.getDepartment() != null && !e.getDepartment().isEmpty()) {
        		arr[9] = e.getDepartment();
        	}
        	if(e.getDepartmentDescription() != null && !e.getDepartmentDescription().isEmpty()) {
        		arr[10] = e.getDepartmentDescription();
        	}
        	if(e.getDivision() != null && !e.getDivision().isEmpty()) {
        		arr[11] = e.getDivision();
        	}
        	if(e.getDivisionDescription() != null && !e.getDivisionDescription().isEmpty()) {
        		arr[12] = e.getDivisionDescription();
        	}
        	if(e.getMailPhone() != null && !e.getMailPhone().isEmpty()) {
        		arr[13] = e.getMailPhone();
        	}
        	if(e.getMailEmail() != null && !e.getMailEmail().isEmpty()) {
        		arr[14] = e.getMailEmail();
        	}
        	if(e.getDateHired() != null && !e.getDateHired().isEmpty()) {
        		arr[15] = e.getDateHired();
        	}
        	if(e.getDateTerminated() != null && !e.getDateTerminated().isEmpty()) {
        		arr[16] = e.getDateTerminated();
        	}
        	if(e.getProfile() != null && !e.getProfile().isEmpty()) {
        		arr[17] = e.getProfile();
        	}
        	if(e.getCompanyName() != null && !e.getCompanyName().isEmpty()) {
        		arr[18] = e.getCompanyName();
        	}
        	if(e.getCostCode() != null && !e.getCostCode().isEmpty()) {
        		arr[19] = e.getCostCode();
        	}
        	if(e.getSkill() != null && !e.getSkill().isEmpty()) {
        		arr[20] = e.getSkill();
        	}
        	if(e.getSkillDescription() != null && !e.getSkillDescription().isEmpty()) {
        		arr[21] = e.getSkillDescription();
        	}
        	if(e.getGradeCode() != null && !e.getGradeCode().isEmpty()) {
        		arr[22] = e.getGradeCode();
        	}
        	if(e.getStatus() != null && !e.getStatus().isEmpty()) {
        		arr[23] = e.getStatus();
        	}
        	
        	data.add(arr);
        }
        
		File compFolder = new File(System.getProperty("EmployeeInfo_compFiles"));
		if (!compFolder.isDirectory())
		compFolder.mkdir();
		File todayFolder = new File(System.getProperty("EmployeeInfo_compFiles")+ File.separator + dtf.format(currentDateTime));
		if (!todayFolder.isDirectory())
		todayFolder.mkdir();
	
		File output = new File(todayFolder + File.separator
		+ outcome + "_"+ Calendar.getInstance().getTimeInMillis() + "_" + fileName);
		
        FileWriter outputfile = new FileWriter(output);
  

        CSVWriter writer = new CSVWriter(outputfile, '|',
                                         CSVWriter.NO_QUOTE_CHARACTER,
                                         CSVWriter.DEFAULT_ESCAPE_CHARACTER,
                                         CSVWriter.DEFAULT_LINE_END);
		
        writer.writeAll(data);
        writer.close();
        outputfile.close();
        
        logger.info("DONE processing file " + output.getName());
		return output.getName();
	}
	private void process() {
		try 
		{
			//setting up variables
			final String process = System.getProperty("EmployeeInfo_fileLoc");
			inputFolder = new File(process);
			String exectued = "OK",outcome = "PROCESSED_";
			ArrayList<EmployeeInfo> employees = new ArrayList<EmployeeInfo>();
			
			int itr;
			EmployeeInfo employee = null;
			
			
			if (inputFolder.isDirectory())
			{
				inputFiles = inputFolder.listFiles(filterEN);
			}
			else
			{
				logger.severe("Path: " + inputFolder.toString() + " is not a directory or does not exist");
				throw new Exception("Path: " + inputFolder.toString() + " is not a directory or does not exist");
			}
					
			for (int i = 0; i < inputFiles.length; i++)
			{
				outcome = "PROCESSED_";
				logger.info("Decrypting file " + inputFiles[i].toString());
				File file = new File(inputFiles[i].toString());
				try {
					try {
						logger.info("keyFile " + PGPEncryption.getEncryptionfile());
						PGPEncryption.decryptFile(file.getAbsolutePath(),PGPEncryption.getEncryptionfile() ,PGPEncryption.getEncryptionpassphrase().toCharArray() ,  file.getName().substring(0, file.getName().indexOf(".")) + ".csv" ,process );
							
						file.delete();	
					
					}catch(Exception e){
						EmployeeInfoController.addError(e.toString());
						outcome = "FAILURE_";
						throw new Exception("Failed to read file");
						
					}
				}catch(Exception e) {
					EmployeeInfoController.addError(e.toString());
					String output = insertFile(file,outcome);
					EmployeeInfoController.addError("Failed File "  + output);
					EmployeeInfoController.sendEmailFile(file);
					logger.severe(e.toString());
				}
			}
				
				
			
			
			
			
			
			
			//logic taken from AIMS_Flight_Interface
			if (inputFolder.isDirectory())
			{
				inputFiles = inputFolder.listFiles(filter);
			}
			else
			{
				logger.severe("Path: " + inputFolder.toString() + " is not a directory or does not exist");
				throw new Exception("Path: " + inputFolder.toString() + " is not a directory or does not exist");
			}
					
			for (int i = 0; i < inputFiles.length; i++)
			{
				outcome = "PROCESSED_";
				logger.info("Checking file " + inputFiles[i].toString());
				File file = new File(inputFiles[i].toString());
				try
				{	
					
					//logger.info("Input: "+ FileUtils.readFileToString(file, StandardCharsets.UTF_8));
					
					// To handle separator "||", first we read the content and replace "||" for a unique separator
					String fileContent = FileUtils.readFileToString(file, "UTF-8");
					fileContent = fileContent.replace("||", "\u0001"); // Using an uncommon char
					
					// Write the modified file on a temp file
					File tempFile = new File(file.getParent(), "temp_" + file.getName());
					FileUtils.writeStringToFile(tempFile, fileContent, "UTF-8");
					
					// Use FileReader with the temp file
					FileReader filereader = new FileReader(tempFile);
					
					CSVParser parser = new CSVParserBuilder().withSeparator('\u0001').build();
					
					CSVReader csvReader = new CSVReaderBuilder(filereader)
                             .withCSVParser(parser)
                             .withSkipLines(1)
                             .build();
					 
					 
					try {
						List<String[]> allData = csvReader.readAll();
						 
						for (String[] row : allData) {
							itr = 0;
							employee = new EmployeeInfo();
							for (String cell : row) {	            	
								 
								if(itr == 0) {
									employee.setEmployeeId(cell);           // EMPLOYEE_ID
								}
								if(itr == 1) {
									employee.setRelationCode(cell);         // RELATION_CODE
								}
								if(itr == 2) {
									employee.setFullName(cell);             // FULL_NAME
								}
								if(itr == 3) {
									employee.setFirstName(cell);            // FIRST_NAME
								}
								if(itr == 4) {
									employee.setLastName(cell);             // LAST_NAME
								}
								if(itr == 5) {
									employee.setRelatedLocation(cell);      // RELATED_LOCATION
								}
								if(itr == 6) {
									employee.setPositionCode(cell);         // POSITION_CODE
								}
								if(itr == 7) {
									employee.setPosition(cell);             // POSITION
								}
								if(itr == 8) {
									employee.setDateOfBirth(cell);          // DATE_OF_BIRTH
								}
								if(itr == 9) {
									employee.setDepartment(cell);           // DEPARTMENT
								}
								if(itr == 10) {
									employee.setDepartmentDescription(cell); // DEPARTMENT_DESCRIPTION
								}
								if(itr == 11) {
									employee.setDivision(cell);             // DIVISION
								}
								if(itr == 12) {
									employee.setDivisionDescription(cell);  // DIVISION_DESCRIPTION
								}
								if(itr == 13) {
									employee.setMailPhone(cell);            // MAIL_PHONE
								}
								if(itr == 14) {
									employee.setMailEmail(cell);            // MAIL_EMAIL
								}
								if(itr == 15) {
									employee.setDateHired(cell);            // DATE_HIRED 
								}
								if(itr == 16) {
									employee.setDateTerminated(cell);       // DATE_TERMINATED 
								}
								if(itr == 17) {
									employee.setProfile(cell);              // PROFILE 
								}
								if(itr == 18) {
									employee.setCompanyName(cell);          // COMPANY_NAME 
								}
								if(itr == 19) {
									employee.setCostCode(cell);             // COST_CODE 
								}
								if(itr == 20) {
									employee.setSkill(cell);                // SKILL
								}
								if(itr == 21) {
									employee.setSkillDescription(cell);     // SKILL_DESCRIPTION
								}
								if(itr == 22) {
									employee.setGradeCode(cell);            // GRADE_CODE
								}
								if(itr == 23) {
									employee.setStatus(cell);               // STATUS
								}
								
								itr++;				                
							}
							
							employees.add(employee);
						   
						}   
						 
					}catch(Exception e){
						EmployeeInfoController.addError(e.toString());
						 
						outcome = "FAILURE_";
						throw new Exception("Failed to read file");
					}finally {
						csvReader.close();
						filereader.close();
						// Delete temp file
						tempFile.delete();
					}
					
					 
					employeesFailure = Collections.synchronizedList(new ArrayList<EmployeeInfo>());
				
					int scheduledPoolSize = 4;
					if(System.getProperty("Thread_Count") != null && !System.getProperty("Thread_Count").isEmpty()) {
						scheduledPoolSize = Integer.parseInt(System.getProperty("Thread_Count"));
					}
					logger.info("Creating default Scheduled Executor Service [poolSize =" + String.valueOf(scheduledPoolSize) + "]");
					ScheduledExecutorService scheduledServ = Executors.newScheduledThreadPool(scheduledPoolSize);
					
					logger.info("SIZE " + employees.size());
					 
					for(EmployeeInfo e: employees) {
						exectued = "OK";
						Worker worker = new Worker(factory);
						worker.setInput(e);
						scheduledServ.execute(worker);
					}
			       
					scheduledServ.shutdown();
					scheduledServ.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);	 			        			        
					String fileName = file.getName(); 

					if(!employeesFailure.isEmpty()){
						exectued = insertFileFailed(new ArrayList<EmployeeInfo>(employeesFailure),"FAILURE_",fileName);
						employeesFailure = null;
						throw new Exception("Failed Employees are in File " + exectued);
					}
				   
				}
				catch(Exception e)
				{
					e.printStackTrace();
					logger.severe(e.toString());
					EmployeeInfoController.addError(e.toString());
					EmployeeInfoController.sendEmailFile(file);
					//insertFile(file,"FAILURE_");
					
					
				}finally {
					 
					insertFile(file,outcome);
					
				}
			}
			
		}
		catch(Throwable e)
		{
			logger.severe(e.toString());
		}
	}
	
	public void run() 
	{
		try {
			if(data.lockAvailable("I01"))
			{
				data.lockTable("I01");
				process();
				data.unlockTable("I01");
			}
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}