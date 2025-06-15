//ponteiros
#include <stdio.h>

int main (){

    int var = 20;

    int *varPtr = NULL;
    //*varPtr = &var; o valor do ponteiro é o enredeço da variável
    
    varPtr = &var; // o endereço do ponteiro aponta pro endereço da variável
    
    printf("o o valor contido no endereco da variavel e: %d e o endereco e: %d \n",*varPtr, varPtr);
    

    int x = 10;
    int *xptr = NULL;
    xptr = &x;

    int y = 20;
    *xptr = y;
    printf("%d %d \n",x,y); 
    // o ponteiro de x recebe o endereço de x, em seguida o valor contido no endereço apontado pelo ponteiro é alterado para 20, fazendo com que x = 20 

    int a = 10;
    double b = 20.50;
    char z = 'a';

    int *aptr = &a;
    double *bptr = &b;
    char *zptr = &z;

    double soma = *aptr + *bptr;

    printf("valor a: %d e o endereco a: %d \n",*aptr, aptr);
    printf("valor b: %.2f e o endereco b: %d \n",*bptr, bptr);
    printf("valor z: %c e o endereco z: %d \n",*zptr, zptr);

    printf("a soma de a com b e: %.2f \n",soma);

    int **ptr;
    int *optr = &a;
    ptr = &optr;

    printf("valor: %d e o endereco: %d \n",*optr, optr);
    printf("valor: %d e o endereco: %d \n",**ptr, ptr);
    //ponteiro de ponteiro é um ponteiro que aponta para o endereço de outro, e armazena o valor que o apontado também armazena

    return 0;
}