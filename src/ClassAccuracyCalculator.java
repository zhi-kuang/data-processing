import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class ClassAccuracyCalculator {
    public static void main(String[] args) {
        String filePath = "D:\\inner\\JavaLearn\\project\\learn-demo\\data-processing\\src\\class_problem_sequence.txt";
        int totalQuestions = 0;
        int correctAnswers = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;
                
                // 每个学生的数据包含三行
                if (lineCount % 3 == 1) {
                    // 第一行表示题目数量
                    totalQuestions += Integer.parseInt(line.trim());
                } else if (lineCount % 3 == 0) {
                    // 第三行表示答题情况，0 表示错，1 表示对
                    String[] answers = line.trim().split(",");
                    for (String answer : answers) {
                        if (answer.trim().equals("1")) {
                            correctAnswers++;
                        }
                    }
                }
            }

            // 计算并打印正确率
            double accuracy = (totalQuestions > 0) ? (double) correctAnswers / totalQuestions : 0;
            System.out.printf("班级的正确率为：%.2f%%\n", accuracy * 100);
        } catch (IOException e) {
            System.err.println("文件读取错误: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("文件格式错误: " + e.getMessage());
        }
    }


}
