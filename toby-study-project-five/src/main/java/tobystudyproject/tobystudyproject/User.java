package tobystudyproject.tobystudyproject;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class User {
    String id;
    String name;
    String password;
    @Builder
    public User(String id, String name, String password) {
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
