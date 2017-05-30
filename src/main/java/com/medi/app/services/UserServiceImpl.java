package com.medi.app.services;

import com.medi.app.enumeration.Actions;
import com.medi.app.model.PatchOperation;
import com.medi.app.model.User;
import javax.ws.rs.NotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by vvicario on 30/05/2017.
 */
public class UserServiceImpl implements UserService{

    private static List<User> users = new ArrayList<>(Arrays.asList(new User("Lucas", "Perez", 31, "M", "Single", 1)));
    private final String NAME = "name";
    private static Integer ID = 1;


    @Override
    public void deleteUser(Integer id){
        User existentUser = users.stream().filter(u -> u.getId().equals(id)).findFirst().orElse(null);
        if (existentUser == null){
            throw new NotFoundException("User does not exist");
        }
        users.remove(existentUser);
    }

    @Override
    public User findUserById(Integer id) {
        User existentUser = users.stream().filter(user -> user.getId().equals(id)).findFirst().orElse(null);
        if(existentUser == null) {
            throw new NotFoundException("User does not exist");
        }
        return existentUser;
    }

    @Override
    public void createUser(User user){
      users.add(new User(user.getName(), user.getSurname(), user.getAge(), user.getGender(), user.getCivilStatus(), ++ID));
    }

    @Override
    public void updateUser(User user, Integer id){
        checkUserId(user, id);
        deleteUser(user.getId());
        users.add(user);
    }

    @Override
    public void updatePartialUser(List<PatchOperation> patchOperations, Integer id) {
        User user = findUserById(id);
        patchOperations.stream().forEach(action -> {
            if(action.getPath().equalsIgnoreCase(NAME)){
                setName(user, action);
            }
            else{
                throw new IllegalArgumentException("Invalid path.");
            }
        });
        updateUser(user, id);
    }

    public static List<User> getUsers() {
        return users;
    }

    private void checkUserId(User user, Integer id){
        if(user.getId() != null && user.getId() != id) {
            throw new IllegalArgumentException("Id is not valid");
        }
    }

    private void setName(User user, PatchOperation action) {
        if(action.getOp().equalsIgnoreCase(Actions.REPLACE.getValue())){
            user.setName(action.getValue());
        }else{
            throw new IllegalArgumentException(action.getOp() + " operation is not supported for this field. The only supported operation for this field is replace.");
        }
    }
}
