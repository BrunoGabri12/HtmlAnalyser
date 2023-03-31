## Desafio Prático: Busca do elemento mais profundo em uma estrutura HTML
Estudo prático que consistia em buscar o elemento mais profundo em uma estrutura HTML. A seguir, vou explicar um pouco mais sobre a estruturação do projeto e a lógica que utilizei para resolver o desafio.

## Estruturação do Projeto
O projeto é composto por três classes com responsabilidades distintas:

   * `HtmlAnalyzer`: Classe principal do projeto. Recebe a URL passada por terminal e chama o método GetPageHtml.
   * `GetPageHtml`: Estabelece a conexão com a URL, recebe o código HTML e retorna um `ArrayList`, onde cada posição representa um elemento do HTML.
   * `SearchDephElement`: Classe responsável por buscar a profundidade dos elementos e verificar qual deles é maior.
   * `HtmlValdation` : Classe responsável por realizar a validação do código HTML, verificando se existem tags em aberto e que não foram fechadas ou se há erros sintáticos. 
   
## Lógica da Busca 
Para realizar a busca do elemento mais profundo, utilizei um ArrayList e expressões regulares. A cada vez que a expressão identificava uma tag de abertura, considerei que a profundidade do HTML aumentava. Quando era encontrado uma tag de fechamento, diminuía a profundidade. E quando era encontrado um elemento que não satisfazia a lógica de abertura ou fechamento, era considerado o trecho de texto do nível mais profundo da estrutura. Quando era identificado esse trecho de texto, o valor de sua profundidade era salvo em um MAP. Dessa forma, o algoritmo realizou o processo para todos os trechos de texto. Para retornar o valor mais profundo, bastou buscar no MAP o elemento com maior valor de profundidade.



## Lógica da Validação do Html 
O HTML é válido sintáticamente e estruturalmente. Para validar sintáticamente, utilei expressões Regulares. Assim,a análise está divida em três partes: 

 * `<([a-zA-Z]+)>` : consistem em analisar tags de abertura 
 * `^[^<>]*$` : Representa os textos, desta forma, qualquer elemento que não <> são considerados como texto e assim são válidos
 * `<(\\/[a-zA-Z]+)>` : Analise se a tag de fechamento está de acordo com os padrões estabelecidos `</tag>`

Para a analise estrutural, verifiquei se a tag que estava sendo aberta era fechada em algum momento do código. Assim, o método é capaz de captar os seguintes casos: 

 * tags de fechamento antes de tags de abertura 
 * tags abertas e não fechadas
 * tags fechadas mas não abertas


## Excessões 
* Caso o usuário digite uma URL errada, a excessão "URL connection Error" será apresentada
* Caso o HTML apresente uma inconsistência, a excessão "HTML Sintax Error" será apresentada 



