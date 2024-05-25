package zely.project.librarysystem.dto.card;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import zely.project.librarysystem.dto.account.AccountDto;
import zely.project.librarysystem.dto.library.LibraryDto;

import java.time.LocalDate;



@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CardCreateRequestDto {

    private String barcode;

    private LocalDate issuedAt;

    private boolean active;

    private AccountDto account;

    private LibraryDto library;

}
