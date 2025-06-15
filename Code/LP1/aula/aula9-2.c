#include <stdio.h>
int n;
int main(){

    int matriz[3][3] = {1,2,3,4,5,6,7,8,9};
    void funcaoprint(int mat[][3]);

    funcaoprint(matriz);

 return 0;
}

void funcaoprint(int mat[][3]){
    int i,j;

        for(i = 0; i<3 ; i++){
            for(j = 0; j<3 ; j++){
                printf("%d",mat[i][j]);
            }
            printf("\n");
        }
}