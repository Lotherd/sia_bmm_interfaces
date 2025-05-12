package trax.aero.logger;

import java.util.logging.ConsoleHandler;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LogManager {
	private static Logger logger = null;


	public static Logger getLogger(String interfaceName) {

		if (logger == null) {
			logger = Logger.getLogger("trax.logger."+interfaceName);
			
			logger.setUseParentHandlers(false);
			logger.setLevel(Level.INFO);

			if (logger.getHandlers().length == 0)
			{
				try {
	
					FileHandler fileHandler = new FileHandler(interfaceName+"%g.log", 5242880, 5, true);
					fileHandler.setLevel(logger.getLevel());
					fileHandler.setFormatter(new LogFormat());
					
					 ConsoleHandler consoleHandler = new ConsoleHandler();
					 consoleHandler.setFormatter(new LogFormat());
					 consoleHandler.setLevel(Level.INFO);
					 
	
					logger.addHandler(fileHandler);
					logger.addHandler(consoleHandler);
	
				} catch (Exception exc) {
					logger.log(Level.SEVERE, exc.getMessage(), exc);
				}
			}
		}
		return logger;
	}

}
