package ED2.algoritmos_de_ordenação;

public class countingSort {
    // Counting Sort para números inteiros não negativos
    public static void CountingSort(int[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Encontrar o maior elemento
        int max = arr[0];
        for (int i = 1; i < n; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }

        // Criar array de contagem
        int[] count = new int[max + 1];

        // Contar ocorrências
        for (int i = 0; i < n; i++) {
            count[arr[i]]++;
        }

        // Atualizar array de contagem para posições
        for (int i = 1; i <= max; i++) {
            count[i] += count[i - 1];
        }

        // Construir array ordenado
        int[] output = new int[n];
        for (int i = n - 1; i >= 0; i--) {
            output[count[arr[i]] - 1] = arr[i];
            count[arr[i]]--;
        }

        // Copiar para o array original
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }
}
