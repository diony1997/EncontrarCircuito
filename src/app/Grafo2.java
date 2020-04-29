/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 *
 * @author Diony
 */
public class Grafo2 {

    private int matriz[][];
    private HashMap<String, Integer> vertices;
    String saida;

    public Grafo2(int tamanho) {
        this.matriz = new int[tamanho][tamanho];
        vertices = new HashMap<>();
        saida = "";
    }

    public void executar() {
        buscarCircuito(acharComeco());
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

    public int acharComeco() {
        //buscar vertice de grau impar
        int qtd;
        for (int i = 0; i < matriz.length; i++) {
            qtd = 0;
            for (int j = 0; j < matriz[0].length; j++) {
                qtd += matriz[i][j];
            }
            if (qtd % 2 == 1) {
                return i;

            }
        }
        //se não houver comeca pela primeira da matriz
        return 0;
    }

    public boolean encontrarPonte(int atual) {
        int aux = 0;
        for (int i = 0; i < matriz.length; i++) {
            if (matriz[atual][i] == 1) {
                aux++;
            }
        }
        if (aux > 1) {
            return false;
        }
        return true;

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
        return qtd;
    }

    public int busca(int anterior, int comeco, boolean verificado[]) {
        int aux = 1;
        verificado[comeco] = true;
        for (int i = 0; i < matriz.length; i++) {
            if (anterior != i) {
                if (!verificado[i]) {
                    if (matriz[comeco][i] == 1) {
                        aux += busca(comeco, i, verificado);
                    }
                }
            }
        }
        return aux;
    }

    public void buscarCircuito(int comeco) {
        //int qtd_Arestas = contarArestas();
        int tamanho = matriz.length;

        for (int i = 0; i < matriz.length; i++) {
            boolean[] verificado = new boolean[matriz.length];
            if (encontrarPonte(i)) {
                tamanho--;
            }
            int cont = busca(comeco,i,verificado);
            if(Math.abs(cont-tamanho) <= 2){
                saida+=vertices.keySet().toArray()[comeco]+" -- "+vertices.keySet().toArray()[i]+"  ";
                if(encontrarPonte(i)){
                    tamanho--;
                }
                matriz[comeco][i] = 0;
                matriz[i][comeco] = 0;
                //qtd_Arestas--;
                buscarCircuito(i);
            }

        }

    }

}
