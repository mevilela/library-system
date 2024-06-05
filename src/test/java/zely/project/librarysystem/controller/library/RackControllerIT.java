package zely.project.librarysystem.controller.library;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.webjars.NotFoundException;
import zely.project.librarysystem.controller.NotFoundExceptionHandler;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.dto.library.RackDto;
import zely.project.librarysystem.mapper.RackMapper;
import zely.project.librarysystem.repository.library.RackRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
class RackControllerIT {

    @Autowired
    private RackRepository rackRepository;

    @Autowired
    private RackController rackController;

    @Autowired
    private RackMapper rackMapper;



    @Test
    void testGetRackById() {

        Rack rack = rackRepository.findAll().get(0);

        RackDto rackDto = rackController.getRackById(rack.getId());

        assertThat(rackDto).isNotNull();
        assertThat(rack.getId()).isEqualTo(rackDto.getId());

    }

    @Transactional
    @Rollback
    @Test
    void testRackIdIsNotFound() {

        assertThrows(NotFoundExceptionHandler.class, () -> {
            rackController.getRackById(55555);
        });

    }

    @Test
    void testGetAllRacks() {

        List<RackDto> rackDtos = rackController.getAllRacks();

        assertThat(rackDtos.size()).isEqualTo(10);

    }


    @Transactional
    @Rollback
    @Test
    void testCreateNewRack() {
        RackDto rackDto = new RackDto();
        rackDto.setRackNumber(1234);
        rackDto.setSection("2nd floor");
        rackDto.setSection("romance");
        rackDto.setLibrary(new LibraryDto());

        ResponseEntity responseEntity = rackController.createNewRack(rackDto);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        RackDto createdRack = (RackDto) responseEntity.getBody();
        assertThat(createdRack).isNotNull();

    }




    @Transactional
    @Rollback
    @Test
    void testUpdateRackById(){

        Rack rack = rackRepository.findAll().get(0);

        RackDto rackDto = rackMapper.toRackDto(rack);

        final int newRackNumber = 201;

        rackDto.setRackNumber(newRackNumber);

        ResponseEntity responseEntity = rackController.updateRackById(rack.getId(), rackDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        Rack updatedRack = rackRepository.findById(rack.getId()).get();
        assertThat(updatedRack.getRackNumber()).isEqualTo(newRackNumber);

    }

    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundExceptionHandler.class, () ->
                rackController.updateRackById(2222, null));
    }

    @Transactional
    @Rollback
    @Test
    void testDeleteRackById(){

        Rack rack = rackRepository.findAll().get(0);

        ResponseEntity responseEntity = rackController.deleteRackById(rack.getId());

        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.NO_CONTENT);

        assertThat(rackRepository.findById(rack.getId())).isEmpty();
    }


    @Test
    void testDeleteNotFound(){
        assertThrows(NotFoundExceptionHandler.class, () ->
                rackController.deleteRackById(333));
    }
}





