package zely.project.librarysystem.dto.account;

import java.time.LocalDate;

public class MemberDto extends AccountDto {

    private LocalDate dateOfMembership;
    private Integer totalBooksCheckedOut;

    public LocalDate getDateOfMembership() {
        return dateOfMembership;
    }

    public void setDateOfMembership(LocalDate dateOfMembership) {
        this.dateOfMembership = dateOfMembership;
    }

    public Integer getTotalBooksCheckedOut() {
        return totalBooksCheckedOut;
    }

    public void setTotalBooksCheckedOut(Integer totalBooksCheckedOut) {
        this.totalBooksCheckedOut = totalBooksCheckedOut;
    }
}
