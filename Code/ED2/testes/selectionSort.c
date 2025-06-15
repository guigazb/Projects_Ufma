#include <stdio.h>
#define true 1
#define false 0

void selectionSortBruto(int* vetor, int n){
    if(vetor != NULL){
        int menor = 0;

        for(int i = 0; i < n; i++){
            menor = i;

            for(int j = i + 1; j < n; j++){
                if(vetor[j]< vetor[menor]){
                    menor = j;
                }
            }

            int aux = vetor[i];
            vetor[i] = vetor[menor];
            vetor[menor] = aux;
        }
    }
}

void selectionSortRecursivo(int* vetor, int inicial, int n){
    if(vetor != NULL){
        if(inicial < n){
            int menor = inicial;
            for(int i = inicial +1; i < n; i++){
                if(vetor[i] < vetor[menor]){
                    menor = i;
                }
            }
            int aux = vetor[inicial];
            vetor[inicial] = vetor[menor];
            vetor[menor] = aux;

            selectionSortRecursivo(vetor, inicial + 1, n);
        }
    }
}


int main(){

    int vetor[10] = {10,50,30,20,5,1,76,434,3,11};

    printf("teste do SelectionSort: \n");
    printf(" vetor sem ordem:");
    for(int i = 0; i < 10; i++){
        printf(" %d ",vetor[i]);
    }
    printf("\n");

    selectionSortRecursivo(vetor, 0, 10);

    printf(" vetor ordenado:");
    for(int j = 0; j < 10; j++){
        printf(" %d ", vetor[j]);
    }

    printf("\n");

    return 0;
}