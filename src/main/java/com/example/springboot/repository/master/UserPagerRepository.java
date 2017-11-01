package com.example.springboot.repository.master;

import com.example.springboot.model.master.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * Desc ：UserRepository By Page
 * Created by JHAO on 2017/10/24.
 */
public interface UserPagerRepository extends PagingAndSortingRepository<User,Integer> {

    //1，也可以通过@Query自定义复杂sql; 如果返回多表查询结果，需要定义一个接口来接收返回值，方法定义：getXxx()

    Page<User> findAll(Pageable pageable);

    Page<User> findUsersByUserName(String userName, Pageable pageable);
}
