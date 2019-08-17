import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//61130701107
public class DBConnection {
    public static void main(String[] args) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        String connectionURL = "jdbc:db2://10.4.53.14:50000/SAMPLE";

        Connection conn = null;
        Statement sqlStatement = null;
        ResultSet result = null;

        try {
            Class.forName("com.ibm.db2.jcc.DB2Driver").newInstance();
            conn = DriverManager.getConnection(connectionURL, "stds61107", "ds690");
            sqlStatement = conn.createStatement();

            int row = 0;
//            String std = "INSERT INTO MOVIE(MOVIE_TITLE, MOVIE_GENRE, MOVIE_YEAR) values ('Harry Potter', 'Fantasy', '2008')";
//            st = "INSERT  into  STUDENT values (101, 'Jack'), (102, 'Ann')";
//            row = sqlStatement.executeUpdate(std);

//            String std = "UPDATE MOVIE SET MOVIE.MOVE_RATE_POINT = 9 WHERE MOVIE.MOVIE_GENRE = 'Crime'";
//            row = sqlStatement.executeUpdate(std);
//            System.out.print("Inserted : " + row + " rows");

            String st = "SELECT MOVIE_TITLE, MOVIE_YEAR, RATE_DESC FROM MOVIE, RATING WHERE MOVIE.MOVE_RATE_POINT = RATING.RATE_POINT";
            result = sqlStatement.executeQuery(st);

            while (result.next()) {
                System.out.print(result.getString("MOVIE_TITLE") + " ");
                System.out.print("(" + result.getString("MOVIE_YEAR") + ")");
                System.out.print(" rate is ");
                System.out.print(result.getString("RATE_DESC") + " ");
                System.out.println();
            }
            conn.close();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
