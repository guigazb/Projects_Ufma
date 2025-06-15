package ED2.algoritmos_de_ordenação;

public class selectionSort {
    public static int[] selectSort(int[] vetor){

        int temp = 0;
        for(int i = 0; i < vetor.length-1; i++){
            int min = i;

            for(int j = i+1; j < vetor.length; j++){
                if(vetor[j] < vetor[min]){
                    min = j;
                }
            }

            temp = vetor[min];
            vetor[min] = vetor[i];
            vetor[i] = temp;
            
        }
        return vetor;
    }
}
