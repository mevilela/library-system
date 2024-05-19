package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.book.PublisherDto;

import java.util.List;
import java.util.Optional;

@Service
public interface PublisherService {
    List<PublisherDto> getAllPublishers();

    Optional<PublisherDto> getPublisherById(Integer id);

    PublisherDto createNewPublisher(PublisherDto publisherDto);

    Optional<PublisherDto> updatePublisherById(Integer id, PublisherDto publisherDto);

    boolean deletePublisherById(Integer id);
}
