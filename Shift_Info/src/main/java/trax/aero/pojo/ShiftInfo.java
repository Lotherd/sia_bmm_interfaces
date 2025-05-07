package trax.aero.pojo;

import java.sql.Timestamp;

/**
 * Clase POJO para almacenar los datos de turnos
 */
public class ShiftInfo {
    
    // Campos para EmployeeSchedule
    private String shiftGroupCode;
    private String companyCode;
    private String empNo;
    private String startShiftDate;
    private String alwaysPresent;
    
    // Campos para ShiftPatterns
    private String shiftDailyCode;
    private String startTime;
    private String endTime;
    private String productiveHours;
    private String dayType;
    private String remark;
    private String isActive;
    private String halfDay;
    
    // Campos de tiempos de descanso
    private String breakStartTime1;
    private String breakEndTime1;
    private String breakStartTime2;
    private String breakEndTime2;
    private String breakStartTime3;
    private String breakEndTime3;
    private String breakStartTime4;
    private String breakEndTime4;
    private String breakStartTime5;
    private String breakEndTime5;
    private String breakStartTime6;
    private String breakEndTime6;
    
    // Campos adicionales para patrones de turnos
    private String shiftGroupCode1;
    private String shiftGroupName;
    private String companyCode1;
    private String totalDays;
    private String isActive1;
    
    // Códigos de turnos diarios
    private String shiftDailyCode1;
    private String shiftDailyCode2;
    private String shiftDailyCode3;
    private String shiftDailyCode4;
    private String shiftDailyCode5;
    private String shiftDailyCode6;
    private String shiftDailyCode7;
    
    // Tipo de registro (para diferenciar entre EmployeeSchedule y ShiftPatterns)
    private String recordType;
    
    // Constructor vacío
    public ShiftInfo() {
    }
    
    // Métodos getter y setter para todos los campos
    public String getShiftGroupCode() {
        return shiftGroupCode;
    }
    
    public void setShiftGroupCode(String shiftGroupCode) {
        this.shiftGroupCode = shiftGroupCode;
    }
    
