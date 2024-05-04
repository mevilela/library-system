package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.mapper.LibraryMapper;
import zely.project.librarysystem.repository.library.LibraryRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class LibraryServiceImpl implements LibraryService {

    private final LibraryRepository libraryRepository;
    private final LibraryMapper libraryMapper;

    public LibraryServiceImpl(LibraryRepository libraryRepository, LibraryMapper libraryMapper) {
        this.libraryRepository = libraryRepository;
        this.libraryMapper = libraryMapper;
    }


    @Override
    public List<LibraryDto> getAllLibraries(String libraryName) {

        List<Library> libraryList;

        if(StringUtils.hasText(libraryName)){
            libraryList = listLibraryByName(libraryName);
        } else {
            libraryList = libraryRepository.findAll();
        }

        return libraryList.stream()
                .map(libraryMapper::toLibraryDto)
                .collect(Collectors.toList());

    }

   public List<Library> listLibraryByName(String libraryName){
        return libraryRepository.findAllByNameIsLikeIgnoreCase("%" + libraryName + "%");
    }


    @Override
    public Optional<LibraryDto> getLibraryById(Integer id) {
        return Optional.ofNullable(libraryMapper.toLibraryDto(libraryRepository.findById(id).orElse(null)));
    }

    @Override
    public LibraryDto createNewLibrary(LibraryDto libraryDto) {
        return libraryMapper.toLibraryDto(libraryRepository.save(libraryMapper.toLibraryEntity(libraryDto)));
    }

    @Override
    public Optional<LibraryDto> updateLibrary(Integer id, LibraryDto libraryDto) {
        return libraryRepository.findById(id)
                .map(foundLibrary -> {
                    foundLibrary.setName(libraryDto.getName());
                    foundLibrary.setAddress(libraryDto.getAddress());

                    return foundLibrary;
                })
                .map(libraryRepository::save)
                .map(libraryMapper::toLibraryDto);

    }
    @Override
    public Boolean deleteLibraryById(Integer id){
        if (libraryRepository.existsById(id)){
            libraryRepository.deleteById(id);
            return true;
        }

        return false;
    }

}