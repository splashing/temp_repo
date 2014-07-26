package org.shopistan.agent;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import org.apache.log4j.Logger;

public class DirectoryMonitoring {

	public static Logger logger = Logger.getRootLogger();
	private static WatchService watcher;
	
	public static void monitor_directory(){
		try{
   		 	watcher = FileSystems.getDefault().newWatchService();
            Path dir = Paths.get(AgentConfig.getInstance().getFolder_to_monitor());
            dir.register(watcher, ENTRY_CREATE);
            System.out.println("Watch Service registered for dir: " + dir.getFileName());
            while (true) {
                WatchKey key;
                try {
               	 	System.out.println("Wating for event");
                    key = watcher.take();
                } catch (InterruptedException ex) {
                	logger.error(ex.getMessage(),new Exception("Key Intruption occur"));
                	return;                   
                }
                 
                for (WatchEvent<?> event : key.pollEvents()) {
                    WatchEvent.Kind<?> kind = event.kind();
                    @SuppressWarnings("unchecked")
                    WatchEvent<Path> ev = (WatchEvent<Path>) event;
                    Path fileName = ev.context();
                     
                    System.out.println(kind.name() + ": " + fileName);
                    if (kind == ENTRY_CREATE) {
	                   	 System.out.println("New File Creation");
	                   	 System.out.println("file path: " + AgentConfig.getInstance().getFolder_to_monitor() + "/" + fileName.toString());
	                   	 String zip_file_path = ZipConversion.convertIntoZip(AgentConfig.getInstance().getFolder_to_monitor() + "/" + fileName.toString(),AgentConfig.getInstance().getBrand_code());
	                   	 SendToCloud.SendToS3(zip_file_path,AgentConfig.getInstance().getBrand_code());
                    }
                }
                 
                boolean valid = key.reset();
                if (!valid) {
                    break;
                }
            }
	   	}catch(IOException iox){
	   		System.err.println("IO exception");
	   		iox.printStackTrace();
	   	}
	   	catch(Exception ex){
	   		ex.printStackTrace();
	   	}
	}
	
}
