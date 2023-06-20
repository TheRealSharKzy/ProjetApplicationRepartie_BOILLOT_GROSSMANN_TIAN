package ProxyProjetAppRepartie.src.main.java.proxy.ProxyProjetAppRepartie.restaurants;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Client {

    public static void main(String[] args) throws IOException, NotBoundException, SQLException, ClassNotFoundException {

         Registry reg = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
         ServiceBD bd = (ServiceBD) reg.lookup("BD");
         System.out.println(bd.getRestaurants());
         System.out.println(bd.reserverTable(3,"Boillot","Thomas",3,"0666666666","2023-06-17"));

    }
}
