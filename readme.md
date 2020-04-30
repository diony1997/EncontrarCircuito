//

Autor � Diony Augusto
Algoritmo que identifica se existe um circuito euleriano e um grafo/d�grafo, caso exista, imprime ele.

//

-Funcionamento

O algoritmo l� o arquivo de entrada e ent�o cria uma matriz de adjac�ncia do grafo.

Se o grafo n�o � orientado:

verifica se o grafo atende os requisitos para ter um circuito euleriano
*O grafo � conexo
*Todos os v�rtices possuem grau par

Caso sim, o algoritmo faz o caminho evitando "destruir pontes"(logica baseado no algoritmo de Fleury), evitar destruir uma aresta que deixara o grafo desconexo para o circuito.
para isso � utilizado uma busca por profundidade modificado, dado um v�rtice, ele retorna quantos v�rtices ele alcan�a.

Se o grafo � orientado:

verifica se o grafo atende os requisitos para ter um circuito euleriano:
*O grafo � conexo.
*Todos os v�rtices alcan�am os v�rtices restantes.

Caso sim, o algoritmo recebe um v�rtice inicial e verifica qual v�rtice adjacente tem o maior alcance para ent�o apagar a aresta 
e fazer o mesmo no novo v�rtice, fazendo assim o circuito (l�gica baseada no algoritmo de Hierholzer).

//

-Inser��o do grafo

salvar na pasta raiz um arquivo chamado "grafo.txt" seguindo o seguinte padr�o:
minha 1: indica��o se o grafo � direcionado ( 1 ) ou n�o ( 0 )
linha1: n�mero de v�rtices ( n )
linha 2 at� a linha (n + 1): nomes dos v�rtices
linha n+ 2 at� o final do arquivo: arestas do grafo (uma por linha) com origem e destino, separados por v�rgula.
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

