package org.shopistan.agent;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import org.apache.log4j.Logger;

public class ZipConversion {

	private static Logger logger = Logger.getRootLogger();
	private static String zip_file_path = AgentConfig.getInstance().getTemp_folder();
	
	public static String convertIntoZip(String old_file,String brand_code){
		String zipFileName = "";
		try{
			File zip_file = new File(zip_file_path);
			if(!zip_file.exists()){
				zip_file.mkdir();
			}
			zipFileName = brand_code+"_inventory_"+new SimpleDateFormat("yyyy-MM-dd_hh-mm-ss'.zip'").format(new Date());
			FileOutputStream fos = new FileOutputStream(zip_file_path+"/"+zipFileName);
			ZipOutputStream zos = new  ZipOutputStream(fos);
			File f = new File(old_file);
			ZipEntry ze = new ZipEntry(f.getName());
			zos.putNextEntry(ze);
			FileInputStream in = new FileInputStream(f);
			byte[] bytes = new byte[1024];
			int c;
		    while ((c = in.read(bytes)) >= 0) {  
		    	zos.write(bytes, 0, c);
	        }
		    in.close();
		    zos.closeEntry();
		    zos.close();
		}catch(Exception ex){
			logger.error(ex.getMessage(),new Exception("Zip Exception"));
		}
		return zip_file_path+"/"+zipFileName;
	}
}
