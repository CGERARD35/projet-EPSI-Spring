package projetPOEIspring.poeidata.services;

import projetPOEIspring.poeidata.models.User;

import java.util.List;

public interface UserService {

    List<User> getAll();

    User getById(Integer id);

    User createUser(User user);

    User getUserByMailAndPassword(String mail, String password);

    void deleteUser(Integer id);

    User updateUser(User user);
}
