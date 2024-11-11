package MSTKrushkalAlgorithm;

import javax.swing.*;
import java.io.*;
import java.util.*;

class Edge implements Comparable<Edge> {
    int startNode, endNode;
    int length;

    public Edge(int startNode, int endNode, int length) {
        this.startNode = startNode;
        this.endNode = endNode;
        this.length = length;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.length, other.length);
    }
}

class UnionFind {
    private int[] parent;

    public UnionFind(int maxNodeId) {
        parent = new int[maxNodeId + 1];
        for (int i = 1; i <= maxNodeId; i++) {
            parent[i] = i;
        }
    }

    public int find(int node) {
        if (parent[node] != node) {
            parent[node] = find(parent[node]);
        }
        return parent[node];
    }

    public void union(int node1, int node2) {
        int root1 = find(node1);
        int root2 = find(node2);
        if (root1 != root2) {
            parent[root1] = root2;
        }
    }
}

public class TransportationNetwork {
    private List<Edge> edges = new ArrayList<>();
    private int maxNodeId = 0;

    public void loadEdgesFromFile() throws IOException {
        // Open file chooser dialog to select input file
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected file
            File selectedFile = fileChooser.getSelectedFile();
            String filePath = selectedFile.getAbsolutePath();

            // Read file contents
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String line;
            reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                // Check if the line has at least 3 columns (index 0 to 2)
                if (data.length >= 3) {
                    try {
                        int startNode = Integer.parseInt(data[0].trim());
                        int endNode = Integer.parseInt(data[1].trim());
                        int length = Integer.parseInt(data[2].trim());
                        edges.add(new Edge(startNode, endNode, length));

                        // Track the maximum node ID to initialize UnionFind correctly
                        maxNodeId = Math.max(maxNodeId, Math.max(startNode, endNode));
                    } catch (NumberFormatException e) {
                        System.err.println("Error parsing line: " + line + " - Skipping this line.");
                    }
                } else {
                    System.err.println("Invalid line format (missing columns): " + line);
                }
            }
            reader.close();
        } else {
            System.out.println("No file selected.");
        }
    }

    public void buildMinimalCostNetwork() throws IOException {
        // Open file chooser dialog to select output file
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Save Output As...");
        fileChooser.setSelectedFile(new File("output.txt"));
        int result = fileChooser.showSaveDialog(null);

        if (result == JFileChooser.APPROVE_OPTION) {
            // Get the selected output file
            File selectedFile = fileChooser.getSelectedFile();
            String outputFilePath = selectedFile.getAbsolutePath();

            // Sort edges by length for Kruskal's algorithm
            Collections.sort(edges);
            UnionFind uf = new UnionFind(maxNodeId); // Use maxNodeId to initialize UnionFind
            List<Edge> mst = new ArrayList<>();
            int totalCost = 0;

            // Build MST
            for (Edge edge : edges) {
                if (uf.find(edge.startNode) != uf.find(edge.endNode)) {
                    uf.union(edge.startNode, edge.endNode);
                    mst.add(edge);
                    totalCost += edge.length;
                }
            }

            // Sort the MST edges by startNode for ordered output
            mst.sort(Comparator.comparingInt(edge -> edge.startNode));

            // Write output to file
            PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath));
            writer.println("Optimal Routes for Minimal Cost Network:");
            for (Edge edge : mst) {
                writer.printf("Route from station %d to station %d, Length: %d\n",
                        edge.startNode, edge.endNode, edge.length);
            }
            writer.printf("\nTotal Minimal Cost for Network: %d\n", totalCost);
            writer.close();
        } else {
            System.out.println("No output file selected.");
        }
    }

    public static void main(String[] args) {
        TransportationNetwork network = new TransportationNetwork();
        try {
            network.loadEdgesFromFile(); // User selects input file
            network.buildMinimalCostNetwork(); // User selects output file
            System.out.println("Output written to selected file successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
