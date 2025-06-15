#include <stdio.h>

int main(){

    int matriz[3][3] = {{1,2,3},{4,5,6},{7,8,9}};
    int i,j;
    
    printf("imprimindo a primeira linha da matriz: %d %d %d \n", matriz[0][0], matriz[0][1], matriz[0][2]);
    
    printf("imprimindo a matriz toda: \n");
    for(i = 0; i< 3;i++){
     for( j= 0; j < 3; j++){
     printf("%d ",matriz[i][j]);
     }
     printf("\n");
    }

    return 0;
}