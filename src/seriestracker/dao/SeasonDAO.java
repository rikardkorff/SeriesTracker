package seriestracker.dao;

import seriestacker.models.Season;
import seriestacker.models.Series;

import java.util.Collection;

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
