package trax.aero.utils;

import java.util.logging.Logger;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import trax.aero.data.ShiftInfoData;
import trax.aero.logger.LogManager;
import trax.aero.pojo.ShiftInfo;

public class Worker implements Runnable {

    private ShiftInfoData data = null;
    private static Logger logger = LogManager.getLogger("ShiftInfo_I02");

    public Worker(EntityManagerFactory factory) {
        data = new ShiftInfoData();
    }

    private ShiftInfo input = null;
    private String executed = "";

    public void run() {
        setExecuted("OK");

        try {
 
            logger.info("Worker procesando datos: " + (input != null ? "ShiftInfo ID: " + input.getShiftGroupCode() : "Sin datos"));
            
       
        }
        catch(Exception e) {
            e.printStackTrace();
            logger.severe(e.toString());
            setExecuted("ERROR: " + e.getMessage());
        } finally {
            try {
                if(data.getCon() != null && !data.getCon().isClosed()) {
                    data.getCon().close();
                }
            } catch (Exception e) {
                logger.severe("Error closing connection: " + e.toString());
            }
        }
    }

    public ShiftInfo getInput() {
        return input;
    }

    public void setInput(ShiftInfo inputs) {
        this.input = inputs;
    }

    public String getExecuted() {
        return executed;
    }

    public void setExecuted(String executed) {
        this.executed = executed;
    }
}