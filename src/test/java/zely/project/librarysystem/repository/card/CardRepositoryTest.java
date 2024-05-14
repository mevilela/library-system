package zely.project.librarysystem.repository.card;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import zely.project.librarysystem.repository.account.AccountRepository;

@SpringBootTest
class CardRepositoryTest {

    @Autowired
    CardRepository cardRepository;

    @Autowired
    AccountRepository accountRepository;

    @Test
    void testLibraryCards(){

        System.out.println(cardRepository.count());
        System.out.println(accountRepository.count());

    }



}