package zely.project.librarysystem.repository.library;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest
class RackRepositoryTest {

    @Autowired
    RackRepository rackRepository;

    @Test
    void testRacks(){

        System.out.println(rackRepository.count());
    }

}