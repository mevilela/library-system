package zely.project.librarysystem.service.booking;

import org.springframework.stereotype.Service;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.LendingDto;
import zely.project.librarysystem.dto.booking.ReturningBookDto;
import zely.project.librarysystem.manager.LendingManager;


@Service
public class LendingServiceImpl implements LendingService {

    private final LendingManager lendingManager;

    public LendingServiceImpl(LendingManager lendingManager) {
        this.lendingManager = lendingManager;
    }

    @Override
    public LendingDto LoanABook(BarcodeReaderDto barcodeReaderDto) {

        return lendingManager.createNewLoan(barcodeReaderDto);


    }

    @Override
    public ReturningBookDto ReturnABook(BarcodeReaderDto barcodeReaderDto) {
        return lendingManager.returningBook(barcodeReaderDto);
    }
}
