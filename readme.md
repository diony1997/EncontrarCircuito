//

Autor – Diony Augusto
Algoritmo que identifica se existe um circuito euleriano e um grafo/dígrafo, caso exista, imprime ele.

//

-Funcionamento

O algoritmo lê o arquivo de entrada e então cria uma matriz de adjacência do grafo.

Se o grafo não é orientado:

verifica se o grafo atende os requisitos para ter um circuito euleriano
*O grafo é conexo
*Todos os vértices possuem grau par

Caso sim, o algoritmo faz o caminho evitando "destruir pontes"(logica baseado no algoritmo de Fleury), evitar destruir uma aresta que deixara o grafo desconexo para o circuito.
para isso é utilizado uma busca por profundidade modificado, dado um vértice, ele retorna quantos vértices ele alcança.

Se o grafo é orientado:

verifica se o grafo atende os requisitos para ter um circuito euleriano:
*O grafo é conexo.
*Todos os vértices alcançam os vértices restantes.

Caso sim, o algoritmo recebe um vértice inicial e verifica qual vértice adjacente tem o maior alcance para então apagar a aresta 
e fazer o mesmo no novo vértice, fazendo assim o circuito (lógica baseada no algoritmo de Hierholzer).

//

-Inserção do grafo

salvar na pasta raiz um arquivo chamado "grafo.txt" seguindo o seguinte padrão:
minha 1: indicação se o grafo é direcionado ( 1 ) ou não ( 0 )
linha1: número de vértices ( n )
linha 2 até a linha (n + 1): nomes dos vértices
linha n+ 2 até o final do arquivo: arestas do grafo (uma por linha) com origem e destino, separados por vírgula.
Exemplo:
1
3
A
B
C
A,B
A,C
C,B
B,A

//

