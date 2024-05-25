package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;
import zely.project.librarysystem.domain.book.Author;
import zely.project.librarysystem.domain.book.Book;
import zely.project.librarysystem.domain.book.Publisher;
import zely.project.librarysystem.dto.book.BookCreateDto;
import zely.project.librarysystem.dto.book.BookDto;
import zely.project.librarysystem.dto.book.BookNameAndAuthorDto;
import zely.project.librarysystem.dto.book.BookSummaryDto;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Set;
import java.util.stream.Collectors;


@Mapper(componentModel = "spring", uses = {PublisherMapper.class, AuthorMapper.class})
public interface BookMapper {

    @Mapping(target = "publisherName", source = "publisher.publisherName")
    @Mapping(target = "authorNames", expression = "java(mapAuthorNames(book))")
    BookDto toBookDto(Book book);


    Book toBookEntity (BookDto bookDto);

    @Mapping(target = "publisherName", source = "publisher.publisherName")
    @Mapping(target = "authorNames", expression = "java(mapAuthorNames(book))")
    BookSummaryDto toBookSummaryDto(Book book);


    @Mapping(target = "authorNames", expression = "java(mapAuthorNames(book))")
    BookNameAndAuthorDto toBookNameAndAuthorDto(Book book);


    @Mapping(target = "publisher", source = "publisherId", qualifiedByName = "mapIdToPublisher")
    @Mapping(target = "authors", source = "authorIdSet", qualifiedByName = "authorIdsToAuthors")
    Book toBookFromCreate (BookCreateDto bookCreateDto);


    BookCreateDto toCreateFromBook(Book book);

    List<BookDto> toBookDtoList(List<Book> books);


    @Named("mapIdToPublisher")
    default Publisher mapIdToPublisher(Integer publisherId){
        Publisher publisher = new Publisher();
        publisher.setId(publisherId);

        return publisher;
    }


    @Named("authorIdsToAuthors")
    default Set<Author> authorIdsToAuthors(Set<Integer> authorIdSet) {
        return authorIdSet.stream().map(
                authorId -> {
                    Author author = new Author();
                    author.setId(authorId);
                    return author;
                }).collect(Collectors.toSet());
    }

    default Set<String> mapAuthorNames(Book book) {
        return book.getAuthors().stream()
                .map(Author::getAuthorName)
                .collect(Collectors.toSet());
    }

}
