import java.util.ArrayList;

/**
 * Classe responsável por realizar a validação do HTML
 * Ela verifica se a sintax das tags estão corretas, ou seja, se apresentam < e
 * > ou </ e >
 * É verificado também se a estrutura está correta, se cada tag de abertura
 * apresenta uma tag de fechamento
 * O método é capaz de captar erros como:
 * tags de fechamento antes de tags de abertura
 * tags abertas e não fechadas
 * tags fechadas mas não abertas
 */

public class HtmlValidation {

    private String tagOpen = "<([a-zA-Z]+)>";
    private String tagClose = "<(\\/[a-zA-Z]+)>";

    // Expressões Regulares para verificar se a sintax das tags são válidas.
    // <([a-zA-Z]+)> verifica se a tag de abertura está dentro dos padrões indicados
    // <tag>
    // ([\\w\\s]+) verifica se é um texto, nesse caso ele aceita qualquer caractere
    // e espaços
    // <(\\/[a-zA-Z]+)> verifica se a tag de fechamento está dentro dos padrões
    // indicados <\tag>
    private String sintaxAnalyzerHtml = "<([a-zA-Z]+)>|^[^<>]*$|<(\\/[a-zA-Z]+)>";
     
    private ArrayList<String> html;

    HtmlValidation(ArrayList<String> html) {
        this.html = html;
    }

    // Irá percorrer o Array verificando se a estrutura está okay
    public boolean sintaxValidation() {

        for (int i = 0; i < this.html.size(); i++) {

            if (!this.html.get(i).matches(this.sintaxAnalyzerHtml)) {

                return false;

            }

        }

        return true;

    }

    public boolean parValidation() {

        // O tamanho do array será sempre atualizado, toda vez que um par for removido,
        // assim é necessário utilizar uma variavel auxiliar
        int sizeHtml = html.size();
        


        


        // a flag irá ser responsável por verificar se um texto ou um par de tags foram
        // removidos da estrutura.
        // Os elementos serão removidos a medida que encontram os seus pares de
        // fechamento.
        boolean flag = false;

        while (sizeHtml > 0) {

            if (html.get(0).matches(tagOpen)) {

                // Irá buscar o elemento de fechamento
                for (int j = 1; j < sizeHtml; j++) {

                    // irá verificar se o elemento representa uma tag de fechamento da respectiva
                    // tag de abertura
                    if (html.get(j).matches(tagClose)) {

                        // Remove os caracteres especiais <\> ou <> para comparar somente o texto
                        // e verificar se as tags são semelhantes
                        String tagHtmlClose = filterTag(html.get(0));
                        String tagHtmlOpen = filterTag(html.get(j));

                    

                        // Caso a tag de fechamento tenha sido encontrada então ambas são removidas o
                        // tamanho do arrayList é atualizado e a flag é atualizada para true,
                        // ou seja, o para for encontrado
                        if (tagHtmlClose.equals(tagHtmlOpen)) {
                            html.remove(j);
                            html.remove(0);
                            sizeHtml = html.size();

                            flag = true;
                            break;
                        }
                    }

                }

            }

            // Se entrou aqui então é um texto
            // Assim sendo, ele será descartado da contagem e a flag será atualizada como
            // verdadeira
            else if (!html.get(0).matches(tagClose)) {

                html.remove(0);
                sizeHtml = html.size();
                flag = true;

            }

            // Se a flag não foi definida como verdadeira então a tag de abertura não
            // encontrou a sua tag de fechamento, assim sendo o HTML está desbalanceado
            if (!flag) {
                return false;
            }

            flag = false;

        }

        // Caso todos os elemntos tenham sido removidos então o html está balanceado
        if (html.size() == 0) {
            return true;
        }

        // Caso não, está desbalanceado
        return false;
    }

    // Irá filtar as tags
    public String filterTag(String tag) {
        tag = tag.trim();
        return tag.replaceAll("<|/|>", "");
    }
}
