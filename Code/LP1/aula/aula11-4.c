#include <stdio.h>
#include <stdlib.h>

int main(){
   
   void copiarstr(char *copiardaqui,char *colaraqui);

   char stra[] = "pao com mortadela";
   char strb[20];

   copiarstr(stra,strb);

   printf("%s \n",strb);


 return 0;
}

void copiarstr(char *copiardaqui,char *colaraqui){
   
   while(*copiardaqui != '\0'){
      *colaraqui = *copiardaqui;
      ++copiardaqui;
      ++colaraqui;
   }
   
   *colaraqui = '\0';

}
