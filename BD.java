import java.rmi.RemoteException;
import java.sql.*;

public class BD implements ServiceBD {

    Connection con = null;

    public void connect(String ip, String port) throws SQLException {

        con = DriverManager.getConnection("jdbc:mysql://"+ip+":"+port+"/projetprogrepartie", "root", "");
    }

    @Override
    public String getRestaurants() throws SQLException, RemoteException {
        connect("localhost", "3306");
        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("select * from restaurant");

        String strJSON = "{ \n\"restaurants\" : [\n";
        while (res.next()){
            //System.out.println("Nom : " +res.getString(2) + " adresse : " + res.getString(3));
            strJSON += "{\n \"nom\" : \"" + res.getString(2) + "\", \n \"adresse\" : \"" + res.getString(3) + "\" \n},\n";
        }
        strJSON += "]\n}";

        return strJSON;

    }
}
