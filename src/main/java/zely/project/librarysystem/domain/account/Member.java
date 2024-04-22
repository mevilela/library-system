package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;

import java.time.LocalDate;


@Entity
public class Member extends Account {

    private LocalDate dateOfMembership;

    private Integer totalBooksCheckedOut;


    public Member() {
    }

    public Member(String password, AccountType accountType, AccountStatus status, Person person, LocalDate dateOfMembership, Integer totalBooksCheckedOut) {
        super(password, accountType, status, person);
        this.dateOfMembership = dateOfMembership;
        this.totalBooksCheckedOut = totalBooksCheckedOut;
    }

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
