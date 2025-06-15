//structs
#include <stdio.h>

struct horario{
    int horas;
    int minutos;
    int segundos;
};

typedef struct{
  int x,y;
} ponto;

int main(){
  //structs funcionam como classes de certa forma
  struct comodo {
    int altura;
    int largura;
    int comprimento;
  };

  struct comodo sala;
  sala.altura = 3;
  sala.largura = 6;
  sala.comprimento = 7;

  struct comodo novasala;
  novasala.altura = sala.altura + 2;
  novasala.largura = sala.largura + 4;
  novasala.comprimento = sala.comprimento + 3;

  printf("altura antiga: %d \n",sala.altura);
  printf("altura nova: %d \n",novasala.altura);

  struct horario teste(struct horario x);

  struct horario agora;
  agora.horas = 20;
  agora.minutos = 30;
  agora.segundos = 30;

  struct horario depois;
  depois = teste(agora);

  printf("%d:%d:%d \n",depois.horas,depois.minutos,depois.segundos);

  ponto p1 = {1,2};

  printf("as coordenadas sao:(%d,%d) \n",p1.x,p1.y);

 return 0;
}

struct horario teste(struct horario x){

    printf("%d:%d:%d \n",x.horas,x.minutos,x.segundos);

    x.horas -= 3;
    x.minutos += 2;
    x.segundos += 10;

 return x;
}