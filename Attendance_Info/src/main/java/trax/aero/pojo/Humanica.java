package trax.aero.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Humanica {
    
    @JsonProperty("COST_CENTRE")
    private String costCentre;
    
    @JsonProperty("MSG_TYPE")
    private String msgType;
    
    @JsonProperty("STATUS")
    private String status;
    
    @JsonProperty("STAFF_NO")
    private String staffNo;
    
    @JsonProperty("SEQ_NO")
    private BigDecimal seqNo;
    
    @JsonProperty("CLK_IN_TIME")
    private String clkInTime;
    
    @JsonProperty("CLK_OUT_TIME")
    private String clkOutTime;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    
    public String getCostCentre() {
        return costCentre;
    }
    
    public void setCostCentre(String costCentre) {
        this.costCentre = costCentre;
    }
    
    public String getMsgType() {
        return msgType;
    }
    
    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }
    
    public String getStatus() {
        return status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    
    public String getStaffNo() {
        return staffNo;
    }
    
    public void setStaffNo(String staffNo) {
        this.staffNo = staffNo;
    }
    
    public BigDecimal getSeqNo() {
        return seqNo;
    }
    
    public void setSeqNo(BigDecimal seqNo) {
        this.seqNo = seqNo;
    }
    
    public String getClkInTime() {
        return clkInTime;
    }
    
    public void setClkInTime(String clkInTime) {
        this.clkInTime = clkInTime;
    }
    
    public String getClkOutTime() {
        return clkOutTime;
    }
    
    public void setClkOutTime(String clkOutTime) {
        this.clkOutTime = clkOutTime;
    }
    
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\ncostCentre: ").append(this.getCostCentre());
        sb.append("\nmsgType: ").append(this.getMsgType());
        sb.append("\nstatus: ").append(this.getStatus());
        sb.append("\nstaffNo: ").append(this.getStaffNo());
        sb.append("\nseqNo: ").append(this.getSeqNo());
        sb.append("\nclkInTime: ").append(this.getClkInTime());
        sb.append("\nclkOutTime: ").append(this.getClkOutTime());
        
        return sb.toString();
    }
}