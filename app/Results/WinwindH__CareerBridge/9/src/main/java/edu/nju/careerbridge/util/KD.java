package edu.nju.careerbridge.util;
 import java.util;
public class KD {

 private double[] val;

 private int dimension;

 private Node left;

 private Node right;


public List<Node> search(Node root,Node target,int k){
    Map<Node, Double> node_distMap = new HashMap<>();
    PriorityQueue<Node> maxHeap = new PriorityQueue<>(k, new Comparator<Node>() {

        @Override
        public int compare(Node o1, Node o2) {
            if (node_distMap.get(o2) - node_distMap.get(o1) > 0)
                return 1;
            else if (node_distMap.get(o2) - node_distMap.get(o1) < 0)
                return -1;
            else
                return 0;
        }
    });
    double[] targetVal = target.val;
    Stack<Node> find = new Stack<>();
    while (root != null) {
        find.push(root);
        int d = root.getDimension();
        if (targetVal[d] <= root.val[d])
            root = root.left;
        else
            root = root.right;
    }
    while (!find.isEmpty()) {
        Node p = find.pop();
        int d = p.getDimension();
        double dist = Math.sqrt(Math.pow(target.getVal(0) - p.getVal(0), 2) + Math.pow(target.getVal(1) - p.getVal(1), 2));
        node_distMap.put(p, dist);
        if (maxHeap.size() < k)
            maxHeap.offer(p);
        else if (maxHeap.size() == k && dist < node_distMap.get(maxHeap.peek())) {
            maxHeap.poll();
            maxHeap.offer(p);
        }
        if (node_distMap.get(maxHeap.peek()) > Math.abs(target.getVal(d) - p.getVal(d))) {
            if (node_distMap.containsKey(p.left))
                find.push(p.right);
            else
                find.push(p.left);
            p = find.pop();
            while (p != null) {
                find.push(p);
                d = p.getDimension();
                if (targetVal[d] <= p.val[d])
                    p = p.left;
                else
                    p = p.right;
            }
        }
    }
    List<Node> result = new ArrayList<>();
    while (maxHeap.size() > 0) {
        result.add(0, maxHeap.poll());
    }
    return result;
}


@Override
public int compare(Node o1,Node o2){
    if (node_distMap.get(o2) - node_distMap.get(o1) > 0)
        return 1;
    else if (node_distMap.get(o2) - node_distMap.get(o1) < 0)
        return -1;
    else
        return 0;
}


public double getVal(int n){
    return val[n];
}


public int getDimensionNum(){
    return val.length;
}


public Node KDTree(List<Node> nodes){
    if (nodes.size() == 0)
        return null;
    if (nodes.size() == 1)
        return nodes.get(0);
    int dimensionCount = nodes.get(0).getDimensionNum();
    int nodesCount = nodes.size();
    int cuttingDimension = -1;
    int maxVariance = -1;
    for (int i = 0; i < dimensionCount; i++) {
        int average = 0;
        for (int j = 0; j < nodesCount; j++) {
            average += nodes.get(j).getVal(i);
        }
        average /= nodesCount;
        int variance = 0;
        for (int j = 0; j < nodesCount; j++) {
            variance += Math.pow(nodes.get(j).getVal(i) - average, 2);
        }
        variance /= nodesCount;
        if (variance > maxVariance) {
            maxVariance = variance;
            cuttingDimension = i;
        }
    }
    int n = cuttingDimension;
    nodes.sort(Comparator.comparingDouble(node -> node.getVal(n)));
    Node root = nodes.get(nodesCount / 2);
    root.setDimension(n);
    List<Node> nodesL = nodes.subList(0, nodesCount / 2);
    root.left = KDTree(nodesL);
    List<Node> nodesR = nodes.subList(nodesCount / 2 + 1, nodesCount);
    root.right = KDTree(nodesR);
    return root;
}


public int getDimension(){
    return dimension;
}


public void setDimension(int dimension){
    this.dimension = dimension;
}


public String toString(){
    return Arrays.toString(val);
}


public void main(String[] args){
    Scanner scan = new Scanner(System.in);
    int rounds = Integer.valueOf(scan.nextLine());
    for (int i = 0; i < rounds; i++) {
        List<Node> nodeList = new ArrayList<>();
        String nodesLine = scan.nextLine();
        String targetLine = scan.nextLine();
        int n = Integer.valueOf(scan.nextLine());
        String[] nodes = nodesLine.split(",");
        for (int j = 0; j < nodes.length; j++) {
            String[] nodeStr = nodes[j].split(" ");
            double[] nodeVal = new double[2];
            for (int k = 0; k < 2; k++) {
                nodeVal[k] = Double.valueOf(nodeStr[k]);
            }
            Node node = new Node(nodeVal);
            nodeList.add(node);
        }
        String[] targetStr = targetLine.split(" ");
        double[] targetVal = new double[2];
        for (int k = 0; k < 2; k++) {
            targetVal[k] = Double.valueOf(targetStr[k]);
        }
        Node target = new Node(targetVal);
        List<Node> result = search(KDTree(nodeList), target, n);
        for (int j = 0; j < result.size() - 1; j++) {
            System.out.printf((result.get(j).getVal(0) + " " + result.get(j).getVal(1) + ",").replaceAll("\\.0", ""));
        }
        System.out.println((result.get(result.size() - 1).getVal(0) + " " + result.get(result.size() - 1).getVal(1)).replaceAll("\\.0", ""));
    }
}


}