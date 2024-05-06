package zely.project.librarysystem.service.library;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
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

    private static final  Integer DEFAULT_PAGE = 0;
    private static final  Integer DEFAULT_PAGE_SIZE = 25;


    @Override
    public Page<LibraryDto> getAllLibraries(String libraryName, Integer pageNumber, Integer pageSize) {


        PageRequest pageRequest = buildRequest(pageNumber, pageSize);

        Page<Library> libraryPage;

        if(StringUtils.hasText(libraryName)){
            libraryPage = listLibraryByName(libraryName, pageRequest);
        } else {
            libraryPage = libraryRepository.findAll(pageRequest);
        }

        return libraryPage.map(libraryMapper::toLibraryDto);


    }

    public PageRequest buildRequest(Integer pageNumber, Integer pageSize){
        int queryPageNumber;
        int queryPageSize;

        if (pageNumber != null && pageNumber > 0){
            queryPageNumber = pageNumber - 1;
        } else {
            queryPageNumber = DEFAULT_PAGE;
        }

        if (pageSize == null){
            queryPageSize = DEFAULT_PAGE_SIZE;
        } else {
            if(pageSize > 1000){
                queryPageSize = 1000;
            } else {
                queryPageSize =  pageSize;
            }
        }

        Sort sort = Sort.by(Sort.Order.asc("name"));
        return PageRequest.of(queryPageNumber, queryPageSize, sort);
    }

   public Page<Library> listLibraryByName(String libraryName, Pageable pageable){
        return libraryRepository.findAllByNameIsLikeIgnoreCase("%" + libraryName + "%", null);
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