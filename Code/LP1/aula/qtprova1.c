#include <stdio.h>

int main(){
 void mesclar_arq(char *arq1,char *arq2,char *arq3);

 char arquivo1[100],arquivo2[100],arquivo3[100];
 printf("informe o nome do arquivo1: ");
 scanf("%s",arquivo1);
 printf("informe o nome do arquivo2: ");
 scanf("%s",arquivo2);
 printf("informe o nome do arquivo3: ");
 scanf("%s",arquivo3);

 mesclar_arq(arquivo1,arquivo2,arquivo3);


 return 0;
}

void mesclar_arq(char *arq1,char *arq2,char *arq3){
    FILE *aq1 = fopen(arq1,"r");
    FILE *aq2 = fopen(arq2,"r");
    FILE *aq3 = fopen(arq3,"w");

    if(aq1 == NULL || aq2 == NULL || aq3 == NULL){
        printf("erro ao abrir os arquivos. \n");
        return ;
    }

    char leitura;
    while((leitura = fgetc(aq1))!= EOF){
        fputc(leitura,aq3);
    }
    while((leitura = fgetc(aq2))!= EOF){
        fputc(leitura,aq3);
    }

    printf("arquivos unidos em %s",arq3);
    fclose(aq1);
    fclose(aq2);
    fclose(aq3);

}