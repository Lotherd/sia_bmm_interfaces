package trax.aero.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Trax {
    
    @JsonProperty("DATE")
    private String date;
    
    @JsonProperty("MSG_TYPE")
    private String msgType;
    
    @JsonProperty("STATUS")
    private String status;
    
    @JsonProperty("SEQ_NO")
    private BigDecimal seqNo;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    
    public String getDate() {
        return date;
    }
    
    public void setDate(String date) {
        this.date = date;
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
    
    public BigDecimal getSeqNo() {
        return seqNo;
    }
    
    public void setSeqNo(BigDecimal seqNo) {
        this.seqNo = seqNo;
    }
    
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}