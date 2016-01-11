package seriestracker.dao.sqlserver;

import java.nio.file.Paths;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import seriestracker.dao.MovieDAO;
import seriestracker.models.Genre;
import seriestracker.models.Movie;

public class MovieSqlServerDAO extends GenericSqlServerDAO implements MovieDAO {

	@Override
	public Collection<Movie> getByGenre(Genre genre) {
		String sqlQuery = "SELECT Id, Name, Plot, Year, FileName, Watched, WatchDate " +
						  "FROM Movie " +
						  "WHERE GenreId = " + genre.getId();
		ArrayList<Movie> movies = new ArrayList<Movie>();
		try{
			Statement statement = this.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()){
				Movie movie = new Movie(resultSet.getInt(0));
				movie.setName(resultSet.getString(1));
				movie.setPlot(resultSet.getString(2));
				movie.setYear(resultSet.getInt(3));
				movie.setFileName(Paths.get(resultSet.getString(4)));
				movie.setWatched(resultSet.getBoolean(5));
				movie.setWatchDate(resultSet.getDate(6));

				movie.setGenre(genre);

				movies.add(movie);
			}

			resultSet.close();
			statement.close();

		}catch(SQLException e){
			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public Collection<Movie> getByYear(int year) {
		String sqlQuery = "SELECT Id, Name, Plot, Year, FileName, Watched, WatchDate " +
						  "FROM Movie " +
						  "WHERE Year = " + year;
		ArrayList<Movie> movies = new ArrayList<Movie>();

		try{
			Statement statement = this.getConnection().createStatement();
			ResultSet resultSet = statement.executeQuery(sqlQuery);

			while (resultSet.next()){
				Movie movie = new Movie(resultSet.getInt(0));
				movie.setName(resultSet.getString(1));
				movie.setPlot(resultSet.getString(2));
				movie.setYear(resultSet.getInt(3));
				movie.setFileName(Paths.get(resultSet.getString(4)));
				movie.setWatched(resultSet.getBoolean(5));
				movie.setWatchDate(resultSet.getDate(6));

				movies.add(movie);
			}

			resultSet.close();
			statement.close();
		}catch (SQLException e){
			e.printStackTrace();
		}
		return movies;
	}

	@Override
	public Movie create(Movie movie) {
		if (movie.getId() == 0 && !movie.getName().isEmpty()){
			String sqlQuery = "INSERT INTO Movie(Name, Plot, Year, FileName, Watched, WatchDate) " +
							  "OUTPUT INSERTED.Id, INSERTED.Name, INSERTED.Plot, INSERTED.Year, INSERTED.FileName, INSERTED.Watched, INSERTED.WatchDate " +
							  "VALUES ('" + movie.getName() + "'" +
							  ", '" + movie.getPlot() + "'" +
							  ", " + movie.getYear() + " " +
							  ", '" + movie.getFileName() + "'" +
							  ", " + movie.getWatched() + " " +
							  ", " + movie.getWatchDate() + ")";
			try{
				Statement statement = this.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQuery);

				if (resultSet.next()){
					Movie newMovie = new Movie(resultSet.getInt(0));
					newMovie.setName(resultSet.getString(1));
					newMovie.setPlot(resultSet.getString(2));
					newMovie.setYear(resultSet.getInt(3));
					newMovie.setFileName(Paths.get(resultSet.getString(4)));
					newMovie.setWatched(resultSet.getBoolean(5));
					newMovie.setWatchDate(resultSet.getDate(6));

					resultSet.close();
					statement.close();

					return newMovie;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return movie;
	}

	@Override
	public Movie update(Movie movie) {
		if (movie.getId() > 0 && !movie.getName().isEmpty()){
			String sqlQuery = "UPDATE Movie(Name, Plot, Year, FileName, Watched, WatchDate) " +
							  "SET Name = '" + movie.getName() + "'" +
							  ", Plot = '" + movie.getPlot() + "'" +
							  ", Year = " + movie.getYear() + " " +
							  ", FileName = '" + movie.getFileName() + "'" +
							  ", Watched = " + movie.getWatched() + " " +
							  ", WatchDate = " + movie.getWatchDate() + " " +
							  "OUTPUT INSERTED.Id, INSERTED.Name, INSERTED.Plot, INSERTED.Year, INSERTED.FileName, INSERTED.Watched, INSERTED.WatchDate " +
							  "WHERE Id = " + movie.getId();
			try{
				Statement statement = this.getConnection().createStatement();
				ResultSet resultSet = statement.executeQuery(sqlQuery);

				if (resultSet.next()){
					Movie newMovie = new Movie(resultSet.getInt(0));
					newMovie.setName(resultSet.getString(1));
					newMovie.setPlot(resultSet.getString(2));
					newMovie.setYear(resultSet.getInt(3));
					newMovie.setFileName(Paths.get(resultSet.getString(4)));
					newMovie.setWatched(resultSet.getBoolean(5));
					newMovie.setWatchDate(resultSet.getDate(6));

					resultSet.close();
					statement.close();

					return newMovie;
				}
			}catch (SQLException e){
				e.printStackTrace();
			}
		}
		return movie;
	}

	@Override
	public void delete(Movie movie) {
		if (movie.getId() > 0){
			String sqlQuery = "DELETE FROM Movie WHERE Id = " + movie.getId();

			try{
				Statement statement = this.getConnection().createStatement();
				statement.execute(sqlQuery);
				statement.close();
			}catch (SQLException e){
				e.printStackTrace();
			}
		}

	}

}
