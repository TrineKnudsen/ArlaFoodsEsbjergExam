package arlaScreens.dal;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCConnectionPool extends ObjectPool<Connection>
{

    private static JDBCConnectionPool INSTANCE;
    private final DatabaseConnector connectionProvider;

    public synchronized static JDBCConnectionPool getInstance() throws IOException // This makes the JDBC Connection Pool a Singleton.
    {
        if(INSTANCE == null)
            INSTANCE = new JDBCConnectionPool();
        return INSTANCE;
    }

    private JDBCConnectionPool() throws IOException
    {
        connectionProvider = new DatabaseConnector();
    }

    @Override
    protected Connection create()
    {
        try
        {
            return connectionProvider.getConnection();
        } catch (SQLServerException ex)
        {
            ex.printStackTrace();
            return null;
        }
    }

    @Override
    public boolean validate(Connection o)
    {
        try
        {
            return (!((Connection) o).isClosed());
        } catch (SQLException e)
        {
            e.printStackTrace();
            return (false);
        }
    }

    @Override
    public void expire(Connection o)
    {
        try
        {
            ((Connection) o).close();
        } catch (SQLException e)
        {
            e.printStackTrace();
        }
    }

}
