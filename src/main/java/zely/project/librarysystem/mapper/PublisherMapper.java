package zely.project.librarysystem.mapper;


import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.book.Publisher;
import zely.project.librarysystem.dto.book.PublisherDto;
import zely.project.librarysystem.dto.book.PublisherResponseDto;

@Mapper(componentModel = "spring")
public interface PublisherMapper {

    PublisherDto toPublisherDto(Publisher publisher);

    Publisher toPublisherEntity(PublisherDto publisherDto);

    PublisherResponseDto toPublisherResponseDto(Publisher publisher);

}
