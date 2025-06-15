package ED2.algoritmos_de_ordenação;

public class ShellSort {
    public static int[] shellSort(int[] vetor){

        int h,temp,j;

        //calculando o h inicial, for de linha única
        for(h = 1; h < vetor.length ; h = (3*h)+1);

        while(h > 0){
            h = (h-1)/3; // atualiza o h
            for(int i = h; i < vetor.length; i++){
                temp = vetor[i];
                j = i;

                //compara as distancias h entre itens
                while(vetor[j-h] > temp){
                    vetor[j] = vetor[j-h];
                    j = j-h;
                    if(j < h){
                        break;
                    }
                    vetor[j] = temp;
                }
            }
        }
        return vetor;
    }
}
