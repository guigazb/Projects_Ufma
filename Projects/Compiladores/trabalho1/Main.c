#include <stdio.h>
#include <stdlib.h>
#include <string.h>

// Compiladores trabalho 01 - implementação AFD análise léxica

int main()
{
    char * entrada = (char *)malloc(100 * sizeof(char));
    printf("Digite a entrada: ");
    fgets(entrada, 100, stdin);
    //entrada[strcspn(entrada, "\n")] = 0; // Remove o caractere de nova linha
    
    int i = 0;
    int estado = 0;
    char caractere_entrada = "";
    char Token[100] = "";

    while (estado == 0 || estado == 1 || estado == 2 || estado == 3 || estado == 4){

        caractere_entrada = (char*) entrada[i];
        strcat(Token, caractere_entrada);
        i++;

        switch (estado){
            
            case 0:
                printf("Estado 0 \n");
                printf("Caractere lido: %c\n", caractere_entrada);
                switch (caractere_entrada){

                    
                    
                    case 0 || 1 || 2 || 3 || 4 || 5 || 6 || 7 || 8 || 9:
                        printf("Um Dígito foi lido \n");
                        estado = 2;
                        break;
                    case '+':
                        printf("Um Sinal foi lido \n");
                        estado = 1;
                        break;
                    case '-':
                        printf("Um Sinal foi lido \n");
                        estado = 1;
                        break;
                    default:
                        printf("Um Caractere invalido foi lido \n");
                        estado = -1;
                        break;
                }
                break;

            case 1:
                printf("Estado 1\n");
                switch (caractere_entrada){
                    case 0 || 1 || 2 || 3 || 4 || 5 || 6 || 7 || 8 || 9:
                        printf("Um Dígito foi lido \n");
                        estado = 2;
                        break;
                    default:
                        printf("Um Caractere invalido foi lido \n");
                        estado = -1;
                        break;
                    }
                break;

            case 2:
                printf("Estado 2\n");
                switch (caractere_entrada){
                    case 0 || 1 || 2 || 3 || 4 || 5 || 6 || 7 || 8 || 9:
                        printf("Um Dígito foi lido \n");
                        estado = 2;
                        break;
                    case ',':
                        printf("Uma virgua=la foi lido \n");
                        estado = 3;
                        break;
                    default:
                        printf("Um outro caractere foi lido \n");
                        estado = 5;
                        break;
                }
                break;

            case 3:
                printf("Estado 3 \n");
                switch (caractere_entrada){
                    case 0 || 1 || 2 || 3 || 4 || 5 || 6 || 7 || 8 || 9:
                        printf("Um Dígito foi lido \n");
                        estado = 4;
                        break;
                    default: 
                        printf("Um Caractere invalido foi lido \n");
                        estado = -1;
                        break; 
                }
                break;

            case 4:
                printf("Estado 4 \n");
                switch (caractere_entrada){
                    case 0 || 1 || 2 || 3 || 4 || 5 || 6 || 7 || 8 || 9:
                        printf("Um Dígito foi lido \n");
                        estado = 4;
                        break;
                    default:
                        printf("Um outro caractere foi lido \n");
                        estado = 5;
                        break;
                }
                break;
        }
    };

    printf("Token lido: %s\n", Token);

    if (estado == 5){ 
        printf("Entrada aceita \n");
    } else {
        printf("Erro de leitura \n");
    }

    return 0;
    free(entrada);
}
