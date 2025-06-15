//arquivos pt2
#include <stdio.h>
#include <stdlib.h>

int main(){

    FILE *arquivo;
    arquivo = fopen("c:\\Users\\Pichau\\Desktop\\texto.txt","w");
    
    fprintf(arquivo,"amabutukan \n");
    fprintf(arquivo,"amabutublow \n");
    fprintf(arquivo,"nigga \n");

    fclose(arquivo);
 //criando o arquivo

    FILE *arq;
    arq = fopen("c:\\Users\\Pichau\\Desktop\\texto.txt","r");

    if(arquivo == NULL){
    printf("o arquivo n existe. \n");
    return 0;
   }

   char frase[100];

   while (fgets(frase , 100 , arq) != NULL){
     printf("%s",frase);
     
   }

   fclose(arq);
 //lendo e copiando o texto do arquivo
 
   arq = fopen("c:\\Users\\Pichau\\Desktop\\texto.txt","a");

   fprintf(arq,"danm bro \n");

   char caracteres[] = "tome lebron james \n";
   fputs(caracteres,arq);

   char numerous[] = "3423423";
   fputs(numerous,arq);

   fclose(arq);
   // adicionando texto ao arquivo

 return 0;
}