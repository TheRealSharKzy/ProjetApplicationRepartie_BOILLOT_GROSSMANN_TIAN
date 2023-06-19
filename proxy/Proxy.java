import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Proxy {
    @GetMapping("/donneesBloquees")
    public String donneesBloquees() throws Exception {
        String host = "localhost";
        int port = 1099;
        Registry registry = java.rmi.registry.LocateRegistry.getRegistry(host, port);
        ServiceDonneesBloquees donneesBloquees = (ServiceDonneesBloquees) registry.lookup("donnéesBloquées");
        HttpResponseDate response = donneesBloquees.fetch();
        return response.getBody();
    }
}
