#include <stdio.h>

int main(){
    /*
    int valores [5];
    valores[0] = 5;
    valores[1] = 20;
    valores[2] = 46;
    valores[3] = 18;
    valores[4] = 34;

    float media = (valores[0] + valores[1] + valores[2] + valores[3] + valores[4])/5;

    printf("a media eh aproximadamente: %.2f \n",media);
    */


    int numeros [5] = {10,20,30,40,60};
    int i;
    float soma = 0;

    for (i=0; i<5;i++){

        soma += numeros[i];

    }

    printf("a media eh aproximadamente: %.2f \n",soma/5);
    return 0;

}
