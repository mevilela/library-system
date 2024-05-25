package zely.project.librarysystem.service.booking;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.LendingDto;
import zely.project.librarysystem.dto.booking.ReturningBookDto;

@Service
public interface LendingService {

    LendingDto LoanABook(BarcodeReaderDto barcodeReaderDto);

    ReturningBookDto ReturnABook(BarcodeReaderDto barcodeReaderDto);

}
