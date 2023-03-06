package com.nadajp.sample.demo.controllers;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.web.client.RestTemplate;

import com.nadajp.sample.demo.model.Resource;
import com.nadajp.sample.demo.model.ResourceResponse;
import com.nadajp.sample.demo.model.ResourcesResponse;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class ResourceControllerTest {
    
    @InjectMocks
    private ResourceController resourceController = new ResourceController();

    @Mock
    private RestTemplate restTemplateMock;

    Resource mockResource;
    List<Resource> mockResourceList;

    public final String URL = "https://reqres.in/api/";

    @BeforeEach                                         
    void setUp() {
        ReflectionTestUtils.setField(resourceController, "reqresUrl", "https://reqres.in/api/");
        setupMockResources();
    }

    @Test
    void getResourceByIdSuccessTest() {    
        
        ResourceResponse resp = new ResourceResponse();
        resp.setData(mockResource);

        Mockito
        .when(restTemplateMock.getForEntity(
            URL + "resource/1", ResourceResponse.class))
            .thenReturn(new ResponseEntity<>(resp, HttpStatus.OK));

        Resource actualResource = resourceController.getResourceById(1);
        
        assertEquals(mockResource.getId(), actualResource.getId());
        assertEquals(mockResource.getName(), actualResource.getName());
        assertEquals(mockResource.getColor(), actualResource.getColor());
        assertEquals(mockResource.getPantone_value(), actualResource.getPantone_value());
  }

  @Test
  void getResourceByIdFailureTest() {         
      Mockito
      .when(restTemplateMock.getForEntity(
          URL + "resource/1", ResourceResponse.class))
          .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

      Resource response = resourceController.getResourceById(1);
      
      assertEquals(null, response);
    }

    @Test
    void getResourceListTest() {
        setupMockResources();

        ResourcesResponse resp = new ResourcesResponse();
        resp.setData(mockResourceList);

        Mockito.when(restTemplateMock.getForEntity(URL + "resource", ResourcesResponse.class))
        .thenReturn(new ResponseEntity<>(resp, HttpStatus.OK));

        List<Resource> response = resourceController.getResourceList(null, null);
        
        assertEquals(2, response.size());
        assertEquals(mockResourceList.get(0).getColor(), response.get(0).getColor());
        assertEquals(mockResourceList.get(1).getColor(), response.get(1).getColor());
        assertEquals(mockResourceList.get(0).getName(), response.get(0).getName());
        assertEquals(mockResourceList.get(1).getName(), response.get(1).getName());
    }

    @Test
    void getResourceListFailureTest() {         
        Mockito
        .when(restTemplateMock.getForEntity(
            URL + "resource", ResourcesResponse.class))
            .thenReturn(new ResponseEntity<>(null, HttpStatus.BAD_REQUEST));

        List<Resource> response = resourceController.getResourceList(null, null);
        
        assertEquals(null, response);
    }

    @Test
    void getResourceListWithParamsTest() {
        setupMockResources();

        ResourcesResponse resp = new ResourcesResponse();
        resp.setData(mockResourceList);

        Mockito.when(restTemplateMock.getForEntity(URL + "resource?page=1&per_page=2", ResourcesResponse.class))
        .thenReturn(new ResponseEntity<>(resp, HttpStatus.OK));

        List<Resource> response = resourceController.getResourceList(1, 2);
        
        assertEquals(2, response.size());
        assertEquals(mockResourceList.get(0).getColor(), response.get(0).getColor());
        assertEquals(mockResourceList.get(1).getColor(), response.get(1).getColor());
        assertEquals(mockResourceList.get(0).getName(), response.get(0).getName());
        assertEquals(mockResourceList.get(1).getName(), response.get(1).getName());
    }


    private void setupMockResources() {
        Resource res1 = new Resource(1, "cerulean", 2000, "#98B2D1", "15-4020");
        Resource res2 = new Resource(2, "fuchsia rose", 2001, "#C74375","17-2031");
        
        mockResource = res1;
        mockResourceList = new ArrayList<Resource>();
        mockResourceList.add(res1);
        mockResourceList.add(res2);
    }
}
