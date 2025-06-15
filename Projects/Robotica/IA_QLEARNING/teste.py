import numpy as np
import matplotlib.pyplot as plt
import matplotlib.animation as animation
import random

# Definição do mapa de exemplo (matriz binária)
MAP_HEIGHT = 9
MAP_WIDTH = 14
mapa = np.zeros((MAP_HEIGHT, MAP_WIDTH))

# Solicitar as coordenadas de início e fim do usuário
start = (int(input("Digite a coordenada de inicio X: ")), int(input("Digite a coordenada de inicio Y: ")))
goal = (int(input("Digite a coordenada final X: ")), int(input("Digite a coordenada final Y: ")))

# Q-Learning parâmetros
alpha = 0.1  # Taxa de aprendizado
gamma = 0.9  # Fator de desconto
epsilon = 0.1  # Probabilidade de exploração inicial
num_episodes = 1000  # Número de episódios de treinamento

# Tabela Q inicializada com zeros
Q = {}

# Ações possíveis: cima, baixo, esquerda, direita
actions = ['up', 'down', 'left', 'right']

# Caminho percorrido pelo robô
robot_path = []

def choose_action(state):
    if random.uniform(0, 1) < epsilon:
        return random.choice(actions)
    else:
        return max(actions, key=lambda a: Q.get((state, a), 0))

def update_Q(state, action, reward, next_state):
    old_value = Q.get((state, action), 0)
    next_max = max(Q.get((next_state, a), 0) for a in actions)
    new_value = (1 - alpha) * old_value + alpha * (reward + gamma * next_max)
    Q[(state, action)] = new_value

def move_robot(position, action):
    x, y = position
    if action == 'up' and x > 0:
        x -= 1
    elif action == 'down' and x < MAP_HEIGHT - 1:
        x += 1
    elif action == 'left' and y > 0:
        y -= 1
    elif action == 'right' and y < MAP_WIDTH - 1:
        y += 1
    return (x, y)

def calculate_reward(robot_position, obstacle_position, destination_position):
    if robot_position == destination_position:
        return 100  # Grande recompensa por alcançar o destino
    elif robot_position == obstacle_position:
        return -100  # Grande penalidade por colidir com o obstáculo
    else:
        return -1  # Pequena penalidade por cada movimento

# Loop de treinamento (episódios)
def treinamento(num_episodes):
    global robot_position, obstacle_position, robot_path
    for episode in range(num_episodes):
        # Reiniciar o estado inicial do robô e do obstáculo
        robot_position = start
        obstacle_position = (MAP_HEIGHT - 1, random.randint(0, MAP_WIDTH - 1))
        robot_path = [robot_position]
        
        while robot_position != goal:
            # Escolher uma ação
            action = choose_action(robot_position)
            
            # Executar a ação e observar o próximo estado e a recompensa
            next_robot_position = move_robot(robot_position, action)
            
            # Atualizar a posição do obstáculo
            obstacle_x, obstacle_y = obstacle_position
            if obstacle_x > 0:
                obstacle_x -= 1
            else:
                obstacle_x = MAP_HEIGHT - 1
                obstacle_y = random.randint(0, MAP_WIDTH - 1)
            obstacle_position = (obstacle_x, obstacle_y)
            
            reward = calculate_reward(next_robot_position, obstacle_position, goal)
            
            # Atualizar a tabela Q
            update_Q(robot_position, action, reward, next_robot_position)
            
            # Atualizar o estado atual do robô
            robot_position = next_robot_position
            
            # Registrar o caminho do robô
            robot_path.append(robot_position)

# Função de animação
def update(frame):
    global robot_position, obstacle_position, robot_path

    # Escolher uma ação e mover o robô
    action = choose_action(robot_position)
    next_robot_position = move_robot(robot_position, action)
    
    # Atualizar a posição do obstáculo
    obstacle_x, obstacle_y = obstacle_position
    if obstacle_x > 0:
        obstacle_x -= 1
    else:
        obstacle_x = MAP_HEIGHT - 1
        obstacle_y = random.randint(0, MAP_WIDTH - 1)
    obstacle_position = (obstacle_x, obstacle_y)
    
    # Atualizar a recompensa e a tabela Q
    reward = calculate_reward(next_robot_position, obstacle_position, goal)
    update_Q(robot_position, action, reward, next_robot_position)
    
    # Atualizar a posição do robô
    robot_position = next_robot_position
    
    # Registrar o caminho do robô
    robot_path.append(robot_position)

    # Atualizar o mapa visual
    mapa.fill(0)
    mapa[goal] = 0.5  # Destino
    mapa[obstacle_position] = 1.0  # Obstáculo
    for pos in robot_path:
        mapa[pos] = 0.25  # Caminho percorrido
    mapa[robot_position] = 0.75  # Robô
    mat.set_data(mapa)
    return [mat]

# Treinamento do modelo
treinamento(num_episodes)

# Configuração da animação
fig, ax = plt.subplots()
mat = ax.matshow(mapa, cmap="coolwarm")

ani = animation.FuncAnimation(fig, update, frames=200, interval=100, blit=True)

# Plotar pontos de início e fim
plt.plot(goal[1], goal[0], marker='s', color='green', markersize=10, label='Goal')
plt.plot(start[1], start[0], marker='s', color='yellow', markersize=10, label='Start')
plt.legend()

plt.show()

for i in range(5):
    alpha = random.randint(0,10) / 10  
    gamma = random.randint(0,10) / 10  
    epsilon = random.randint(0,10) / 10
    treinamento(num_episodes)

    

    fig, ax = plt.subplots()
    mat = ax.matshow(mapa, cmap ="coolwarm")

    ani = animation.FuncAnimation(fig, update, frames=200, interval=100, blit=True)
    
    #plt.plot(obstacle_position[1], obstacle_position[0], marker='s', color='red', markersize=15)
    plt.plot(goal[1], goal[0], marker='s', color='green', markersize=10, label='Goal')
    plt.plot(start[1], start[0], marker='s', color='yellow', markersize=10, label='Start')
    plt.legend()
    
    plt.show()
