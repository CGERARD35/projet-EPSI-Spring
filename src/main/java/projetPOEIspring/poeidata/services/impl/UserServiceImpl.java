package projetPOEIspring.poeidata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.models.User;
import projetPOEIspring.poeidata.repositories.UserRepository;
import projetPOEIspring.poeidata.services.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return this.userRepository.findAll();
    }

    @Override
    public User getById(Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UnknownResourceException("Unknown user."));
    }


    @Override
    public User createUser(User user) {

        return null;
    }

    @Override
    public User getUserByMailAndPassword(String mail, String password) {
        return null;
    }

    @Override
    public void deleteUser(Integer id) {
        User userToDelete = this.getById(id);
        this.userRepository.delete(userToDelete);
    }

    @Override
    public User updateUser(User user) {
        return null;
    }
}
