package tobystudyproject.tobystudyproject.before;

import tobystudyproject.tobystudyproject.User;

import java.util.List;

public interface IUserDao {
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
