package com.medi.app.services;

import com.medi.app.model.PatchOperation;
import com.medi.app.model.User;

import java.util.List;

/**
 * Created by vvicario on 30/05/2017.
 */
public interface UserService {

    User findUserById(Integer id);

    void createUser(User user);

    void updateUser(User user, Integer id);

    void updatePartialUser(List<PatchOperation> patchOperations, Integer id);

    void deleteUser(Integer id);

}
