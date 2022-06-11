package projetPOEIspring.poeidata.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import projetPOEIspring.poeidata.exceptions.UnknownResourceException;
import projetPOEIspring.poeidata.models.User;
import projetPOEIspring.poeidata.repositories.UserRepository;
import projetPOEIspring.poeidata.services.UserService;

import java.util.List;

@Service
@Qualifier("userService")
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;


    @Override
    public List<User> getAll() {
        return this.userRepository.findAll(Sort.by("id").ascending());
    }

    @Override
    public User getById(Integer id) {
        return this.userRepository.findById(id)
                .orElseThrow(() -> new UnknownResourceException("Unknown user."));
    }


    @Override
    public User createUser(User user) {

        return this.userRepository.save(user);

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
        User existingUser = this.getById(user.getId());
        existingUser.setMail(user.getMail());
        existingUser.setNom(user.getNom());
        existingUser.setPassword(user.getPassword());
        existingUser.setPrenom(user.getPrenom());
        existingUser.setRole(user.getRole());
        existingUser.setStatut(user.getStatut());
        return this.userRepository.save(existingUser);

    }
}
