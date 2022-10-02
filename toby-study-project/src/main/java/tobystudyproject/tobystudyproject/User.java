package tobystudyproject.tobystudyproject;

import lombok.*;
import tobystudyproject.tobystudyproject.sectionfive.Level;

@Getter
@Setter
@NoArgsConstructor
public class User {
    Level level;
    int login;
    int recoment;

    String id;
    String name;
    String password;

    @Builder
    public User(Level level, int login, int recoment, String id, String name, String password) {
        this.level = level;
        this.login = login;
        this.recoment = recoment;
        this.id = id;
        this.name = name;
        this.password = password;
    }
}
