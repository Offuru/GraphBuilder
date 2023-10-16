package GraphBuilder.models;

import java.io.FileWriter;
import java.util.List;
import java.util.ArrayList;
import java.io.IOException;

public class AdjancencyMatrix {

    List<List<Integer>> matrix;
    String filePath;

    public AdjancencyMatrix(String filePath) {
        matrix = new ArrayList<>();
        this.filePath = filePath;
    }

    public void addNode() {
        List<Integer> tempList = new ArrayList<>();

        for (int i = 0; i < matrix.size() + 1; i++)
            tempList.add(0);

        for (List<Integer> row : matrix)
            row.add(0);

        matrix.add(tempList);
    }

    public void deleteNode(int key) {
        for (List<Integer> row : matrix)
            row.remove(key);
        matrix.remove(key);
    }

    public void updateEdge(int startKey, int endKey, int distance) {
        matrix.get(startKey).set(endKey, distance);
    }

    public Integer getEdge(int startKey, int endKey) {
        return matrix.get(startKey).get(endKey);
    }

    public void saveMatrix() {

        try {
            FileWriter file = new FileWriter(filePath);
            file.write(matrix.size() + "\n");

            for (List<Integer> row : matrix) {
                for (Integer edge : row)
                    file.write(edge + " ");
                file.write("\n");
            }

            file.close();

        } catch (IOException e) {
            System.out.print(e.getMessage());
        }

    }

    public void printMatrix() {

        System.out.println(matrix.size() + "\n");

        for (List<Integer> row : matrix) {
            for (Integer edge : row) {
                System.out.print(edge + " ");
            }
            System.out.println();
        }
        System.out.println();

        saveMatrix();

    }
}
