/*-----------------------------------------------------------------------
DICT.h
arquivo com as especificações para o TAD dicionario/mapa em C 
-------------------------------------------------------------------------
Autor: Guilherme Barrio Nascimento
maio/2024
------------------------------------------------------------------------*/
#ifndef DICT_h
#define DICT_h
#include "dict.c"

// definições locais

typedef struct dictnode Dictnode;

typedef struct dict Dict;

//especificações para o tad dicionario/mapa

Dict *mapCreate (void); 
/* Construir uma nova dicionario vazia
Pre-condição: (chamar a função mapcreate)
Pos-condição: retorna um ponteiro para um dicionario vazio */


int mapInsert(Dict* dicionario, void* data,void* key);
/* insere data e key como ultimo elemento do dicionário
Pre-condição: (dict foi criada por mapCreate) && (data != NULL)
Pos-condição: retorna se foi possivel inserir o item ou não */


int mapNumNodes(Dict* dicionario);
/* retorna o numero de itens no dicionário
Pre-condição: (dict foi criada por mapCreate)
Pos-condição: retorna o numero de elementos na dicionario, código de erro se a dicionario não existir */


void* mapQuery(Dict* dicionario, void*key,int(*cmp)(void*,void*));
/* retorna se o item especificado está na dicionario
Pre-condição: (dict foi criada por mapCreate)
Pos-condição: retorna true se o item for encontrado */

void* mapRemove(Dict* dicionario,void* key,int(*cmp)(void*,void*));
/* remove o elemento especificado
Pre-condição: (dict foi criada por mapCreate)
Pos-condição: o elemento especificado foi removido */

void* mapGetfirst(Dict* dicionario);
/* retorna o elemento inicial do dicionario
Pre-condição: (dict foi criada por mapCreate)
Pos-condição: retorna o current da dicionario */

void* mapGetnext(Dict* dicionario);
/* retorna o elemento current da dicionario
Pre-condição: (dict foi criada por mapCreate)
Pos-condição: retorna o current da dicionario */

int mapDestroy (Dict *dicionario);
/* destroi a dicionario
Pre-condição: (dict foi criada por mapCreate) && (não existem itens no dicionario)
Pos-condição: a dicionario foi destruida */


#endif /* __DICT_H */