package zely.project.librarysystem.domain.account;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Person {
    private java.lang.String name;
    private java.lang.String address;
    private java.lang.String email;
    private java.lang.String phone;

    public Person(String email) {
        this.email = email;
    }
}
