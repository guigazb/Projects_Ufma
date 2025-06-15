from collections import defaultdict

#função que calcula o fecho epsilon de um estado
def calcular_fecho_epsilon(transicoes, estados):
    fecho_epsilon = {estado: set() for estado in estados}
    
    for estado in estados:
        pilha = [estado]
        fecho_epsilon[estado].add(estado)
        
        while pilha:
            atual = pilha.pop()
            for proximo in transicoes.get((atual, 'ε'), []):
                if proximo not in fecho_epsilon[estado]:
                    fecho_epsilon[estado].add(proximo)
                    pilha.append(proximo)
    
    return fecho_epsilon

#função que transforma um AFE em um AFN
def transformar_afe_para_afn(estados, estado_inicial, transicoes, estados_finais):
    alfabeto = set()
    for (estado, simbolo), _ in transicoes.items():
        if simbolo != 'ε':
            alfabeto.add(simbolo)
    
    fecho_epsilon = calcular_fecho_epsilon(transicoes, estados)
    
    afn_transicoes = defaultdict(list)
    
    for estado in estados:
        for simbolo in alfabeto:
            destinos = set()
            for estado_epsilon in fecho_epsilon[estado]:
                destinos.update(transicoes.get((estado_epsilon, simbolo), []))
            for destino in destinos:
                afn_transicoes[(estado, simbolo)].append(destino)
    
    afn_estados_finais = set()
    for estado in estados:
        if fecho_epsilon[estado].intersection(estados_finais):
            afn_estados_finais.add(estado)
    
    return estados, estado_inicial, afn_transicoes, afn_estados_finais

#função que exibe a tabela de transição
def exibir_tabela_transicao(estados, alfabeto, transicoes):

    # Determine o comprimento máximo para formatação
    max_estado_len = max(len(str(estado)) for estado in estados)
    max_destino_len = 10  # Valor padrão para largura da coluna de destinos
    
    # Criar cabeçalho da tabela
    header = f"{'Estado':<{max_estado_len}} | "
    header += " | ".join(f"{simbolo:^{max_destino_len}}" for simbolo in sorted(alfabeto))
    print("\nTabela de Transição:")
    print("-" * len(header))
    print(header)
    print("-" * len(header))
    
    # Criar linhas da tabela
    for estado in sorted(estados):
        row = f"{estado:<{max_estado_len}} | "
        for simbolo in sorted(alfabeto):
            destinos = transicoes.get((estado, simbolo), [])
            destinos_str = ",".join(sorted(destinos)) if destinos else "-"
            row += f"{destinos_str:^{max_destino_len}} | "
        print(row.rstrip(" |"))
    
    print("-" * len(header))

def main():

    estados = [estado.strip() for estado in input("Informe os estados do autômato: ").split(',')]
    estado_inicial = input("Informe o estado inicial: ").strip()
    
    print("Informe a função programa:")
    transicoes = defaultdict(list)
    alfabeto = set()
    
    while True:
        entrada = input().strip()
        if not entrada:  # linha em branco encerra o input
            break
        
        # aceita transições das duas formas: "0a1" ou "01"
        if len(entrada) >= 3:  # "0a1"
            origem = entrada[0]
            simbolo = entrada[1]
            destino = entrada[2]
            transicoes[(origem, simbolo)].append(destino)
            alfabeto.add(simbolo)
        elif len(entrada) == 2:  #  "01"
            origem = entrada[0]
            destino = entrada[1]
            transicoes[(origem, 'ε')].append(destino)
    
    estados_finais = [estado.strip() for estado in input("Informe os estados finais: ").split(',')]
    
    # Transformar AFE em AFN
    afn_estados, afn_estado_inicial, afn_transicoes, afn_estados_finais = transformar_afe_para_afn(
        estados, estado_inicial, transicoes, estados_finais
    )
    
   
    print("\nAFN resultante:")
    print(f"Estados: {', '.join(afn_estados)}")
    print(f"Estado inicial: {afn_estado_inicial}")
    
 
    exibir_tabela_transicao(afn_estados, alfabeto, afn_transicoes)
    
  
    print("\nTransições:")
    transicoesOrdenadas = sorted(afn_transicoes.items())
    for (estado, simbolo), destinos in transicoesOrdenadas:
        if destinos:
            for destino in sorted(destinos):
                print(f"{estado}{simbolo}{destino}")
    
    print(f"\nEstados finais: {', '.join(sorted(afn_estados_finais))}")

    print(f" Membros da equipe: \n 1 - Guilherme Barrio Nascimento \n 2 - Mayra Safira Costa Gomes \n 3 - Edson Pinho Rabelo \n 4 - Nicolas Alexander \n")

if __name__ == "__main__":
    main()