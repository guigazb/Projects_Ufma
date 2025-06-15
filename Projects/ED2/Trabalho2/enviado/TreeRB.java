public class TreeRB<AnyType extends Comparable<AnyType>> implements BalancedTree<AnyType> {

    public class NodeRB {

        AnyType element;
        public NodeRB pai;
        public NodeRB left;
        public NodeRB right;
        Color color;
        int N; // conta subarvores

        enum Color {
            RED, BLACK
        }

        public NodeRB(AnyType e) {
            this(e, null, null, null, Color.RED);
        }

        public NodeRB(AnyType e, NodeRB pai, NodeRB left, NodeRB right, Color color) {
            this.element = e;
            this.pai = pai;
            this.left = left;
            this.right = right;
            this.color = color;
            this.N = 1;
        }
    }

    private NodeRB root;

    public boolean isRed(NodeRB x) {
        return x != null && x.color == NodeRB.Color.RED;
    }

    public void setRed(NodeRB x) {
        if (x != null) x.color = NodeRB.Color.RED;
    }

    public void setBlack(NodeRB x) {
        if (x != null) x.color = NodeRB.Color.BLACK;
    }

    private NodeRB min(NodeRB h) {
        if (h.left == null)
            return h;
        else
            return min(h.left);
    }

    private NodeRB deleteMin(NodeRB h) {
        if (h.left == null)
            return null;
        if (!isRed(h.left) && !isRed(h.left.left))
            h = moveRedLeft(h);
        h.left = deleteMin(h.left);
        return balance(h);
    }

    private NodeRB parentOf(NodeRB node) {
        return node == null ? null : node.pai;
    }

    private NodeRB leftOf(NodeRB node) {
        return node == null ? null : node.left;
    }

    private NodeRB rightOf(NodeRB node) {
        return node == null ? null : node.right;
    }

    
    private NodeRB rotateLeft(NodeRB h) {
        if (h == null || h.right == null) return h; 
        NodeRB x = h.right;
        h.right = x.left;
        if (x.left != null) x.left.pai = h; 
        x.left = h;
        x.color = h.color;
        setRed(h);
        x.pai = h.pai; 
        h.pai = x; 
        return x;
    }

    private NodeRB rotateRight(NodeRB h) {
        if (h == null || h.left == null) return h; 
        NodeRB x = h.left;
        h.left = x.right;
        if (x.right != null) x.right.pai = h; 
        x.right = h;
        x.color = h.color;
        setRed(h);
        x.pai = h.pai; 
        h.pai = x; 
        return x;
    }

    private NodeRB moveRedLeft(NodeRB h) {
        flipColors(h);
        if (isRed(h.right.left)) {
            h.right = rotateRight(h.right);
            h = rotateLeft(h);
            flipColors(h);
        }
        return h;
    }

    private NodeRB moveRedRight(NodeRB h) {
        flipColors(h);
        if (isRed(h.left.left)) {
            h = rotateRight(h);
            flipColors(h);
        }
        return h;
    }

    private void flipColors(NodeRB h) {
        if (h != null) {
            h.color = (h.color == NodeRB.Color.RED) ? NodeRB.Color.BLACK : NodeRB.Color.RED;
            if (h.left != null) h.left.color = (h.left.color == NodeRB.Color.RED) ? NodeRB.Color.BLACK : NodeRB.Color.RED;
            if (h.right != null) h.right.color = (h.right.color == NodeRB.Color.RED) ? NodeRB.Color.BLACK : NodeRB.Color.RED;
        }
    }

    @Override
    public void insert(AnyType value) {
        NodeRB node = new NodeRB(value);
        root = insert(root, node);
        fixAfterInsertion(node);
    }

    private NodeRB insert(NodeRB root, NodeRB node) {
        if (root == null) {
            return node;
        }

        int cmp = node.element.compareTo(root.element);
        if (cmp < 0) {
            root.left = insert(root.left, node);
            root.left.pai = root;
        } else if (cmp > 0) {
            root.right = insert(root.right, node);
            root.right.pai = root;
        }

        return root;
    }

    private void fixAfterInsertion(NodeRB node) {
        while (node != null && node != root && isRed(node.pai)) {
            if (parentOf(node) == leftOf(parentOf(parentOf(node)))) {
                NodeRB y = rightOf(parentOf(parentOf(node)));
                if (isRed(y)) {
                    setBlack(parentOf(node));
                    setBlack(y);
                    setRed(parentOf(parentOf(node)));
                    node = parentOf(parentOf(node));
                } else {
                    if (node == rightOf(parentOf(node))) {
                        node = parentOf(node);
                        rotateLeft(node);
                    }
                    setBlack(parentOf(node));
                    setRed(parentOf(parentOf(node)));
                    rotateRight(parentOf(parentOf(node)));
                }
            } else {
                NodeRB y = leftOf(parentOf(parentOf(node)));
                if (isRed(y)) {
                    setBlack(parentOf(node));
                    setBlack(y);
                    setRed(parentOf(parentOf(node)));
                    node = parentOf(parentOf(node));
                } else {
                    if (node == leftOf(parentOf(node))) {
                        node = parentOf(node);
                        rotateRight(node);
                    }
                    setBlack(parentOf(node));
                    setRed(parentOf(parentOf(node)));
                    rotateLeft(parentOf(parentOf(node)));
                }
            }
        }
        root.color = NodeRB.Color.BLACK;
    }

    @Override
    public boolean remove(AnyType value) {
        if (!find(value))
            return false;
        if (!isRed(root.left) && !isRed(root.right))
            setRed(root);
        root = remove(root, value);
        if (root != null)
            setBlack(root);
        return true;
    }

    private NodeRB remove(NodeRB h, AnyType value) {
        if (value.compareTo(h.element) < 0) {
            if (!isRed(h.left) && !isRed(h.left.left))
                h = moveRedLeft(h);
            h.left = remove(h.left, value);
        } else {
            if (isRed(h.left))
                h = rotateRight(h);
            if (value.compareTo(h.element) == 0 && (h.right == null))
                return null;
            if (!isRed(h.right) && !isRed(h.right.left))
                h = moveRedRight(h);
            if (value.compareTo(h.element) == 0) {
                NodeRB x = min(h.right);
                h.element = x.element;
                h.right = deleteMin(h.right);
            } else
                h.right = remove(h.right, value);
        }
        return balance(h);
    }

    
    private NodeRB balance(NodeRB h) {
        if (isRed(h.right))
            h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left))
            h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right))
            flipColors(h);
        return h;
    }

    @Override
    public boolean find(AnyType value) {
        return find(root, value);
    }

    private boolean find(NodeRB h, AnyType value) {
        while (h != null) {
            int cmp = value.compareTo(h.element);
            if (cmp < 0)
                h = h.left;
            else if (cmp > 0)
                h = h.right;
            else
                return true;
        }
        return false;
    }

    @Override
    public int getHeight() {
        return getHeight(root);
    }

    private int getHeight(NodeRB h) {
        if (h == null)
            return -1;
        return 1 + Math.max(getHeight(h.left), getHeight(h.right));
    }

    @Override
    public void printInOrder() {
        printInOrder(root);
    }

    private void printInOrder(NodeRB h) {
        if (h != null) {
            printInOrder(h.left);
            System.out.println("[" + h.element + "]");
            printInOrder(h.right);
        }
    }

}
