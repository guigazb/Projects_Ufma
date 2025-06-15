
public class TreeAVL<AnyType extends Comparable<AnyType>> implements BalancedTree<AnyType>{
    
    @SuppressWarnings("hiding")
    public class NodeAVL <AnyType>{
        AnyType element;
        NodeAVL<AnyType> left;
        NodeAVL<AnyType> right;
        int height;
        
        public NodeAVL(AnyType e){
            this(e,null,null);
        }
        
        public NodeAVL(AnyType e, NodeAVL<AnyType> left, NodeAVL<AnyType> right){
            this.element = e;
            this.left = left;
            this.right = right;
            this.height = 0;
        }
    
    }
    

    private NodeAVL<AnyType> root;

    public TreeAVL() {
        root = null;
    }

    private NodeAVL<AnyType> rotateWithLeftChild(NodeAVL<AnyType> k2) {
        NodeAVL<AnyType> k1 = k2.left;
        k2.left = k1.right;
        k1.right = k2;
        k2.height = Math.max(height(k2.left), height(k2.right)) + 1;
        k1.height = Math.max(height(k1.left), k2.height) + 1;
        return k1;
    }

    private NodeAVL<AnyType> rotateWithRightChild(NodeAVL<AnyType> k1) {
        NodeAVL<AnyType> k2 = k1.right;
        k1.right = k2.left;
        k2.left = k1;
        k1.height = Math.max(height(k1.left), height(k1.right)) + 1;
        k2.height = Math.max(height(k2.right), k1.height) + 1;
        return k2;
    }

    private NodeAVL<AnyType> doubleWithLeftChild(NodeAVL<AnyType> k3) {
        k3.left = rotateWithRightChild(k3.left);
        return rotateWithLeftChild(k3);
    }

    private NodeAVL<AnyType> doubleWithRightChild(NodeAVL<AnyType> k1) {
        k1.right = rotateWithLeftChild(k1.right);
        return rotateWithRightChild(k1);
    }

    private NodeAVL<AnyType> findMin(NodeAVL<AnyType> node) {
        if (node == null) {
            return null;
        }
        while (node.left != null) {
            node = node.left;
        }
        return node;
    }

    @Override
    public void insert(AnyType value) {
        root = insert(value, root);
    }

    private NodeAVL<AnyType> insert(AnyType value, NodeAVL<AnyType> node) {
        if (node == null) {
            return new NodeAVL<>(value);
        }

        int compareResult = value.compareTo(node.element);

        if (compareResult < 0) {
            node.left = insert(value, node.left);
        } else if (compareResult > 0) {
            node.right = insert(value, node.right);
        }

        return balance(node);
    }

    private NodeAVL<AnyType> balance(NodeAVL<AnyType> node) {
        if (node == null) {
            return null;
        }

        if (height(node.left) - height(node.right) > 1) {
            if (height(node.left.left) >= height(node.left.right)) {
                node = rotateWithLeftChild(node);
            } else {
                node = doubleWithLeftChild(node);
            }
        } else if (height(node.right) - height(node.left) > 1) {
            if (height(node.right.right) >= height(node.right.left)) {
                node = rotateWithRightChild(node);
            } else {
                node = doubleWithRightChild(node);
            }
        }

        node.height = Math.max(height(node.left), height(node.right)) + 1;
        return node;
    }

    @Override
    public boolean remove(AnyType value) {
        try {
            root = remove(value, root);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    private NodeAVL<AnyType> remove(AnyType value, NodeAVL<AnyType> node) {
        if (node == null) {
            throw new RuntimeException("Element not found");
        }

        int compareResult = value.compareTo(node.element);

        if (compareResult < 0) {
            node.left = remove(value, node.left);
        } else if (compareResult > 0) {
            node.right = remove(value, node.right);
        } else if (node.left != null && node.right != null) {
            node.element = findMin(node.right).element;
            node.right = remove(node.element, node.right);
        } else {
            node = (node.left != null) ? node.left : node.right;
        }

        return balance(node);
    }


    @Override
    public boolean find(AnyType value) {
        return find(value, root);
    }

    private boolean find(AnyType value, NodeAVL<AnyType> node) {
        if (node == null) {
            return false;
        }

        int compareResult = value.compareTo(node.element);

        if (compareResult < 0) {
            return find(value, node.left);
        } else if (compareResult > 0) {
            return find(value, node.right);
        } else {
            return true;
        }
    }

    @Override
    public int getHeight() {
        return height(root);
    }

    private int height(NodeAVL<AnyType> node) {
        return node == null ? -1 : node.height;
    }

    @Override
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(NodeAVL<AnyType> node) {
        if (node != null) {
            printInOrder(node.left);
            System.out.print("[" + node.element + "]");
            printInOrder(node.right);
        }
    }

}
