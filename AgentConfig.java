package org.shopistan.agent;

public class AgentConfig {
	
	private String brand_code;
	private String folder_to_monitor;
	private String temp_folder;
	private String url_to_inform;
	
	private static AgentConfig instance = null;
	
	private AgentConfig(){
		
	}
	
	public static AgentConfig getInstance(){
		 if(instance == null) {
	         instance = new AgentConfig();
	      }
	      return instance;
	}
	
	

	public String getUrl_to_inform() {
		return url_to_inform;
	}



	public void setUrl_to_inform(String url_to_inform) {
		this.url_to_inform = url_to_inform;
	}



	public static void setInstance(AgentConfig instance) {
		AgentConfig.instance = instance;
	}



	public String getBrand_code() {
		return brand_code;
	}

	public void setBrand_code(String brand_code) {
		this.brand_code = brand_code;
	}

	public String getFolder_to_monitor() {
		return folder_to_monitor;
	}

	public void setFolder_to_monitor(String folder_to_monitor) {
		this.folder_to_monitor = folder_to_monitor;
	}

	public String getTemp_folder() {
		return temp_folder;
	}

	public void setTemp_folder(String temp_folder) {
		this.temp_folder = temp_folder;
	}
	
	
	
	

}
