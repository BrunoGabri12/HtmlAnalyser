import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Classe responsável por realizar a busca em profundidade do elemento
 * 
 */
public class SearchDeepElements {

    // Expressão regular para verificar as tags de abertura e fechamento
    private String tagOpen = "<([a-zA-Z]+)>";
    private String tagClose = "<(\\/[a-zA-Z]+)>";
    private ArrayList<String> html;

    SearchDeepElements(ArrayList<String> html) {
        this.html = new ArrayList<>(html);
    }

    public String findDeepElement() {

        int depth = 0;

        // Map para armazenar a profundidade de cada elemento
        Map<String, Integer> elementDepth = new HashMap<String, Integer>();

        for (String string : this.html) {

            // Se a tag for uma tag de fechamento, significa que a profundade diminuiu.
            // O que ocorre para cada tag de fechamento
            if (string.matches(this.tagClose)) {
                depth -= 1;
            }

            // Se uma nova tag de abertura for identificada, significa que a profundidade
            // aumentou
            else if (string.matches(tagOpen)) {
                depth += 1;
            }

            // Caso não seja uma tag de abertura e de fechamento, então é o elemento que
            // estamos procurando. Assim sendo, adicionamos ele no Map
            else {
                elementDepth.put(string, new Integer(depth));

            }
        }

        return maxDepth(elementDepth);

    }

    // Encontra o elemento com maior profundidade
    private String maxDepth(Map<String, Integer> elementDepth) {

        int maxDephValue = Integer.MIN_VALUE;
        String string = null;

        // Irá buscar pelo elemento mais fundo.
        for (Map.Entry<String, Integer> element : elementDepth.entrySet()) {
            if (element.getValue() > maxDephValue) {
                maxDephValue = element.getValue();
                string = element.getKey();

            }
        }

        return string;
    }

}
