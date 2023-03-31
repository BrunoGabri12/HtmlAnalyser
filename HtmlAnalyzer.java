
import java.net.URL;
import java.util.ArrayList;

/**
 * Classe principal do problema.
 * Chama os métodos de validação do html
 * e de busca pelo elementos mais profundo
 */
public class HtmlAnalyzer {

    public static void main(String[] args) {

        // urlPath recebe como parametro a url digitada pelo usuário
        String urlPath = args[0];

        // Irá transformar a string recebida em uma url
        try {

            // Depois buscar
            URL url = new URL(urlPath);

            // Toda a estrutura html em um array
            ArrayList<String> html = new getHtmlPage(url).Connection();

            HtmlValidation htmlValidator = new HtmlValidation(new ArrayList<>(html));

            if (!htmlValidator.sintaxValidation() || !htmlValidator.parValidation()) {

                throw new Exception("HTML Sintex Error");
            }

            // Retorna o elemento de maior profundidade
            String result = new SearchDeepElements(html).findDeepElement();
            System.out.println(result);

        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

}