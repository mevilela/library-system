package zely.project.librarysystem.dto.booking;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class BarcodeReaderDto {

    private String bookItemBarcode;

    private String libraryCardBarcode;

}
