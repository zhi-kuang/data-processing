import java.io.*;
import java.util.*;

public class KnowledgePointTrendSimulator {
    public static void main(String[] args) {
        String inputFilePath = "mastery_knowledge_points.txt"; // 输入文件
        String outputFilePath = "knowledge_point_trends.json"; // 输出文件
        List<Map<String, Object>> studentsList = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(inputFilePath))) {
            String line;
            int studentId = 1;

            while ((line = br.readLine()) != null) {
                String[] values = line.replace("[", "").replace("]", "").split(",");
                List<Map<String, Object>> knowledgePoints = new ArrayList<>();

                for (int i = 0; i < values.length; i++) {
                    double currentScore = Double.parseDouble(values[i].trim());
                    List<String> trendData = generateTrendData(currentScore);

                    Map<String, Object> pointData = new HashMap<>();
                    pointData.put("Trend", trendData); // 模拟生成的变化数据
                    knowledgePoints.add(Collections.singletonMap("知识点" + (i + 1), pointData));
                }

                Map<String, Object> studentData = new HashMap<>();
                studentData.put("student_id", studentId++);
                studentData.put("knowledge_points", knowledgePoints);
                studentsList.add(studentData);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 输出 JSON 文件
        try (PrintWriter writer = new PrintWriter(new FileWriter(outputFilePath))) {
            writer.println("{");
            writer.println("  \"students\": [");
            for (int i = 0; i < studentsList.size(); i++) {
                Map<String, Object> student = studentsList.get(i);
                writer.print("    {");
                writer.print("\"student_id\": " + student.get("student_id") + ", ");
                writer.print("\"knowledge_points\": [");

                List<Map<String, Object>> knowledgePoints = (List<Map<String, Object>>) student.get("knowledge_points");
                for (int j = 0; j < knowledgePoints.size(); j++) {
                    Map<String, Object> point = knowledgePoints.get(j);
                    for (Map.Entry<String, Object> entry : point.entrySet()) {
                        writer.print("{");
                        writer.print("\"" + entry.getKey() + "\": {");
                        // 生成 Trend 数组的 JSON 格式
                        writer.print("\"Trend\": [");
                        List<String> trend = (List<String>) ((Map<String, Object>) entry.getValue()).get("Trend");
                        for (int k = 0; k < trend.size(); k++) {
                            writer.print(trend.get(k));
                            if (k < trend.size() - 1) {
                                writer.print(", ");
                            }
                        }
                        writer.print("]");
                        writer.print("}");
                        writer.print("}");
                    }
                    if (j < knowledgePoints.size() - 1) {
                        writer.print(", ");
                    }
                }
                writer.print("]");
                writer.print("}");
                if (i < studentsList.size() - 1) {
                    writer.println(",");
                }
            }
            writer.println("  ]");
            writer.println("}");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // 根据当前掌握情况生成5个变化数据
    private static List<String> generateTrendData(double currentScore) {
        List<String> trendData = new ArrayList<>();
        Random random = new Random();
        trendData.add(String.format("%.4f", currentScore)); // 添加当前掌握情况

        for (int i = 1; i < 5; i++) { // 生成接下来的4个数据点
            double fluctuation = random.nextDouble() * 0.1 - 0.05; // 随机波动 -0.05到0.05之间
            double newScore = Math.max(0.0, Math.min(1.0, Double.parseDouble(trendData.get(i - 1)) + fluctuation)); // 确保掌握情况在0到1之间
            trendData.add(String.format("%.4f", newScore)); // 保留四位小数
        }
        return trendData;
    }
}
