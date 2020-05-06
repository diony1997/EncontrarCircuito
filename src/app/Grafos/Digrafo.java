package app.Grafos;

import java.util.Arrays;

/**
 *
 * @author Diony
 */
public class Digrafo extends Grafo {

    public Digrafo(int tamanho) {
        super(tamanho);
    }

    @Override
    public String executar() {
        if (verificarCircuito()) {
            buscarCircuito(0);
            return "Circuito Encontrado:\n" + getSaida();
        }
        return "O Grafo fornecido não contem um Circuito Euleriano";
    }

    //verificar se existe um circuito
    @Override
    public boolean verificarCircuito() {
        if (!conexo()) {
            return false;
        }
        //Positivas = arestas que chegam a um vertice
        //Negativas = arestas que saem de um vertice
        int[] arestasPositivas = new int[matriz.length];
        int[] arestasNegativas = new int[matriz.length];

        for (int i = 0; i < matriz.length; i++) {
            int arestas = 0;
            for (int j = 0; j < matriz.length; j++) {
                if (matriz[i][j] > 0) {
                    arestasPositivas[j] += matriz[i][j];
                    arestas += matriz[i][j];
                }
            }
            arestasNegativas[i] = arestas;
        }
        return (Arrays.equals(arestasNegativas, arestasPositivas));
    }

    //verifica se o grafo é conexo
    public boolean conexo() {
        boolean[] verificado = new boolean[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                verificado[j] = false;
            }
            buscaDFS(verificado, i);
            for (int j = 0; j < matriz.length; j++) {
                if (!verificado[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    //retorna um vertice que alcança a maior qtd de vertices a partir de um dado vertice
    @Override
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
                atual = buscaDFS(verificado, i);
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

    //procura um circuito para impressão
    @Override
    public void buscarCircuito(int origem) {
        int qtdArestas = 0;
        //calculando quantidade de arestas
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
            //retira a aresta da matriz
            matriz[origem][i]--;
            saida += vertices.keySet().toArray()[origem] + " -- " + vertices.keySet().toArray()[i] + "  ";
            origem = i;
            qtdArestas--;

        }
    }

    //Modificado devido as arestas direcionadas
    @Override
    public void inserirAresta(String de, String para) {
        int fonte = vertices.get(de);
        int destino = vertices.get(para);
        matriz[fonte][destino]++;
    }
}
