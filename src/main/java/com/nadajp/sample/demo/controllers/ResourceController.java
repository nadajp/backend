package com.nadajp.sample.demo.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.nadajp.sample.demo.model.Resource;
import com.nadajp.sample.demo.model.ResourceResponse;
import com.nadajp.sample.demo.model.ResourcesResponse;

@RestController
@RequestMapping(value="/resource", produces = "application/json")
@CrossOrigin(origins = {"http://localhost:4200", "https://replit.com/@nadajp/frontend"})
public class ResourceController {

    @Value("${reqresUrl}")
    private String reqresUrl;

    @Autowired
    private RestTemplate restTemplate;
    
    @GetMapping("")
    public List<Resource> getResourceList(@RequestParam(required = false) Integer page, 
                                           @RequestParam(required = false) Integer per_page) {
        
        String url = buildUrl(reqresUrl, page, per_page);                                    
        ResponseEntity<ResourcesResponse> entity =  restTemplate.getForEntity(url,ResourcesResponse.class);

        return entity.getStatusCode() == HttpStatus.OK ? entity.getBody().getData() : null;
    }

    private String buildUrl(String reqresUrl, Integer page, Integer per_page) {
        String url= reqresUrl + "resource";
        if (page != null) {
            url += "?page=" + page;
            if (per_page != null) {
                url += "&per_page=" + per_page;
            }
        }
        return url;
    }

    @GetMapping("/{id}")
    public Resource getResourceById(@PathVariable long id) {
        String url= reqresUrl + "resource/" + id;
  
        ResponseEntity<ResourceResponse> entity =  restTemplate.getForEntity(url, ResourceResponse.class);
        
        // add null check
        return entity.getStatusCode() == HttpStatus.OK ? entity.getBody().getData() : null;
    }

    @DeleteMapping(value = "/{id}")
	public void deleteResource(@PathVariable int id) {
        String url= reqresUrl + "resource/" + id;

        restTemplate.delete(url);		
	}
}