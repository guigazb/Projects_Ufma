//pré processamento
#include <stdio.h>

#define PI 3.14159
#define NULO 0
#define AREACIRCULO(RAIO) RAIO*RAIO*PI
// x é maior que y? se sim,x,se não,y
#define MAIOR(x,y) x > y ? x : y
#define MINUSCULO(char) char >= 'a' && char <= 'z'

int main(){
  
   double areacirculo(double raio);

   double raio = 2;

   printf("a area do circulo e: %lf \n", areacirculo(raio));
   printf("a area do circulo e: %lf \n", AREACIRCULO(10));
  
  char x = '6';

  if(MINUSCULO(x)){
    printf("e minuscula \n");
  }else{
    printf("nao e minuscula \n");
  }

  printf("%d \n",MAIOR(10,20));
  
  return 0;
}

double areacirculo(double raio){
    return raio*raio*PI;
}