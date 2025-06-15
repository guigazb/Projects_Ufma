package ED2.algoritmos_de_busca;

public class buscaBinaria {
    
    // Busca binária iterativa para encontrar um elemento em um array ordenado
    public static int binarySearch(int[] arr, int target) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = left + (right - left) / 2;

            // Verifica se o elemento está no meio
            if (arr[mid] == target) {
                return mid;
            }

            // Se o elemento é maior, ignora a metade esquerda
            if (arr[mid] < target) {
                left = mid + 1;
            }
            // Se o elemento é menor, ignora a metade direita
            else {
                right = mid - 1;
            }
        }
        // Elemento não encontrado
        return -1;
    }
}