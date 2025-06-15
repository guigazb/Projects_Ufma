//ponteiros e vetores
#include <stdio.h>
#include <stdlib.h>

int main(){

   int vetor[3] = {1,2,3};
   int *vetptr = vetor;

   int i;
   for (i = 0; i<3;i++){
      printf("valor %d: %d \n",i,*vetptr);
      ++vetptr;
   }

   int *ponteiro = &vetor[2];

   printf("valor 2: %d \n",*ponteiro);

   int *ponteirofoda = vetor;

   *(ponteirofoda +1) = 100;

   printf("%d \n",vetor[1]);

   int somavet(int vetor[],const int n);

   int vet[10] = {2,2,2,2,2,2,2,2,2,2};

   printf("a soma dos membros desse vetor e: %d",somavet(vet,10));

  return 0;
}

int somavet(int vetor[],const int n){
   int soma = 0;
   int *ponteiro;
   int *const vetorfinal = vetor + n;

   for(ponteiro = vetor; ponteiro < vetorfinal ; ++ponteiro){
      soma += *ponteiro;
   }
  return soma;
}