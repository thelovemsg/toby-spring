package tobystudyproject.tobystudyproject.section_four_exception;

import tobystudyproject.tobystudyproject.User;

import java.util.List;

public interface UserDaoInterface {
    void add(User User);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
