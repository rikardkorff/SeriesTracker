package seriestracker.dao.seriestracker.dao.sqlserver;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import seriestracker.dao.SeriesDAO;
import seriestracker.models.Genre;
import seriestracker.models.Series;

public class SeriesSqlServerDAO extends GenericSqlServerDAO implements SeriesDAO {

	@Override
	public Series findByName(String name) {
		String query = "SELECT Id, Name FROM Series WHERE Name = '" + name + "'";
        try {
            Statement statement = this.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            if (resultSet.next()) {
                Series series = new Series(resultSet.getInt(0));
                series.setName(resultSet.getString(1));
                return series;
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
		return null;
	}

	@Override
	public Series create(Series series) {
		if (series.getId() == 0 && series.getName() != ""){
			String sqlQuery = "INSERT INTO Series(Name) " +
                                "OUTPUT INSERTED.Id, INSERTED.Name " +
                                "VALUES('" + series.getName() + "')";

            try{
                Statement statement = this.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()){
                    Series newSeries = new Series(resultSet.getInt(0));
                    newSeries.setName(resultSet.getString(1));
                    resultSet.close();
                    statement.close();
                    return newSeries;
                }
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
		return null;
	}

	@Override
	public Series update(Series series) {
        if (series.getId() > 0 && !series.getName().isEmpty()){
            String sqlQuery = "UPDATE Series " +
                                "SET Name = '" + series.getName() + "'" +
                                "OUTPUT INSERTED.Id, INSERTED.Name" +
                                "WHERE Id = " + series.getId();
            try{
                Statement statement = this.getConnection().createStatement();
                ResultSet resultSet = statement.executeQuery(sqlQuery);

                if (resultSet.next()){
                    Series newSeries = new Series(resultSet.getInt(0));
                    newSeries.setName(resultSet.getString(1));
                    resultSet.close();
                    statement.close();
                    return newSeries;
                }
            }catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return series;
	}

	@Override
	public void delete(Series series) {
		if (series.getId() > 0){
            String sqlQuery = "DELETE FROM Series WHERE Id = " + series.getId();
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
