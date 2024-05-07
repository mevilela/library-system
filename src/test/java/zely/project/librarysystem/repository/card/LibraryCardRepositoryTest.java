package zely.project.librarysystem.repository.card;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import zely.project.librarysystem.domain.account.Account;
import zely.project.librarysystem.repository.account.AccountRepository;

import java.sql.SQLOutput;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class LibraryCardRepositoryTest {

    @Autowired
    LibraryCardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testLibraryCards(){

        System.out.println(cardRepository.count());
        System.out.println(accountRepository.count());

    }



}