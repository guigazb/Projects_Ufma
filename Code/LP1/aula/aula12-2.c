//structs inicialização e vetores de struct
#include <stdio.h>

struct horario{
    int horas;
    int minutos;
    int segundos;
};

int main(){
  //structs funcionam como classes de certa forma
  struct comodo {
    int altura;
    int largura;
    int comprimento;
  }sala = {4,6,8};
  
  printf("altura: %d , largura: %d , comprimento: %d \n",sala.altura,sala.largura,sala.comprimento);

  struct comodo quarto = {3,5,4};

  printf("altura: %d , largura: %d , comprimento: %d \n",quarto.altura,quarto.largura,quarto.comprimento);
  
  //valores não atribuidos assumem 0, nos casos todos foram atribuidos

  struct comodo quartocasal = {.largura = 3,.altura = 5,.comprimento = 4};

  struct comodo cozinha = {10};
  //apenas o primeiro valor é preenchido

  struct comodo banheiro[2] = {{10,20,30},{5,10,15}}; //estrutura do tipo comodo que contém 2 vezes os atributos da estrutura

  printf("altura: %d , largura: %d , comprimento: %d \n",banheiro[0].altura,banheiro[0].largura,banheiro[0].comprimento);
  printf("altura: %d , largura: %d , comprimento: %d \n",banheiro[1].altura,banheiro[1].largura,banheiro[1].comprimento);

  return 0;
}