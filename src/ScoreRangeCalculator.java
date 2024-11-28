import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ScoreRangeCalculator {
    public static void main(String[] args) {
        String filePath = "D:\\inner\\JavaLearn\\project\\learn-demo\\learn-demo\\src\\class_problem_sequence.txt";
        Map<String, Integer> scoreRangeDistribution = new HashMap<>();
        scoreRangeDistribution.put("0-60", 0);
        scoreRangeDistribution.put("60-80", 0);
        scoreRangeDistribution.put("80-100", 0);

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;

            int studentTotalQuestions = 0;
            int studentCorrectAnswers = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;

                // 每个学生的数据包含三行
                if (lineCount % 3 == 1) {
                    // 第一行表示题目数量
                    studentTotalQuestions = Integer.parseInt(line.trim());
                } else if (lineCount % 3 == 0) {
                    // 第三行表示答题情况，0 表示错，1 表示对
                    String[] answers = line.trim().split(",");
                    studentCorrectAnswers = 0;
                    for (String answer : answers) {
                        if (answer.trim().equals("1")) {
                            studentCorrectAnswers++;
                        }
                    }

                    // 计算该学生的正确率
                    double accuracy = (double) studentCorrectAnswers / studentTotalQuestions * 100;

                    // 分配到相应的分数段
                    if (accuracy < 60) {
                        scoreRangeDistribution.put("0-60", scoreRangeDistribution.get("0-60") + 1);
                    } else if (accuracy < 80) {
                        scoreRangeDistribution.put("60-80", scoreRangeDistribution.get("60-80") + 1);
                    } else {
                        scoreRangeDistribution.put("80-100", scoreRangeDistribution.get("80-100") + 1);
                    }
                }
            }

            // 输出结果
            System.out.println("\"scoreRangeDistribution\": {");
            for (Map.Entry<String, Integer> entry : scoreRangeDistribution.entrySet()) {
                System.out.println("    \"" + entry.getKey() + "\": " + entry.getValue() + ",");
            }
            System.out.println("}");
            
        } catch (IOException e) {
            System.err.println("文件读取错误: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("文件格式错误: " + e.getMessage());
        }
    }
}
