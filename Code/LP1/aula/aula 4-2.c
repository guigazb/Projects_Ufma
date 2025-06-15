// switch case
#include <stdio.h>

int main () {

  int dia = 0;
  printf("diga um dia da semana em numero: \n");
  scanf("%d",&dia);
  switch(dia){
    case 1:
        printf("domingo. \n");
        break;
    case 2:
        printf("segunda. \n");
        break;
    case 3:
        printf("terca \n");
        break;
    case 4:
        printf("quarta \n");
        break;

    case 5:
        printf("quinta \n");
        break;

    case 6:
        printf("SEXTOU \n");
        break;

    case 7:
        printf("sabado \n");
        break;

    default:
        printf("valor invalido. \n");
        break;
       
   }

}
