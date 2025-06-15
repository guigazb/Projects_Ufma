package ED2.algoritmos_de_ordenação;

public class mergeSorting {
    
    public static int[] MergeSort(int[] vetor){
        int[]temp = new int[vetor.length];
        return mergeMain(vetor,temp,0,vetor.length-1);
    }

    public static int[] mergeMain(int[]vetor, int[]t,int esquerda, int direita){
        int meio;
        if(esquerda < direita){
            meio = (esquerda + direita)/2;
            mergeMain(vetor, t, esquerda, meio);
            mergeMain(vetor, t, meio + 1, direita);
            merge(vetor,t,esquerda,meio+1,direita);
        }
        return vetor;
    }

    private static void merge(int[]vetor,int[]t,int esquerdaPos, int direitaPos, int direitaFim){
        int esquerdaFim = direitaPos - 1;
        int tempPos = esquerdaPos;
        int numElementos = direitaFim-esquerdaPos+1;

        while(esquerdaPos <= esquerdaFim && direitaPos <= direitaFim){
            if(vetor[esquerdaPos] <= vetor[direitaPos]){
                tempPos++;
                esquerdaPos++;
                t[tempPos] = vetor[esquerdaPos];
            }else{
                tempPos++;
                direitaPos++;
                t[tempPos] = vetor[direitaPos];
            }
        }

        while( esquerdaPos <= esquerdaFim){
            tempPos++;
            esquerdaPos++;
            t[tempPos] = vetor[esquerdaPos];
        }

        while( direitaPos <= direitaFim){
            tempPos++;
            direitaPos++;
            t[tempPos] = vetor[direitaPos];
        }

        for(int i = 0; i < numElementos; i++,direitaFim--){
            vetor[direitaFim] = t[direitaFim];
        }
    }
}
