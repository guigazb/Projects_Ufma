#include <stdio.h>
#include <string.h>

int main(){
   /* string minusculas para maiusculas
   void maiuscula(char *str);

   char caracteres[100];
   printf("digite uma string: ");
   fgets(caracteres,sizeof(caracteres),stdin);

   maiuscula(caracteres);

   printf("em maiusculas: %s",caracteres);*/

   // confere se a string é um palindromo
 
   char palavra[100],copia[100];
   printf("digite uma palavra: ");
   fgets(palavra ,sizeof(palavra),stdin);

   strcpy(copia, palavra);
   strrev(copia);
   
   printf("%s \n",copia);
   printf("%s \n",palavra);

   printf("%d \n",strcmp(palavra, copia));

   if (strcmp(palavra, copia) == 0){
    printf(" e um palindromo \n");
   }else{
    printf(" nao e palindromo \n");
   } 

   return 0;

}

void maiuscula(char *str){

    while(*str != '\0'){
        if(*str >= 'a' && *str <= 'z'){
            *str -= 32;
        }
        str++;
    }
}