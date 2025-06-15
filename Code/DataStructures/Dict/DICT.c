/*-----------------------------------------------------------------------
DICT.c
arquivo com a implementação para o TAD dicionário/mapa em C 
-------------------------------------------------------------------------
Autor: Guilherme Barrio Nascimento
maio/2024
------------------------------------------------------------------------*/

#ifndef _DICT_C_
#define _DICT_C_

/* Implementação do TAD dicionario */
#include "dict.h" /* inclui a especificacao do TAD */
#include <stdio.h>
#include <stdlib.h>
#include <string.h>

#define true  1
#define false 0

typedef struct dictnode{
    void* data;
    void* key;
    struct Dictnode *next;
}Dictnode;

typedef struct dict{
    Dictnode* first;
    Dictnode* cur;
}Dict;

Dict *mapCreate (void){
    Dict* dicionario = (Dict*)malloc(sizeof(Dict));
    if(dicionario != NULL){
        dicionario->first = NULL;
        return dicionario;
    }
    return NULL;
}


void *mapGetfirst(Dict *dicionario){ // necessário para getnext funcionar
    if(dicionario != NULL){
        if(dicionario->first != NULL){
            dicionario->cur = dicionario->first;
            return dicionario->cur->data;
        }
    }
    return NULL;
}

int mapInsert(Dict* dicionario,void* data,void* key){
    if(dicionario != NULL){
        Dictnode* newnode = (Dictnode*)malloc(sizeof(Dictnode));
        Dictnode* last;
        if(newnode != NULL){
            newnode->data = data;
            newnode->next = NULL;
            newnode->key = key;
            if(dicionario->first == NULL){
                dicionario->first = newnode;
            }else{
                last = dicionario->first;
                while(last->next != NULL){
                    last = last->next;
                }
                last->next = newnode;
            }
            return true;
        }
    }
    return false;
}


int mapNumNodes(Dict* dicionario){
    if(dicionario != NULL){
        Dictnode* last;
        int num;
        if(dicionario->first != NULL){
            last = dicionario->first;
            num++;
            while(last->next != NULL){
                num++;
                last = last->next;
            }  
         return num;
    }
    }
    return -1;
}



void* mapQuery(Dict* dicionario, void*key,int(*cmp)(void*,void*)){
    if(dicionario != NULL && key != NULL){
        if(dicionario->first != NULL){
            Dictnode* atual = dicionario->first;
            int stat = cmp(atual->key,key);
            while(stat != true && atual->next != NULL){
                atual = atual->next;
                stat = cmp(atual->key,key);
            }
            if(stat == true){
                return atual->data;
            }
        }
    }
    return NULL;
}

void* mapRemove(Dict* dicionario,void* key,int(*cmp)(void*,void*)){
    if(dicionario != NULL && key != NULL){
        if(dicionario->first != NULL){
        Dictnode* del = dicionario->first;
        Dictnode* beforedel;
        void* salvo;
        int stat = cmp(key,del->key);
        while(stat != true && del->next != NULL){
            beforedel = del;
            del = del->next;
            stat = cmp(key,del->key);
        }
        if(stat == true){
            salvo = del->data;
            if(beforedel == NULL){
                dicionario->first = del->next;
            }else{
                beforedel->next = del->next;
            }
            free(del);
            return salvo; 
        }
        
       }
    }
    return NULL;
}

void* mapGetnext(Dict* dicionario){
    if(dicionario != NULL){
        if(dicionario->cur != NULL){
            dicionario->cur = dicionario->cur->next;
            return dicionario->cur->data;
        }
    }
    return NULL;
}

int mapDestroy (Dict *dicionario){
    if(dicionario != NULL){
        if(dicionario->first == NULL){
            free(dicionario);
            return true;
        }
    }
    return false;
}

#endif /* __DICT_C */