// calloc
#include <stdio.h>
#include <stdlib.h>

int main(){

   int *ptr;
   ptr = (int*)calloc(10,sizeof(int));
   // calloc recebe o tamanho de cada unidade , e multiplica pelo valor informado, alocando segundo o exemplo , 10 x 4 = 40 bytes de memória, inicializando esse bloco com o valor 0

   *(ptr+1) = 10;
   
   printf("%d \n",*ptr);
   printf("%d \n",*(ptr+1)); // p[1] também serve

   int i ;
   for(i = 0; i<10;i++){

    printf("o enredeco de p[%d]: %d e o valor: %d \n",i, (ptr+i) ,*(ptr+ i));

   }

   int *bptr = (int *) malloc(10*sizeof(int));
   
   for(i=0;i<10;i++){
    bptr[i] = (i +3)*3;
    printf("enredeco[%d]: %d cujo valor e: %d \n",i,(bptr+i),*(bptr+i));
   }
   
   bptr = (int*)realloc(bptr,3*(sizeof(bptr)));

   for(i=0;i<30;i++){
    bptr[i] = (i +3)*3;
    printf("enredeco[%d]: %d cujo valor e: %d \n",i,(bptr+i),*(bptr+i));
   }
   // realloc move os valores alocados para o ponteiro para outro espaço da memória, enquanto aumenta a quantidade de memória reservada

 return 0;
}
