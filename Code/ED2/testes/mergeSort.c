#include <stdio.h>

void mergeSort(int* vetor,int n){}

int main(){

    int vetor[10] = {10,50,30,20,5,1,76,434,3,11};

    printf("teste do mergeSort: \n");
    printf(" vetor sem ordem:");
    for(int i = 0; i < 10; i++){
        printf(" %d ",vetor[i]);
    }
    printf("\n");

    mergeSort(vetor, 10);
    //mergeSortRecursivo(vetor, 10, 1); 

    printf(" vetor ordenado:");
    for(int j = 0; j < 10; j++){
        printf(" %d ", vetor[j]);
    }

    printf("\n");

    return 0;
}