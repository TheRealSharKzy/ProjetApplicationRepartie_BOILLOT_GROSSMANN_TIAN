import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class DonneesBloquees implements ServiceDonneesBloquees{
    @Override
    public HttpResponseDate fetch() throws Exception {
        String url="https://data.enseignementsup-recherche.gouv.fr//explore/dataset/fr-esr-principaux-etablissements-enseignement-superieur/download?format=json&amp;timezone=Europe/Berlin&amp;use_labels_for_header=false";
        HttpClient httpClient= HttpClient.newHttpClient();
        HttpRequest httpRequest= HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response=httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        return new HttpResponseDate(response.statusCode(), response.body(), response.headers().firstValue("Content-Type").orElse("Unknown"));
    }
}
