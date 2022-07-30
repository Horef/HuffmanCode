import java.util.*;

public class Main {

    public static void main(String[] args) {
        System.out.println("Please enter your text:");
        Scanner input = new Scanner(System.in);

        String text = input.nextLine().toLowerCase();
        String encoding = huffmanCode(text);
        System.out.println("Huffman code is: " + encoding);
    }

    public static String huffmanCode(String text) {
        HashMap<Character, Double> alphabet = countProbabilities(text);
        DoubleBinNode huffmanTree = huffmanTree(alphabet);
        HashMap<Character, String> codeMap = new HashMap<>();
        huffmanTreeToCode(huffmanTree, "", codeMap);
        System.out.println("This is the codeMap:");
        printMap(codeMap);
        System.out.println("Original information content is: " + informationContent(alphabet));

        StringBuilder codeBuilder = new StringBuilder();
        for (char c : text.toCharArray()) {
            codeBuilder.append(codeMap.get(c));
        }

        return codeBuilder.toString();
    }

    public static void huffmanTreeToCode(DoubleBinNode root, String encoding, HashMap<Character, String> code) {
        if (root.getCharacter()!=0) {
            code.put(root.getCharacter(),encoding);
            return;
        }
        if(root.hasLeft()) huffmanTreeToCode(root.getLeft(), encoding+"0", code);
        if(root.hasRight()) huffmanTreeToCode(root.getRight(), encoding+"1", code);
    }

    public static DoubleBinNode huffmanTree(HashMap<Character, Double> alphabet) {
        PriorityQueue<DoubleBinNode> root = new PriorityQueue<>();
        for (char c : alphabet.keySet()) {
            root.add(new DoubleBinNode(alphabet.get(c),c));
        }
        while (root.size() >= 2) {
            DoubleBinNode smallest1 = root.remove();
            DoubleBinNode smallest2 = root.remove();
            double totalProbability = smallest1.getProbability() + smallest2.getProbability();
            DoubleBinNode z = new DoubleBinNode(totalProbability, smallest1, smallest2);
            root.add(z);
        }
        return root.remove();
    }

    public static <T,K> void printMap(HashMap<T,K> map) {
        for (T key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }

    public static HashMap<Character, Double> countProbabilities(String text) {
        HashMap<Character, Integer> charCount = new HashMap<>();
        for (char c : text.toCharArray()) {
            if (charCount.containsKey(c))
                charCount.put(c, charCount.get(c)+1);
            else
                charCount.put(c, 1);
        }

        HashMap<Character, Double> charProbabilities = new HashMap<>();
        for (char c : charCount.keySet()) {
            charProbabilities.put(c,((double)charCount.get(c))/text.length());
        }

        return charProbabilities;
    }

    public static <T> double informationContent(HashMap<T, Double> map) {
        double sum = 0;
        for (double prob : map.values()) {
            sum += prob*(Math.log(prob)/Math.log(2));
        }
        return (-1)*sum;
    }
}
