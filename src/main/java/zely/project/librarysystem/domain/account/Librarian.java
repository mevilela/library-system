package zely.project.librarysystem.domain.account;

import jakarta.persistence.Entity;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collection;
import java.util.List;

@Entity
@Getter
@Setter
@Builder
public class Librarian extends Account {

    private java.lang.String department;


    public Librarian() {
        super();
        this.setAccountType(AccountType.LIBRARIAN);
    }


    public Librarian(String department) {
        super();
        this.department = department;
        this.setAccountType(AccountType.LIBRARIAN);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if(getAccountType() == AccountType.LIBRARIAN)
            return List.of(new SimpleGrantedAuthority("MEMBER"), new SimpleGrantedAuthority("LIBRARIAN"));
        else return List.of(new SimpleGrantedAuthority("MEMBER"));
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
