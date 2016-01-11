package seriestracker.dao.sqlserver;

import seriestracker.dao.GenreDAO;
import seriestracker.models.Genre;

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
        String sqlQuery = "SELECT Id, Name FROM Genre";
        ArrayList<Genre> genres = new ArrayList<Genre>();
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            while (resultSet.next()) {
                Genre genre = new Genre(resultSet.getInt("Id"), resultSet.getString("Name"));
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
    public Genre findByName(String name) {
        String sqlQuery = "SELECT Id, Name FROM Genre WHERE Name = '" + name + "'";
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(sqlQuery);

            if (resultSet.next()) {
                Genre genre = new Genre(resultSet.getInt("Id"), resultSet.getString("Name"));
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
    public void create(Genre genre) {
        if (genre.getId() == 0 && genre.getName() != null && !genre.getName().isEmpty()) {
            String sqlQuery = "INSERT INTO Genre(Name) " +
                                "OUTPUT INSERTED.Id, INSERTED.Name " +
                                "VALUES('" + genre.getName() + "')";

            try{
                Statement statement = this.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()){
                    genre.setId(resultSet.getInt("Id"));
                    genre.setName(resultSet.getString("Name"));
                    genre.setHasChanged(false);

                    resultSet.close();
                    statement.close();
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public void update(Genre genre) {
        if (genre.getId() > 0 && !genre.getName().isEmpty()){
            String sqlQuery = "UPDATE Genre " +
                                "SET Name = '" + genre.getName() + "' " +
                                "OUTPUT INSERTED.Id, INSERTED.Name " +
                                "WHERE Id = " + genre.getId();
            try{
                Statement statement = this.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()){
                    genre.setId(resultSet.getInt("Id"));
                    genre.setName(resultSet.getString("Name"));
                    genre.setHasChanged(false);

                    resultSet.close();
                    statement.close();
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
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
