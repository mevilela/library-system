package zely.project.librarysystem.bootstrap;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.util.ResourceUtils;
import org.webjars.NotFoundException;
import zely.project.librarysystem.csv.AccountCsvRecord;
import zely.project.librarysystem.csv.LibraryCSVRecord;
import zely.project.librarysystem.csv.RackCsvRecord;
import zely.project.librarysystem.domain.account.*;
import zely.project.librarysystem.domain.card.LibraryCard;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.domain.library.LibraryCode;
import zely.project.librarysystem.domain.library.Rack;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.card.LibraryCardRepository;
import zely.project.librarysystem.repository.library.LibraryCodeRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.repository.library.RackRepository;
import zely.project.librarysystem.service.account.AccountCsvService;
import zely.project.librarysystem.service.library.LibraryCsvService;
import zely.project.librarysystem.service.library.RackCsvService;


import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static zely.project.librarysystem.domain.account.AccountType.LIBRARIAN;
import static zely.project.librarysystem.domain.account.AccountType.MEMBER;

@Component
@RequiredArgsConstructor
public class BootstrapData implements CommandLineRunner {


    private final LibraryRepository libraryRepository;
    private final LibraryCsvService libraryCsvService;
    private final AccountRepository accountRepository;
    private final AccountCsvService accountCsvService;
    private final RackRepository rackRepository;
    private final RackCsvService rackCsvService;
    private final LibraryCardRepository cardRepository;
    private final LibraryCodeRepository libraryCodeRepository;


    @Transactional
    @Override
    public void run(java.lang.String... args) throws Exception {
        loadLibraryData();
        loadLibraryCsvData();
        loadAccountData();
        loadAccountCsvData();
        loadRackCsvData();
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

                String accountTypeStr = String.valueOf(accountRecord.getAccountType().toUpperCase());

                AccountType accountType;
                Account account;

                try {
                    accountType = AccountType.valueOf(accountTypeStr);
                } catch (IllegalArgumentException ex) {
                    throw new IllegalArgumentException("Unknown account type: " + accountTypeStr);
                }

                switch (accountType) {
                    case MEMBER:
                        account = new Member();
                        ((Member) account).setAccountType(MEMBER);
                        ((Member) account).setDateOfMembership(accountRecord.getDateOfMembership());
                        ((Member) account).setTotalBooksCheckedOut(accountRecord.getTotalBooksCheckedOut());
                        break;

                    case LIBRARIAN:
                        account = new Librarian();
                        ((Librarian) account).setAccountType(LIBRARIAN);
                        ((Librarian) account).setDepartment(accountRecord.getDepartment());
                        break;

                    default:
                        throw new IllegalArgumentException("Unsupported account type: " + accountType);
                }


//                if (account.getAccountType().equals("MEMBER")) {
//                    account = new Member();
//                    account.setAccountType(MEMBER);
//                    ((Member) account).setDateOfMembership(accountRecord.getDateOfMembership());
//                    ((Member) account).setTotalBooksCheckedOut(accountRecord.getTotalBooksCheckedOut());
//                } else if (accountType.equals(LIBRARIAN)) {
//                    account = new Librarian();
//                    account.setAccountType(LIBRARIAN);
//                    ((Librarian) account).setDepartment(accountRecord.getDepartment());
//                } else {
//                    throw new IllegalArgumentException("Unknown account type");
//                }

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
            Library library1 = new Library();
            LibraryCard libraryCard = new LibraryCard();
            library1.setAddress("rua das flores, 10");
            library1.setName("Library 1");
            libraryCard.setLibrary(library1);
            libraryCard.setBarcode("45454867654116");
            libraryRepository.save(library1);
            cardRepository.save(libraryCard);

        }
    }

    private void loadRackCsvData() throws FileNotFoundException{

        if (rackRepository.count() == 0){
            File file = ResourceUtils.getFile("classpath:csvdata/rack-data.csv");
            List<RackCsvRecord> rackRecords = rackCsvService.convertCSV(file);

            for (RackCsvRecord record : rackRecords){
                Rack rack = new Rack();

                rack.setRackNumber(record.getRackNumber());
                rack.setLocation(record.getLocation());
                rack.setSection(record.getSection());

                LibraryCode libraryCode = new LibraryCode();
                libraryCode.setCodeId(record.getLibraryCode());

                libraryCodeRepository.save(libraryCode);

                Optional<LibraryCode> libraryCodeOptional = libraryCodeRepository.findById(libraryCode.getCodeId());
                if (libraryCodeOptional.isPresent()) {
                    rack.setLibraryCode(libraryCodeOptional.get());
                    rackRepository.save(rack);
                } else {
                    throw new NotFoundException("Library not found for rack with code: " + record.getLibraryCode());
                }

                rackRepository.save(rack);
            }

        }

    }
}
