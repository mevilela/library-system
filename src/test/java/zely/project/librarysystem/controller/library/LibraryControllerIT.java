package zely.project.librarysystem.controller.library;

import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.webjars.NotFoundException;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.mapper.LibraryMapper;
import zely.project.librarysystem.repository.library.LibraryRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.core.Is.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.List;

@SpringBootTest
class LibraryControllerIT {

    @Autowired
    LibraryController libraryController;

    @Autowired
    LibraryRepository libraryRepository;

    @Autowired
    LibraryMapper libraryMapper;


    @Autowired
    WebApplicationContext wac;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(wac).build();
    }


    @Test
    public void testListLibraryByName() throws Exception {
        mockMvc.perform(get("/api/library")
                        .queryParam("libraryName", "Central"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()", is(1)));
    }

    @Test
    void testGetAllLibraries(){
        List<LibraryDto> libraryDtos = libraryController.getAllLibraries(null);

        assertThat(libraryDtos.size()).isEqualTo(136);

    }

    @Transactional
    @Rollback
    @Test
    void testEmptyList(){
        libraryRepository.deleteAll();
        List<LibraryDto> libraryDtos = libraryController.getAllLibraries(null);

        assertThat(libraryDtos.size()).isEqualTo(0);
    }

    @Test
    void testGetById() {
        Library library = libraryRepository.findAll().get(0);


        LibraryDto libraryDto = libraryController.getLibraryById(library.getId());

        assertThat(libraryDto).isNotNull();
    }

    @Test
    void testLibraryIsNotFound(){
        assertThrows(NotFoundException.class, () -> {
            libraryController.getLibraryById(333);
        });
    }

    @Transactional
    @Rollback
    @Test
    void createNewLibraryTest(){
        LibraryDto libraryDto = new LibraryDto();
        libraryDto.setName("Library 2");
        libraryDto.setAddress("45, Flower Street");
        libraryDto.setId(2);

        ResponseEntity responseEntity = libraryController.createNewLibrary(libraryDto);

        assertThat(responseEntity).isNotNull();
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatus.CREATED);

        LibraryDto createdLibrary = (LibraryDto) responseEntity.getBody();
        assertThat(createdLibrary).isNotNull();

    }

    @Transactional
    @Rollback
    @Test
    void updateExistingLibrary(){
        Library library = libraryRepository.findAll().get(0);
        LibraryDto libraryDto = libraryMapper.toLibraryDto(library);

        final String newName = "Library FLower Street";

        libraryDto.setName(newName);

        ResponseEntity responseEntity = libraryController.updateLibraryById(library.getId(), libraryDto);
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        Library updatedLibrary = libraryRepository.findById(library.getId()).get();
        assertThat(updatedLibrary.getName()).isEqualTo(newName);
    }

    @Test
    void testUpdateNotFound(){
        assertThrows(NotFoundException.class, () ->
                libraryController.updateLibraryById(2222, null));
    }

    @Transactional
    @Rollback
    @Test
    void deleteByIdFound(){
        Library library = libraryRepository.findAll().get(0);

        ResponseEntity responseEntity = libraryController.deleteLibraryById(library.getId());
        assertThat(responseEntity.getStatusCode()).isEqualTo(HttpStatusCode.valueOf(204));

        assertThat(libraryRepository.findById(library.getId()).isEmpty());


    }

    @Test
    void testDeleteNotFound(){
        assertThrows(NotFoundException.class, () ->
                libraryController.deleteLibraryById(333));
    }
}