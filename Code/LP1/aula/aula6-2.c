#include <stdio.h>
#include <locale.h>
#include <string.h>

#define N 20

int main(){
    
    setlocale(LC_ALL,"portuguese");
    
    /*
    char string[N];
    printf("digite algo (scanf normal):  \n");
    scanf("%s",string);
    fflush(stdin);

    printf("resultado: %s \n\n",string);

    printf("digite algo (scanf brabo):  \n");
    scanf("%9[^\n]s",string);
    fflush(stdin);

    printf("resultado: %s \n\n",string);
    
    
    printf("digite algo (gets normal):  \n");
    gets(string);
    fflush(stdin);

    puts("resultado:");
    puts(string);
    puts("");

    printf("digite algo (fgets):  \n");
    fgets(string,10,stdin);
    fflush(stdin);

    puts("resultado:");
    puts(string);
    puts("");

    */

    

    char origem[N] = {"olá, mundo!"};
    char destino[N];

    printf("antes do strcpy: \n");
    puts(origem);
    puts(destino);

    strcpy(destino,origem);

    printf("depois do strcpy: \n");
    puts(origem);
    puts(destino);



    return 0;
}