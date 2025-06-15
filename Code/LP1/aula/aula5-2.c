// do while e casting
#include <stdio.h>

int main(){

    int i = 0;
    do{
        printf("%d \n", i);
        i++;
    } while(i <= 10);

    int x = 16;
    int y = 3;

    float div = (float)x/y; //o casting força que o resultado da divisão seja do tipo determinado

    printf("a divisao entre x e y e: %f \n",div);

    return 0;
}
