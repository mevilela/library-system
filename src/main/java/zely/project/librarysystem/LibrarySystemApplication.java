package zely.project.librarysystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import zely.project.librarysystem.domain.account.*;
import zely.project.librarysystem.domain.library.Library;
import zely.project.librarysystem.repository.account.AccountRepository;
import zely.project.librarysystem.repository.library.LibraryRepository;
import zely.project.librarysystem.service.library.LibraryCsvService;

import java.io.FileNotFoundException;
import java.lang.String;
import java.time.LocalDate;

@SpringBootApplication
public class LibrarySystemApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibrarySystemApplication.class, args);

	}
}