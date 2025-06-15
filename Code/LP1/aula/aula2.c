#include <stdio.h>

#define texto "quero dinheiro."

int main(){

     printf("%s\n",texto);

     int idade = 0;
     float altura = 0.0;
     char nome[50] = "";

     printf("diga a sua idade: ");
     scanf("%d", &idade);

     printf("diga sua altura: ");
     scanf("%f", &altura);

     printf("diga seu nome: ");
     scanf("%s",&nome);

     printf("sua idade e : %d \n",idade);
     printf("sua altura e: %.2f \n",altura);
     printf("seu nome e: %s \n",nome);
}
