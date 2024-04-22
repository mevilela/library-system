package zely.project.librarysystem.service.library;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.dto.library.LibraryDto;
import zely.project.librarysystem.mapper.LibraryMapper;
import zely.project.librarysystem.repository.library.LibraryRepository;

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
    public List<LibraryDto> getAllLibraries() {
        return libraryRepository.findAll()
                .stream()
                .map(libraryMapper::toLibraryDto)
                .collect(Collectors.toList());

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
}