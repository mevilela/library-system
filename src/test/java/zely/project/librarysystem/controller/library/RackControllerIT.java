package zely.project.librarysystem.controller.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zely.project.librarysystem.dto.library.RackDto;
import zely.project.librarysystem.repository.library.RackRepository;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class RackControllerIT {

    @Autowired
    RackController rackController;

    @Autowired
    RackRepository rackRepository;



    @Test
    void testGetAllRacks() {

        List<RackDto> rackDtoList = rackController.getAllRacks();

        assertThat(rackDtoList.size()).isNotNull();


    }
}