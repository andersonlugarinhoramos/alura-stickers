import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.util.List;
import java.util.Map;

public class App {
    public static void main(String[] args) throws Exception {

        var url = "https://imdb-api.com/en/API/Top250Movies/k_9dmwz0vb";
        URI endereco = URI.create(url);
        var client = HttpClient.newHttpClient();
        var request = HttpRequest.newBuilder(endereco).GET().build();
        HttpResponse<String> response = client.send(request, BodyHandlers.ofString());
        String body = response.body();

        var parser = new JsonParser();
        List<Map<String, String>> listaDeFilmes = parser.parse(body);

        for (Map<String, String> filme : listaDeFilmes) {
            System.out.println("Title:  \u001b[1m" + filme.get("title") + "\u001b[m");
            System.out.println("Image:  " + filme.get("image"));
            System.out.println(
                    "Rating: \u001b[38;2;255;255;255m \u001b[48;2;42;122;228m " + filme.get("imDbRating") + "\u001b[m");
            System.out.println();
        }
    }
}
