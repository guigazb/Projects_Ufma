// structs que contém ponteiros e funções
#include <stdio.h>
#include <stdlib.h>

int main(){

    struct horario{
        int *phoras;
        int *pminutos;
        int *psegundos;
    };

    struct horario hoje;

    int horas = 100;
    int minutos = 50;
    int segundos = 20;

    hoje.phoras = &horas;
    hoje.pminutos = &minutos;
    hoje.psegundos = &segundos;

    printf("hora: %d,minuto: %d,segundos: %d \n",*hoje.phoras,*hoje.pminutos,*hoje.psegundos);

    *hoje.phoras = 20;

    printf("hora: %d \n",*hoje.phoras);

    void testevar(int var);
    void testeptr(int *ptr);

    int valor = 1;
    int *valorptr = &valor;

    printf("valor do ponteiro: %d \n",*valorptr);

    testeptr(valorptr);

    printf("valor do ponteiro: %d \n",*valorptr);

    // a função incrementa o valor diretamente pelo endereço de memória, sendo essa mudança fixa ao longo do código e não restrita a própria função como é o caso de variável simples


    return 0;
}

void testevar(int var){
    ++var;
}

void testeptr(int *ptr){
    ++*ptr;
}