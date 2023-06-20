package ProxyProjetAppRepartie.src.main.java.proxy.ProxyProjetAppRepartie.établissementSupérieur;

import ProxyProjetAppRepartie.src.main.java.proxy.ProxyProjetAppRepartie.établissementSupérieur.DonneesBloquees;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

class MainServeur{
    public static void main(String[] args) throws RemoteException {
        Registry registry = LocateRegistry.createRegistry(1099);
        registry.rebind("donnéesBloquées", UnicastRemoteObject.exportObject(new DonneesBloquees(), 0));
    }
}