package org.geektimes.projects.user.service.impl;

import org.geektimes.projects.user.domain.User;
import org.geektimes.projects.user.repository.DatabaseUserRepository;
import org.geektimes.projects.user.service.UserService;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.validation.Validator;
import java.util.List;

public class UserServiceImpl implements UserService {

    @Resource(name = "bean/EntityManager")
    private EntityManager entityManager;

    @Resource(name = "bean/Validator")
    private Validator validator;

    @Override
    public boolean register(User user) {
//        validator.validate(user);
        if (queryUserByNameAndPassword(user.getName(), user.getPassword()) != null) {
            return false;
        }
        EntityTransaction transaction = entityManager.getTransaction();
        transaction.begin();
        entityManager.persist(user);
        transaction.commit();

        return true;
    }

    @Override
    public boolean deregister(User user) {
//        return databaseUserRepository.deleteById(user.getId());
        return false;
    }


    @Override
    public boolean update(User user) {
//        return databaseUserRepository.update(user);
        return false;
    }

    @Override
    public User queryUserById(Long id) {
        return entityManager.find(User.class, id);
    }

    @Override
    public User queryUserByNameAndPassword(String name, String password) {
        List l = entityManager.createQuery("from User where name= ?1 and password = ?2")
                .setParameter(1, name).setParameter(2, password).getResultList();
        if (l == null || l.size() == 0) {
            return null;
        }
        return (User)l.get(0);
    }
}
