package ca.georgian.comp1011m2022ice4;

import org.w3c.dom.ls.LSOutput;

import javax.xml.transform.Result;
import java.sql.*;
import java.util.ArrayList;

/* Singleton */
public class DBManager
{
    /********************** SINGLETON SECTION ********************************/
    // Step 1. - private static instance member variable
    private static DBManager m_instance = null;
    // Step 2. - make the default constructor private
    private DBManager() {}
    // Step 3. - create a public static entry point / instance method
    public static DBManager Instance()
    {
        // Step 4. - check if the private instance member variable is null
        if(m_instance == null)
        {
            // Step 5. - instantiate a new DBManager instance
            m_instance = new DBManager();
        }
        return m_instance;
    }
    /************************************************************************/

    // private instance member variables
    private String m_user = "root";
    private String m_password = "rootpassword";
    private String m_connectURL = "jdbc:mysql://localhost:3306/comp1011m2022";

    /**
     * This method inserts a Vector2D object into the database
     *
     * @param vector2D
     * @return
     * @throws SQLException
     */

    public int insertVector2D(Vector2D vector2D) throws SQLException {
        int vectorID = -1;

        // initializing the result set object
        ResultSet resultSet = null;

        // create a query string
        String sql = "INSERT INTO vectors(X, Y) VALUES(?, ?);";

        try
        (       /* head of the try / catch block */
                Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
                PreparedStatement statement = connection.prepareStatement(sql, new String[] {"vectorID"});

        )
        {

            // configure prepared statement
            statement.setFloat(1, vector2D.getX());
            statement.setFloat(2, vector2D.getY());

            // run the command on the Database
            statement.executeUpdate();

            // get the VectorID
            resultSet = statement.getGeneratedKeys();
            while(resultSet.next())
            {
                // get the VectorID from the Database
                vectorID = resultSet.getInt(1);
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(resultSet != null)
            {
                resultSet.close();
            }
        }

        return vectorID;
    }

    public ArrayList<Vector2D> readVectorTable()
    {
        // Instantiates an ArrayList collection of type Vector2D (empty container)
        ArrayList<Vector2D> vectors = new ArrayList<Vector2D>();

        String sql = "SELECT vectors.vectorID, X, Y FROM vectors GROUP BY vectors.vectorID";

        try
        (
        Connection connection = DriverManager.getConnection(m_connectURL, m_user, m_password);
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(sql);
        )
        {
            // while there is another record, loop
            while(resultSet.next())
            {
                // deserialize (decode) the data from the database table
                int vectorID = resultSet.getInt("vectorID");
                float X = resultSet.getFloat("X");
                float Y = resultSet.getFloat("Y");

                // create an anonymous Vector2D object with the database from the database
                // then add the new Vector2D object to the vectors ArrayList
                vectors.add(new Vector2D(vectorID, X, Y));
            }
        }
        catch(Exception exception)
        {
            exception.printStackTrace();
        }

        return vectors;
    }
}
