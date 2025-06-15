package ED2.algoritmos_de_ordenação;
import java.util.*;

public class bucketSort {
    // Bucket Sort para números de ponto flutuante entre 0 e 1
    public static void BucketSort(float[] arr) {
        int n = arr.length;
        if (n <= 0) return;

        // Criar n buckets vazios
        @SuppressWarnings("unchecked")
        ArrayList<Float>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            buckets[i] = new ArrayList<Float>();
        }

        // Colocar elementos nos buckets
        for (int i = 0; i < n; i++) {
            int bucketIndex = (int) (n * arr[i]);
            buckets[bucketIndex].add(arr[i]);
        }

        // Ordenar cada bucket
        for (int i = 0; i < n; i++) {
            Collections.sort(buckets[i]);
        }

        // Concatenar todos os buckets de volta no array
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++) {
                arr[index++] = buckets[i][j];
            }
        }
    }
}