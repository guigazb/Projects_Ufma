// arquivos pt3
#include <stdio.h>
#include <stdlib.h>

int main(){

    void copiarconteudo(FILE *file1,FILE *file2);

    FILE *arq1;
    arq1 = fopen("c:\\Users\\Pichau\\Desktop\\copiar.txt","w");
    
    fprintf(arq1,"umineko \n");
    fprintf(arq1,"higurashi \n");

    fclose(arq1);

    FILE *arq2;
    arq2 = fopen("c:\\Users\\Pichau\\Desktop\\colar.txt","w");

    fclose(arq2);
    
   //criando os arquivos

    arq1 = fopen("c:\\Users\\Pichau\\Desktop\\copiar.txt","r");

    if(arq1 == NULL){
    printf("o arquivo n existe. \n");
    return 0;
   }

   arq2 = fopen("c:\\Users\\Pichau\\Desktop\\colar.txt","w");
 //abrindo o arquivo 1 para leitura e o 2 para escrita
   
   copiarconteudo(arq1,arq2);

    fclose(arq1);
    fclose(arq2);

    return 0;
}

void copiarconteudo(FILE *file1,FILE *file2){
    char leitura[1000];

    while(fgets(leitura,1000,file1) != NULL){
        fputs(leitura,file2);
    }
    //enquanto a leitura retornar texto, ela continua e copia o texto lido para o novo arquivo
}