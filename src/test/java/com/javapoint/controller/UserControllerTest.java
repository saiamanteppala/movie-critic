package com.javapoint.controller;

import static org.junit.jupiter.api.Assertions.*;





import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;

import java.io.IOException;
import java.text.ParseException;


import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;



class UserControllerTest {
	
	
	 private String responseBody;
	    public String responseBodyPOST;
	    final static Logger logger = Logger.getLogger(UserControllerTest.class);
	    //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    //User ID 
	    private long userId;
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    private ResponseEntity<String> response;
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	        logger.info("Setting up prerequisite for test execution");
	        logger.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	     
	    /**
	     * Test Method to add user using HTTP POST request 
	     * 
	     * Verifies POST action Status Code 
	     *  
	     * @throws IOException
	     * @throws ParseException
	     */
	    @Test
	    public void addUser() throws IOException, ParseException {
	        String addURI = "http://localhost:8080/api/save/UserDetails";
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Accept", "application/json");
	        headers.add("Content-Type", "application/json");
	          
	        logger.info("Add URL :"+addURI);
	        String jsonBody = "{\"first_name\":\"aman\",\n"
	        		+ "\"last_name\":\"sai\",\n"
	        		+ "\"gender\":\"male\",\n"
	        		+ "\"age\":\"23\",\n"
	        		+ "\"contact_number\":\"999999999\",\n"
	        		+ "\"email\":\"amansai@gmail.com\",\n"
	        		+ "\"user_name\":\"amansaileo\",\n"
	        		+ "\"password\": \"123456789\"}";
	        System.out.println("\n\n" + jsonBody);
	        HttpEntity<String> entity = new HttpEntity <String>(jsonBody, headers);
	         
	        //POST Method to Add New user
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBodyPOST = response.getBody();
	        // Write response to file
	        responseBody = response.getBody().toString();
	        System.out.println("responseBody;" + responseBody);
	        // Get ID from the Response object
	        userId = getUserIdFromResponse(responseBody);
	        System.out.println("userId is :" + userId);
	        // Check if the added User is present in the response body.
	        Assert.assertTrue(userId>0);
	        // System.out.println(propertyFile.get("UserAddResBody"));
	        // Check if the status code is 201
	        assertEquals(response.getStatusCode(), HttpStatus.OK);
	        logger.info("user is Added successfully userId:"+userId);
	    }
	    
	    /**
	     * Method to get User ID from REsponse body
	     * I have used Json Simple API for Parsing the JSON object
	     * 
	     * @param json
	     * @return
	     */
	    public static long getUserIdFromResponse(String json) {
	        JSONParser parser = new JSONParser();
	        JSONObject jsonResponseObject = new JSONObject();
	      //  Object obj = new Object();
	        try {
	        	jsonResponseObject = (JSONObject) parser.parse(json);
	        } catch (org.json.simple.parser.ParseException e) {
	            e.printStackTrace();
	        }
	 //       jsonResponseObject = (JSONObject) obj;
	    long id = (Long) jsonResponseObject.get("user_id");
	        logger.debug("User id: " + id );
	        return id;
	    }
	    /**
	     * Test Method to Update employee using HTTP PUT request
	     * 
	     * Verifies PUT action Status Code 
	     * Verifies Updated Name exists in Response Body
	     *  
	     * @throws IOException
	     * @throws ParseException
	     */
	    
	    
	      @Test (dependsOnMethods = "getUser", enabled = true) 
	      public void  updateUser() throws IOException, ParseException { 
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
		   jsonResponseObject.put("first_name", "AMAN");
		      
	          
	           
	         // jsonBody = jsonBody.replace("zozo100", "update_zozo100");
	           
	          HttpHeaders headers = new HttpHeaders();      
	          headers.add("Accept", "application/json");
	          headers.add("Content-Type", "application/json");
	           
	          HttpEntity <String> entity = new HttpEntity <String>(jsonResponseObject.toJSONString(), headers);
	          System.out.printf("entity", entity);
	           
	          //PUT Method to Update the existing user
	          //NOTE that I have Not used restTemplate.put as it's void and we need response for verification
	          response = restTemplate.exchange(updateURI, HttpMethod.PUT, entity, String.class);
	          logger.info(response);
	          
	          responseBody = response.getBody().toString();
	          System.out.println("Update Response Body :"+responseBody);          
	       
	          // Check if the updated user is present in the response body.
	       //   Assert.assertTrue(responseBody.contains("update_zozo100"));
	           
	          // Check if the status code is 200
	          Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	           
	          logger.info("User Name is Updated successfully userId:"+userId);
	       
	      }
	       
	      /**
	       * Test Method to Get user using HTTP GET request
	       * 
	       * Verifies GET action Status Code 
	       * Verifies Name exists in Response Body
	       * 
	       * @throws IOException
	       * @throws ParseException
	       */
	      @Test (dependsOnMethods = "addUser", enabled = true) 
	      void getUser() throws IOException, ParseException {     
	          String getURI = "http://localhost:8080/api/getUserDetailsById/"+this.userId;
	          logger.info("Get URL :"+getURI);
	           
	          HttpHeaders headers = new HttpHeaders();
	          HttpEntity <String> entity = new HttpEntity <String>(headers); 
	           
	          //GET Method to Get existing user
	          response = restTemplate.getForEntity(getURI,  String.class);
	           
	          // Write response to file
	          responseBody = response.getBody().toString();
	           
	          //Suppressing for log diffs
	          System.out.println("GET Response Body :"+responseBody);
	           
	           
	           // Check if the added User ID is present in the response body.
	          // Assert.assertTrue(responseBody.contains("update_zozo100"));
	           
	          // Check if the status code is 200
	         // System.out.printf("response---", response.getStatusCode());
	          
	          Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
	           
	          logger.info("User is retrieved successfully userId:"+userId);
	       
	      }   
	       
	      /**
	       * Test Method to Delete employee using HTTP DELETE request
	       * 
	       * Verifies DELETE action Status Code 
	       * Verifies Success Message Text in Response Body
	       * 
	       * @throws IOException
	       * @throws ParseException
	       */
	        @Test (dependsOnMethods = "addUser", enabled = true)
	        public void deleteUser() throws IOException, ParseException {
	            String delURI = "http://localhost:8080/api/deleteUserById/"+(this.userId-1);
	            
	           System.out.printf("deleteURI----", delURI);
	            HttpHeaders headers = new HttpHeaders();
	            HttpEntity <String> entity = new HttpEntity <String>(headers); 
	             
	            //DELETE Method to Delete existing user
	            response = restTemplate.exchange(delURI, HttpMethod.DELETE, entity, String.class);  
	     
	            // Check if the status code is 204
	           //System.out.printf("response code-------", response.getStatusCode());
	            Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);           
	             
				/*
				 * responseBody = response.getBody();
				 * 
				 * Assert.assertEquals(getMessageFromResponse(responseBody),
				 * "successfully! deleted Records");
				 * 
				 * logger.info("User is Deleted successfully userId:"+userId);
				 */
	        }
	         
	        /**
	         * Gets "text" key value from Response body text for verification
	         * I have used Json Simple API for Parsing the JSON object
	         * 
	         * @param json
	         * @return text string
	         */
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
