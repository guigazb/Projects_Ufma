//unions
#include <stdio.h>

int main(){
   
   union numero{
    int x;
    double y;
   };

   union numero valor = {20};
   //valor armazenado na primeira variável apenas

   printf("x = %d e y = %lf \n",valor.x,valor.y);
   
   valor.y = 10.0;

   //armazenando o valor na segunda variável apenas

   printf("x = %d e y = %.2lf \n",valor.x,valor.y);

   // apenas uma váriavel pode ser refenciada por vez

    return 0;
}