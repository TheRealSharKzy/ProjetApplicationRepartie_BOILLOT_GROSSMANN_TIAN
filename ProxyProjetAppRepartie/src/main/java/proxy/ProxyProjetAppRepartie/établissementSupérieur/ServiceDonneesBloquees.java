package proxy.ProxyProjetAppRepartie.établissementSupérieur;

import proxy.ProxyProjetAppRepartie.HttpResponseDate;

import java.net.http.HttpResponse;
import java.rmi.Remote;

public interface ServiceDonneesBloquees extends Remote {
    HttpResponseDate fetch() throws Exception;
}
