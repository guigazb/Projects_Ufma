#include <stdio.h>
#define true 1
#define false 0

void bubbleSortBruto(int* vetor, int n){ // iterativo - bruto - complexidade n^2
    for(int i = 0; i < n; i++){
        for(int j = 0; j < n-1; j++){
            if(vetor[j] > vetor[j+1]){
                int aux = vetor[j];
                vetor[j] = vetor[j+1];
                vetor[j+1] = aux;
            }
        }
    }
}

void bubbleSortOtimizado(int* vetor, int n){ // iterativo - otimizado - complexidade n
    int troca = true; // a variavel troca serve pra evitar comparações repetidas no algoritmo
    while(troca == true){
        troca = false;
        for(int i = 0; i < n; i++){
            if(vetor[i] > vetor[i+1]){
                int aux = vetor[i];
                vetor[i] = vetor[i+1];
                vetor[i+1] = aux;
                troca = true;
            }

        }
        n--; // o decremento de n serve para reduzir as comparações desnecessárias
    }
}


// a função recebe o vetor e seu tamanho

void bubbleSort(int* vetor, int n){ // recursivo - complexidade nXn?
    if(n >= 2){
        for(int i = 0; i < n-1; i++){
            if(vetor[i] > vetor[i+1]){
                int aux = vetor[i];
                vetor[i] = vetor[i+1];
                vetor[i+1] = aux;
            }
        }
        bubbleSort(vetor, n-1);
    }
    return;
}

int main(){

    int vetor[10] = {10,50,30,20,5,1,76,434,3,11};

    printf("teste do BubbleSort: \n");
    printf(" vetor sem ordem:");
    for(int i = 0; i < 10; i++){
        printf(" %d ",vetor[i]);
    }
    printf("\n");

    bubbleSort(vetor,10);

    printf(" vetor ordenado:");
    for(int j = 0; j < 10; j++){
        printf(" %d ", vetor[j]);
    }

    printf("\n");

    return 0;
}