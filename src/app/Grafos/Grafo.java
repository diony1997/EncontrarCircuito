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
            buscarCircuito(0);
            return "Circuito Encontrado:\n" + getSaida();
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

        matriz[fonte][destino] = 1;
        matriz[destino][fonte] = 1;
    }
    
    public boolean validarVertice(String nome){
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
        return "Matriz de Adjacência:\n"+out;
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
                if (matriz[i][j] == 1) {
                    qtd_Arestas++;
                }
            }
            //procurar vertice desconexo
            if (qtd_Arestas == 0 && matriz.length > 0) {
                return false;
            }
            //procura vertice de grau impar se houver mais de 2 vertices
            if (qtd_Arestas % 2 == 1 && matriz.length > 2) {
                return false;
            }
        }
        return true;
    }

    //retorna a qtd de aresta alcançáveis
    public int buscaDFS(boolean[] verificado, int origem) {
        int cont = 1;
        verificado[origem] = true;
        for (int i = 0; i < verificado.length; i++) {
            if (matriz[origem][i] == 1 && !verificado[i]) {
                cont += buscaDFS(verificado, i);
            }
        }
        return cont;
    }

    //verifica se pode retirar a aresta sem deixar o grafo desconexo
    public boolean encontrarPonte(int origem, int destino) {
        int cont = 0, ponte, semPonte;
        boolean[] verificado = new boolean[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[origem][i] == 1) {
                cont++;
            }
        }
        if (cont == 1) {
            return false;
        }
        ponte = buscaDFS(verificado, origem);
        for (int i = 0; i < verificado.length; i++) {
            verificado[i] = false;
        }
        matriz[origem][destino] = 0;
        matriz[destino][origem] = 0;
        semPonte = buscaDFS(verificado, origem);
        matriz[origem][destino] = 1;
        matriz[destino][origem] = 1;
        return semPonte < ponte;
    }

    //procura um circuito para impressão
    public void buscarCircuito(int origem) {
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[origem][i] == 1) {
                if (!encontrarPonte(origem, i)) {
                    saida += vertices.keySet().toArray()[origem] + " -- " + vertices.keySet().toArray()[i] + "  ";
                    matriz[origem][i] = 0;
                    /*
                    Condição necessária para a impressão de um circuito de um grafo
                    com 2 vertices, ja que se apagar a volta a olgoritmo se encerra
                    */
                    if (matriz.length > 2) {
                        matriz[i][origem] = 0;
                    }
                    buscarCircuito(i);
                    break;
                }
            }
        }
    }
}
