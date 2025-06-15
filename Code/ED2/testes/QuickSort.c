#include <stdio.h>

int particiona(int *vetor, int esquerda, int direita){  // esquerda e direita são os indices dos "ponteiros", sendo o primeiro para o inicio
    // e o segundo para o final do intervalo
    if(vetor != NULL){
        int pivo = (vetor[esquerda] + vetor[direita] + vetor[(esquerda + direita)/2] )/3;
        
        while(esquerda <= direita){

            while(vetor[esquerda] < pivo){
                esquerda++;
            }
            while(vetor[direita] > pivo){
                direita--;
            }

            if(esquerda <= direita){
                int aux = vetor[esquerda];
                vetor[esquerda] = vetor[direita];
                vetor[direita] = aux;
                esquerda++;
                direita--;
            }
        }
    }
    return esquerda;
}

void quickSort(int* vetor, int esquerda, int direita){
    if(vetor != NULL){
        if(esquerda < direita){
            int posicao = particiona(vetor,esquerda,direita);
            quickSort(vetor,esquerda,posicao - 1);
            quickSort(vetor,posicao,direita);
        }
    }
}


int main(){

    int vetor[10] = {10,50,30,20,5,1,76,434,3,11};

    printf("teste do QuickSort: \n");
    printf(" vetor sem ordem:");
    for(int i = 0; i < 10; i++){
        printf(" %d ",vetor[i]);
    }
    printf("\n");

    quickSort(vetor,0,9);

    printf(" vetor ordenado:");
    for(int j = 0; j < 10; j++){
        printf(" %d ", vetor[j]);
    }

    printf("\n");

    return 0;
}