package seriestracker.dao.sqlserver;

import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import seriestracker.dao.EpisodeDAO;
import seriestracker.models.Episode;

public class EpisodeSqlServerDAO extends GenericSqlServerDAO implements EpisodeDAO {

	@Override
	public Collection<Episode> getBySeasonId(int seasonId) {
		String sqlQuery = "SELECT Id, EpisodeNo, Description, FileName, Watched, WatchDate " +
							"FROM Episode " +
							"WHERE SeasonId = " + seasonId;
        ArrayList<Episode> episodes = new ArrayList<Episode>();
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                Episode episode = new Episode(resultSet.getInt(0));
                episode.setEpisodeNo(resultSet.getInt(1));
                episode.setDescription(resultSet.getString(2));
                episode.setFileName(Paths.get(resultSet.getString(3)));
                episode.setWatched(resultSet.getBoolean(4));
                if (episode.getWatched()){
                	episode.setWatchDate(resultSet.getDate(5));
                }
                episodes.add(episode);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return episodes;
	}

	@Override
	public Episode create(Episode episode) {
        if (episode.getId() == 0 &&
        		episode.getEpisodeNo() > 0 &&
        		! episode.getDescription().isEmpty()) {
            String sqlQuery = "INSERT INTO Genre(EpisodeNo, Description, FileName, Watched, WatchDate) " +
                                "OUTPUT INSERTED.Id, INSERTED.EpisodeNo, INSERTED.Description,  INSERTED.FileName, INSERTED.Watched, INSERTED.WatchDate " +
                                "VALUES(" + episode.getEpisodeNo() +
                                ", '" + episode.getDescription() + "'" +
                                ", '" + episode.getFileName() + "'" +
                                ", " + episode.getWatched() +
                                ", '" + episode.getWatchDate() + "')";

            try{
                Statement statement = this.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()){
                    Episode newEpisode = new Episode(resultSet.getInt(0));
                    newEpisode.setEpisodeNo(resultSet.getInt(1));
                    newEpisode.setDescription(resultSet.getString(2));
                    newEpisode.setFileName(Paths.get(resultSet.getString(3)));
                    newEpisode.setWatched(resultSet.getBoolean(4));
                    newEpisode.setWatchDate(resultSet.getDate(5));

                    resultSet.close();
                    statement.close();
                    return newEpisode;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return episode;
	}

	@Override
	public Episode update(Episode episode) {
		if (episode.getId() > 0 &&
				episode.getEpisodeNo() > 0 &&
				!episode.getDescription().isEmpty()){
			String sqlQuery = "UPDATE Episode " +
							  "SET EpisodeNo = " + episode.getEpisodeNo() +
                              ", '" + episode.getDescription() + "'" +
                              ", '" + episode.getFileName() + "'" +
                              ", " + episode.getWatched() +
                              ", '" + episode.getWatchDate() + "'" +
                              "OUTPUT INSERTED.Id, INSERTED.EpisodeNo, INSERTED.Description,  INSERTED.FileName, INSERTED.Watched, INSERTED.WatchDate " +
                              "WHERE Id = " + episode.getId();
			try{
				Statement statement = this.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQuery);

				if (resultSet.next()){
					Episode newEpisode = new Episode(resultSet.getInt(0));
					newEpisode.setEpisodeNo(resultSet.getInt(1));
					newEpisode.setDescription(resultSet.getString(2));
					newEpisode.setFileName(Paths.get(resultSet.getString(3)));
					newEpisode.setWatched(resultSet.getBoolean(4));
					newEpisode.setWatchDate(resultSet.getDate(5));

					resultSet.close();
					statement.close();
					return newEpisode;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return episode;
	}

	@Override
	public void delete(Episode episode) {
        if (episode.getId() > 0){
            String sqlQuery = "DELETE FROM Episode WHERE Id = " + episode.getId();
            try{
                Statement statement = this.getConnection().createStatement();
                statement.execute(sqlQuery);
                statement.close();
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
	}

}
