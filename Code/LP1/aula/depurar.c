#include <stdio.h>
#include<stdlib.h>
int i = 0;

int main(){
   
   void somanum();

   printf("%d",i);

   somanum();

   printf("%d",i);



    return 0;
}

void somanum(){
    
    i = i +1;

}