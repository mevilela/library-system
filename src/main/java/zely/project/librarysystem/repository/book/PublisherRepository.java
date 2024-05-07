package zely.project.librarysystem.repository.book;

import org.springframework.data.jpa.repository.JpaRepository;
import zely.project.librarysystem.domain.book.Publisher;

public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}
