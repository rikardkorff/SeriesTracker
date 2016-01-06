package seriestracker.dao;

import java.util.Collection;

import seriestracker.models.Episode;

/**
 * Created by rkorff on 2015-07-12.
 */
public interface EpisodeDAO {
    Collection<Episode> getBySeasonId(int seasonId);
    Episode create(Episode episode);
    Episode update(Episode episode);
    void delete(Episode episode);
}
