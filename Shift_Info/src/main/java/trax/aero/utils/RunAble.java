package trax.aero.utils;

import java.io.File;
import java.io.FileWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.logging.Logger;

import trax.aero.Encryption.PGPEncryption;
import trax.aero.data.ShiftInfoData;
import trax.aero.logger.LogManager;
import trax.aero.pojo.ShiftInfo;

public class RunAble implements Runnable {
    
    // Logger
    Logger logger = LogManager.getLogger("ShiftInfo_I02");
    
    // Variables
    private ShiftInfoData data;
    
    public RunAble() {
        data = new ShiftInfoData();
    }
    
    /**
     * Genera el archivo EmployeeSchedule
     */
    private void generateEmployeeScheduleFile() {
        try {
            // Configurar directorios
            final String exportDir = System.getProperty("ShiftInfo_exportLoc");
            File outputFolder = new File(exportDir);
            
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }
            
            // Obtener datos de horarios de empleados
            List<ShiftInfo> shifts = data.getEmployeeScheduleData();
            
            if (shifts.isEmpty()) {
                logger.info("No employee schedule records found to export");
                return;
            }
            
            // Generar nombre de archivo con timestamp
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            LocalDateTime currentDateTime = LocalDateTime.now();
            String fileName = "EmployeeSchedule_" + dtf.format(currentDateTime) + ".txt";
            
            File outputFile = new File(outputFolder, fileName);
            
            // Escribir el archivo de texto
            try (FileWriter writer = new FileWriter(outputFile)) {
                // Escribir encabezados
                writer.write("SHIFTGROUPCODE;COMPANY_CODE;EMP_NO;STARTSHIFTDATE;ALWAYS_PRESENT\n");
                
                // Escribir registros
                for (ShiftInfo shift : shifts) {
                    StringBuilder line = new StringBuilder();
                    
                    // Agregar campos en el orden requerido
                    appendValueWithSemicolon(line, shift.getShiftGroupCode());
                    appendValueWithSemicolon(line, shift.getCompanyCode());
                    appendValueWithSemicolon(line, shift.getEmpNo());
                    appendValueWithSemicolon(line, shift.getStartShiftDate());
                    appendValue(line, shift.getAlwaysPresent()); // Último campo sin punto y coma al final
                    
                    // Agregar nueva línea
                    line.append("\n");
                    
                    // Escribir la línea al archivo
                    writer.write(line.toString());
                }
            }
            
            // Opcionalmente, encriptar el archivo si es necesario
            if (Boolean.parseBoolean(System.getProperty("ShiftInfo_encryptOutput", "false"))) {
                encryptFile(outputFile);
            }
            
            logger.info("Successfully exported " + shifts.size() + " employee schedule records to " + outputFile.getAbsolutePath());
            
        } catch (Exception e) {
            logger.severe("Error generating employee schedule file: " + e.toString());
        }
    }
    
    /**
     * Genera el archivo ShiftPatterns
     */
    private void generateShiftPatternsFile() {
        try {
            // Configurar directorios
            final String exportDir = System.getProperty("ShiftInfo_exportLoc");
            File outputFolder = new File(exportDir);
            
            if (!outputFolder.exists()) {
                outputFolder.mkdirs();
            }
            
            // Obtener datos de patrones de turnos
            List<ShiftInfo> shifts = data.getShiftPatternsData();
            
            if (shifts.isEmpty()) {
                logger.info("No shift pattern records found to export");
                return;
            }
            
            // Generar nombre de archivo con timestamp
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss");
            LocalDateTime currentDateTime = LocalDateTime.now();
            String fileName = "ShiftPatterns_" + dtf.format(currentDateTime) + ".txt";
            
            File outputFile = new File(outputFolder, fileName);
            
            
            try (FileWriter writer = new FileWriter(outputFile)) {
               
                writer.write("SHIFTDAILYCODE;COMPANY_CODE;START_TIME;ENDTIME;PRODUCTIVEHOURS;DAYTYPE;REMARK;IS_ACTIVE;HALFDAY;BREAK_STARTTIME_1;BREAK_ENDTIME_1;BREAK_STARTTIME_2;BREAK_ENDTIME_2;BREAK_STARTTIME_3;BREAK_ENDTIME_3;BREAK_STARTTIME_4;BREAK_ENDTIME_4;BREAK_STARTTIME_5;BREAK_ENDTIME_5;BREAK_STARTTIME_6;BREAK_ENDTIME_6;SHIFTGROUPCODE;SHIFTGROUPCODE_1;SHIFTGROUPNAME;COMPANY_CODE_1;TOTALDAYS;IS_ACTIVE_1;SHIFTDAILYCODE_1;SHIFTDAILYCODE_2;SHIFTDAILYCODE_3;SHIFTDAILYCODE_4;SHIFTDAILYCODE_5;SHIFTDAILYCODE_6;SHIFTDAILYCODE_7\n");
                
              
                for (ShiftInfo shift : shifts) {
                    StringBuilder line = new StringBuilder();
                    
                    
                    appendValueWithSemicolon(line, shift.getShiftDailyCode());
                    appendValueWithSemicolon(line, shift.getCompanyCode());
                    appendValueWithSemicolon(line, shift.getStartTime());
                    appendValueWithSemicolon(line, shift.getEndTime());
                    appendValueWithSemicolon(line, shift.getProductiveHours());
                    appendValueWithSemicolon(line, shift.getDayType());
                    appendValueWithSemicolon(line, shift.getRemark());
                    appendValueWithSemicolon(line, shift.getIsActive());
                    appendValueWithSemicolon(line, shift.getHalfDay());
                    
                   
                    appendValueWithSemicolon(line, shift.getBreakStartTime1());
                    appendValueWithSemicolon(line, shift.getBreakEndTime1());
                    appendValueWithSemicolon(line, shift.getBreakStartTime2());
                    appendValueWithSemicolon(line, shift.getBreakEndTime2());
                    appendValueWithSemicolon(line, shift.getBreakStartTime3());
                    appendValueWithSemicolon(line, shift.getBreakEndTime3());
                    appendValueWithSemicolon(line, shift.getBreakStartTime4());
                    appendValueWithSemicolon(line, shift.getBreakEndTime4());
                    appendValueWithSemicolon(line, shift.getBreakStartTime5());
                    appendValueWithSemicolon(line, shift.getBreakEndTime5());
                    appendValueWithSemicolon(line, shift.getBreakStartTime6());
                    appendValueWithSemicolon(line, shift.getBreakEndTime6());
                    
                   
                    appendValueWithSemicolon(line, shift.getShiftGroupCode());
                    appendValueWithSemicolon(line, shift.getShiftGroupCode1());
                    appendValueWithSemicolon(line, shift.getShiftGroupName());
                    appendValueWithSemicolon(line, shift.getCompanyCode1());
                    appendValueWithSemicolon(line, shift.getTotalDays());
                    appendValueWithSemicolon(line, shift.getIsActive1());
                    
                    
                    appendValueWithSemicolon(line, shift.getShiftDailyCode1());
                    appendValueWithSemicolon(line, shift.getShiftDailyCode2());
                    appendValueWithSemicolon(line, shift.getShiftDailyCode3());
                    appendValueWithSemicolon(line, shift.getShiftDailyCode4());
                    appendValueWithSemicolon(line, shift.getShiftDailyCode5());
                    appendValueWithSemicolon(line, shift.getShiftDailyCode6());
                    appendValue(line, shift.getShiftDailyCode7()); 
                    
                    
                    line.append("\n");
                    
                    
                    writer.write(line.toString());
                }
            }
            
           
            if (Boolean.parseBoolean(System.getProperty("ShiftInfo_encryptOutput", "false"))) {
                encryptFile(outputFile);
            }
            
            logger.info("Successfully exported " + shifts.size() + " shift pattern records to " + outputFile.getAbsolutePath());
            
        } catch (Exception e) {
            logger.severe("Error generating shift patterns file: " + e.toString());
        }
    }
    
    
    private void appendValueWithSemicolon(StringBuilder sb, String value) {
        sb.append(value != null ? value : "");
        sb.append(";");
    }
    
    
    private void appendValue(StringBuilder sb, String value) {
        sb.append(value != null ? value : "");
    }
    
   
    private void encryptFile(File file) throws Exception {
        try {
            logger.info("Encrypting file " + file.getName());
            
            String encryptedFileName = file.getAbsolutePath() + ".pgp";
            
            PGPEncryption.encryptFile(
                file.getAbsolutePath(),
                encryptedFileName,
                PGPEncryption.getEncryptionfile(),
                true
            );
            
           
            if (new File(encryptedFileName).exists()) {
                file.delete();
                logger.info("File successfully encrypted: " + encryptedFileName);
            }
        } catch (Exception e) {
            logger.severe("Error encrypting file: " + e.toString());
            throw e;
        }
    }
    
    private void process() {
        try {
            if (data.lockAvailable("I02")) {
                data.lockTable("I02");
                
                
                generateEmployeeScheduleFile();
                generateShiftPatternsFile();
                
                data.unlockTable("I02");
            }
        } catch (Exception e) {
            logger.severe("Error in shift export process: " + e.toString());
        }
    }
    
    @Override
    public void run() {
        process();
    }
}