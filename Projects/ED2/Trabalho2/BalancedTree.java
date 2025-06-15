// interface implementada pelas arvores AVL e rubro negra

public interface BalancedTree <T extends Comparable<T>>{
    
    void insert(T value);
    boolean remove(T value);
    boolean find(T value);
    int getHeight();
    void printInOrder();

}