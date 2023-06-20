package proxy.ProxyProjetAppRepartie;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import proxy.ProxyProjetAppRepartie.restaurants.ServiceBD;
import proxy.ProxyProjetAppRepartie.établissementSupérieur.ServiceDonneesBloquees;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.registry.Registry;
import java.sql.SQLException;

@RestController
public class Proxy {
    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/donneesBloquees")
    public String donneesBloquees() throws Exception {
        String host = "localhost";
        int port = 1098;
        Registry registry = java.rmi.registry.LocateRegistry.getRegistry(host, port);
        ServiceDonneesBloquees donneesBloquees = (ServiceDonneesBloquees) registry.lookup("donnéesBloquées");
        HttpResponseDate response = donneesBloquees.fetch();
        return response.getBody();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/restaurants")
    public String restaurants() throws IOException, NotBoundException, SQLException {
        String host = "localhost";
        int port = 1099;
        Registry registry = java.rmi.registry.LocateRegistry.getRegistry(host, port);
        ServiceBD bd = (ServiceBD) registry.lookup("BD");
        return bd.getRestaurants();
    }
}
