package tobystudyproject.tobystudyproject;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
public class User {

    Level level;
    int login;
    int recommend;
    String email;
    public Level getLevel(){
        return level;
    }

    public void setLevel(Level level){
        this.level = level;
    }
    public void upgradeLevel() {
        Level nextLevel = this.level.nextLevel();
        if (nextLevel == null)
            throw new IllegalStateException(this.level + " 은 업그레이드가 불가능합니다.");
        else
            this.level = nextLevel;
    }
    String id;
    String name;
    String password;
    @Builder
    public User(String id, String name, String password, Level level, int recommend, int login, String email) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.recommend = recommend;
        this.login = login;
        this.level = level;
        this.email = email;
    }
}
