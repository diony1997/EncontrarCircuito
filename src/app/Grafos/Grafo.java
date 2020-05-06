package app.Grafos;

import java.util.HashMap;

/**
 *
 * @author Diony
 */
public class Grafo {

    protected int matriz[][];
    protected HashMap<String, Integer> vertices;
    protected String saida;

    public Grafo(int tamanho) {
        this.matriz = new int[tamanho][tamanho];
        this.vertices = new HashMap<>();
        this.saida = "";
    }

    public String executar() {
        if (verificarCircuito()) {
            if (matriz.length > 1) {
                buscarCircuito(0);
                return "Circuito Encontrado:\n" + getSaida();
            } else {
                return "Grafo Possui apenas um vértice\n" + getSaida();
            }
        } else {
            return "O Grafo fornecido não contem um Circuito Euleriano";
        }
    }

    public void inserirVertice(String novo) {
        vertices.put(novo, vertices.size());
    }

    public void inserirAresta(String de, String para) {
        int fonte = vertices.get(de);
        int destino = vertices.get(para);

        matriz[fonte][destino]++;
        matriz[destino][fonte]++;
    }

    public boolean validarVertice(String nome) {
        return vertices.containsKey(nome);
    }

    //impressão da matriz de adjacência
    public String imprimir() {
        String out = "";
        for (int[] linha : matriz) {
            for (int valor : linha) {
                out += valor + " ";
            }
            out += "\n";
        }
        return "Matriz de Adjacência:\n" + out;
    }

    public String getSaida() {
        return saida;
    }

    //verifica se existe um circuito euleriano
    public boolean verificarCircuito() {
        int qtd_Arestas;
        for (int i = 0; i < matriz.length; i++) {
            qtd_Arestas = 0;
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] > 0) {
                    qtd_Arestas += matriz[i][j];
                    //caso a aresta esteja saindo e indo para o mesmo vertice
                    if (i == j) {
                        qtd_Arestas += matriz[i][j];
                    }
                }
            }
            //procurar vertice desconexo
            if (qtd_Arestas == 0 && matriz.length > 1) {
                return false;
            }
        }
        return true;
    }

    //retorna a qtd de vértices alcançáveis
    public int buscaDFS(boolean[] verificado, int origem) {
        int cont = 1;
        verificado[origem] = true;
        for (int i = 0; i < verificado.length; i++) {
            if (matriz[origem][i] > 0 && !verificado[i]) {
                cont += buscaDFS(verificado, i);
            }
        }
        return cont;
    }

    //procura um circuito para impressão
    public void buscarCircuito(int origem) {
        int qtdArestas = 0;
        //calcula a quantidade de arestas
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] > 0) {
                    qtdArestas += matriz[i][j];
                }
            }
        }
        //procura a aresta com o vertice que possui maior alcance
        while (qtdArestas > 0) {
            int i = encontrarCaminho(origem);
            //retira a arestas da matriz
            matriz[origem][i]--;
            matriz[i][origem]--;
            saida += vertices.keySet().toArray()[origem] + " -- " + vertices.keySet().toArray()[i] + "  ";
            origem = i;
            qtdArestas -= 2;
        }
    }

    //retorna um vertice que alcança a maior qtd de vertices a partir de um dado vertice
    public int encontrarCaminho(int origem) {
        int cont = 0, saida = 0;
        boolean[] verificado = new boolean[matriz.length];
        //procura quantos arestas existem no vertice
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[origem][i] > 0) {
                cont += matriz[origem][i];
                saida = i;
            }
        }
        //se existir apenas uma aresta
        if (cont == 1) {
            return saida;
        }
        int maior = 0, atual;
        //procura qual aresta esta ligada a mais vertices
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[origem][i] > 0) {
                //retira a aresta para verificar se é uma ponte (se caso restir ela, o grafo fica desconexo)
                matriz[origem][i]--;
                matriz[i][origem]--;
                atual = buscaDFS(verificado, i);
                //insere a aresta novamente
                matriz[origem][i]++;
                matriz[i][origem]++;
                if (atual > maior) {
                    maior = atual;
                    saida = i;
                }
                for (int j = 0; j < verificado.length; j++) {
                    verificado[j] = false;
                }
            }
        }
        return saida;
    }

}
