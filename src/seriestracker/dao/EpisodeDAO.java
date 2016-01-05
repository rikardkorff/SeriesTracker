package seriestracker.dao;

import seriestacker.models.Episode;

import java.util.Collection;

/**
 * Created by rkorff on 2015-07-12.
 */
public interface EpisodeDAO {
    Collection<Episode> getBySeasonId(int seasonId);
    Episode create(Episode episode);
    Episode update(Episode episode);
    void delete(Episode episode);
}
