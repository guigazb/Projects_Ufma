package ED2.algoritmos_de_ordenação;

public class radixSort {
    // Função auxiliar para obter o maior elemento
    private static int getMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }

    // Função auxiliar para fazer counting sort baseado em um dígito
    private static void countingSortByDigit(int[] arr, int exp) {
        int n = arr.length;
        int[] output = new int[n];
        int[] count = new int[10];

        // Contar ocorrências
        for (int i = 0; i < n; i++) {
            count[(arr[i] / exp) % 10]++;
        }

        // Atualizar array de contagem
        for (int i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }

        // Construir array de saída
        for (int i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
        }

        // Copiar para array original
        for (int i = 0; i < n; i++) {
            arr[i] = output[i];
        }
    }

    // Radix Sort para números inteiros não negativos
    public static void RadixSort(int[] arr) {
        int max = getMax(arr);

        // Fazer counting sort para cada dígito
        for (int exp = 1; max / exp > 0; exp *= 10) {
            countingSortByDigit(arr, exp);
        }
    }
}
