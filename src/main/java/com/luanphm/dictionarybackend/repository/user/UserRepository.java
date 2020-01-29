package com.luanphm.dictionarybackend.repository.user;

import com.luanphm.dictionarybackend.entity.User;
import org.hibernate.Session;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;

public interface UserRepository extends UserJpaRepository, UserCustomRepository {



}
