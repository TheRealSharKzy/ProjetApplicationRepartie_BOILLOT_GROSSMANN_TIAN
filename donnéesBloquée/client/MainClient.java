import java.net.http.HttpResponse;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;

public class MainClient {
    public static void main(String[] args) throws Exception {
        String host = (args.length < 1) ? "localhost" : args[0];
        int port = (args.length < 2) ? 1099 : Integer.parseInt(args[1]);
        Registry registry = java.rmi.registry.LocateRegistry.getRegistry(host, port);
        ServiceDonneesBloquees donneesBloquees = (ServiceDonneesBloquees) registry.lookup("donnéesBloquées");
        HttpResponseDate response = donneesBloquees.fetch();
        System.out.println("Response code: " + response.getStatusCode());
        System.out.println("Response body: " + response.getBody());
        System.out.println("Content type: " + response.getContentType());
    }
}
