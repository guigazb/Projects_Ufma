// ponteiros apontando em structs
#include <stdio.h>
#include <stdlib.h>


int main(){
    
    struct horario{
        int horas;
        int minutos;
        int segundos;
    };

    struct horario agora,*depois;
    
    depois = &agora;

    (*depois).horas = 2;
    (*depois).minutos = 24;
    (*depois).segundos = 20; 

    //usando ponteiros para alocar valores no endereço da struct agora

    printf("%d:%d:%d \n",agora.horas,agora.minutos,agora.segundos);

    depois->horas = 3;
    depois->minutos = 25;
    depois->segundos = 25; 

    //sintaxe reduzida para fazer ponteiros apontarem structs

    printf("%d:%d:%d \n",agora.horas,agora.minutos,agora.segundos);

    int soma = 100;

    struct horario antes ;

    antes.horas = (soma + depois->segundos)/24;
    antes.minutos = (agora.minutos + depois->horas);
    antes.segundos = (agora.segundos + depois->minutos);

    printf("%d:%d:%d \n",antes.horas,antes.minutos,antes.segundos);

    //agora.minutos e (*depois).minutos tem o mesmo valor contido


    return 0;
}