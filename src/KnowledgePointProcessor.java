import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class KnowledgePointProcessor {
    public static void main(String[] args) {
        String inputFilePath = "mastery_knowledge_points.txt"; // 输入文件路径
        String outputFilePath = "knowledge_point.json"; // 输出文件路径

        List<String> outputLines = new ArrayList<>();
        outputLines.add("{\"students\": [");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int studentId = 1; // 学生 ID 从 1 开始

            while ((line = br.readLine()) != null) {
                line = line.trim();
                if (!line.isEmpty()) {
                    // 处理每一行
                    String[] points = line.replaceAll("\\[|\\]", "").trim().split(",\\s*");

                    // 构造新的知识点格式
                    StringBuilder knowledgePointJson = new StringBuilder("\"knowledge_points\": [");
                    for (int i = 0; i < points.length; i++) {
                        knowledgePointJson.append("{\"知识点").append(i + 1).append("\": ").append(points[i]).append("}");
                        if (i < points.length - 1) {
                            knowledgePointJson.append(", ");
                        }
                    }
                    knowledgePointJson.append("]");

                    // 生成最终的 JSON 行
                    String studentJson = "{" +
                            "\"student_id\": " + studentId + ", " +
                            knowledgePointJson +
                            "}";
                    outputLines.add(studentJson);
                    studentId++; // 增加学生 ID
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        outputLines.add("]}");

        // 将结果写入输出文件
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(outputFilePath))) {
            for (String line : outputLines) {
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println("处理完成，结果已保存到 " + outputFilePath);
    }
}
