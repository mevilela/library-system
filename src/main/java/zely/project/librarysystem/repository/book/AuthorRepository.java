package zely.project.librarysystem.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
