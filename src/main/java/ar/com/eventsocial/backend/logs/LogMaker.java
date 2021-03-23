package ar.com.eventsocial.backend.logs;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

public class LogMaker {

	final static Logger logger = Logger.getLogger(LogMaker.class);

	private static final DateFormat sdf = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");

	private final Boolean kibanaLog = false;

	public LogMaker() {
		setDefaultProperties();
	}

	private void setDefaultProperties() {
		Properties properties = new Properties();
		properties.setProperty("log4j.rootLogger", "TRACE,stdout,MyFile");
		properties.setProperty("log4j.rootCategory", "TRACE");
		properties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		properties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.stdout.layout.ConversionPattern", "[%-5p] - %m%n");
		properties.setProperty("log4j.appender.MyFile", "org.apache.log4j.RollingFileAppender");
		properties.setProperty("log4j.appender.MyFile.File", "Ph-Bakend.log");
		properties.setProperty("log4j.appender.MyFile.MaxFileSize", "0KB"); //
		properties.setProperty("log4j.appender.MyFile.MaxBackupIndex", "1");
		properties.setProperty("log4j.appender.MyFile.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.MyFile.layout.ConversionPattern",
				"%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");
		PropertyConfigurator.configure(properties);
	}

	private void setErrorProperties() {

		Properties properties = new Properties();
		properties.setProperty("log4j.rootLogger", "TRACE,stdout,MyFile");
		properties.setProperty("log4j.rootCategory", "TRACE");
		properties.setProperty("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
		properties.setProperty("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.stdout.layout.ConversionPattern", "[%-5p] - %m%n");
		properties.setProperty("log4j.appender.MyFile", "org.apache.log4j.RollingFileAppender");
		properties.setProperty("log4j.appender.MyFile.File", "Ph-Bakend.log");
		properties.setProperty("log4j.appender.MyFile.MaxFileSize", "0KB"); //
		properties.setProperty("log4j.appender.MyFile.MaxBackupIndex", "1");
		properties.setProperty("log4j.appender.MyFile.layout", "org.apache.log4j.PatternLayout");
		properties.setProperty("log4j.appender.MyFile.layout.ConversionPattern",
				"%d{yyyy/MM/dd HH:mm:ss.SSS} [%5p] %t (%F) - %m%n");
		PropertyConfigurator.configure(properties);
	}

	public void infoRecibe(String url, String response) {
		setDefaultProperties();
		if (kibanaLog.booleanValue()) {
			Map<String, Object> log = new HashMap<>();
			log.put("Time-Stamp", sdf.format(new Date().getTime()));
			log.put("Request", url);
			log.put("Response", response);
			logger.info(log);
		} else {

			Map<String, Object> log = new HashMap<>();
			log.put("Time-Stamp", sdf.format(new Date().getTime()));
			log.put("Request", url);
			log.put("Response", response);
			logger.info(log);
		}
	}

	public void debug(String service, String metodo, String message) {
		setDefaultProperties();
		Map<String, Object> log = new HashMap<>();
		log.put("Time-Stamp", sdf.format(new Date().getTime()));
		log.put("Service", service);
		log.put("Metodo", metodo);
		log.put("Mensaje", message);

		if (kibanaLog.booleanValue()) {
			logger.debug(log);
		} else {
			logger.debug(log);
		}
	}

	public void ErrorRecibe(String service, String metodo, String exception) {

		setDefaultProperties();

		if (kibanaLog.booleanValue()) {
			Map<String, Object> log = new HashMap<>();
			log.put("Time-Stamp", sdf.format(new Date().getTime()));
			log.put("Service", service);
			log.put("Metodo", metodo);
			log.put("Exception", exception);
			// String jsonLog = new Gson().toJson(log).replace("\n", "");
			logger.error(log);
		} else {
			Map<String, Object> log = new HashMap<>();
			log.put("Time-Stamp", sdf.format(new Date().getTime()));
			log.put("Service", service);
			log.put("Metodo", metodo);
			log.put("Exception", exception);
			logger.error(log);
		}
	}

}
