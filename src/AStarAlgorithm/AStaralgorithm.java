package AStarAlgorithm;

import javax.swing.*;
import java.util.*;
import java.io.*;

public class AStaralgorithm {
    private static Map<Integer, List<Integer>> graph = new HashMap<>();

    public static void main(String[] args)throws IOException {
        loadGraph();
        PrintWriter writer = new PrintWriter(new FileWriter("C:\\Users\\bvars\\IdeaProjects\\OOPs ProjectIII\\src\\AStarAlgorithm\\output.txt"));
        writer.println("Graph adjacency list:");
        int i = 0;

        int startNode = 1;
        int goalNode = 1088769;

        long currentTimeMillis1 = System.currentTimeMillis();
        int distance = dfs(startNode, goalNode);
        writer.println("Shortest distance from node " + startNode + " to node " + goalNode + " through DFS is: " + distance);
        long currentTimeMillis2 = System.currentTimeMillis();
        writer.println("DFS took " + (currentTimeMillis2 - currentTimeMillis1) + " milliseconds.");

        currentTimeMillis1 = System.currentTimeMillis();
        distance = bfs(startNode, goalNode);
        writer.println("Shortest distance from node " + startNode + " to node " + goalNode + " through BFS is: " + distance);
        currentTimeMillis2 = System.currentTimeMillis();
        writer.println("BFS took " + (currentTimeMillis2 - currentTimeMillis1) + " milliseconds.");

        currentTimeMillis1 = System.currentTimeMillis();
        distance = aStar(startNode, goalNode);
        writer.println("Shortest distance from node " + startNode + " to node " + goalNode + " through a* is: " + distance);
        currentTimeMillis2 = System.currentTimeMillis();
        writer.println("A* took " + (currentTimeMillis2 - currentTimeMillis1) + " milliseconds.");

        if (graph.size() <= 10000000) {
            for (Map.Entry<Integer, List<Integer>> entry : graph.entrySet()) {
                if(i<50000){
                    writer.println("Node " + entry.getKey() + ": " + entry.getValue());
                    i++;
                }else break;
            }
        }
    }

    private static void loadGraph() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setDialogTitle("Select dataset.txt file");

        int result = fileChooser.showOpenDialog(null);
        if (result == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();

            try (BufferedReader br = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = br.readLine()) != null && line.startsWith("#")) {
                }
                while ((line = br.readLine()) != null) {
                    String[] parts = line.trim().split("\\s+");
                    if (parts.length == 2) {
                        int node1 = Integer.parseInt(parts[0]);
                        int node2 = Integer.parseInt(parts[1]);
                        graph.computeIfAbsent(node1, k -> new ArrayList<>()).add(node2);
                    }
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("No file chosen");
        }
    }

    public static int dfs(int start, int end) {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Integer> distances = new HashMap<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(start);
        distances.put(start, 0);
        visited.add(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();

            if (node == end) {
                return distances.get(node);
            }

            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    stack.push(neighbor);
                    distances.put(neighbor, distances.get(node) + 1);
                }
            }
        }

        return -1;
    }


    public static int bfs(int start, int end) {
        Set<Integer> visited = new HashSet<>();
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distances = new HashMap<>();

        queue.add(start);
        distances.put(start, 0);
        visited.add(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int neighbor : graph.getOrDefault(node, new ArrayList<>())) {
                if (!visited.contains(neighbor)) {
                    visited.add(neighbor);
                    queue.add(neighbor);
                    distances.put(neighbor, distances.get(node) + 1);
                    if (neighbor == end) {
                        return distances.get(neighbor);
                    }
                }
            }
        }
        return -1;
    }

    private static int aStar(int start, int goal) {
        PriorityQueue<Node> openSet = new PriorityQueue<>(Comparator.comparingInt(node -> node.fScore));
        Set<Integer> closedSet = new HashSet<>();

        Map<Integer, Integer> gScore = new HashMap<>();
        Map<Integer, Integer> fScore = new HashMap<>();

        gScore.put(start, 0);
        fScore.put(start, heuristic(start, goal));

        openSet.add(new Node(start, fScore.get(start)));

        while (!openSet.isEmpty()) {
            Node current = openSet.poll();

            if (current.id == goal) {
                return gScore.get(current.id);
            }

            closedSet.add(current.id);

            for (int neighbor : graph.getOrDefault(current.id, Collections.emptyList())) {
                if (closedSet.contains(neighbor)) {
                    continue;
                }

                int tentativeGScore = gScore.getOrDefault(current.id, Integer.MAX_VALUE) + 1; // Distance is 1 for each edge

                if (tentativeGScore < gScore.getOrDefault(neighbor, Integer.MAX_VALUE)) {
                    gScore.put(neighbor, tentativeGScore);
                    fScore.put(neighbor, tentativeGScore + heuristic(neighbor, goal));
                    openSet.add(new Node(neighbor, fScore.get(neighbor)));
                }
            }
        }
        return -1;
    }
    private static int heuristic(int node, int goal) {
        return 0;
    }
    private static class Node {
        int id;
        int fScore;

        Node(int id, int fScore) {
            this.id = id;
            this.fScore = fScore;
        }
    }
}