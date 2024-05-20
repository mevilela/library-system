package zely.project.librarysystem.service.book;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.domain.book.Publisher;
import zely.project.librarysystem.dto.book.PublisherDto;
import zely.project.librarysystem.mapper.PublisherMapper;
import zely.project.librarysystem.repository.book.PublisherRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PublisherServiceImpl implements PublisherService {

    private final PublisherRepository publisherRepository;
    private final PublisherMapper publisherMapper;

    public PublisherServiceImpl(PublisherRepository publisherRepository, PublisherMapper publisherMapper) {
        this.publisherRepository = publisherRepository;
        this.publisherMapper = publisherMapper;
    }

    @Override
    public List<PublisherDto> getAllPublishers() {
        List<Publisher> publishers = publisherRepository.findAll();

        return publishers.stream().map(
                publisherMapper::toPublisherDto).collect(Collectors.toList());
    }

    @Override
    public Optional<PublisherDto> getPublisherById(Integer id) {
        return Optional.ofNullable(publisherMapper.toPublisherDto(publisherRepository.findById(id).orElse(null)));
    }

    @Override
    public PublisherDto createNewPublisher(PublisherDto publisherDto) {
        return publisherMapper.toPublisherDto(publisherRepository.save(publisherMapper.toPublisherEntity(publisherDto)));
    }

    @Override
    public Optional<PublisherDto> updatePublisherById(Integer id, PublisherDto publisherDto) {
        return publisherRepository.findById(id).map(
                foundPublisher -> {
                    foundPublisher.setPublisherName(publisherDto.getPublisherName());
                    return foundPublisher;
                }).map(publisherRepository::save).map(publisherMapper::toPublisherDto);
    }



    @Override
    public boolean deletePublisherById(Integer id) {
        if (publisherRepository.existsById(id)){
            publisherRepository.deleteById(id);
            return true;
        }

        return false;
    }
}



