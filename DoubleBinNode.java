public class DoubleBinNode implements Comparable {
    private Double probability;
    private char character;
    private DoubleBinNode left;
    private DoubleBinNode right;

    public DoubleBinNode() {
        this.probability = -1.0;
        this.right = null;
        this.left = null;
    }

    public DoubleBinNode(double probability) {
        this.probability=probability;
        this.right = null;
        this.left = null;
    }

    public DoubleBinNode(Double probability, char character) {
        this.probability = probability;
        this.character = character;
    }

    public DoubleBinNode(double probability, DoubleBinNode right, DoubleBinNode left) {
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

    public char getCharacter() {
        return character;
    }

    public void setCharacter(char character) {
        this.character = character;
    }

    public DoubleBinNode getLeft() {
        return left;
    }

    public void setLeft(DoubleBinNode left) {
        this.left = left;
    }

    public DoubleBinNode getRight() {
        return right;
    }

    public void setRight(DoubleBinNode right) {
        this.right = right;
    }

    @Override
    public int compareTo(Object o) {
        if (!(o instanceof DoubleBinNode)) {
            return 0;
        }
        DoubleBinNode other = (DoubleBinNode) o;
        if (this.probability-other.probability>0) return 1;
        else if (this.probability- other.probability<0) return -1;
        return 0;
    }
}
