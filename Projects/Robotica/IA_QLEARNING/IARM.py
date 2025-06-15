import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import random


#antes de executar o código, é recomendável ler o aquivo pdf anexado, já que contém explicações importantes sobre a execução


# Definição do mapa de exemplo (matriz binária)
linhas = 9
colunas = 14
mapa = np.zeros((linhas, colunas))

# Solicitar as coordenadas de início e fim do usuário
inicio = (int(input("Digite a coordenada de inicio X: ")), int(input("Digite a coordenada de inicio Y: ")))
objetivo = (int(input("Digite a coordenada final X: ")), int(input("Digite a coordenada final Y: ")))

# Q-Learning parâmetros
alpha = 0.1  # Taxa de aprendizado
gamma = 0.9  # Fator de desconto
epsilon = 0.1  # Probabilidade de exploração inicial
num_episodios = 100000  # Número de episódios de treinamento

# Tabela Q inicializada com zeros
Q = {}

# Ações possíveis: cima, baixo, esquerda, direita
acoes = ['cima', 'baixo', 'esquerda', 'direita']

# Caminho percorrido pelo robô
caminho_robo = []

# Melhor caminho encontrado
melhor_caminho = []
comprimento_melhorcaminho = float('inf')

def escolha_acao(estado):
    if random.uniform(0, 1) < epsilon:
        return random.choice(acoes)
    else:
        return max(acoes, key=lambda a: Q.get((estado, a), 0))

def atualiza_Q(estado, acao, ganho, next_estado):
    valor_antigo = Q.get((estado, acao), 0)
    prox_max = max(Q.get((next_estado, a), 0) for a in acoes)
    novo_valor = (1 - alpha) * valor_antigo + alpha * (ganho + gamma * prox_max)
    Q[(estado, acao)] = novo_valor

def movimento_robo(posicao, acao):
    x, y = posicao
    if acao == 'cima' and x > 0:
        x -= 1
    elif acao == 'baixo' and x < linhas - 1:
        x += 1
    elif acao == 'esquerda' and y > 0:
        y -= 1
    elif acao == 'direita' and y < colunas - 1:
        y += 1
    return (x, y)

def calculate_ganho(robo_posicao, obstaculo_posicao, destino_posicao):
    if robo_posicao == destino_posicao:
        return 100  # Grande recompensa por alcançar o destino
    elif robo_posicao == obstaculo_posicao:
        return -100  # Grande penalidade por colidir com o obstáculo
    else:
        return -1  # Pequena penalidade por cada movimento

# Loop de treinamento (episódios)
def treinamento(num_episodios):
    global robo_posicao, obstaculo_posicao, caminho_robo, melhor_caminho, comprimento_melhorcaminho
    for episode in range(num_episodios):
        
        # Reiniciar o estado inicial do robô e do obstáculo
        robo_posicao = inicio
        obstaculo_posicao = (linhas - 1, random.randint(0, colunas - 1))
        caminho_robo = [robo_posicao]
        
        while robo_posicao != objetivo:
            
            # Escolher uma ação
            acao = escolha_acao(robo_posicao)
            
            # Executar a ação e observar o próximo estado e a recompensa
            next_robo_posicao = movimento_robo(robo_posicao, acao)
            
            # Atualizar a posição do obstáculo
            obstaculo_x, obstaculo_y = obstaculo_posicao
            if obstaculo_x > 0:
                obstaculo_x -= 1
            else:
                obstaculo_x = linhas - 1
                obstaculo_y = random.randint(0, colunas - 1)
            obstaculo_posicao = (obstaculo_x, obstaculo_y)
            
            ganho = calculate_ganho(next_robo_posicao, obstaculo_posicao, objetivo)
            
            # Atualizar a tabela Q
            atualiza_Q(robo_posicao, acao, ganho, next_robo_posicao)
            
            # Atualizar o estado atual do robô
            robo_posicao = next_robo_posicao
            
            # Registrar o caminho do robô
            caminho_robo.append(robo_posicao)
            
            # Verificar se o robô alcançou o objetivo
            if robo_posicao == objetivo:
                if len(caminho_robo) < comprimento_melhorcaminho:
                    comprimento_melhorcaminho = len(caminho_robo)
                    melhor_caminho = caminho_robo.copy()
                break

# Função de animação
def animate_melhor_caminho(frame):
    global melhor_caminho, obstaculo_posicao

    # Atualizar a posição do obstáculo
    obstaculo_x, obstaculo_y = obstaculo_posicao
    if obstaculo_x > 0:
        obstaculo_x -= 1
    else:
        obstaculo_x = linhas - 1
        obstaculo_y = random.randint(0, colunas - 1)
    obstaculo_posicao = (obstaculo_x, obstaculo_y)

    # Atualizar o mapa visual
    mapa.fill(0)
    mapa[objetivo] = 0.5  # Destino
    mapa[obstaculo_posicao] = 1.0  # Obstáculo
    for pos in melhor_caminho[:frame + 1]:
        mapa[pos] = 0.25  # Caminho percorrido
    if frame < len(melhor_caminho):
        mapa[melhor_caminho[frame]] = 0.75  # Robô
    mat.set_data(mapa)
    return [mat]

def pos_valida(pos):
    return 0 <= pos[0] < linhas and 0 <= pos[1] < colunas and mapa[pos] == 0

if pos_valida(inicio) == True and pos_valida(objetivo) == True:
    
    # Treinamento do modelo
    treinamento(num_episodios)

    # Configuração da animação
    fig, ax = plt.subplots()
    mat = ax.matshow(mapa, cmap = "coolwarm")

    ani = animation.FuncAnimation(fig, animate_melhor_caminho, frames=len(melhor_caminho), interval=500, blit=True)

    # Plotar pontos de início e fim
    plt.plot(objetivo[1], objetivo[0], marker = 's', color = 'green', markersize = 10, label = 'objetivo')
    plt.plot(inicio[1], inicio[0], marker = 's', color = 'yellow', markersize = 10, label = 'inicio')
    plt.legend()

    plt.show()

    for i in range(5):
        alpha = random.randint(0,10) / 10  
        gamma = random.randint(0,10) / 10  
        epsilon = random.randint(0,10) / 10
        treinamento(num_episodios)

        

        fig, ax = plt.subplots()
        mat = ax.matshow(mapa, cmap = "coolwarm")

        ani = animation.FuncAnimation(fig, animate_melhor_caminho, frames=len(melhor_caminho), interval = 500, blit=True)
        
        plt.plot(objetivo[1], objetivo[0], marker='s', color='green', markersize=10, label='objetivo')
        plt.plot(inicio[1], inicio[0], marker='s', color='yellow', markersize=10, label='inicio')
        plt.legend()
        
        plt.show()
else:
    print(f'posição inválida! ')
