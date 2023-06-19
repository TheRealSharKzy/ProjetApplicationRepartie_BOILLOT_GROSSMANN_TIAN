import java.net.http.HttpResponse;
import java.rmi.Remote;

public interface ServiceDonneesBloquees extends Remote {
    HttpResponseDate fetch() throws Exception;
}
