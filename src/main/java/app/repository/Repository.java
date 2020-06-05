package app.repository;

import java.util.List;

public interface Repository<E> {
    List<E> get();
    E get(String id);
    void delete(String id);
    void update(E item);
    void save(E item);
}
