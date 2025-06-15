#include <stdio.h>

void insertionSort(int* vetor, int n){ // iterativo - complexidade n^2
    if(vetor != NULL){
        // insertion sort trabalha com um subconjunto do vetor, e ordena ele, após ordernar, aumenta o conjunto, até ordenar tudo
        for(int i = 1; i < n; i++){ // n
            int j = i;
            int aux = vetor[j];
            while(j > 0 && aux < vetor[j-1]){ // n
                // enquanto o valor do final do subconjunto for menor que seu antecessor, será feita uma troca decrementando a posição
                vetor[j] = vetor[j-1];
                j--;
            }
            // j está na posição correta para o valor do final do conjunto, então, INSERE aux em vetor[j]
            vetor[j] = aux;
        }
    }
}

void insertionSortRecursivo(int* vetor, int n, int limite){ //  recursivo - complexidade diferente
    if(vetor != NULL){
        if(limite < n){
            int j = limite;
            int aux = vetor[j];
            while(j > 0 && aux < vetor[j-1]){
                vetor[j] = vetor[j-1];
                j--;
            }
            vetor[j] = aux;
            insertionSortRecursivo(vetor, n, limite +1);
        }
    }
}


int main(){

    int vetor[10] = {10,50,30,20,5,1,76,434,3,11};

    printf("teste do InsertionSort: \n");
    printf(" vetor sem ordem:");
    for(int i = 0; i < 10; i++){
        printf(" %d ",vetor[i]);
    }
    printf("\n");

    //insertionSort(vetor, 10);
    insertionSortRecursivo(vetor, 10, 1); 

    printf(" vetor ordenado:");
    for(int j = 0; j < 10; j++){
        printf(" %d ", vetor[j]);
    }

    printf("\n");

    return 0;
}