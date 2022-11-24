package com.javapoint.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.text.ParseException;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

public class ReviewControllerTest {
	
	
	

	 private String responseBody;
	    public String responseBodyPOST;
	    final static Logger logger = Logger.getLogger(ReviewControllerTest.class);
	    //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    //Review ID 
	    private long reviewId;
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    private ResponseEntity<String> response;
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	        logger.info("Setting up prerequisite for test execution");
	        logger.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	
	    @Test
	    public void addReview() throws IOException, ParseException {
	        String addURI = "http://localhost:8080/api/save/ReviewDetails";
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Accept", "application/json");
	        headers.add("Content-Type", "application/json");
	          
	        logger.info("Add URL :"+addURI);
	        String jsonBody = "{\"movie_reviews\":\"good\",\n"
	        		+ "\"movie_ratings\": 2.3}";
	        System.out.println("\n\n" + jsonBody);
	        HttpEntity<String> entity = new HttpEntity <String>(jsonBody, headers);
	         
	        //POST Method to Add New review
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBodyPOST = response.getBody();
	        // Write response to file
	        responseBody = response.getBody().toString();
	        System.out.println("responseBody;" + responseBody);
	        // Get ID from the Response object
	        reviewId = getReviewIdFromResponse(responseBody);
	        System.out.println("review Id is :" + reviewId);
	        // Check if the added Review is present in the response body.
	        Assert.assertTrue(reviewId>0);
	        // System.out.println(propertyFile.get("UserAddResBody"));
	        // Check if the status code is 201
	        assertEquals(response.getStatusCode(), HttpStatus.OK);
	        logger.info("user is Added successfully reviewId:"+reviewId);
	    }
	   
	    
	    
	    public static long getReviewIdFromResponse(String json) {
	        JSONParser parser = new JSONParser();
	        JSONObject jsonResponseObject = new JSONObject();
	      //  Object obj = new Object();
	        try {
	        	jsonResponseObject = (JSONObject) parser.parse(json);
	        } catch (org.json.simple.parser.ParseException e) {
	            e.printStackTrace();
	        }
	 //       jsonResponseObject = (JSONObject) obj;
	    long id = (Long) jsonResponseObject.get("review_id");
	        logger.debug("Review id: " + id );
	        return id;
	    }
	    
	    
	    
	    

	    @Test (dependsOnMethods = "getReview", enabled = true) 
	      public void  updateReview() throws IOException, ParseException { 
	          String updateURI = "http://localhost:8080/api/updateUserDetails";
	          logger.info("Update URL :"+updateURI);
	          
	          responseBodyPOST = response.getBody();
	          
	          JSONParser parser = new JSONParser();
		        JSONObject jsonResponseObject = new JSONObject();
		      //  Object obj = new Object();
		        try {
		        	jsonResponseObject = (JSONObject) parser.parse(responseBodyPOST);
		        } catch (org.json.simple.parser.ParseException e) {
		            e.printStackTrace();
		        }
		 //       jsonResponseObject = (JSONObject) obj;
		        jsonResponseObject.put("movie_reviews", "very good");
		      
	          
	           
	         // jsonBody = jsonBody.replace("zozo100", "update_zozo100");
	           
	          HttpHeaders headers = new HttpHeaders();      
	          headers.add("Accept", "application/json");
	          headers.add("Content-Type", "application/json");
	           
	          HttpEntity <String> entity = new HttpEntity <String>(jsonResponseObject.toJSONString(), headers);
	          System.out.printf("entity", entity);
	           
	          //PUT Method to Update the existing review
	          //NOTE that I have Not used restTemplate.put as it's void and we need response for verification
	          response = restTemplate.exchange(updateURI, HttpMethod.PUT, entity, String.class);
	          logger.info(response);
	          
	          responseBody = response.getBody().toString();
	          System.out.println("Update Response Body :"+responseBody);          
	       
	          //  Check if the updated review is present in the response body.
	       //   Assert.assertTrue(responseBody.contains("update_zozo100"));
	           
	          // Check if the status code is 200
	          Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	           
	          logger.info("User Name is Updated successfully userId:"+reviewId);
	       
	      }
	    
	    
	    @Test (dependsOnMethods = "addReview", enabled = true) 
	      void getReview() throws IOException, ParseException {     
	          String getURI = "http://localhost:8080/api/getReviewDetailsById/"+this.reviewId;
	          logger.info("Get URL :"+getURI);
	           
	          HttpHeaders headers = new HttpHeaders();
	          HttpEntity <String> entity = new HttpEntity <String>(headers); 
	           
	          //GET Method to Get existing review
	          response = restTemplate.getForEntity(getURI,  String.class);
	           
	          // Write response to file
	          responseBody = response.getBody().toString();
	           
	          //Suppressing for log diffs
	          System.out.println("GET Response Body :"+responseBody);
	           
	           
	           // Check if the added Review ID is present in the response body.
	          // Assert.assertTrue(responseBody.contains("update_zozo100"));
	           
	          // Check if the status code is 200
	         // System.out.printf("response---", response.getStatusCode());
	          
	          Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	           
	          logger.info("User is retrieved successfully reviewId:"+reviewId);
	       
	      }   
 
	    
	    @Test (dependsOnMethods = "addReview", enabled = true)
        public void deleteReview() throws IOException, ParseException {
            String delURI = "http://localhost:8080/api/deleteReviewById/"+(this.reviewId-1);
            
           System.out.printf("deleteURI----", delURI);
            HttpHeaders headers = new HttpHeaders();
            HttpEntity <String> entity = new HttpEntity <String>(headers); 
             
            //DELETE Method to Delete existing review
            response = restTemplate.exchange(delURI, HttpMethod.DELETE, entity, String.class);  
     
            // Check if the status code is 204
           System.out.printf("response code-------", response.getStatusCode());
            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);           
             
			
        }

	   
 
	    
	    public static String getMessageFromResponse(String json) {
            String successMessageText = null;
            try {
                JSONParser parser = new JSONParser();
                JSONObject jsonResponseObject = new JSONObject();
                jsonResponseObject = (JSONObject) (parser.parse(json));
                String successMessage = jsonResponseObject.get("success").toString();
                 
                jsonResponseObject = (JSONObject) (parser.parse(successMessage));
                successMessageText = jsonResponseObject.get("text").toString();
            } catch (org.json.simple.parser.ParseException e) {
                e.printStackTrace();
            }
            return successMessageText;
        }
         
    @AfterTest
    public void afterTest() {
        logger.info("Clean up after test execution");
        logger.info("Creating RestTemplate object as Null");
        this.restTemplate = new RestTemplate(); 
    }

}
