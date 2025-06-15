def analisador_lexico_tipo2(entrada):
    # Definição dos estados
    INICIAL = 0      # Estado inicial
    INTEIRO = 1      # Encontrou dígito(s)
    VIRGULA = 2      # Encontrou vírgula após dígito
    DECIMAL = 3      # Encontrou dígito(s) após vírgula
    
    # Estados finais
    estados_finais = [INTEIRO, DECIMAL]
    
    # Variável global para o estado corrente (conforme a implementação Tipo 2)
    estado_corrente = INICIAL
    
    # Índice atual na string de entrada
    i = 0
    
    # Lexema reconhecido
    lexema = ""
    
    # Laço principal do AFD
    while i < len(entrada):
        # Caractere atual
        c = entrada[i]
        
        # Switch para controlar a transição de estados
        if estado_corrente == INICIAL:
            if c.isdigit():
                lexema += c
                estado_corrente = INTEIRO
            else:
                # Ignoramos caracteres que não iniciam um número
                i += 1
                continue
                
        elif estado_corrente == INTEIRO:
            if c.isdigit():
                lexema += c
            elif c == ',':
                lexema += c
                estado_corrente = VIRGULA
            elif c == '.':
                # Encontrou ponto, então retorna apenas o inteiro
                print(f"Token reconhecido: {lexema}")
                return lexema
            else:
                # Qualquer outro caractere termina o reconhecimento
                print(f"Token reconhecido: {lexema}")
                return lexema
                
        elif estado_corrente == VIRGULA:
            if c.isdigit():
                lexema += c
                estado_corrente = DECIMAL
            else:
                # Formato inválido após a vírgula
                print("Erro: formato inválido após a vírgula")
                return None
                
        elif estado_corrente == DECIMAL:
            if c.isdigit():
                lexema += c
            else:
                # Qualquer outro caractere termina o reconhecimento
                print(f"Token reconhecido: {lexema}")
                return lexema
        
        # Avança para o próximo caractere
        i += 1
        
        # Se chegamos ao final da entrada e estamos em um estado final, aceitamos
        if i >= len(entrada) and estado_corrente in estados_finais:
            print(f"Token reconhecido: {lexema}")
            return lexema
    
    # Se chegamos aqui e não estamos em um estado final, rejeitamos
    if estado_corrente not in estados_finais:
        print("Nenhum token foi reconhecido")
        return None
    else:
        print(f"Token reconhecido: {lexema}")
        return lexema


def analisador_lexico_tipo2_c(entrada):
    """
    Versão em pseudocódigo C da implementação Tipo 2
    """
    print("Versão equivalente em C:")
    codigo_c = """
    #include <stdio.h>
    #include <string.h>
    #include <ctype.h>

    char* analisador_lexico(const char* entrada) {
        // Definição dos estados
        enum Estados {
            INICIAL = 0,      // Estado inicial
            INTEIRO = 1,      // Encontrou dígito(s)
            VIRGULA = 2,      // Encontrou vírgula após dígito
            DECIMAL = 3       // Encontrou dígito(s) após vírgula
        };
        
        // Variável global para o estado corrente (conforme a implementação Tipo 2)
        int estado_corrente = INICIAL;
        
        // Índice atual na string de entrada
        int i = 0;
        
        // Lexema reconhecido
        char lexema[100] = "";
        int lexema_idx = 0;
        
        // Laço principal do AFD
        while (entrada[i] != '\\0') {
            // Caractere atual
            char c = entrada[i];
            
            // Switch para controlar a transição de estados
            switch (estado_corrente) {
                case INICIAL:
                    if (isdigit(c)) {
                        lexema[lexema_idx++] = c;
                        estado_corrente = INTEIRO;
                    } else {
                        // Ignoramos caracteres que não iniciam um número
                        i++;
                        continue;
                    }
                    break;
                    
                case INTEIRO:
                    if (isdigit(c)) {
                        lexema[lexema_idx++] = c;
                    } else if (c == ',') {
                        lexema[lexema_idx++] = c;
                        estado_corrente = VIRGULA;
                    } else if (c == '.') {
                        // Encontrou ponto, então retorna apenas o inteiro
                        lexema[lexema_idx] = '\\0';
                        printf("Token reconhecido: %s\\n", lexema);
                        return strdup(lexema);
                    } else {
                        // Qualquer outro caractere termina o reconhecimento
                        lexema[lexema_idx] = '\\0';
                        printf("Token reconhecido: %s\\n", lexema);
                        return strdup(lexema);
                    }
                    break;
                    
                case VIRGULA:
                    if (isdigit(c)) {
                        lexema[lexema_idx++] = c;
                        estado_corrente = DECIMAL;
                    } else {
                        // Formato inválido após a vírgula
                        printf("Erro: formato inválido após a vírgula\\n");
                        return NULL;
                    }
                    break;
                    
                case DECIMAL:
                    if (isdigit(c)) {
                        lexema[lexema_idx++] = c;
                    } else {
                        // Qualquer outro caractere termina o reconhecimento
                        lexema[lexema_idx] = '\\0';
                        printf("Token reconhecido: %s\\n", lexema);
                        return strdup(lexema);
                    }
                    break;
            }
            
            // Avança para o próximo caractere
            i++;
        }
        
        // Se chegamos ao final da entrada e estamos em um estado final, aceitamos
        lexema[lexema_idx] = '\\0';
        if (estado_corrente == INTEIRO || estado_corrente == DECIMAL) {
            printf("Token reconhecido: %s\\n", lexema);
            return strdup(lexema);
        } else {
            printf("Nenhum token foi reconhecido\\n");
            return NULL;
        }
    }

    int main() {
        const char* testes[] = {
            "var := 3,14",  // Deve reconhecer 3,14
            "var := 3.14",  // Deve reconhecer apenas 3
            "123,456",      // Deve reconhecer 123,456
            "123.",         // Deve reconhecer 123
            "45",           // Deve reconhecer 45
            "var := abc",   // Não deve reconhecer nenhum token
            ",123",         // Não deve reconhecer (não começa com dígito)
            "123,",         // Erro: termina com vírgula
            "0,005"         // Deve reconhecer 0,005
        };
        
        for (int i = 0; i < 9; i++) {
            printf("\\nEntrada: '%s'\\n", testes[i]);
            char* resultado = analisador_lexico(testes[i]);
            printf("Resultado: %s\\n", resultado ? resultado : "NULL");
            if (resultado) free(resultado);
        }
        
        return 0;
    }
    """
    print(codigo_c)
    return "Código C fornecido acima"


# Função de teste para a implementação em Python
def testar_analisador():
    testes = [
        "var := 3,14",     # Deve reconhecer 3,14
        "var := 3.14",     # Deve reconhecer apenas 3
        "123,456",         # Deve reconhecer 123,456
        "123.",            # Deve reconhecer 123
        "45",              # Deve reconhecer 45
        "var := abc",      # Não deve reconhecer nenhum token
        ",123",            # Não deve reconhecer (não começa com dígito)
        "123,",            # Erro: termina com vírgula
        "0,005"            # Deve reconhecer 0,005
    ]
    
    print("Testes da implementação Tipo 2 em Python:")
    for teste in testes:
        print(f"\nEntrada: '{teste}'")
        resultado = analisador_lexico_tipo2(teste)
        print(f"Resultado: {resultado}")
    
    # Mostrar o código em C
    print("\n" + "="*50 + "\n")
    analisador_lexico_tipo2_c("")


if __name__ == "__main__":
    testar_analisador()