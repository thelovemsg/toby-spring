package tobystudyproject.tobystudyproject.dao;

import tobystudyproject.tobystudyproject.User;

import java.util.List;

public interface IUserDao {

    void add(User user);
    User get(String id);
    List<User> getAll();
    void update(User user);
    void deleteAll();
    int getCount();
}
