package seriestracker.dao;

import java.util.Collection;

import seriestracker.models.Season;
import seriestracker.models.Series;

/**
 * Created by rkorff on 2015-07-12.
 */
public interface SeasonDAO {
    Collection<Season> getBySeries(Series series);
    Season getBySeasonNo(int seriesId, int seasonNo);
    Season create(int seriesId, Season season);
    Season update(Season season);
    void delete(Season season);
}
