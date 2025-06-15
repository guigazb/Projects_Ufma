//alocação dinÂmica
#include <stdio.h>
#include <stdlib.h>

struct x{
    int a;
    int b;
    int c;
};

int main(){
   
   int x ;
   double y ;
   int vetor[10];
   int matriz[10][10];
   struct x estrutura;

   printf("%d \n",sizeof(x));
   printf("%d \n",sizeof(y));
   printf("%d \n",sizeof(vetor));
   printf("%d \n",sizeof(matriz));
   printf("%d \n",sizeof(estrutura)); // 3 x 4(int) = 12
   //sizeof retorna o tamanho de uma variável ou tipo de dado

   int *ptr = (int *) malloc(sizeof(int)); //sem o casting a função retorna um ponteiro tipo void(generico)
   
   *ptr = 100;

   printf("o endereco alocado foi: %i \n",ptr);

   printf("o valor contido em p: %i \n",*ptr);
   free(ptr); // libera o espaço alocado , importante quando não se usa mais aquele espaço da memoria

   printf("o valor contido em p: %i \n",*ptr);

   /* o free desalocou a memória reservada, porém o ponteiro continua apontando para aquele enredeço e continua valendo 100, no entanto aquele
   espaço pode ser usado por outro programa ou código */


    return 0;
}