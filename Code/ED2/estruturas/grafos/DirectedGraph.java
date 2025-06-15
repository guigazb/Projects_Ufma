package ED2.estruturas.grafos;
import java.util.*;

public class DirectedGraph {
    private Map<Integer, List<Integer>> adjList; // Lista de adjacência

    // Construtor
    public DirectedGraph() {
        adjList = new HashMap<>();
    }

    // Adiciona um vértice ao grafo
    public void addVertex(int vertex) {
        adjList.putIfAbsent(vertex, new ArrayList<>());
    }

    // Adiciona uma aresta direcionada de src para dest
    public void addEdge(int src, int dest) {
        adjList.putIfAbsent(src, new ArrayList<>());
        adjList.putIfAbsent(dest, new ArrayList<>());
        adjList.get(src).add(dest);
    }

    // Remove uma aresta direcionada de src para dest
    public void removeEdge(int src, int dest) {
        List<Integer> neighbors = adjList.get(src);
        if (neighbors != null) {
            neighbors.remove(Integer.valueOf(dest));
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

    // Busca em profundidade (DFS)
    public List<Integer> dfs(int startVertex) {
        List<Integer> result = new ArrayList<>();
        Set<Integer> visited = new HashSet<>();
        dfsRecursive(startVertex, visited, result);
        return result;
    }

    private void dfsRecursive(int vertex, Set<Integer> visited, List<Integer> result) {
        if (!adjList.containsKey(vertex)) return;
        visited.add(vertex);
        result.add(vertex);
        for (int neighbor : adjList.get(vertex)) {
            if (!visited.contains(neighbor)) {
                dfsRecursive(neighbor, visited, result);
            }
        }
    }
}
