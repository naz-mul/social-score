package me.nalam.socialscore.dao;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public interface Dao<T, Id extends Serializable> {
    Optional<T> get(Id id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);

}