    public String getCompanyCode() {
        return companyCode;
    }
    
    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }
    
    public String getEmpNo() {
        return empNo;
    }
    
    public void setEmpNo(String empNo) {
        this.empNo = empNo;
    }
    
    public String getStartShiftDate() {
        return startShiftDate;
    }
    
    public void setStartShiftDate(String startShiftDate) {
        this.startShiftDate = startShiftDate;
    }
    
    public String getAlwaysPresent() {
        return alwaysPresent;
    }
    
    public void setAlwaysPresent(String alwaysPresent) {
        this.alwaysPresent = alwaysPresent;
    }
    
    public String getShiftDailyCode() {
        return shiftDailyCode;
    }
    
    public void setShiftDailyCode(String shiftDailyCode) {
        this.shiftDailyCode = shiftDailyCode;
    }
    
    public String getStartTime() {
        return startTime;
    }
    
    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }
    
    public String getEndTime() {
        return endTime;
    }
    
    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }
    
    public String getProductiveHours() {
        return productiveHours;
    }
    
    public void setProductiveHours(String productiveHours) {
        this.productiveHours = productiveHours;
    }
    
    public String getDayType() {
        return dayType;
    }
    
    public void setDayType(String dayType) {
        this.dayType = dayType;
    }
    
    public String getRemark() {
        return remark;
    }
    
    public void setRemark(String remark) {
        this.remark = remark;
    }
    
    public String getIsActive() {
        return isActive;
    }
    
    public void setIsActive(String isActive) {
        this.isActive = isActive;
    }
    
    public String getHalfDay() {
        return halfDay;
    }
    
    public void setHalfDay(String halfDay) {
        this.halfDay = halfDay;
    }
    
    public String getBreakStartTime1() {
        return breakStartTime1;
    }
    
    public void setBreakStartTime1(String breakStartTime1) {
        this.breakStartTime1 = breakStartTime1;
    }
    
    public String getBreakEndTime1() {
        return breakEndTime1;
    }
    
    public void setBreakEndTime1(String breakEndTime1) {
        this.breakEndTime1 = breakEndTime1;
    }
    
    public String getBreakStartTime2() {
        return breakStartTime2;
    }
    
    public void setBreakStartTime2(String breakStartTime2) {
        this.breakStartTime2 = breakStartTime2;
    }
    
    public String getBreakEndTime2() {
        return breakEndTime2;
    }
    
    public void setBreakEndTime2(String breakEndTime2) {
        this.breakEndTime2 = breakEndTime2;
    }
    
    public String getBreakStartTime3() {
        return breakStartTime3;
    }
    
    public void setBreakStartTime3(String breakStartTime3) {
        this.breakStartTime3 = breakStartTime3;
    }
    
    public String getBreakEndTime3() {
        return breakEndTime3;
    }
    
    public void setBreakEndTime3(String breakEndTime3) {
        this.breakEndTime3 = breakEndTime3;
    }
    
    public String getBreakStartTime4() {
        return breakStartTime4;
    }
    
    public void setBreakStartTime4(String breakStartTime4) {
        this.breakStartTime4 = breakStartTime4;
    }
    
    public String getBreakEndTime4() {
        return breakEndTime4;
    }
    
    public void setBreakEndTime4(String breakEndTime4) {
        this.breakEndTime4 = breakEndTime4;
    }
    
    public String getBreakStartTime5() {
        return breakStartTime5;
    }
    
    public void setBreakStartTime5(String breakStartTime5) {
        this.breakStartTime5 = breakStartTime5;
    }
    
    public String getBreakEndTime5() {
        return breakEndTime5;
    }
    
    public void setBreakEndTime5(String breakEndTime5) {
        this.breakEndTime5 = breakEndTime5;
    }
    
    public String getBreakStartTime6() {
        return breakStartTime6;
    }
    
    public void setBreakStartTime6(String breakStartTime6) {
        this.breakStartTime6 = breakStartTime6;
    }
    
    public String getBreakEndTime6() {
        return breakEndTime6;
    }
    
    public void setBreakEndTime6(String breakEndTime6) {
        this.breakEndTime6 = breakEndTime6;
    }
    
    public String getShiftGroupCode1() {
        return shiftGroupCode1;
    }
    
    public void setShiftGroupCode1(String shiftGroupCode1) {
        this.shiftGroupCode1 = shiftGroupCode1;
    }
    
    public String getShiftGroupName() {
        return shiftGroupName;
    }
    
    public void setShiftGroupName(String shiftGroupName) {
        this.shiftGroupName = shiftGroupName;
    }
    
    public String getCompanyCode1() {
        return companyCode1;
    }
    
    public void setCompanyCode1(String companyCode1) {
        this.companyCode1 = companyCode1;
    }
    
    public String getTotalDays() {
        return totalDays;
    }
    
    public void setTotalDays(String totalDays) {
        this.totalDays = totalDays;
    }
    
    public String getIsActive1() {
        return isActive1;
    }
    
    public void setIsActive1(String isActive1) {
        this.isActive1 = isActive1;
    }
    
    public String getShiftDailyCode1() {
        return shiftDailyCode1;
    }
    
    public void setShiftDailyCode1(String shiftDailyCode1) {
        this.shiftDailyCode1 = shiftDailyCode1;
    }
    
    public String getShiftDailyCode2() {
        return shiftDailyCode2;
    }
    
    public void setShiftDailyCode2(String shiftDailyCode2) {
        this.shiftDailyCode2 = shiftDailyCode2;
    }
    
    public String getShiftDailyCode3() {
        return shiftDailyCode3;
    }
    
    public void setShiftDailyCode3(String shiftDailyCode3) {
        this.shiftDailyCode3 = shiftDailyCode3;
    }
    
    public String getShiftDailyCode4() {
        return shiftDailyCode4;
    }
    
    public void setShiftDailyCode4(String shiftDailyCode4) {
        this.shiftDailyCode4 = shiftDailyCode4;
    }
    
    public String getShiftDailyCode5() {
        return shiftDailyCode5;
    }
    
    public void setShiftDailyCode5(String shiftDailyCode5) {
        this.shiftDailyCode5 = shiftDailyCode5;
    }
    
    public String getShiftDailyCode6() {
        return shiftDailyCode6;
    }
    
    public void setShiftDailyCode6(String shiftDailyCode6) {
        this.shiftDailyCode6 = shiftDailyCode6;
    }
    
    public String getShiftDailyCode7() {
        return shiftDailyCode7;
    }
    
    public void setShiftDailyCode7(String shiftDailyCode7) {
        this.shiftDailyCode7 = shiftDailyCode7;
    }
    
    public String getRecordType() {
        return recordType;
    }
    
    public void setRecordType(String recordType) {
        this.recordType = recordType;
    }
}