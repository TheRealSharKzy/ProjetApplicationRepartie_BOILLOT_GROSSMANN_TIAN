import java.io.*;
import java.sql.*;

public class BD implements ServiceBD {

    Connection con = null;

    public void connect() throws SQLException, IOException {
        File fileToParse = new File("config.ini");
        FileReader fr = new FileReader(fileToParse);
        BufferedReader br = new BufferedReader(fr);

        String password = br.readLine();


        con = DriverManager.getConnection("jdbc:mysql://webetu.iutnc.univ-lorraine.fr/boillot14u", "boillot14u", password);
    }

    @Override
    public String getRestaurants() throws SQLException, IOException {

        connect();

        Statement statement = con.createStatement();
        ResultSet res = statement.executeQuery("select * from restaurants");

        String strJSON = "{ \n\"restaurants\" : [\n";
        while (res.next()){
            //System.out.println("Nom : " +res.getString(2) + " adresse : " + res.getString(3));
            strJSON += "{\n \"nom\" : \"" + res.getString(2) + "\", \n \"adresse\" : \"" + res.getString(3) + "\" \n},\n";
        }
        strJSON += "]\n}";

        return strJSON;

    }
}
