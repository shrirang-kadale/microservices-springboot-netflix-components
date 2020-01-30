package com.example.demo;
 
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
 
@RestController
public class SchoolServiceController {
	
    @Autowired
    RestTemplate restTemplate;
    
    @Autowired DiscoveryClient discoveryClient;
    
    @GetMapping(value = "/getSchoolDetails")
    public String getS() {
    	return "wooooooooooooohhhhhhhhhoooooooooo";
    }

 
    @GetMapping(value = "/getSchoolDetails/{schoolname}")
    public String getStudents(@PathVariable String schoolname) 
    {
        System.out.println("Getting School details for " + schoolname);
        

        List<ServiceInstance> instances = discoveryClient.getInstances("discovery-client-1");
        
        String URL = instances.get(0).getInstanceId() + "/getStudentDetailsForSchool/{schoolname}";
        
        String Host = "http://" + instances.get(0).getServiceId() + ":" + instances.get(0).getPort() + "/getStudentDetailsForSchool/{schoolname}";
        
        System.out.println("URL >>>>>>> " + URL);
        
        System.out.println("Host +++++++ " + Host);
        
        String response = restTemplate.exchange(Host,
                                HttpMethod.GET, null, new ParameterizedTypeReference<String>() {}, schoolname).getBody();
 
        System.out.println("Response Received as " + response);
        
 
        return "School Name -  " + schoolname + " \n Student Details " + response;
    }
 
    @Bean
    @LoadBalanced
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}