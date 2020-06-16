Algoritmo que identifica se existe um circuito euleriano em um grafo/dígrafo, caso exista, imprime ele.

### Funcionamento

------------
O algoritmo lê o arquivo de entrada e então cria uma matriz de adjacência do grafo.

- Caso o arquivo de entrada não seja valido, o algoritmo indica o erro e se encerra.

- Caso o grafo não seja orientado:
Verifica se o grafo atende os requisitos para ter um circuito euleriano:
	- O grafo é conexo
	- Todos os vértices possuem grau par

Caso sim, o algoritmo faz o caminho evitando "destruir pontes" (logica baseado no algoritmo de Fleury), evitar destruir uma aresta que deixará o grafo desconexo para o circuito. 
Para isso é utilizado uma busca por profundidade modificado, dado um vértice, ele retorna quantos vértices ele alcança (lógica baseada no algoritmo de Hierholzer).
- Caso o grafo seja orientado:
verifica se o grafo atende os requisitos para ter um circuito euleriano:

	- O grafo é conexo.
	- Todos os vértices alcançam os vértices restantes.

Caso sim, o algoritmo recebe um vértice inicial e verifica qual vértice adjacente tem o maior alcance para então apagar a aresta 
e fazer o mesmo no novo vértice, fazendo assim o circuito (lógica baseada no algoritmo de Hierholzer).

### Utilização

------------


##### Inserção do Grafo

Salvar na pasta raiz um arquivo chamado "grafo.txt" seguindo o seguinte padrão:

linha 1: indicação se o grafo é orientado ( 1 ) ou não ( 0 )

linha 2: número de vértices ( n )

linha 3 até a linha (n + 1): nomes dos vértices

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

------------


<a href="https://github.com/diony1997"><img src="https://avatars2.githubusercontent.com/u/32603543?s=460&u=d0f0068bc3c65043b04c687f1e209f305ceb657f&v=4"  height="100" width="100" ></a>
