package proxy.ProxyProjetAppRepartie.restaurants;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
        res.next();
        strJSON += "{\n \"nom\" : \"" + res.getString(2) + "\", \n \"adresse\" : \"" + res.getString(3) + "\", \n \"longitude\" : " + res.getString(4) + ", \n \"latitude\" : " + res.getString(5) + "\n }\n";
        while (res.next()){
            strJSON += ",{\n \"nom\" : \"" + res.getString(2) + "\", \n \"adresse\" : \"" + res.getString(3) + "\", \n \"longitude\" : " + res.getString(4) + ", \n \"latitude\" : " + res.getString(5) + "\n }\n";
        }
        strJSON += "]\n}";

        return strJSON;

    }

    @Override
    public Boolean reserverTable(int idRestaurant,String nom,String prenom, int nbPersonne, String telephone, String date) throws SQLException, IOException {

        connect();

        PreparedStatement statement = con.prepareStatement("insert into reservation(nom,prenom,nbPersonne,telephone,dateReservation,idRestaurant) values (?,?,?,?,date(?),?)");
        statement.setString(1,nom);
        statement.setString(2,prenom);
        statement.setInt(3,nbPersonne);
        statement.setString(4,telephone);
        statement.setString(5,date);
        statement.setInt(6,idRestaurant);
        return !statement.execute();

    }
}
