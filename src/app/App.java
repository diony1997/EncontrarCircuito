package app;

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
        // TODO code application logic here
        Grafo teste = leitor();
        System.out.println(teste.imprimir());
        System.out.println(teste.executar());
        System.out.println(teste.imprimir());

    }

    public static Grafo leitor() {
        Grafo novo = new Grafo(0);

        try {
            File arquivo = new File("grafo2.txt");
            Scanner leitor = new Scanner(arquivo);
            int tipo, tamanho;
            tipo = leitor.nextInt();
            System.out.println("Tipo - " + tipo);
            //Criação de um grafo não direcional
            if (tipo == 0) {
                tamanho = leitor.nextInt();
                System.out.println("Tamanho - " + tamanho);
                novo = new Grafo(tamanho);
                System.out.println("Inserção de vertices");
                leitor.nextLine();
                for (int i = 0; i < tamanho; i++) {
                    String t1 = leitor.nextLine();
                    System.out.println("Vertice - " + t1);
                    novo.inserirVertice(t1);

                }
                String valor, arestas[];
                System.out.println("Inserção de arestas");
                while (leitor.hasNextLine()) {
                    valor = leitor.nextLine();
                    arestas = valor.split(",");
                    System.out.println("valor - " + valor);
                    System.out.println("aresta0 - " + arestas[0] + "\naresta1 - " + arestas[1]);
                    novo.inserirAresta(arestas[0], arestas[1]);
                }
                leitor.close();
                return novo;
            } else if (tipo == 1) {
                tamanho = leitor.nextInt();
                System.out.println("Tamanho - " + tamanho);
                novo = new Digrafo(tamanho);
                System.out.println("Inserção de vertices");
                leitor.nextLine();
                for (int i = 0; i < tamanho; i++) {
                    String t1 = leitor.nextLine();
                    System.out.println("Vertice - " + t1);
                    novo.inserirVertice(t1);

                }
                String valor, arestas[];
                System.out.println("Inserção de arestas");
                while (leitor.hasNextLine()) {
                    valor = leitor.nextLine();
                    arestas = valor.split(",");
                    System.out.println("valor - " + valor);
                    System.out.println("aresta0 - " + arestas[0] + "\naresta1 - " + arestas[1]);
                    novo.inserirAresta(arestas[0], arestas[1]);
                }
                return novo;
            }

        } catch (Exception e) {
            System.out.println("Erro na leitura do grafo:\n" + e);
        }
        return novo;
    }

}
