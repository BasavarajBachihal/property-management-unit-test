package com.mycompany.propertymanagement.controller;


import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.service.PropertyService;
import org.junit.jupiter.api.Assertions;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

@ExtendWith(MockitoExtension.class)
public class PropertyControllerTest {
    @InjectMocks
    private PropertyController propertyController;
    @Mock
    private PropertyService propertyService;

    @Test
    @DisplayName("Test Success Scenario for saving new Property")
    void testSaveProperty(){

        PropertyDTO dto = new PropertyDTO();
        dto.setTitle("Dummy property");

        PropertyDTO savedProperty = new PropertyDTO();
        savedProperty.setId(1L);
        savedProperty.setTitle(dto.getTitle());

        Mockito.when(propertyService.saveProperty(dto)).thenReturn(savedProperty);

        ResponseEntity<PropertyDTO> responseEntity =propertyController.saveProperty(dto);
        Assertions.assertNotNull(responseEntity.getBody().getId());
        Assertions.assertEquals(HttpStatus.CREATED.value(), responseEntity.getStatusCodeValue());

    }
    @Test
    @DisplayName("Test Success Scenario for Fetching new Properties")
    void testGetProperty(){
        List<PropertyDTO> propertyList = new ArrayList<>();
        PropertyDTO dto = new PropertyDTO();
        dto.setId(1L);
        dto.setTitle("Dummy Property");
        propertyList.add(dto);

        Mockito.when(propertyService.getAllProperties()).thenReturn(propertyList);
        ResponseEntity<List<PropertyDTO>> responseEntity =propertyController.getAllProperties();
         assertEquals(1, responseEntity.getBody().size());
         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());

}
     @Test
    @DisplayName("Test Success Scenario for Updating only price of the Properties")
    void testUpdateProperty(){

        PropertyDTO dto = new PropertyDTO();
        dto.setPrice(67676.78);
        Mockito.when(propertyService.updateProperty(Mockito.any(),Mockito.anyLong())).thenReturn(dto);
         ResponseEntity<PropertyDTO> responseEntity = propertyController.updateProperty(dto, 1L);

         assertEquals(67676.78, responseEntity.getBody().getPrice());
         assertEquals(HttpStatus.OK.value(), responseEntity.getStatusCodeValue());
     }
}
