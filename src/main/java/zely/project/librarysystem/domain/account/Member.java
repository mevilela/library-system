package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Entity
@Getter
@Builder
@Setter

public class Member extends Account {

    private LocalDate dateOfMembership;

    private Integer totalBooksCheckedOut;


    public Member() {
        super();
        this.setAccountType(AccountType.MEMBER);
    }


    public Member(LocalDate dateOfMembership, Integer totalBooksCheckedOut) {
        super();
        this.dateOfMembership = dateOfMembership;
        this.setAccountType(AccountType.MEMBER);
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("MEMBER"));
    }

    @Override
    public String getUsername() {
        return getPerson().getEmail();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
