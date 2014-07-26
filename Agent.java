package org.shopistan.agent;
import org.apache.log4j.Logger;
Agent X, Agent Y
public class Agent
{
	
	public static Logger logger = Logger.getRootLogger();
	
	public static void start(){
		logger.info("*** ERP Agent started ***");
    	DirectoryMonitoring.monitor_directory();
	}
	
	public static void stop(){
		logger.debug("Exit program");
		System.exit(0);
	}
	
    public static void main( String[] args )
    {
    	if(args.length == 5 && args[0].equals("start")){
    		AgentConfig.getInstance().setBrand_code(args[1]);
    		AgentConfig.getInstance().setFolder_to_monitor(args[2]);
    		AgentConfig.getInstance().setTemp_folder(args[3]);
    		AgentConfig.getInstance().setUrl_to_inform(args[4]);
    		start();
    	}else if(args.length != 0 && args[0].equals("stop")){
    		stop();
    	}else{
    		stop();
    	}
    }
    
}
