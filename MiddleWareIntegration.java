package org.shopistan.agent;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.NameValuePair;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;

public class MiddleWareIntegration {
	
	private static Logger logger = Logger.getRootLogger();
	private static String endPoint = AgentConfig.getInstance().getUrl_to_inform();
	
	public static void InformMiddleWare(String s3_object_key, String brandCode){
		try{
			HttpClient client = new HttpClient();
			NameValuePair arg1 = new NameValuePair("brand_code",brandCode);
			NameValuePair arg2 = new NameValuePair("object_key",s3_object_key);
			logger.info("Redirect to: " + endPoint);
			PostMethod method = new PostMethod(endPoint);
			method.setRequestBody(new NameValuePair[]{arg1, arg2});
			client.executeMethod(method);
		    method.releaseConnection();
		}catch(Exception ex){
			logger.error("middle ware inform exception message: "+ex.getMessage(),new Exception("Middleware Integration Exception"));
		}
	}
	

}
