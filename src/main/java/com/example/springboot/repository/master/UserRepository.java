package com.example.springboot.repository.master;

import com.example.springboot.model.master.User;
import org.springframework.data.repository.CrudRepository;

/**
 * Desc ：User Repository
 * Created by JHAO on 2017/10/23.
 */

public interface UserRepository extends CrudRepository<User,Integer> {
    User findUsersByUserName(String userName);
}
