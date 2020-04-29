/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.HashMap;

/**
 *
 * @author Diony
 */
public class Grafo {

    private int matriz[][];
    private HashMap<String, Integer> vertices;

    public Grafo(int tamanho) {
        this.matriz = new int[tamanho][tamanho];
        vertices = new HashMap<>();
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

    //impressão da matriz de adjacencia
    public String imprimir() {
        String out = "";
        for (int[] linha : matriz) {
            for (int valor : linha) {
                out += valor + " ";
            }
            out += "\n";
        }
        return out;
    }

    public String executar() {
        System.out.println("Começou");
        if (verificarCircuito()) {
            return "Circuito Encontrado:\n" + buscarCircuito("", 0);
        } else {
            return "O Grafo fornecido não contem um Circuito Euleriano";
        }
    }

    //verificar se é possivel existir um circuito
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
            //procura vertice de grau impar
            if (qtd_Arestas % 2 == 1) {
                return false;
            }
        }
        return true;
    }

    //procura qual um circuito para impressão
    public String buscarCircuito(String saida, int atual) {

        //criando uma nova matriz para não alterar a original
        int[][] matrizTemp = new int[matriz.length][matriz.length];
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz.length; j++) {
                matrizTemp[i][j] = matriz[i][j];
            }
        }
        int cont = contarArestas();
        while (cont > 0) {
            for (int i = 0; i < matriz.length; i++) {
                //adiciona a aresta na saida e apaga ela da matriz 
                if (matriz[atual][i] == 1) {
                    saida += vertices.keySet().toArray()[atual] + " -- " + vertices.keySet().toArray()[i] + "  ";
                    matriz[atual][i] = 0;
                    matriz[i][atual] = 0;
                    //comeca a procurar uma nova aresta no novo vertice 
                    atual = i;
                    System.out.println("cont = " + cont);
                    System.out.println(imprimir());
                    cont--;
                    break;
                }
            }
        }
        return saida;
    }

    public int contarArestas() {
        int qtd = 0;
        for (int i = 0; i < matriz.length; i++) {
            for (int j = 0; j < matriz[i].length; j++) {
                if (matriz[i][j] == 1) {
                    qtd++;
                }
            }
        }
        return qtd / 2;
    }

}
