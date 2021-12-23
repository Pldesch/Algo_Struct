import java.util.PriorityQueue;//Not finished

public class Huffman {


    private static int R = 256; // ASCII alphabet
    // See page 828 for inner Node class.
    // See text for helper methods and expand().
    public static void compress(String s)
    {
        char[] input = s.toCharArray();
        // Tabulate frequency counts.
        int[] freq = new int[R];
        for(char c : input) freq[c]++;
        // Build Huffman code trie.
        Node root = buildTrie(freq);
        // Build code table (recursive).
        String[] st = new String[R];
        buildCode(st, root, "");
        // Print trie for decoder (recursive).
        writeTrie(root);
        // Print number of chars.
        BinaryStdOut.write(input.length);
        // Use Huffman code to encode input.
        for (int i = 0; i < input.length; i++)
        {
            String code = st[input[i]];
            for (int j = 0; j < code.length(); j++)
                if (code.charAt(j) == '1')
                    BinaryStdOut.write(true);
                else BinaryStdOut.write(false);
        }
        BinaryStdOut.close();
    }

    private static void writeTrie(Node x) {
        if(x.right == null && x.left == null){
            ret
        }
    }

    private static void buildCode(String[] st, Node x, String s) {
        if(x.left == null && x.right == null){//isLeaf
            st[x.key] = s;
            return;
        }
        buildCode(st, x.left, s + '0');
        buildCode(st, x.right, s+'1');
    }

    public static class Node{
        Node left;
        Node right;
        int key;
        int val;

        public Node(int key, int val, Node left, Node right){
            this.left = left;
            this.right = right;
            this.key = key;
            this.val = val;
        }
    }

    private static Node buildTrie(int[] freq) {
        PriorityQueue<Node> pq = new PriorityQueue<Node>();
        for(char c = 0; c<R;c++){
            if(freq[c]>0) pq.add(new Node(c, freq[c], null, null)); //add to pq all words with freq > 0
        }
        while(pq.size()>1){
            Node x = pq.poll();//equal to delMin
            Node y = pq.poll();//equal to delMin
            Node parent = new Node('\0', x.val + y.val, x, y);
            pq.add(parent);
        }
        return pq.poll();
    }
}