public class HuffmanBinNode<K> implements Comparable {
    private double probability;
    private K value;
    private HuffmanBinNode<K> left;
    private HuffmanBinNode<K> right;

    public HuffmanBinNode() {
        this.probability = -1.0;
        this.right = null;
        this.left = null;
    }

    public HuffmanBinNode(double probability) {
        this.probability=probability;
        this.right = null;
        this.left = null;
    }

    public HuffmanBinNode(Double probability, K value) {
        this.probability = probability;
        this.value = value;
    }

    public HuffmanBinNode(double probability, HuffmanBinNode<K> right, HuffmanBinNode<K> left) {
        this.probability = probability;
        this.left = left;
        this.right = right;
    }

    public boolean hasLeft() {
        return this.left != null;
    }

    public boolean hasRight() {
        return this.right != null;
    }

    public Double getProbability() {
        return probability;
    }

    public void setProbability(Double probability) {
        this.probability = probability;
    }

    public K getValue() {
        return value;
    }

    public void setValue(K value) {
        this.value = value;
    }

    public HuffmanBinNode<K> getLeft() {
        return left;
    }

    public void setLeft(HuffmanBinNode<K> left) {
        this.left = left;
    }

    public HuffmanBinNode<K> getRight() {
        return right;
    }

    public void setRight(HuffmanBinNode<K> right) {
        this.right = right;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof HuffmanBinNode)) {
            return 0;
        }
        HuffmanBinNode<K> other = (HuffmanBinNode<K>) o;
        if (this.probability-other.probability>0) return 1;
        else if (this.probability- other.probability<0) return -1;
        return 0;
    }
}
