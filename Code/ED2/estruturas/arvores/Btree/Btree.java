package ED2.estruturas.arvores.Btree;

public class Btree {
    // Nó da Árvore B
    static class BtreeNode {
        int[] keys; // Array de chaves
        int t;      // Grau mínimo (define o intervalo de número de chaves)
        BtreeNode[] children; // Array de ponteiros para filhos
        int n;      // Número atual de chaves
        boolean leaf; // Verdadeiro se o nó for folha

        public BtreeNode(int t, boolean leaf) {
            this.t = t;
            this.leaf = leaf;
            this.keys = new int[2 * t - 1];
            this.children = new BtreeNode[2 * t];
            this.n = 0;
        }
    }

    private BtreeNode root; // Raiz da árvore
    private int t;         // Grau mínimo

    public Btree(int t) {
        this.t = t;
        this.root = null;
    }

    // Inserir uma chave na árvore
    public void insert(int key) {
        if (root == null) {
            root = new BtreeNode(t, true);
            root.keys[0] = key;
            root.n = 1;
        } else {
            if (root.n == 2 * t - 1) {
                BtreeNode newRoot = new BtreeNode(t, false);
                newRoot.children[0] = root;
                splitChild(newRoot, 0, root);
                int i = 0;
                if (newRoot.keys[0] < key) {
                    i++;
                }
                insertNonFull(newRoot.children[i], key);
                root = newRoot;
            } else {
                insertNonFull(root, key);
            }
        }
    }

    // Inserir em um nó não cheio
    private void insertNonFull(BtreeNode node, int key) {
        int i = node.n - 1;

        if (node.leaf) {
            while (i >= 0 && node.keys[i] > key) {
                node.keys[i + 1] = node.keys[i];
                i--;
            }
            node.keys[i + 1] = key;
            node.n++;
        } else {
            while (i >= 0 && node.keys[i] > key) {
                i--;
            }
            if (node.children[i + 1].n == 2 * t - 1) {
                splitChild(node, i + 1, node.children[i + 1]);
                if (node.keys[i + 1] < key) {
                    i++;
                }
            }
            insertNonFull(node.children[i + 1], key);
        }
    }

    // Dividir um nó filho cheio
    private void splitChild(BtreeNode parent, int i, BtreeNode fullChild) {
        BtreeNode newNode = new BtreeNode(fullChild.t, fullChild.leaf);
        newNode.n = t - 1;

        // Copiar as últimas (t-1) chaves
        for (int j = 0; j < t - 1; j++) {
            newNode.keys[j] = fullChild.keys[j + t];
        }

        // Copiar os últimos t filhos se não for folha
        if (!fullChild.leaf) {
            for (int j = 0; j < t; j++) {
                newNode.children[j] = fullChild.children[j + t];
            }
        }

        fullChild.n = t - 1;

        // Mover chaves do pai
        for (int j = parent.n; j >= i + 1; j--) {
            parent.children[j + 1] = parent.children[j];
        }

        // Linkar novo nó
        parent.children[i + 1] = newNode;

        // Mover chaves do pai
        for (int j = parent.n - 1; j >= i; j--) {
            parent.keys[j + 1] = parent.keys[j];
        }

        // Copiar chave do meio
        parent.keys[i] = fullChild.keys[t - 1];
        parent.n++;
    }

    // Buscar uma chave na árvore
    public boolean search(int key) {
        return search(root, key);
    }

    private boolean search(BtreeNode node, int key) {
        if (node == null) {
            return false;
        }

        int i = 0;
        while (i < node.n && key > node.keys[i]) {
            i++;
        }

        if (i < node.n && key == node.keys[i]) {
            return true;
        }

        if (node.leaf) {
            return false;
        }

        return search(node.children[i], key);
    }
}