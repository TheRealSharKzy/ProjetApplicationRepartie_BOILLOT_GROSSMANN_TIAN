package proxy.ProxyProjetAppRepartie.restaurants;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

public class ServeurCentral {

    public static void main(String[] args) {
        try {
            Registry registry = LocateRegistry.createRegistry(1099);
            registry.rebind("BD", UnicastRemoteObject.exportObject(new BD(), 0));
            System.out.println("Serveur central lancé");
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
