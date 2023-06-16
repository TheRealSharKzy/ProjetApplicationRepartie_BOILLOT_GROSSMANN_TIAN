import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class DonneesBloquees implements ServiceDonneesBloquees{
    @Override
    public HttpResponseDate fetch() throws Exception {
        String url="https://data.enseignementsup-recherche.gouv.fr//explore/dataset/fr-esr-principaux-etablissements-enseignement-superieur/download?format=json&amp;timezone=Europe/Berlin&amp;use_labels_for_header=false";
        HttpClient httpClient= HttpClient.newHttpClient();
        HttpRequest httpRequest= HttpRequest.newBuilder().uri(URI.create(url)).build();
        HttpResponse<String> response=httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        String body=response.body();
        JSONArray jsonArray= new JSONArray(body);
        JSONArray filtre=new JSONArray();
        for (int i=0; i<jsonArray.length(); i++){
            JSONObject jsonObject=jsonArray.getJSONObject(i);
            try {
                String uucr_nom=jsonObject.getJSONObject("fields").getString("uucr_nom");
                if (uucr_nom.equals("Nancy"))filtre.put(jsonObject);
            }catch (JSONException e) {
                //System.out.println("Pas de nom");
            }
        }
        return new HttpResponseDate(response.statusCode(), filtre.toString(), response.headers().firstValue("Content-Type").orElse("Unknown"));
    }
}
