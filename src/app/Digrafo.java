package app;

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
                if (matriz[i][j] == 1) {
                    arestasPositivas[j]++;
                    arestas++;
                }
            }
            arestasNegativas[i] = arestas;
        }
        return (Arrays.equals(arestasNegativas, arestasPositivas));
    }

    //verificar se o grafo é conexo
    public boolean conexo() {
        boolean[] verificado = new boolean[matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                verificado[j] = false;
            }
            buscaDNF(verificado, i);
            for (int j = 0; j < matriz.length; j++) {
                if (!verificado[j]) {
                    return false;
                }
            }
        }
        return true;
    }

    //retorna um vertice com maior dnf a partir de um dado vertice
    public int encontrarCaminho(int origem) {
        int cont = 0, saida = 0;
        boolean[] verificado = new boolean[matriz.length];
        //procura quantos arestas existem no vertice
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[origem][i] == 1) {
                cont++;
                saida = i;
            }
        }
        //se existir apenas uma aresta
        if (cont == 1) {
            return saida;
        }
        int maior = 0, atual;
        //procura qual aresta liga a um vertice
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[origem][i] == 1) {
                atual = buscaDNF(verificado, i);
                if (atual > maior) {
                    maior = atual;
                    saida = i;
                }
                for (int j = 0; j < verificado.length; j++) {
                    verificado[i] = false;
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
                if (matriz[i][j] == 1) {
                    qtdArestas++;
                }
            }
        }
        //procura a aresta com maior o vertice de maior DNF
        while (qtdArestas > 0) {
            int i = encontrarCaminho(origem);
            matriz[origem][i] = 0;
            saida += vertices.keySet().toArray()[origem] + " -- " + vertices.keySet().toArray()[i] + "  ";
            origem = i;
            qtdArestas--;

        }
    }

    @Override
    public void inserirAresta(String de, String para) {
        int fonte = vertices.get(de);
        int destino = vertices.get(para);
        matriz[fonte][destino] = 1;
    }
}
