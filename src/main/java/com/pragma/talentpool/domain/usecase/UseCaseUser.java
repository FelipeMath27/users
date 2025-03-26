package com.pragma.talentpool.domain.usecase;


import com.pragma.talentpool.domain.api.IUserServicePort;
import com.pragma.talentpool.domain.model.User;
import com.pragma.talentpool.domain.spi.IUserPersistencePort;

import java.util.List;

public class UseCaseUser implements IUserServicePort {

    private final IUserPersistencePort userPersistencePort;

    public UseCaseUser(IUserPersistencePort userPersistencePort){
        this.userPersistencePort = userPersistencePort;
    }

    @Override
    public void saveUser(User user){
        userPersistencePort.saveUser(user);
    }

    @Override
    public List<User> getAllUser() {
        return userPersistencePort.getAllUser();
    }

    @Override
    public User getUser(String email) {
        return userPersistencePort.getUser(email);
    }

    @Override
    public void updateUser(User user){
        userPersistencePort.updateUser(user);
    }

    @Override
    public void deleteUser(String email){
        userPersistencePort.deleteUser(email);
    }
}
