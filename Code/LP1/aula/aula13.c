//arquivos
#include <stdio.h>
#include <stdlib.h>

int main(){

  FILE *file;
  file = fopen("c:\\Users\\Pichau\\Desktop\\teste.txt","w");

  fprintf(file,"10 20 30");

  fclose(file);
  
  FILE *arquivo;
  arquivo = fopen("c:\\Users\\Pichau\\Desktop\\teste.txt","r");
  //como segundo parametros podemos passar r(read),w(write),a(append)
   
   if(arquivo == NULL){
    printf("o arquivo n existe. \n");
    return 0;
   }
 
  int x,y,z;

  fscanf(arquivo,"%d %d %d",&x,&y,&z);
  
  
  printf("%d \n",x);
  printf("%d \n",y);
  printf("%d \n",z); 

  fclose(arquivo);

  // cria o arquivo no mesmo diretório do código arquivo = fopen("teste.txt","w");

  return 0;  
}