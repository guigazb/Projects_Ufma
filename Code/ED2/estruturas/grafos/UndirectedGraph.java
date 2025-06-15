package ED2.estruturas.grafos;
import java.util.*;

public class UndirectedGraph {
    private Map<Integer, List<Integer>> adjList; // Lista de adjacência

    // Construtor
    public UndirectedGraph() {
        adjList = new HashMap<>();
    }

    // Adiciona um vértice ao grafo
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Adiciona uma aresta não direcionada entre src e dest
    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
        adjList.get(dest).add(src); // Aresta bidirecional
    }

    // Remove uma aresta não direcionada entre src e dest
    public void removeEdge(int src, int dest) {
        List<Integer> neighborsSrc = adjList.get(src);
        List<Integer> neighborsDest = adjList.get(dest);
        if (neighborsSrc != null) {
            neighborsSrc.remove(Integer.valueOf(dest));
        }
        if (neighborsDest != null) {
            neighborsDest.remove(Integer.valueOf(src));
        }
    }

    // Retorna os vizinhos de um vértice
    public List<Integer> getNeighbors(int vertex) {
        return adjList.getOrDefault(vertex, new ArrayList<>());
    }

    // Retorna todos os vértices do grafo
    public Set<Integer> getVertices() {
        return adjList.keySet();
    }

    // Busca em largura (BFS)
    public List<Integer> bfs(int startVertex) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();

        if (!adjList.containsKey(startVertex)) return result;

        visited.add(startVertex);
        queue.offer(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            for (int neighbor : adjList.get(vertex)) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }
}
