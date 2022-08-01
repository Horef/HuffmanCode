import java.util.*;

/**
 * This is the main class that is responsible for running the Huffman algorithm.
 */
public class Main {

    public static void main(String[] args) {
        //Getting the text to encode from the user.
        //If you wish to pass the HashMap straight, you can freely remove these lines.
        System.out.println("Please enter your text:");
        Scanner input = new Scanner(System.in);
        String text = input.nextLine().toLowerCase();

        //Counting probabilities for each symbol.
        HashMap<Character, Double> alphabet = countProbabilities(text);

        HashMap<Character, String> codes = huffmanCodeMap(alphabet);
        System.out.println("The individual encoding values are:");
        printMap(codes);

        String encoding = huffmanCodeText(alphabet, text);
        System.out.println("Huffman code is: " + encoding);
    }

    /**
     * Given an alphabet of generic values, encode them with huffman algorithm.
     * @param alphabet (value: probability) pairs.
     * @return dictionary of (value: encoding) pairs.
     * @param <K> type of values.
     */
    public static <K> HashMap<K, String> huffmanCodeMap(HashMap<K, Double> alphabet) {
        HuffmanBinNode<K> huffmanTree = huffmanTree(alphabet);

        HashMap<K, String> codeMap = new HashMap<>();
        huffmanTreeToCode(huffmanTree, "", codeMap);

        return codeMap;
    }

    /**
     * Given the alphabet of the values in the given text, return the encoded text.
     * @param alphabet (char: probability) pairs for each char in the text.
     * @param text text to encode.
     * @return encoded text (String).
     */
    public static String huffmanCodeText(HashMap<Character, Double> alphabet, String text) {
        //Build Huffman tree.
        HuffmanBinNode<Character> huffmanTree = huffmanTree(alphabet);

        //Generates the code for each individual value in the tree.
        HashMap<Character, String> codeMap = new HashMap<>();
        huffmanTreeToCode(huffmanTree, "", codeMap);

        //Encode the whole text with codes from codeMap.
        StringBuilder codeBuilder = new StringBuilder();
        for (char c : text.toCharArray()) {
            codeBuilder.append(codeMap.get(c));
        }

        return codeBuilder.toString();
    }

    /**
     * Recursive function which gets the corresponding encoding from the given Huffman tree.
     * @param root root of the Huffman tree.
     * @param encoding temporary string to build the individual encodings.
     * @param code map to put the finished encodings into.
     * @param <K> type of the root values.
     */
    public static <K> void huffmanTreeToCode(HuffmanBinNode<K> root, String encoding, HashMap<K, String> code) {
        if (root.getValue()!=null) {
            code.put(root.getValue(),encoding);
            return;
        }
        if(root.hasLeft()) huffmanTreeToCode(root.getLeft(), encoding+"0", code);
        if(root.hasRight()) huffmanTreeToCode(root.getRight(), encoding+"1", code);
    }

    /**
     * Generates Huffman Encoding Tree from a given alphabet dictionary.
     * @param alphabet dictionary containing all possible values and their probabilities.
     * @return root of the generated tree.
     * @param <K> type of keys in the alphabet.
     */
    public static <K> HuffmanBinNode<K> huffmanTree(HashMap<K, Double> alphabet) {
        PriorityQueue<HuffmanBinNode<K>> root = new PriorityQueue<>();
        for (K c : alphabet.keySet()) {
            root.add(new HuffmanBinNode<>(alphabet.get(c),c));
        }
        while (root.size() >= 2) {
            HuffmanBinNode<K> smallest1 = root.remove();
            HuffmanBinNode<K> smallest2 = root.remove();
            double totalProbability = smallest1.getProbability() + smallest2.getProbability();
            HuffmanBinNode<K> z = new HuffmanBinNode<>(totalProbability, smallest1, smallest2);
            root.add(z);
        }
        return root.remove();
    }

    /**
     * Gets a generic map and prints all the (key,value) pairs in the format: "[key]: value"
     * @param map dictionary to print.
     * @param <T> type of keys.
     * @param <K> type of values.
     */
    public static <T,K> void printMap(HashMap<T,K> map) {
        for (T key : map.keySet()) {
            System.out.println(key + ": " + map.get(key));
        }
    }

    /**
     * Counts probabilities for each character in a given text, with respect to the whole text.
     * @param text text to count probabilities for.
     * @return dictionary with keys - distinct characters, values - their probabilities.
     */
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

    /**
     * Returns Shannon information content from a given map of values with their probabilities.
     * @param map dictionary of possible values and their probabilities.
     * @return information content. (By Shannon information definition.)
     * @param <T> value of the dictionary keys.
     */
    public static <T> double informationContent(HashMap<T, Double> map) {
        double sum = 0;
        for (double prob : map.values()) {
            sum += prob*(Math.log(prob)/Math.log(2));
        }
        return (-1)*sum;
    }
}
