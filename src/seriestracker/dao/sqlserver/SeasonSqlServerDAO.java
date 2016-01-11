package seriestracker.dao.sqlserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import seriestracker.dao.SeasonDAO;
import seriestracker.models.Season;
import seriestracker.models.Series;

public class SeasonSqlServerDAO extends GenericSqlServerDAO implements SeasonDAO {

	@Override
	public Collection<Season> getBySeries(Series series) {
		String sqlQuery = "SELECT Id, SeasonNo " +
						  "FROM Season " +
						  "WHERE SeriesId = " + series.getId();
		ArrayList<Season> seasons = new ArrayList<Season>();
		try{
			Statement statement = this.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()){
				Season season = new Season(resultSet.getInt(0));
				season.setSeasonNo(resultSet.getInt(1));

				seasons.add(season);
			}

			resultSet.close();
			statement.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return seasons;
	}

	@Override
	public Season getBySeasonNo(int seriesId, int seasonNo) {
		if (seriesId > 0 && seasonNo > 0){
			String sqlQuery = "SELECT Id, SeasonNo " +
							  "FROM Season " +
							  "WHERE SeriesId = " + seriesId +
							  " AND SeasonNo = " + seasonNo;
			try{
				Statement statement = this.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQuery);

				if (resultSet.next()){
					Season season = new Season(resultSet.getInt(0));
					season.setSeasonNo(resultSet.getInt(1));

					resultSet.close();
					statement.close();

					return season;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return null;
	}

	@Override
	public Season create(int seriesId, Season season) {
		if (seriesId > 0 && season.getId() > 0){
			String sqlQuery = "INSERT INTO Season(SeriesId, SeasonNo) " +
							  "OUTPUT INSERTED.Id, INSERTED.SeasonNo " +
							  "VALUES (" + seriesId +
							  ", " + season.getSeasonNo() + ")";
			try{
				Statement statement = this.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQuery);

				if (resultSet.next()){
					Season newSeason = new Season(resultSet.getInt(0));
					newSeason.setSeasonNo(resultSet.getInt(1));

					resultSet.close();
					statement.close();

					return newSeason;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return season;
	}

	@Override
	public Season update(Season season) {
		if (season.getId() > 0 && season.getSeasonNo() > 0){
			String sqlQuery = "UPDATE Season " +
							  "SET SeasonNo = " + season.getSeasonNo() +
							  " OUTPUT INSERTED.Id, INSERTED.SeasonNo" +
							  " WHERE Id = " + season.getId();
			try{
				Statement statement = this.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQuery);

				if (resultSet.next()){
					Season newSeason = new Season(resultSet.getInt(0));
					newSeason.setSeasonNo(resultSet.getInt(1));

					resultSet.close();
					statement.close();

					return newSeason;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return season;
	}

	@Override
	public void delete(Season season) {
		if (season.getId() > 0){
			String sqlQuery = "DELETE FROM Season WHERE Id = " + season.getId();
			try{
				Statement statement= this.getConnection().createStatement();
				statement.execute(sqlQuery);
				statement.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
	}

}
