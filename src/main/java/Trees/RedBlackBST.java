package Trees;

public class RedBlackBST<Key extends Comparable<Key>, Value>
{
    private static final boolean BLACK = false;
    private static final boolean RED = true;
    private Node root;
    private class Node{
        Key key;
        Value val;
        Node left, right;
        int n;
        boolean color;

        Node(Key key, Value val, int n, boolean color){
            this.key = key;
            this.val = val;
            this.n = n;
            this.color = color;
        }
    }
    private boolean isRed(Node h){
        return h.color == RED;
    }
    private Node rotateLeft(Node h){
        Node x = h.right;
        h.right = x.left;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1+size(h.left)+size(h.right);
        return x;
    }
    private Node rotateRight(Node h){
        Node x = h.left;
        h.left = x.right;
        x.color = h.color;
        h.color = RED;
        x.n = h.n;
        h.n = 1+size(x.left)+size(x.right);
        return x;
    }
    private void flipColors(Node h){
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }
    private int size(){
        return size(root);
    }

    private int size(Node x) {
        if(x==null) return 0;
        return x.n;
    }

    public void put(Key key, Value val)
    { // Search for key. Update value if found; grow table if new.
        root = put(root, key, val);
        root.color = BLACK;
    }
    private Node put(Node h, Key key, Value val)
    {
        if (h == null) // Do standard insert, with red link to parent.
            return new Node(key, val, 1, RED);
        int cmp = key.compareTo(h.key);
        if (cmp < 0) h.left = put(h.left, key, val);
        else if (cmp > 0) h.right = put(h.right, key, val);
        else h.val = val;
        if (isRed(h.right) && !isRed(h.left)) h = rotateLeft(h);
        if (isRed(h.left) && isRed(h.left.left)) h = rotateRight(h);
        if (isRed(h.left) && isRed(h.right)) flipColors(h);
        h.n = size(h.left) + size(h.right) + 1;
        return h;
    }
}