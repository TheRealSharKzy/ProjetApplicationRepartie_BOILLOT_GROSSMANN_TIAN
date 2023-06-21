package proxy.ProxyProjetAppRepartie;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.*;
import proxy.ProxyProjetAppRepartie.restaurants.ServiceBD;
import proxy.ProxyProjetAppRepartie.établissementSupérieur.ServiceDonneesBloquees;

import java.io.IOException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.Registry;
import java.sql.SQLException;

@RestController
public class Proxy {

    Registry registry;

    public Proxy() throws RemoteException {
        registry = java.rmi.registry.LocateRegistry.getRegistry("localhost", 1099);
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/donneesBloquees")
    public String donneesBloquees() throws Exception {
        ServiceDonneesBloquees donneesBloquees = (ServiceDonneesBloquees) registry.lookup("donnéesBloquées");
        HttpResponseDate response = donneesBloquees.fetch();
        return response.getBody();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @GetMapping("/restaurants")
    public String restaurants() throws IOException, NotBoundException, SQLException {
        ServiceBD bd = (ServiceBD) registry.lookup("BD");
        return bd.getRestaurants();
    }

    @CrossOrigin(origins = "http://localhost:8080")
    @PostMapping("/reservation")
    public void postData(@RequestBody String data) throws IOException, NotBoundException, JSONException, SQLException {
        ServiceBD bd = (ServiceBD) registry.lookup("BD");
        JSONObject jsonObject = new JSONObject(data);
        System.out.println("Add reservation: "+data);
        bd.reserverTable(jsonObject.getInt("idResto"), jsonObject.getString("nom"), jsonObject.getString("prenom"), jsonObject.getInt("nbPers"), jsonObject.getString("tel"), jsonObject.getString("date") );
    }
}
