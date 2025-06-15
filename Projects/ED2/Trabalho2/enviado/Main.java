import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Main {

    private static class PerformanceMetrics {
        long comparacoes;
        long atribuicoes;
        long tempoEmMiliSec;
        String nomeEstrutura;

        public PerformanceMetrics(String name) {
            this.nomeEstrutura = name;
            reset();
        }

        public void reset() {
            comparacoes = 0;
            atribuicoes = 0;
            tempoEmMiliSec = 0;
        }

        @Override
        public String toString() {
            return String.format("Estrutura: %s\ncomparacoes: %d\natribuicoes: %d\nTempo: %d ms\n",
                    nomeEstrutura, comparacoes, atribuicoes, tempoEmMiliSec);
        }
    }

    // Classe genérica para representar um elemento que pode ser string ou número
    public static class Element implements Comparable<Element> {
        private String value;

        public Element(String value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return value;
        }

        @Override
        public boolean equals(Object obj) {
            if (this == obj)
                return true;
            if (obj == null || getClass() != obj.getClass())
                return false;
            Element other = (Element) obj;
            return value.equals(other.value);
        }

        @Override
        public int hashCode() {
            return value.hashCode();
        }

        @Override
        public int compareTo(Element other) {
            // Tenta comparar como números se ambos forem números
            try {
                Double num1 = Double.parseDouble(this.value);
                Double num2 = Double.parseDouble(other.value);
                return num1.compareTo(num2);
            } catch (NumberFormatException e) {
                // Se não forem números, compara como strings
                return this.value.compareTo(other.value);
            }
        }
    }

    private static List<Element> carregaDoArquivo(String filename) throws IOException {

        List<Element> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String linha;
            while ((linha = br.readLine()) != null) {
                linha = linha.trim();
                if (!linha.isEmpty()) {
                    lista.add(new Element(linha));
                }
            }
        }
        return lista;
    }

    // Método para carregar dados nas estruturas
    @SuppressWarnings("unchecked")
    private static void carregaEstrutura(List<Element> data, Object estrutura) {
        for (Element item : data) {
            if (estrutura instanceof ArrayList) {
                ((ArrayList<Element>) estrutura).add(item);
            } else if (estrutura instanceof LinkedList) {
                ((LinkedList<Element>) estrutura).add(item);
            } else if (estrutura instanceof BalancedTree) {
                ((BalancedTree<Element>) estrutura).insert(item);
            } else if (estrutura instanceof HashTentativaLinear) {
                ((HashTentativaLinear<Element, Element>) estrutura).put(item, item);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void buscaElementosDe_A_PresentesEm_B(
            LinkedList<T> A, Object B, PerformanceMetrics metricas) {
        metricas.reset();
        Instant comeco = Instant.now();

        List<T> interseccao = new ArrayList<>();
        for (T element : A) {
            metricas.comparacoes++;
            boolean achado = false;

            if (B instanceof LinkedList) {
                achado = ((LinkedList<T>) B).contains(element);
            } else if (B instanceof BalancedTree) {
                achado = ((BalancedTree<T>) B).find(element);
            } else if (B instanceof HashTentativaLinear) {
                achado = ((HashTentativaLinear<T, T>) B).contains(element);
            }

            if (achado) {
                metricas.atribuicoes++;
                interseccao.add(element);
            }
        }

        metricas.tempoEmMiliSec = Duration.between(comeco, Instant.now()).toMillis();
        System.out.println("Elementos em A e B: " + interseccao);
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void insertEm_B_ElementoDe_A_NaoPresenteEm_B(
            LinkedList<T> A, Object B, PerformanceMetrics metricas) {
        metricas.reset();
        Instant comeco = Instant.now();

        for (T element : A) {
            metricas.comparacoes++;
            boolean existe = false;

            if (B instanceof LinkedList) {
                existe = ((LinkedList<T>) B).contains(element);
                if (!existe) {
                    metricas.atribuicoes++;
                    ((LinkedList<T>) B).add(element);
                }
            } else if (B instanceof BalancedTree) {
                existe = ((BalancedTree<T>) B).find(element);
                if (!existe) {
                    metricas.atribuicoes++;
                    ((BalancedTree<T>) B).insert(element);
                }
            } else if (B instanceof HashTentativaLinear) {
                existe = ((HashTentativaLinear<T, T>) B).contains(element);
                if (!existe) {
                    metricas.atribuicoes++;
                    ((HashTentativaLinear<T, T>) B).put(element, element);
                }
            }
        }

        metricas.tempoEmMiliSec = Duration.between(comeco, Instant.now()).toMillis();
    }

    @SuppressWarnings("unchecked")
    public static <T extends Comparable<T>> void removeElementoDe_A_PresenteEm_B(
            LinkedList<T> A, Object B, PerformanceMetrics metricas) {
        metricas.reset();
        Instant comeco = Instant.now();

        for (T element : A) {
            metricas.comparacoes++;
            boolean existe = false;

            if (B instanceof LinkedList) {
                existe = ((LinkedList<T>) B).contains(element);
                if (existe) {
                    metricas.atribuicoes++;
                    ((LinkedList<T>) B).remove(element);
                }
            } else if (B instanceof BalancedTree) {
                existe = ((BalancedTree<T>) B).find(element);
                if (existe) {
                    metricas.atribuicoes++;
                    ((BalancedTree<T>) B).remove(element);
                }
            } else if (B instanceof HashTentativaLinear) {
                existe = ((HashTentativaLinear<T, T>) B).contains(element);
                if (existe) {
                    metricas.atribuicoes++;
                    ((HashTentativaLinear<T, T>) B).delete(element);
                }
            }
        }

        metricas.tempoEmMiliSec = Duration.between(comeco, Instant.now()).toMillis();
    }

    private static <T> void printList(String mensagem, List<T> list) {
        System.out.println(mensagem);
        for (T element : list) {
            System.out.println(element);
        }
    }

    public static void main(String[] args) {
        try {

            // Teste conjuntos pequenos
            System.out.println("\n===  Teste conjuntos pequenos ===");

            // Carregar arquivos
            List<Element> dataA = carregaDoArquivo("ArquivoMenorPequeno.txt");
            List<Element> dataB = carregaDoArquivo("ArquivoMaiorPequeno.txt");

            // Criar estruturas
            LinkedList<Element> conjuntoA = new LinkedList<>();
            carregaEstrutura(dataA, conjuntoA);

            // Estruturas para conjunto B
            LinkedList<Element> listaB = new LinkedList<>();
            TreeAVL<Element> avlB = new TreeAVL<>();
            TreeRB<Element> rbB = new TreeRB<>();
            HashTentativaLinear<Element, Element> hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);

            // Criar métricas para cada estrutura
            PerformanceMetrics metricasLista = new PerformanceMetrics("Lista Encadeada");
            PerformanceMetrics metricasAVL = new PerformanceMetrics("Arvore Avl");
            PerformanceMetrics metricasRB = new PerformanceMetrics("Arvore Rubro Negra");
            PerformanceMetrics metricasHash = new PerformanceMetrics("Tabela Hash");

            // Executar os testes
            System.out.println("\n=== Busca de elementos de A presentes em B ===");
            
            buscaElementosDe_A_PresentesEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos em A e B:", listaB);

            buscaElementosDe_A_PresentesEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos em A e na Arvore AVL B: ");
            avlB.printInOrder();
            System.out.println();

            buscaElementosDe_A_PresentesEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos em A e na Arvore RB B: ");
            rbB.printInOrder();
            System.out.println();

            buscaElementosDe_A_PresentesEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);
           


            // limpa estruturas
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);

            
            System.out.println("\n=== Insere elementos de A em B, se o elemento não estiver já em B ===");

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos na Lista B após inserção:", listaB);

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos na Arvore avl B após inserção: ");
            avlB.printInOrder();
            System.out.println();

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos na Arvore RB B após inserção: ");
            rbB.printInOrder();
            System.out.println();

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);
           


            // limpa estruturas
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);


            System.out.println("\n=== Remove elementos de A que estão presentes em B ===");

            removeElementoDe_A_PresenteEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos em B após remoção:", listaB);

            removeElementoDe_A_PresenteEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos na Arvore Avl B após remoção: ");
            avlB.printInOrder();
            System.out.println();

            removeElementoDe_A_PresenteEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos na Arvore RB B após remoção: ");
            rbB.printInOrder();
            System.out.println();

            removeElementoDe_A_PresenteEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);



            // Teste conjuntos Medianos
            System.out.println("\n===  Teste conjuntos Medianos ===");
            
            // Carregar arquivos
            dataA = carregaDoArquivo("ArquivoMenorMedio.txt");
            dataB = carregaDoArquivo("ArquivoMaiorMedio.txt");

            // Criar estruturas
            conjuntoA = new LinkedList<>();
            carregaEstrutura(dataA, conjuntoA);

            // Estruturas para conjunto B
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);

            // Criar métricas para cada estrutura
            metricasLista = new PerformanceMetrics("Lista Encadeada");
            metricasAVL = new PerformanceMetrics("Arvore Avl");
            metricasRB = new PerformanceMetrics("Arvore Rubro Negra");
            metricasHash = new PerformanceMetrics("Tabela Hash");

            // Executar os testes
            System.out.println("\n=== Busca de elementos de A presentes em B ===");
            
            buscaElementosDe_A_PresentesEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos em A e B:", listaB);

            buscaElementosDe_A_PresentesEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos em A e na Arvore AVL B: ");
            avlB.printInOrder();
            System.out.println();

            buscaElementosDe_A_PresentesEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos em A e na Arvore RB B: ");
            rbB.printInOrder();
            System.out.println();

            buscaElementosDe_A_PresentesEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);
           


            // limpa estruturas
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);

            
            System.out.println("\n=== Insere elementos de A em B, se o elemento não estiver já em B ===");

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos na Lista B após inserção:", listaB);

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos na Arvore avl B após inserção: ");
            avlB.printInOrder();
            System.out.println();

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos na Arvore RB B após inserção: ");
            rbB.printInOrder();
            System.out.println();

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);


            // limpa estruturas
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);


            System.out.println("\n=== Remove elementos de A que estão presentes em B ===");

            removeElementoDe_A_PresenteEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos em B após remoção:", listaB);

            removeElementoDe_A_PresenteEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos na Arvore Avl B após remoção: ");
            avlB.printInOrder();
            System.out.println();

            removeElementoDe_A_PresenteEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos na Arvore RB B após remoção: ");
            rbB.printInOrder();
            System.out.println();

            removeElementoDe_A_PresenteEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);
            


            // Teste conjuntos Grandes
            System.out.println("\n===  Teste conjuntos grandes ===");

            // Carregar arquivos
            dataA = carregaDoArquivo("ArquivoMenorGrande.txt");
            dataB = carregaDoArquivo("ArquivoMaiorGrande.txt");

            // Criar estruturas
            conjuntoA = new LinkedList<>();
            carregaEstrutura(dataA, conjuntoA);

            // Estruturas para conjunto B
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);

            // Criar métricas para cada estrutura
            metricasLista = new PerformanceMetrics("Lista Encadeada");
            metricasAVL = new PerformanceMetrics("Arvore Avl");
            metricasRB = new PerformanceMetrics("Arvore Rubro Negra");
            metricasHash = new PerformanceMetrics("Tabela Hash");

            // Executar os testes
            System.out.println("\n=== Busca de elementos de A presentes em B ===");
            
            buscaElementosDe_A_PresentesEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos em A e B:", listaB);

            buscaElementosDe_A_PresentesEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos em A e na Arvore AVL B: ");
            avlB.printInOrder();
            System.out.println();

            buscaElementosDe_A_PresentesEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos em A e na Arvore RB B: ");
            rbB.printInOrder();
            System.out.println();

            buscaElementosDe_A_PresentesEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);
        


            // limpa estruturas
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);

            
            System.out.println("\n=== Insere elementos de A em B, se o elemento não estiver já em B ===");

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos na Lista B após inserção:", listaB);

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos na Arvore avl B após inserção: ");
            avlB.printInOrder();
            System.out.println();

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos na Arvore RB B após inserção: ");
            rbB.printInOrder();
            System.out.println();

            insertEm_B_ElementoDe_A_NaoPresenteEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);
           


            // limpa estruturas
            listaB = new LinkedList<>();
            avlB = new TreeAVL<>();
            rbB = new TreeRB<>();
            hashB = new HashTentativaLinear<>(dataB.size() * 2);

            // Carregar dados em B
            carregaEstrutura(dataB, listaB);
            carregaEstrutura(dataB, avlB);
            carregaEstrutura(dataB, rbB);
            carregaEstrutura(dataB, hashB);


            System.out.println("\n=== Remove elementos de A que estão presentes em B ===");

            removeElementoDe_A_PresenteEm_B(conjuntoA, listaB, metricasLista);
            System.out.println(metricasLista);
            printList("Elementos em B após remoção:", listaB);

            removeElementoDe_A_PresenteEm_B(conjuntoA, avlB, metricasAVL);
            System.out.println(metricasAVL);
            System.out.print("Elementos na Arvore Avl B após remoção: ");
            avlB.printInOrder();
            System.out.println();

            removeElementoDe_A_PresenteEm_B(conjuntoA, rbB, metricasRB);
            System.out.println(metricasRB);
            System.out.print("Elementos na Arvore RB B após remoção: ");
            rbB.printInOrder();
            System.out.println();

            removeElementoDe_A_PresenteEm_B(conjuntoA, hashB, metricasHash);
            System.out.println(metricasHash);
            

        } catch (IOException e) {
            System.err.println("Erro ao ler arquivos: " + e.getMessage());
        }
    }
}