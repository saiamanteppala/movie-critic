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

public class MovieControllerTest {
	
	
	
	 private String responseBody;
	    public String responseBodyPOST;
	    final static Logger logger = Logger.getLogger(MovieControllerTest.class);
	    //RESTTemplate Object
	    private RestTemplate restTemplate;
	     
	    //User ID 
	    private long movieId;
	    // Create Response Entity - Stores HTTPStatus Code, Response Body, etc
	    private ResponseEntity<String> response;
	    @BeforeTest
	    public void beforeTest() throws IOException, ParseException {
	        logger.info("Setting up prerequisite for test execution");
	        logger.info("Creating RestTemplate object before tests");
	        this.restTemplate = new RestTemplate(); 
	    }
	
	
	 @Test
	 public void addMovie() throws IOException, ParseException {
	        String addURI = "http://localhost:8080/api/save/movieDetails";
	        HttpHeaders headers = new HttpHeaders();        
	        headers.add("Accept", "application/json");
	        headers.add("Content-Type", "application/json");
	          
	        logger.info("Add URL :"+addURI);
	        String jsonBody = "{\"movie_name\":\"kanthara\",\n"
	        		+ "\"movie_categories\":\"devotional\",\n"
	        		+ "\"movie_language\":\"Kannada,Telugu\",\n"
	        		+ "\"movie_genres\":\"Thriller\",\n"
	        		+ "\"release_date\":\"    \",\n"
	        		+ "\"movie_duration\":\"   \"}";
	        System.out.println("\n\n" + jsonBody);
	        HttpEntity<String> entity = new HttpEntity <String>(jsonBody, headers);
	         
	        //POST Method to Add New movie
	        response = this.restTemplate.postForEntity(addURI, entity, String.class);
	        responseBodyPOST = response.getBody();
	        // Write response to file
	        responseBody = response.getBody().toString();
	        System.out.println("responseBody;" + responseBody);
	        // Get ID from the Response object
	        movieId = getMovieIdFromResponse(responseBody);
	        System.out.println("movieId is :" + movieId);
	        // Check if the added User is present in the response body.
	        Assert.assertTrue(movieId>0);
	        // System.out.println(propertyFile.get("UserAddResBody"));
	        // Check if the status code is 201
	        assertEquals(response.getStatusCode(), HttpStatus.OK);
	        logger.info("user is Added successfully userId:"+movieId);
	    }
	 
	 public static long getMovieIdFromResponse(String json) {
	        JSONParser parser = new JSONParser();
	        JSONObject jsonResponseObject = new JSONObject();
	      //  Object obj = new Object();
	        try {
	        	jsonResponseObject = (JSONObject) parser.parse(json);
	        } catch (org.json.simple.parser.ParseException e) {
	            e.printStackTrace();
	        }
	 //       jsonResponseObject = (JSONObject) obj;
	    long id = (Long) jsonResponseObject.get("movie_id");
	        logger.debug("Movie id: " + id );
	        return id;
	    }
	 
	 

	  @Test (dependsOnMethods = "getMovie", enabled = true) 
	  public void  updateMovie() throws IOException, ParseException { 
          String updateURI = "http://localhost:8080/api//updateMovieDetails";
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
	   jsonResponseObject.put("movie_name", "KANTHARA");
	      
          
           
         // jsonBody = jsonBody.replace("zozo100", "update_zozo100");
           
          HttpHeaders headers = new HttpHeaders();      
          headers.add("Accept", "application/json");
          headers.add("Content-Type", "application/json");
           
          HttpEntity <String> entity = new HttpEntity <String>(jsonResponseObject.toJSONString(), headers);
          System.out.printf("entity", entity);
           
          //PUT Method to Update the existing movie
          //NOTE that I have Not used restTemplate.put as it's void and we need response for verification
          response = restTemplate.exchange(updateURI, HttpMethod.PUT, entity, String.class);
          logger.info(response);
          
          responseBody = response.getBody().toString();
          System.out.println("Update Response Body :"+responseBody);          
       
          // Check if the updated user is present in the response body.
       //   Assert.assertTrue(responseBody.contains("update_zozo100"));
           
          // Check if the status code is 200
          Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
           
          logger.info("User Name is Updated successfully userId:"+movieId);
       
      }

	@Test (dependsOnMethods = "addMovie", enabled = true) 
	 void getMovie() throws IOException, ParseException {     
        String getURI = "http://localhost:8080/api/getMovieDetailsById/"+this.movieId;
        logger.info("Get URL :"+getURI);
         
        HttpHeaders headers = new HttpHeaders();
        HttpEntity <String> entity = new HttpEntity <String>(headers); 
         
        //GET Method to Get existing Movie
        response = restTemplate.getForEntity(getURI,  String.class);
         
        // Write response to file
        responseBody = response.getBody().toString();
         
        //Suppressing for log diffs
        System.out.println("GET Response Body :"+responseBody);
         
         
         // Check if the added Movie ID is present in the response body.
        // Assert.assertTrue(responseBody.contains("update_zozo100"));
         
        // Check if the status code is 200
       // System.out.printf("response---", response.getStatusCode());
        
        Assert.assertEquals(response.getStatusCode(), HttpStatus.OK);
         
        logger.info("User is retrieved successfully userId:"+movieId);
     
    }   

	 @Test (dependsOnMethods = "addMovie", enabled = true)
     public void deleteMovie() throws IOException, ParseException {
         String delURI = "http://localhost:8080/api/deleteMovieById/"+(this.movieId-1);
         
       // System.out.printf("deleteURI----", delURI);
         HttpHeaders headers = new HttpHeaders();
         HttpEntity <String> entity = new HttpEntity <String>(headers); 
          
         //DELETE Method to Delete existing Movie
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
