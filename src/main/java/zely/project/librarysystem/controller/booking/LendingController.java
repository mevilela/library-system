package zely.project.librarysystem.controller.booking;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import zely.project.librarysystem.dto.booking.BarcodeReaderDto;
import zely.project.librarysystem.dto.booking.LendingDto;
import zely.project.librarysystem.dto.booking.ReturningBookDto;
import zely.project.librarysystem.service.booking.LendingService;

@RestController
@RequestMapping("/api/lending")

public class LendingController {

    private final LendingService lendingService;

    public LendingController(LendingService lendingService) {
        this.lendingService = lendingService;
    }


    @PostMapping("/loan")
    public ResponseEntity requestLoan(@RequestBody BarcodeReaderDto barcodeReaderDto){

       LendingDto savedLoanRequest = lendingService.LoanABook(barcodeReaderDto);

        return new ResponseEntity<>(savedLoanRequest, HttpStatus.CREATED);

    }

    @PostMapping("/return")
    public ResponseEntity returnBook(@RequestBody BarcodeReaderDto barcodeReaderDto){

        ReturningBookDto returnBook = lendingService.ReturnABook(barcodeReaderDto);

        return new ResponseEntity<>(returnBook, HttpStatus.CREATED);

    }
}

