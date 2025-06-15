// arquivos depressaço
#include <stdio.h>
#include <stdlib.h>

struct person{
 int id; char fname[20]; char lname[20];
};

int main(){
    
    FILE * arq;
    char str [100];
    arq = fopen ("arquivo.txt" , "r");
    if (arq == NULL){
    printf ("Erro de abertura");
    return -1;
    }
    while (feof(arq)==0){
    fscanf(arq,"%s", str);
    printf("%s ",str);
    }
    fclose (arq);

    
    FILE *outfile;
    // open file for writing
    outfile = fopen ("person.dat", "w");
    if (outfile == NULL) {
    fprintf(stderr, "\nError opend file\n");
    exit (1); }
    struct person input1 = {1, "rohan", "sharma"};
    struct person input2 = {2, "mahendra", "dhoni"};

        // write struct to file
    fwrite (&input1, sizeof(struct person), 1,outfile);
    fwrite (&input2, sizeof(struct person), 1,outfile);

    //ponteiro,tamanho de cada elemento,numero de elementos,arquivo
    
    if(fwrite != 0)
    printf("contents to file written successfully !\n");
    else
    printf("error writing file !\n");
    // close file
    fclose (outfile);

       
    FILE *aquivodento;
    struct person input;
    
    // Open person.dat for reading
    aquivodento = fopen ("person.dat", "r");
    if (arquivodento == NULL) {
    fprintf(stderr, "\nError opening file\n");
    exit (1); 
    }

    // read file contents till end of file
    while(fread(&input, sizeof(struct person),1, arquivodento)){
    printf("id = %d name = %s %s\n",input.id, input.fname, input.lname);
    }
    //ponteiro,tamanho de cada elemento,numero de elementos,arquivo 
    
    // close file
    fclose (arquivodento);



    return 0;
}