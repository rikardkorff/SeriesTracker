package seriestracker.dao.seriestracker.dao.sqlserver;

import seriestacker.models.Genre;
import seriestracker.dao.GenreDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

/**
 * Created by rkorff on 2015-07-12.
 */
public class GenreSqlServerDAO extends GenericSqlServerDAO implements GenreDAO{
    @Override
    public Collection<Genre> findAll() {
        String sqlQuery = "SELECT Id, Description FROM Genre";
        ArrayList<Genre> genres = new ArrayList<Genre>();
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                Genre genre = new Genre(resultSet.getInt(0));
                genre.setName(resultSet.getString(1));
                genres.add(genre);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return genres;
    }

    @Override
    public Genre findByDescription(String description) {
        String sqlQuery = "SELECT Id, Description FROM Genre WHERE Description = '" + description + "'";
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                Genre genre = new Genre(resultSet.getInt(0));
                genre.setName(resultSet.getString(1));
                resultSet.close();
                statement.close();
                return genre;
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public Genre create(Genre genre) {
        if (genre.getId() == 0 && !genre.getName().isEmpty()) {
            String sqlQuery = "INSERT INTO Genre(Description) " +
                                "OUTPUT INSERTED.Id, INSERTED.Description " +
                                "VALUES('" + genre.getName() + "')";

            try{
                Statement statement = this.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()){
                    Genre newGenre = new Genre(resultSet.getInt(0));
                    newGenre.setName(resultSet.getString(1));
                    resultSet.close();
                    statement.close();
                    return newGenre;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return genre;
    }

    @Override
    public Genre update(Genre genre) {
        if (genre.getId() > 0 && !genre.getName().isEmpty()){
            String sqlQuery = "UPDATE Genre " +
                                "SET Description = '" + genre.getName() + "'" +
                                "OUTPUT INSERTED.Id, INSERTED.Description" +
                                "WHERE Id = " + genre.getId();
            try{
                Statement statement = this.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()){
                    Genre newGenre = new Genre(resultSet.getInt(0));
                    newGenre.setName(resultSet.getString(1));
                    resultSet.close();
                    statement.close();
                    return newGenre;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return genre;
    }

    @Override
    public void delete(Genre genre) {
        if (genre.getId() > 0){
            String sqlQuery = "DELETE FROM Genre WHERE Id = " + genre.getId();
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
