package zely.project.librarysystem.controller.library;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import zely.project.librarysystem.mapper.RackMapper;
import zely.project.librarysystem.repository.library.RackRepository;

@SpringBootTest
class RackControllerIT {

    @Autowired
    RackController rackController;

    @Autowired
    RackRepository rackRepository;

    @Autowired
    RackMapper rackMapper;


    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    void testGetAllRacks() {


    }
    }
}