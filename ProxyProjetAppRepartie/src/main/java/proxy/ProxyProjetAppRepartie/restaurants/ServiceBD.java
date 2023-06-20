package proxy.ProxyProjetAppRepartie.restaurants;

import java.io.IOException;
import java.rmi.Remote;
import java.sql.SQLException;

public interface ServiceBD extends Remote {

    String getRestaurants() throws SQLException, IOException;

    Boolean reserverTable(int idRestaurant, String nom, String prenom, int nbPers, String tel, String date) throws SQLException, IOException;
}
