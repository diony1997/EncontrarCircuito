package app;

import app.Exception.EntradaInvalidaException;
import app.Grafos.Digrafo;
import app.Grafos.Grafo;
import java.io.File;
import java.util.Scanner;

/**
 *
 * @author Diony
 */
public class App {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Grafo teste = leitor();
        System.out.println(teste.imprimir());
        System.out.println(teste.executar());

    }

    public static Grafo leitor() {
        Grafo novo;

        try {
            File arquivo = new File("grafo.txt");
            Scanner leitor = new Scanner(arquivo);
            int tipo, tamanho;
            tipo = leitor.nextInt();
            switch (tipo) {

                //Criação de um grafo não direcional
                case 0: {
                    tamanho = leitor.nextInt();
                    novo = new Grafo(tamanho);
                    leitor.nextLine();
                    //inserção dos vértices
                    for (int i = 0; i < tamanho; i++) {
                        String t1 = leitor.nextLine();
                        //checar se o vertice é repetido
                        if (!novo.validarVertice(t1)) {
                            novo.inserirVertice(t1);
                        } else {
                            throw new EntradaInvalidaException("\nVertice Repetido: " + t1);
                        }
                    }
                    String valor, arestas[];
                    //inserção das arestas
                    while (leitor.hasNextLine()) {
                        valor = leitor.nextLine();
                        arestas = valor.split(",");
                        //checar se os vertices existem
                        if (novo.validarVertice(arestas[0]) && novo.validarVertice(arestas[1])) {
                            novo.inserirAresta(arestas[0], arestas[1]);
                        } else {
                            throw new EntradaInvalidaException("\nVertices Não Declarados: " + valor);
                        }
                    }
                    leitor.close();
                    return novo;
                }

                //Criação de um grafo direcional
                case 1: {
                    tamanho = leitor.nextInt();
                    novo = new Digrafo(tamanho);
                    leitor.nextLine();
                    //inserção dos vértices
                    for (int i = 0; i < tamanho; i++) {
                        String t1 = leitor.nextLine();
                        //checar se o vertice é repetido
                        if (!novo.validarVertice(t1)) {
                            novo.inserirVertice(t1);
                        } else {
                            throw new EntradaInvalidaException("\nVertice Repetido: " + t1);
                        }

                    }
                    String valor, arestas[];
                    //inserção das arestas
                    while (leitor.hasNextLine()) {
                        valor = leitor.nextLine();
                        arestas = valor.split(",");
                        //checar se os vertices existem
                        if (novo.validarVertice(arestas[0]) && novo.validarVertice(arestas[1])) {
                            novo.inserirAresta(arestas[0], arestas[1]);
                        } else {
                            throw new EntradaInvalidaException("\nVertices Não Declarados: " + valor);
                        }
                    }
                    leitor.close();
                    return novo;
                }
                //caso o tipo não seja 0 ou 1
                default:
                    throw new EntradaInvalidaException("\nTipo Invalido");
            }

        } catch (Exception e) {
            System.out.println("Erro na leitura do grafo:\nInserção Inválida\n" + e);
        }
        //Encerra a aplicação caso a entrada incorreta
        System.exit(0);
        return new Grafo(0);
    }

}
