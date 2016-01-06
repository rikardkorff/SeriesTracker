package seriestracker.dao;

import java.util.Collection;

import seriestracker.models.Genre;

/**
 * Created by rkorff on 2015-07-12.
 */
public interface GenreDAO {
    Collection<Genre> findAll();
    Genre findByDescription(String description);
    Genre create(Genre genre);
    Genre update(Genre genre);
    void delete(Genre genre);
}
