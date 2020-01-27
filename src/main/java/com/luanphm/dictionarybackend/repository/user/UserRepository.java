package com.luanphm.dictionarybackend.repository.user;

import com.luanphm.dictionarybackend.entity.User;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends CrudRepository<User, String> {

    @Query( "SELECT new User(e.username, e.email, e.password, e.role)" +
            "FROM User e " +
            "WHERE e.username = :username")
    User getUserWithRole(@Param("username") String username);

    @Transactional
    default void add(Session session, User user) {
        session.save(user);
    }

}
