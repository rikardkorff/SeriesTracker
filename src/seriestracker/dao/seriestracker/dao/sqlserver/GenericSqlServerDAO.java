package seriestracker.dao.seriestracker.dao.sqlserver;

/**
 * Created by rkorff on 2015-07-12.
 */

import seriestracker.common.Config;
import java.sql.Connection;

import java.sql.DriverManager;
import java.sql.SQLException;

public abstract class GenericSqlServerDAO {
    private Connection connection;

    protected Connection getConnection() throws SQLException{
        if (this.connection == null){
            this.Connect();
        }
        return this.connection;
    }

    protected void setConnection(Connection connection){
        this.connection = connection;
    }

    protected void Connect() throws SQLException{
        String connectionUrl = Config.getString("SQLServerProtocol")
                + Config.getString("SQLServerDataBase")
                + Config.getString("SQLServerAuthentication");

        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        this.setConnection(DriverManager.getConnection(connectionUrl));
    }

    public GenericSqlServerDAO(){

    }
}
