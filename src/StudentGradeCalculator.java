import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class StudentGradeCalculator {
    public static void main(String[] args) {
        String filePath = "D:\\inner\\JavaLearn\\project\\learn-demo\\learn-demo\\src\\class_problem_sequence.txt";
        int excellentCount = 0, goodCount = 0, averageCount = 0, poorCount = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;

            int studentTotalQuestions = 0;
            int studentCorrectAnswers = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;

                // 读取每个学生的答题情况
                if (lineCount % 3 == 1) {
                    // 第一行表示题目数量
                    studentTotalQuestions = Integer.parseInt(line.trim());
                } else if (lineCount % 3 == 0) {
                    // 第三行表示答题正确情况
                    String[] answers = line.trim().split(",");
                    studentCorrectAnswers = 0;
                    for (String answer : answers) {
                        if (answer.trim().equals("1")) {
                            studentCorrectAnswers++;
                        }
                    }

                    // 计算该学生的正确率
                    double accuracy = (double) studentCorrectAnswers / studentTotalQuestions;
                    
                    // 分类
                    if (accuracy >= 0.85) {
                        excellentCount++;
                    } else if (accuracy >= 0.70) {
                        goodCount++;
                    } else if (accuracy >= 0.50) {
                        averageCount++;
                    } else {
                        poorCount++;
                    }
                }
            }

            // 输出优、良、中、差的统计结果
            System.out.println("优等生人数: " + excellentCount);
            System.out.println("良等生人数: " + goodCount);
            System.out.println("中等生人数: " + averageCount);
            System.out.println("差等生人数: " + poorCount);

        } catch (IOException e) {
            System.err.println("文件读取错误: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("文件格式错误: " + e.getMessage());
        }
    }
}
