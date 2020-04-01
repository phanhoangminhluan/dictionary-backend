package com.luanphm.dictionarybackend.repository.user;

import com.luanphm.dictionarybackend.entity.User;
import com.luanphm.dictionarybackend.handler.MyJpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserJpaRepository extends MyJpaRepository<User, String> {

    @Query( "SELECT new User(e.id, e.email, e.password) " +
            "FROM User e")
    List<User> getSpecificFieldsAll();

    @Query( "SELECT new User(e.id, e.email, e.password, e.role) " +
            "FROM User e " +
            "WHERE e.id = :username")
    User getUserWithRole(@Param("username") String username);



}
