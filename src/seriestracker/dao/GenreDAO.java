package seriestracker.dao;

import java.util.Collection;

import seriestracker.models.Genre;

/**
 * Created by rkorff on 2015-07-12.
 */
public interface GenreDAO {
    Collection<Genre> findAll();
    Genre findByName(String name);
    void create(Genre genre);
    void update(Genre genre);
    void delete(Genre genre);
}
