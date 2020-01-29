package com.luanphm.dictionarybackend.handler;

import com.luanphm.dictionarybackend.entity.IdObject;
import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface MyJpaRepository<T extends IdObject, ID extends Serializable> extends CrudRepository<T, ID> {

   default List<T> getSpecificFieldsAll() {
       return null;
   }

    default T getSpecificFieldsById(ID id) {
       return null;
    }

    @Transactional
    default boolean add(Session session, T entity) {
        try {
            session.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    default boolean update(Session session, T entity) {
        try {
            session.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

}
