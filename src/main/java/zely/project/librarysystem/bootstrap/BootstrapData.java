package zely.project.librarysystem.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import zely.project.librarysystem.csv.AccountCsvRecord;
import zely.project.librarysystem.csv.LibraryCSVRecord;
import zely.project.librarysystem.domain.account.*;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.service.account.AccountCsvService;
import zely.project.librarysystem.service.library.LibraryCsvService;


import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;

import static zely.project.librarysystem.domain.account.AccountType.LIBRARIAN;
import static zely.project.librarysystem.domain.account.AccountType.MEMBER;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {

    private final LibraryRepository libraryRepository;
    private final AccountRepository accountRepository;
    private final LibraryCsvService libraryCsvService;
    private final AccountCsvService accountCsvService;

    @Transactional
    @Override
    public void run(java.lang.String... args) throws Exception {
        loadAccountData();
        loadLibraryData();
        loadLibraryCsvData();
        loadAccountCsvData();

    }

    private void loadLibraryCsvData() throws FileNotFoundException {
        if (libraryRepository.count() < 10) {
            File file = ResourceUtils.getFile("classpath:csvdata/library-data.csv");
            List<LibraryCSVRecord> recs = libraryCsvService.convertCSV(file);

            for (LibraryCSVRecord record : recs){
                Library library = new Library();
                library.setName(record.getName());
                library.setAddress(record.getAddress());

                libraryRepository.save(library);
            }
        }
    }

    private void loadAccountCsvData() throws FileNotFoundException{
        if (accountRepository.count() < 12){
            File file = ResourceUtils.getFile("classpath:csvdata/account-data.csv");
            List<AccountCsvRecord> accountRecs = accountCsvService.convertCSV(file);

            for(AccountCsvRecord accountRecord : accountRecs){
                String accountType = String.valueOf(accountRecord.getAccountType().toUpperCase());

                Account account;
                if (accountType.equals(MEMBER)) {
                    account = new Member();
                    account.setAccountType(MEMBER);
                    ((Member) account).setDateOfMembership(accountRecord.getDateOfMembership());
                    ((Member) account).setTotalBooksCheckedOut(accountRecord.getTotalBooksCheckedOut());
                } else if (accountType.equals(LIBRARIAN)) {
                    account = new Librarian();
                    account.setAccountType(LIBRARIAN);
                    ((Librarian) account).setDepartment(accountRecord.getDepartment());
                } else {
                    throw new IllegalArgumentException("Unknown account type");
                }

                Person person = new Person(accountRecord.getName(), accountRecord.getAddress(), accountRecord.getEmail(), accountRecord.getPhone());
                account.setPerson(person);
                account.setPassword(accountRecord.getPassword());
                account.setAccountStatus(AccountStatus.valueOf(accountRecord.getAccountStatus().toUpperCase()));

                accountRepository.save(account);

            }
        }
    }


    private void loadAccountData(){

        if (libraryRepository.count() == 0){

            Person maria = new Person("Maria", "Rua Paissandu 344", "maria@gmail.com", "99199-2032");

            Member member = new Member();
            member.setPerson(maria);
            member.setAccountType(MEMBER);
            member.setAccountStatus(AccountStatus.ACTIVE);
            member.setPassword("123");
            member.setDateOfMembership(LocalDate.of(2024, 04, 12));
            member.setTotalBooksCheckedOut(4);

            Person joao = new Person("Joao", "Rua Paissandu 344", "joao@gmail.com", "99199-0000");
            Librarian librarian = new Librarian();
            librarian.setPerson(joao);
            librarian.setDepartment("science");
            librarian.setAccountStatus(AccountStatus.ACTIVE);
            librarian.setAccountType(MEMBER);
            librarian.setPassword("123");

            accountRepository.save(member);
            accountRepository.save(librarian);

        }
    }

    private void loadLibraryData(){

        if (libraryRepository.count() == 0){
            Library library1 = new Library("Library 1", "Rua das Flores, 10");
            libraryRepository.save(library1);

        }
    }

};
