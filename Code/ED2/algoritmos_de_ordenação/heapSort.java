package ED2.algoritmos_de_ordenação;

public class heapSort {
    
    public static void heapsort(int[]vetor, int n){
        constroiMaxHeap(vetor,n);
        for(int i = n; i >= 2; i--){
            int aux = vetor[1];
            vetor[1] = vetor[i];
            vetor[i] = aux;
            maxHeapify(vetor,1,i-1);
        }
    }

    private static void constroiMaxHeap(int[]vetor, int n){
        for(int i = (n/2); i > 1; i--){
            maxHeapify(vetor,i,n);
        }
    }

    private static int left(int i){
        return 2 * i;
    }

    private static int right(int i){
        return 2 * i + 1;
    }

    private static void maxHeapify(int[]vetor, int i, int n){
        int l = left(i);
        int r = right(i);
        int maior = 0;

        if(l <= n && vetor[l] > vetor[i]){
            maior = l;
        }else{
            maior = i;
        }
        if(r <= n && vetor[r] > vetor[maior]){
            maior = r;
        }
        if(maior != i){
            int troca = vetor[i];
            vetor[i] = vetor[maior];
            vetor[maior] = troca;
            maxHeapify(vetor, maior, n);
        }
    }
}
