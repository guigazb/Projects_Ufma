#include <stdio.h>

int main (){

    /*
    int A,B,SOMA,SUBT,MULT;
    float DIV;

    printf("diga o valor A: \n");
    scanf("%d",&A);
    printf("diga o valor B: \n");
    scanf("%d",&B);

    SOMA = A + B;
    SUBT = A - B;
    MULT = A * B;
    DIV = A / B;

    printf("a soma e: %d \n",SOMA);
    printf("a subtracao e: %d \n", SUBT);
    printf("a multiplicacao e: %d \n",MULT);
    printf("a divisao e: %f \n",DIV);
    */
    int valor = 10;
    printf("o valor inicial e: %d \n", valor);

    valor ++;
    printf(" o valor somado em 1: %d \n",valor);
    
    valor --;
    printf("o valor subtraido em 1: %d \n", valor);
    
    valor +=2;
    printf("o valor adicionado em dois: %d \n", valor);
    
    valor -= 2;
    printf("o valor subtraido dois: %d \n", valor);
    
    valor *= 4;
    printf("o valor multiplicado por quatro: %d \n", valor);
    
    valor /= 2;
    printf("o valor dividido por quatro: %d \n", valor);

}