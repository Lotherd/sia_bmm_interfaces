package trax.aero.pojo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class Message {
    
    @JsonProperty("HUMANICA")
    private Humanica humanica;
    
    @JsonProperty("TRAX")
    private Trax trax;
    
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<>();
    
    public Humanica getHumanica() {
        return humanica;
    }
    
    public void setHumanica(Humanica humanica) {
        this.humanica = humanica;
    }
    
    public Trax getTrax() {
        return trax;
    }
    
    public void setTrax(Trax trax) {
        this.trax = trax;
    }
    
    public void setResendRequest(Humanica humanica) {
        if (this.trax == null) {
            this.trax = new Trax();
        }
        this.trax.setMsgType("ResendRequest");
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        Date date = new Date();
        this.trax.setDate(formatter.format(date));
        this.trax.setStatus("RR");
        this.trax.setSeqNo(humanica.getSeqNo());
    }
    
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }
    
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }
}