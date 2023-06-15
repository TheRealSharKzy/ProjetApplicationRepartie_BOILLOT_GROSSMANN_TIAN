import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.sql.SQLException;

public class Client {

    public static void main(String[] args) throws RemoteException, NotBoundException, SQLException, ClassNotFoundException {

         Registry reg = LocateRegistry.getRegistry(args[0], Integer.parseInt(args[1]));
         ServiceBD bd = (ServiceBD) reg.lookup("BD");
        System.out.println(bd.getRestaurants());

    }
}
