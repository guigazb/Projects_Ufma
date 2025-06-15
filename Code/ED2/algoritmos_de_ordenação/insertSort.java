package ED2.algoritmos_de_ordenação;

public class insertSort {
    public static int[] insertionSort(int[] vetor){

        int i,j,chave;
        for(j = 1; j < vetor.length; j++){
            
            chave = vetor[j];
            i = j-1;
            
            while(i >= 0 && vetor[i] > chave){
                vetor[i+1] = vetor[i];
                i--;
            }

            vetor[i+1] = chave;
            
        }
        return vetor;
    }
}
