import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServeurCentral {
    public ServeurCentral() throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("BD", UnicastRemoteObject.exportObject(new BD(), 0));
    }

    public static void main(String[] args) {
        try {
            new ServeurCentral();
            System.out.println("Serveur central lancé");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
