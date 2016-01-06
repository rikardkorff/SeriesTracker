package seriestracker.dao;

import seriestracker.models.Series;

public interface SeriesDAO {
    Series findByName(String name);
    Series create(Series series);
    Series update(Series series);
    void delete(Series series);
}
