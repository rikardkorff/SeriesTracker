package seriestracker.dao;

import seriestacker.models.Genre;
import seriestacker.models.Movie;

import java.util.Collection;

/**
 * Created by rkorff on 2015-07-12.
 */
public interface MovieDAO {
    Collection<Movie> getByGenre(Genre genre);
    Collection<Movie> getByYear(int year);
    Movie create(Movie movie);
    Movie update(Movie movie);
    void delete(Movie movie);
}
