#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include "dll.h"
#include "dll.c"
#define true 1
#define false 0

typedef struct {
    char nome[30];
    int temporadas;
    float nota;
}Serie;


void listarColecao(Dllist *lista){ 
    int ind = 0;
    int num = dllNumNodes(lista);
    Serie *var = (Serie*) dllGetfirst(lista);
    if(lista != NULL && num > 0){
        while(var != NULL){
            printf("item %d: \n",ind + 1);
            printf("nome: %s ,temporadas: %d ,nota: %.2f \n", var->nome, var->temporadas, var->nota);
            var = (Serie*)dllGetnext(lista);
            ind++;
        }
    }else if(num <= 0){
            printf("sem itens para imprimir \n");
        }
}  

int cmp(void* a, void* b){
    Serie *pa = (Serie *) a;
    Serie *pb = (Serie*) b;
    if(pa != NULL && pb != NULL){
        if(strcmp(pa->nome,pb->nome) == 0 && pa->temporadas == pb->temporadas && pa->nota == pb->nota){
           return true;
        }
        return false;
    }
    return -1;
}

int listaClean(Dllist* lista){ 
    if(lista != NULL){
        Serie* delete;
        while(delete != NULL){
            delete = (Serie*) dllRemovelast(lista);
        } 
        return true;
    }
    return false;
}

int main(){
    
    int flag = true;
    void listarColecao(Dllist *lista);
    int cmp(void* a, void* b);
    int listaClean(Dllist* lista);

    Dllist *lista = NULL;

    while(flag == true){
        
        printf("1 - Criar colecao \n");
        printf("2 - Inserir um elemento \n");
        printf("3 - Remover um elemento \n");
        printf("4 - Consultar um elemento \n");
        printf("5 - Listar os elementos \n");
        printf("6 - Destruir a colecao \n");
        printf("7 - Esvaziar a colecao \n");
        printf("0 - Sair \n");
        printf("deseja realizar qual operacao? \n ");
        
        int opcao;
        scanf("%d", &opcao);
        switch(opcao){
            case 1:{
                if(lista != NULL){
                    printf("existe uma colecao criada \n");
                    printf("destrua a colecao anterior antes de criar uma nova \n");
                    printf("\n");
                    break;
                }
                lista = dllCreate();
                if(lista != NULL){
                    printf("Colecao criada com sucesso! \n");
                    printf("\n");
                    break;
                }else{
                    printf("a coleção não foi criada, tente novamente... \n");
                    printf("\n");
                    break;
                }
            }
            case 2:{
                Serie *item = (Serie *) malloc(sizeof(Serie));
                if(lista != NULL && item != NULL){
                    printf("Nome: ");
                    scanf(" %[^\n]s", item->nome);
                    printf("temporadas: ");
                    scanf("%d", &item->temporadas);
                    printf("nota: ");
                    scanf("%f", &item->nota);
                    if(dllInsertAsLast(lista,item) == true){ 
                        printf("Serie inserida com sucesso \n");
                        printf("\n");
                        break;
                    }else{
                        printf("Erro ao inserir Serie \n");
                        printf("\n");
                        break;
                    }
                }
                printf("erro de parametro \n");
                printf("crie uma colecao antes... \n");
                printf("\n");
                break;
            }
            case 3:{
                Serie *item = (Serie *) malloc(sizeof(Serie));
                if(lista != NULL && item != NULL){
                    printf("Nome: ");
                    scanf(" %[^\n]s", item->nome);
                    printf("temporadas: ");
                    scanf("%d", &item->temporadas);
                    printf("nota: ");
                    scanf("%f", &item->nota);
                    if(dllRemovespec(lista, item, cmp) != NULL){
                        printf("item removido com sucesso! \n");
                        printf("\n");
                        break;
                    }else{
                       printf("nao foi possivel remover o item \n");
                       printf("\n");
                       break; 
                    }
                }
                printf("erro de parametro \n");
                printf("crie uma colecao antes ou insira um item valido... \n");
                printf("\n");
                break;
            }
            case 4:{
                Serie *item = (Serie*) malloc(sizeof(Serie));
                if(lista != NULL && item != NULL){
                    printf("Nome: ");
                    scanf(" %[^\n]s", item->nome);
                    printf("temporadas: ");
                    scanf("%d", &item->temporadas);
                    printf("nota: ");
                    scanf("%f", &item->nota);

                    Serie *chave = (Serie*) dllQueryspec(lista, item, cmp); 
                    if(chave != NULL){
                        printf("Serie encontrada ! \n");
                        printf("Nome: %s, temporadas: %d, nota: %.2f \n", chave->nome, chave->temporadas, chave->nota);
                        printf("\n");
                        break;
                    }else{
                        printf("a Serie nao foi encontrada \n");
                        printf("\n");
                        break;
                    }  
                }
                printf("erro de parametro \n");
                printf("crie uma colecao antes ou insira um item valido... \n");
                printf("\n");
                break;
            }
            case 5:{ 
                if(lista != NULL){
                    printf("aqui estao os itens da colecao: \n");
                    listarColecao(lista);
                    printf("\n");
                    break;
                }
                printf("erro de parametro \n");
                printf("crie uma colecao antes ... \n");
                printf("\n");
                break;
            }
            case 6:{
                if(lista != NULL){
                    if(dllDestroy(lista) == true){
                        printf("Colecao destruida \n");
                        printf("\n");
                        lista = NULL;
                        break;
                    }else{
                        printf("A colecao nao foi destruida \n");
                        printf("esvazie a colecao antes de destruir \n");
                        printf("\n");
                        break;
                    }
                }
                printf("erro de parametro \n");
                printf("crie uma colecao antes ... \n");
                printf("\n");
                break;
            }
            case 7:{
                if(lista != NULL){
                    if(listaClean(lista) == true){ 
                       printf("A colecao foi esvaziada \n");
                       printf("\n");
                       break;
                    }else{
                      printf("A colecao nao foi esvaziada \n");
                      printf("\n");
                      break;
                    }
                }
                printf("erro de parametro \n");
                printf("crie uma colecao antes ... \n");
                printf("\n");
                break;
            }
            case 0:{
                printf("sistema encerrado com sucesso! \n");
                printf("\n");
                return 0;
            }
            default:{
                printf("opcao invalida \n");
                printf("\n");
                break;
            }
        }
    } flag = false;
}