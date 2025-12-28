package in.sp.main.service;

import in.sp.main.entity.User;

public interface UserService {

    User loginUser(String email, String password);
    boolean registerUser(User user);
}



