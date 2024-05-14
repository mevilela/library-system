package zely.project.librarysystem.mapper;

import org.mapstruct.Mapper;
import zely.project.librarysystem.domain.card.Card;
import zely.project.librarysystem.dto.card.CardDto;


@Mapper(componentModel = "spring", uses = {AccountMapper.class, LibrarianMapper.class, MemberMapper.class, LibraryMapper.class})
public interface CardMapper {

    CardDto toCardDto(Card card);

    Card toCardEntity (CardDto cardDto);
}
