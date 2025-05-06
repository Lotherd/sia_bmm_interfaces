package trax.aero.logger;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Formatter;
import java.util.logging.LogRecord;

public class LogFormat extends Formatter {

	public static final DateFormat dateFormat = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss:SSS");


	public LogFormat() {
	}


	@Override
	public String format(LogRecord record) {

		StringBuffer formatStr = new StringBuffer();

		formatStr.append(dateFormat.format(new Date(record.getMillis())));

		formatStr.append("|" + record.getLevel().getName() + ": ");

		formatStr.append(formatMessage(record));

		if (record.getSourceClassName() != null) {

			formatStr.append(" | [" + record.getSourceClassName());

			if (record.getSourceMethodName() != null)
				formatStr.append("/" + record.getSourceMethodName() + "()]");
			formatStr.append(" | [" + record.getLoggerName() + "] ");
			
		} else
			formatStr.append(" [" + record.getLoggerName() + "] ");

		// formatStr.append("\n");
		// formatStr.append(record.getLevel().getName());

		if (record.getThrown() != null) {

			StringWriter sw = new StringWriter();
			PrintWriter pw = new PrintWriter(sw);
			pw.println();

			record.getThrown().printStackTrace(pw);
			pw.close();

			formatStr.append("\n");
			formatStr.append(sw.toString());
		}

		// formatStr.append("\n");
		formatStr.append("\n");

		return formatStr.toString();
	}


	
}
