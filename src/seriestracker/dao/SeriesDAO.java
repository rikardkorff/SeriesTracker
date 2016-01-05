package seriestracker.dao;

/**
 * Created by rkorff on 2015-07-11.
 */

import seriestacker.models.Series;

public interface SeriesDAO {
    Series findByName(String name);
    Series create(Series series);
    Series update(Series series);
    void delete(Series series);
}
