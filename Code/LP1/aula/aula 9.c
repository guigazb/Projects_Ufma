#include <stdio.h>

int fatorial(int x){

  int resultado;

  if (x==0)
    resultado = 1;
  else
    resultado = x * fatorial(x-1);

  return resultado;
}

float soma(float valor1,float valor2){

   float modulo(float x);

   if (valor1 <0)
     valor1 = modulo(valor1);
   if (valor2 <0)
     valor2 = modulo(valor2);

   float sum = valor1 + valor2;
   
   return sum;
}

float modulo(float x){

    return x * -1;
}

float maior(float num1, float num2){
    if (num1 > num2)
        return num1;
    else
        return num2;
}

int valorzin(void){
    // variavel local automática e estática
    
    int multiplic = 2;
    multiplic *= 2;
    static int multiplic2 = 2;
    multiplic2 *= 2;

   printf("%d \n",multiplic);
   printf("%d \n",multiplic2);
}

int main(){
   
   float x,y,m,s,f;
   float maior(float num1, float num2);
   int valorzin(void);
   int fatorial(int x);

   valorzin();
   valorzin();

   
   printf("diga o primeiro valor: \n");
   scanf("%f", &x);
   fflush(stdin);

   printf("diga o segundo valor: \n");
   scanf("%f",&y);
   fflush(stdin);
   
   m = maior(x,y);

   printf("o maior e: %.1f \n",m);

   s = soma(x,y);

   printf("a soma dos valores absolutos e: %.1f \n",s);

   f = fatorial(x);

   printf("o fatorial do primeiro valor e: %.1f \n",f);

   f = fatorial(y);

   printf("o fatorial do segundo valor e: %.1f \n",f);

   return 0;
 
}