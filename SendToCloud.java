package org.shopistan.agent;

import java.io.File;

import org.apache.log4j.Logger;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;



public class SendToCloud{
	
	private static String aws_key = "AKIAIH2FJKBTB2J3ROTQ";
	private static String aws_secret = "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";
	private static String bucket = "brandinventories";
	private static Logger logger = Logger.getRootLogger();
	
	public static void SendToS3(String file, String brand_code){
	
			BasicAWSCredentials awsCred = new BasicAWSCredentials(aws_key, aws_secret);
			AmazonS3 s3client = new AmazonS3Client(awsCred);
			try {
	            logger.info("Uploading a new object to S3 from a file\n");
	            File file_to_be_upload = new File(file);
	            s3client.putObject(new PutObjectRequest(
	            		                 bucket, brand_code+"/"+file_to_be_upload.getName().replace(" ", "_"), file_to_be_upload));
	            
	            MiddleWareIntegration.InformMiddleWare(file_to_be_upload.getName().replace(" ", "_"), brand_code);
	            file_to_be_upload.delete();
	         } catch (AmazonServiceException ase) {
	            logger.error("Caught an AmazonServiceException, which " +
	            		"means your request made it " +
	                    "to Amazon S3, but was rejected with an error response" +
	                    " for some reason.");
	            logger.error("Error Message:    " + ase.getMessage());
	        } catch (AmazonClientException ace) {
	            logger.error("Caught an AmazonClientException, which " +
	            		"means the client encountered " +
	                    "an internal error while trying to " +
	                    "communicate with S3, " +
	                    "such as not being able to access the network.");
	            logger.error("Error Message: " + ace.getMessage());
	        }

	}

}
