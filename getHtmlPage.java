import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class getHtmlPage {

    private URL url;

    public ArrayList<String> Connection() throws Exception {

        // Estabelece a conexão com a URL
        HttpURLConnection connection = (HttpURLConnection) getUrl().openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("Accept", "text/html");

        try {

            // buffer de leitura do arquivo html
            BufferedReader buffer = new BufferedReader(new InputStreamReader(connection.getInputStream()));

            String l;
            ArrayList<String> html = new ArrayList<>();

            // Irá adicionar todos os elementos na estrutura de dados
            while ((l = buffer.readLine()) != null) {
                // Trim irá remover os espaços em brancos no começo e final da frase
                l = l.trim();

                html.add(l);

            }

            // Retorna um array contendo o html
            return html;

        } catch (Exception ex) {
            // Relançando a exception
            throw new Exception("URL connection error");

        } finally {
            // garante que a conexão irá fechar mesmo se acontecer uma excessão
            connection.disconnect();
        }

    }

    getHtmlPage(URL urlPage) {
        setUrl(urlPage);
    }

    /* Getters and Setters */
    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

}
