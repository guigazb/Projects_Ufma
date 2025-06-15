package ED2.algoritmos_de_ordenação;
import java.util.Random;

public class quickSorting {
    
    public static void quickSort(int[]vetor, int inicio, int fim){
        if(inicio < fim){
            int posicionaPivo = particiona(vetor,inicio,fim);
            quickSort(vetor, inicio, posicionaPivo-1);
            quickSort(vetor, posicionaPivo+1, fim);
        }
    }

    private static int particiona(int[]vetor, int inicio, int fim){
        int pivo = vetor[inicio];
        int i = inicio + 1;
        int f = fim;
        while(i <= f){
            if(vetor[i] <= pivo){
                i++;
            }else if( pivo < vetor[f]){
                f--;
            }else{
                int troca = vetor[i];
                vetor[i] = vetor[f];
                vetor[f] = troca;
                i++;
                f--;
            }
        }
        vetor[inicio] = vetor[f];
        vetor[f] = pivo;
        return f;
    }

    public static void quickSortRandom(int[]vetor, int p, int r){
        if(p < r){
            int q = particionaRandom(vetor, p, r);
            quickSortRandom(vetor, p, q-1);
            quickSortRandom(vetor, q+1, r);
        }
    }

    private static int particionaRandom(int[]vetor, int p, int r){
        Random random = new Random();
        int i = random.nextInt(r - p + 1) + p; // gera um índice aleatório entre p e r
        
        int aux = vetor[r];
        vetor[r] = vetor[i];
        vetor[i] = aux;
        return particiona(vetor, p, r); // verificar se está correto
    }
}
