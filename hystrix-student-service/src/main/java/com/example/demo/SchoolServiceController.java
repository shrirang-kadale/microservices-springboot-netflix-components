/**
 * 
 */
package com.example.demo;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

/**
 * @author skadale
 *
 */

@RestController
public class SchoolServiceController {
	
    @Autowired
    RestTemplate restTemplate;
    
    
    @HystrixCommand(fallbackMethod = "callStudentServiceAndGetData_Fallback")
    @RequestMapping(value = "/getSchoolDetails/{schoolname}", method = RequestMethod.GET)
    public String getStudents(@PathVariable String schoolname) {

    	 
        System.out.println("Getting School details for " + schoolname);
 
        String response = restTemplate
                .exchange("http://localhost:9005/getStudentDetailsForSchool/{schoolname}"
                , HttpMethod.GET
                , null
                , new ParameterizedTypeReference<String>() {
            }, schoolname).getBody();
 
        System.out.println("Response Received as " + response + " -  " + new Date());
 
        return "NORMAL FLOW !!! - School Name -  " + schoolname + " :::  " +
                    " Student Details " + response + " -  " + new Date();
    }
    
    @SuppressWarnings("unused")
    private String callStudentServiceAndGetData_Fallback(String schoolname) {
 
        System.out.println("Student Service is down!!! fallback route enabled...");
 
        return "CIRCUIT BREAKER ENABLED!!! No Response From Student Service at this moment. " +
                    " Service will be back shortly - " + new Date();
    }
 
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
