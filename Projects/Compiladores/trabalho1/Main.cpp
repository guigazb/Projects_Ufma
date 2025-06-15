#include <iostream>
#include <string>
using namespace std;

// Compiladores trabalho 01 - implementação AFD análise léxica em C++

int main() {

    string entrada = "";
    //std::cout << "Digite a entrada: ";
    //std::getline(std::cin, entrada);
    
    int i = 0;
    int estado = 0;
    char caractere_entrada;
    std::string Token = "";

    bool flag = true;
    bool erro = false;

    while (flag == true){

        std::cout << "\n O que deseja fazer?" << std::endl;
        std::cout << "1 - Ler a entrada" << std::endl;
        std::cout << "2 - Sair" << std::endl;
        int opcao;
        std::cin >> opcao;

        switch (opcao) {
            case 1:

                std::cout << "Digite a entrada: ";
                std::cin.ignore(); // Limpa o buffer
                std::getline(std::cin, entrada);
                
                while (estado == 0 || estado == 1 || estado == 2 || estado == 3 || estado == 4) {
        
                    if (i >= entrada.size()) {
            
                        // Fim da string, sair do loop
                        estado = 5;
                        break;
                    }
            
                    caractere_entrada = entrada[i];
                    Token += caractere_entrada;
                    i++;
            
                    switch (estado) {
                        case 0:
                            std::cout << "\n Estado 0" << std::endl;
                            std::cout << "Caractere lido: " << caractere_entrada << std::endl;
                            
                            if (isdigit(caractere_entrada)) {
                                std::cout << "Um Dígito foi lido" << std::endl;
                                estado = 2;
                            } else if (caractere_entrada == 32) {
                                std::cout << "Um espaço em branco foi lido" << std::endl;
                                estado = 0;
                            } else if (caractere_entrada == '+' || caractere_entrada == '-') {
                                std::cout << "Um Sinal foi lido" << std::endl;
                                estado = 1;
                            } else {
                                std::cout << "Um Caractere invalido foi lido" << std::endl;
                                estado = -1;
                                erro = true;
                            }
            
                            std::cout << "\n Token lido: " << Token << std::endl;
            
                            break;
            
                        case 1:
                            std::cout << "\n Estado 1" << std::endl;
                            std::cout << "Caractere lido: " << caractere_entrada << std::endl;

                            if (isdigit(caractere_entrada)) {
                                std::cout << "Um Dígito foi lido" << std::endl;
                                estado = 2;
                            } else {
                                std::cout << "Um Caractere invalido foi lido" << std::endl;
                                estado = -1;
                                erro = true;
                            }
                            std::cout << "\n Token lido: " << Token << std::endl;
            
                            break;
            
                        case 2:
                            std::cout << "\n Estado 2" << std::endl;
                            std::cout << "Caractere lido: " << caractere_entrada << std::endl;

                            if (isdigit(caractere_entrada)) {
                                std::cout << "Um Dígito foi lido" << std::endl;
                                estado = 2;
                            } else if (caractere_entrada == ',') {
                                std::cout << "Uma virgula foi lida" << std::endl;
                                estado = 3;
                            } else {
                                std::cout << "Um outro caractere foi lido" << std::endl;
                                estado = 5;
                                erro = true;
                            }
            
                            std::cout << "\n Token lido: " << Token << std::endl;
            
                            break;
            
                        case 3:
                            std::cout << "\n Estado 3" << std::endl;
                            std::cout << "Caractere lido: " << caractere_entrada << std::endl;

                            if (isdigit(caractere_entrada)) {
                                std::cout << "Um Dígito foi lido" << std::endl;
                                estado = 4;
                            } else {
                                std::cout << "Um Caractere invalido foi lido" << std::endl;
                                estado = -1;
                                erro = true;
                            }
            
                            std::cout << "\n Token lido: " << Token << std::endl;
            
                            break;
            
                        case 4:
                            std::cout << "\n Estado 4" << std::endl;
                            std::cout << "Caractere lido: " << caractere_entrada << std::endl;

                            if (isdigit(caractere_entrada)) {
                                std::cout << "Um Dígito foi lido" << std::endl;
                                estado = 4;
                            } else {
                                std::cout << "Um outro caractere foi lido" << std::endl;
                                estado = 5;
                            }
            
                            std::cout << "\n Token lido: " << Token << std::endl;
            
                            break;
                    }
                } 

                if (estado == 5 && erro == false) {
                    std::cout << "\n Entrada aceita" << std::endl;
                    estado = 0;
                    i = 0;
                    
                } else {
                    std::cout << "\n Erro de leitura" << std::endl;
                }
            
                std::cout << "\n Token final: " << Token << std::endl;
                Token = "";
            
                break;
            case 2:
                std::cout << "Saindo..." << std::endl;
                flag = false;
                break;
            default:
                std::cout << "Opção inválida. Tente novamente." << std::endl;
        }

           
    }
    
    return 0;
}