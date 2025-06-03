package trax.aero.pojo;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Generated;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder({
"CUSTOMER",
"OID",
"ACTION",
"A/C REG",
"CONFIRMATION_STATUS",
"ENGINE",
"CHECK TYPE",
"LINE",
"CHECK DESCRIPTION",
"PLANNED START",
"PLANNED END",
"REMARKS",
"LAST MODIFIED",
"MODIFIED BY"
})
@Generated("jsonschema2pojo")
public class ESlot {

    @JsonProperty("CUSTOMER")
    private String customer;
    @JsonProperty("OID")
    private String oid;
    @JsonProperty("ACTION")
    private String action;
    @JsonProperty("A/C REG")
    private String aCReg;
    @JsonProperty("CONFIRMATION_STATUS")
    private String confirmationStatus;
    @JsonProperty("ENGINE")
    private String eNGINE;
    @JsonProperty("CHECK TYPE")
    private String checkType;
    @JsonProperty("LINE")
    private String line;
    @JsonProperty("CHECK DESCRIPTION")
    private String checkDescription;
    @JsonProperty("PLANNED START")
    private String plannedStart;
    @JsonProperty("PLANNED END")
    private String plannedEnd;
    @JsonProperty("REMARKS")
    private String remarks;
    @JsonProperty("LAST MODIFIED")
    private String lastModified;
    @JsonProperty("MODIFIED BY")
    private String modifiedBy;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();
    
    @JsonProperty("CUSTOMER")
    public String getCustomer() {
        return customer;
    }
    
    @JsonProperty("CUSTOMER")
    public void setCustomer(String customer) {
        this.customer = customer;
    }
    
    @JsonProperty("OID")
    public String getOid() {
        return oid;
    }
    
    @JsonProperty("OID")
    public void setOid(String oid) {
        this.oid = oid;
    }
    
    @JsonProperty("ACTION")
    public String getAction() {
        return action;
    }
    
    @JsonProperty("ACTION")
    public void setAction(String action) {
        this.action = action;
    }
    
    @JsonProperty("A/C REG")
    public String getACReg() {
        return aCReg;
    }
    
    @JsonProperty("A/C REG")
    public void setACReg(String aCReg) {
        this.aCReg = aCReg;
    }
    
    @JsonProperty("CONFIRMATION_STATUS")
    public String getConfirmationStatus() {
        return confirmationStatus;
    }
    
    @JsonProperty("CONFIRMATION_STATUS")
    public void setConfirmationStatus(String confirmationStatus) {
        this.confirmationStatus = confirmationStatus;
    }
    
    @JsonProperty("CHECK TYPE")
    public String getCheckType() {
        return checkType;
    }
    
    @JsonProperty("CHECK TYPE")
    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }
    
    @JsonProperty("LINE")
    public String getLine() {
        return line;
    }
    
    @JsonProperty("LINE")
    public void setLine(String line) {
        this.line = line;
    }
    
    @JsonProperty("CHECK DESCRIPTION")
    public String getCheckDescription() {
        return checkDescription;
    }
    
    @JsonProperty("CHECK DESCRIPTION")
    public void setCheckDescription(String checkDescription) {
        this.checkDescription = checkDescription;
    }
    
    @JsonProperty("PLANNED START")
    public String getPlannedStart() {
        return plannedStart;
    }
    
    @JsonProperty("PLANNED START")
    public void setPlannedStart(String plannedStart) {
        this.plannedStart = plannedStart;
    }
    
    @JsonProperty("PLANNED END")
    public String getPlannedEnd() {
        return plannedEnd;
    }
    
    @JsonProperty("PLANNED END")
    public void setPlannedEnd(String plannedEnd) {
        this.plannedEnd = plannedEnd;
    }
    
    @JsonProperty("REMARKS")
    public String getRemarks() {
        return remarks;
    }
    
    @JsonProperty("REMARKS")
    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }
    
    @JsonProperty("LAST MODIFIED")
    public String getLastModified() {
        return lastModified;
    }
    
    @JsonProperty("LAST MODIFIED")
    public void setLastModified(String lastModified) {
        this.lastModified = lastModified;
    }
    
    @JsonProperty("MODIFIED BY")
    public String getModifiedBy() {
        return modifiedBy;
    }
    
    @JsonProperty("MODIFIED BY")
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }
    
    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    
    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @JsonProperty("ENGINE")
    public String geteNGINE() {
        return eNGINE;
    }

    @JsonProperty("ENGINE")
    public void seteNGINE(String eNGINE) {
        this.eNGINE = eNGINE;
    }
}