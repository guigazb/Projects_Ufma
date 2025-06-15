#include <stdio.h>

int main(){
    char *alfabeto;
    

    for(alfabeto = "a"; *alfabeto <= "z"; alfabeto++){
        printf(" %c ",*alfabeto);
        }
    

    void troca (int *a,int *b);
    int a = 1,b = 2;
    
    troca(&a,&b);

    printf("a: %d e b: %d",a,b);

    return 0;
}

void troca (int *a,int *b){
    int aux = *a;
    *a = *b;
    *b = aux;

}