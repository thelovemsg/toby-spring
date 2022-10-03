package tobystudyproject.tobystudyproject.dao;

import tobystudyproject.tobystudyproject.User;

import java.util.List;

public interface IUserDao {

    void update(User user);
    void add(User user);
    User get(String id);
    List<User> getAll();
    void deleteAll();
    int getCount();
}
