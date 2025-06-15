#include <stdio.h>
#include <string.h>
#include "dict.h"
#define true 1
#define false 0

int cmp(void* a, void* b){
    char* pa  = (char*) a ;
    char* pb  = (char*) b;
    if(pa != NULL && pb != NULL){
        if(strcmp(pa,pb) == 0){
           return true;
        }
        return false;
    }
    return -1;
}

int main(){
    int cmp(void* a, void* b);

    Dict* dicionario = NULL;
    dicionario = (Dict*) mapCreate();
    mapInsert(dicionario,"lebron","jogador");
    printf("o item e: %s \n",(char*)mapGetfirst(dicionario));

    char item [30] = {};
    char chave[30] = {};
    printf("item: ");
    scanf("%s", item);
    printf("chave: ");
    scanf("%s", chave);
    if(mapInsert(dicionario,item,chave) == true){ 
        printf("item inserido com sucesso! \n");
        printf("\n");
                        
    }else{
        printf("Erro ao inserir item... \n");
        printf("\n");
                        
    }

     char* impresso = (char*) mapQuery(dicionario, chave, cmp); 
    if(impresso != NULL){
        printf("item encontrado ! \n");
        printf("item: %s, chave: %s \n", impresso, chave);
        printf("\n");
                        
    }else{
        printf("O item nao foi encontrado... \n");
        printf("\n");
                        
    }  

}