package trax.aero.pojo;

import org.joda.time.DateTime;
import java.util.Date;

public class Punch {
    
    private String employeeId;
    private String punchType;
    private Date punchDateTime;
    
    public Punch() { }
    
    public Punch(Punch copy) {
        this.employeeId = copy.getEmployeeId();
        this.punchType = copy.getPunchType();
        this.punchDateTime = copy.getPunchDateTime();
    }
    
    public String getEmployeeId() {
        return employeeId;
    }
    
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    
    public String getPunchType() {
        return punchType;
    }
    
    public void setPunchType(String punchType) {
        this.punchType = punchType;
    }
    
    public Date getPunchDateTime() {
        return punchDateTime;
    }
    
    public void setPunchDateTime(Date punchDateTime) {
        this.punchDateTime = punchDateTime;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\nemployeeId: ").append(this.getEmployeeId());
        sb.append("\npunchType: ").append(this.getPunchType());
        
        DateTime date = null;
        if (this.getPunchDateTime() != null) {
            date = new DateTime(this.getPunchDateTime());
        }
        
        sb.append("\npunchDateTime: ").append(date);
        
        return sb.toString();
    }
}