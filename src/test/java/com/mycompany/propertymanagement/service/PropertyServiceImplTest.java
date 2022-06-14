package com.mycompany.propertymanagement.service;


import com.mycompany.propertymanagement.converter.PropertyConverter;
import com.mycompany.propertymanagement.dto.PropertyDTO;
import com.mycompany.propertymanagement.entity.PropertyEntity;
import com.mycompany.propertymanagement.exception.BusinessException;
import com.mycompany.propertymanagement.repository.PropertyRepository;
import com.mycompany.propertymanagement.service.impl.PropertyServiceImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.when;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



    @ExtendWith(MockitoExtension.class)

    public class PropertyServiceImplTest {

        @InjectMocks
        private PropertyServiceImpl propertyServiceImpl;

        @Mock
        private PropertyConverter propertyConverter;

        @Mock
        private PropertyRepository propertyRepository;

        @Test
        void testSaveProperty_Success(){

            PropertyDTO dto = new PropertyDTO();
            dto.setTitle("Dummy");

            PropertyEntity propertyEntity = new PropertyEntity();
            propertyEntity.setTitle("Dummy");

            PropertyEntity savedEntity = new PropertyEntity();
            savedEntity.setTitle("Dummy");
            savedEntity.setId(1L);

            PropertyDTO savedDTO = new PropertyDTO();
            savedDTO.setTitle("Dummy");
            savedDTO.setId(1L);

            Mockito.when(propertyConverter.convertDTOtoEntity(Mockito.any())).thenReturn(propertyEntity);
            Mockito.when(propertyRepository.save(Mockito.any())).thenReturn(savedEntity);
            Mockito.when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

            PropertyDTO result = propertyServiceImpl.saveProperty(dto);
            Assertions.assertEquals(savedDTO.getId(), result.getId());
        }

        @Test
        void testGetAllProperty_Success() {

            List<PropertyEntity> propertyEntities = new ArrayList();
            PropertyEntity propertyEntity = new PropertyEntity();
            propertyEntity.setId(1L);
            propertyEntity.setTitle("Dummy");
            propertyEntities.add(propertyEntity);
            PropertyDTO savedDTO = new PropertyDTO();
            savedDTO.setTitle("Dummy");
            savedDTO.setId(1L);

            Mockito.when(propertyRepository.findAll()).thenReturn(propertyEntities);
            Mockito.when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);
            List<PropertyDTO> listPropDTO = propertyServiceImpl.getAllProperties();

            Assertions.assertEquals(1, listPropDTO.size());

        }

        @Test
        void testUpdateProperty_Success() {
            PropertyDTO savedDTO = new PropertyDTO();
            savedDTO.setTitle("Dummy");
            savedDTO.setId(1L);
            savedDTO.setPrice(234455.4);
            savedDTO.setAddress("xyz");
            savedDTO.setDescription("abc");

            PropertyEntity pe = new PropertyEntity();
            pe.setId(1l);
            pe.setTitle("Dummy");
            savedDTO.setId(1L);
            pe.setPrice(234455.4);
            pe.setDescription("xyz");

            when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
            when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

            PropertyDTO uodatedProperty = propertyServiceImpl.updateProperty(savedDTO, 1L);
            Assertions.assertEquals(savedDTO.getTitle(), uodatedProperty.getTitle());
            Assertions.assertEquals(savedDTO.getPrice(), uodatedProperty.getPrice());

        }

        @Test
        void testUpdatePropertyDescription_Success() {
            PropertyDTO savedDTO = new PropertyDTO();
            savedDTO.setTitle("Dummy");
            savedDTO.setId(1L);
            savedDTO.setPrice(234455.4);
            savedDTO.setAddress("xyz");
            savedDTO.setDescription("Updated abc");
            PropertyEntity pe = new PropertyEntity();
            pe.setId(1l);
            pe.setTitle("Dummy");
            savedDTO.setId(1L);
            pe.setPrice(234455.4);
            pe.setDescription("Updated abc");

            when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(pe));
            when(propertyConverter.convertEntityToDTO(Mockito.any())).thenReturn(savedDTO);

            PropertyDTO uodatedProperty = propertyServiceImpl.updateProperty(savedDTO, 1L);
            Assertions.assertEquals(savedDTO.getDescription(), uodatedProperty.getDescription());


        }

        /*@Test
        void testUpdatePropertyDescription_Failure() {

            PropertyDTO savedDTO = new PropertyDTO();
            when(propertyRepository.findById(Mockito.anyLong())).thenReturn(Optional.empty());
            BusinessException exception = Assertions.assertThrows(BusinessException.class, () -> {
                PropertyDTO uodatedProperty = propertyServiceImpl.updateProperty(savedDTO, 1L);
            });
            Assertions.assertEquals("No Property Found for update", exception.getMessage());

        }*/
    }


