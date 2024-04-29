package zely.project.librarysystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zely.project.librarysystem.domain.account.*;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;

import java.time.LocalDate;

@SpringBootApplication
public class LibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);

	}

	@Bean
	public CommandLineRunner insertData(AccountRepository accountRepository, LibraryRepository libraryRepository) {

		return args -> {

			Person maria = new Person("Maria", "Rua Paissandu 344", "maria@gmail.com", "99199-2032");
			Member member = new Member();
			member.setPerson(maria);
			member.setAccountType(AccountType.MEMBER);
			member.setAccountStatus(AccountStatus.ACTIVE);
			member.setPassword("123");
			member.setDateOfMembership(LocalDate.of(2024, 04, 12));
			member.setTotalBooksCheckedOut(4);

			Person joao = new Person("Joao", "Rua Paissandu 344", "joao@gmail.com", "99199-0000");
			Librarian librarian = new Librarian();
			librarian.setPerson(joao);
			librarian.setDepartment("book checkout");
			librarian.setAccountStatus(AccountStatus.ACTIVE);
			librarian.setAccountType(AccountType.LIBRARIAN);
			librarian.setPassword("123");


			Library library = new Library("Library 1", "Rua das Flores, 10");

			accountRepository.save(member);
			accountRepository.save(librarian);
			libraryRepository.save(library);


		};

	}
}