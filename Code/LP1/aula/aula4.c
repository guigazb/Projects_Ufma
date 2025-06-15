// if else 
#include <stdio.h>

int main (){
     
    int idade = 0;
    printf("diga a sua idade: \n");
    scanf("%d",&idade);
    if (idade < 18 && idade > 12){
        printf("tu eh kid");
    }
    else if (idade <= 12)
    {
        printf("tu n devia nem estar usando pc!");
    }
    else if (idade >= 18){
        printf("brabo");
    }
    
    return 0;
}
