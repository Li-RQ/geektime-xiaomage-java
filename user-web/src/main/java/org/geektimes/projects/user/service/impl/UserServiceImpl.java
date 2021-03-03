package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.service.UserService;

public class UserServiceImpl implements UserService {

    private DatabaseUserRepository databaseUserRepository = new DatabaseUserRepository();

    @Override
    public boolean register(User user) {
        return databaseUserRepository.save(user);
    }

    @Override
    public boolean deregister(User user) {
        return databaseUserRepository.deleteById(user.getId());
    }

    @Override
    public boolean update(User user) {
        return databaseUserRepository.update(user);
    }

    @Override
    public User queryUserById(Long id) {
        return databaseUserRepository.getById(id);
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        return databaseUserRepository.getByNameAndPassword(name, password);
    }
}
