package com.luanphm.dictionarybackend.handler;

import org.hibernate.Session;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.NoRepositoryBean;

import javax.transaction.Transactional;
import java.io.Serializable;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@NoRepositoryBean
public interface MyJpaRepository<E, ID extends Serializable> extends CrudRepository<E, ID> {

    default List<E> getSpecificFieldsAll() {
       return null;
   }

    default E getSpecificFieldsById(ID id) {
       return null;
    }

    @Override
    Iterable<E> findAll();

    @Override
    Optional<E> findById(ID id);

    default E getById(ID id) {
        Optional<E> entity = findById(id);
        return entity.orElse(null);
    }

    default List<E> getAll() {

        return StreamSupport
                .stream(findAll().spliterator(), false)
                .collect(Collectors.toList());

    }

    @Transactional
    default boolean add(Session session, E entity){
        try {
            session.save(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Transactional
    default boolean update(Session session, E entity) {
        try {
            session.update(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
