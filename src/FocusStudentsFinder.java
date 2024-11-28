import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

class Student {
    int index;       // 学生序号
    double accuracy; // 正确率

    public Student(int index, double accuracy) {
        this.index = index;
        this.accuracy = accuracy;
    }
}

public class FocusStudentsFinder {
    public static void main(String[] args) {
        String filePath = "D:\\inner\\JavaLearn\\project\\learn-demo\\learn-demo\\src\\class_problem_sequence.txt";
        List<Student> students = new ArrayList<>();
        int studentIndex = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineCount = 0;
            int studentTotalQuestions = 0;
            int studentCorrectAnswers = 0;

            while ((line = reader.readLine()) != null) {
                lineCount++;

                if (lineCount % 3 == 1) {
                    studentTotalQuestions = Integer.parseInt(line.trim());
                } else if (lineCount % 3 == 0) {
                    String[] answers = line.trim().split(",");
                    studentCorrectAnswers = 0;
                    for (String answer : answers) {
                        if (answer.trim().equals("1")) {
                            studentCorrectAnswers++;
                        }
                    }

                    double accuracy = (double) studentCorrectAnswers / studentTotalQuestions * 100;
                    students.add(new Student(studentIndex, accuracy));

                    studentIndex++;
                }
            }

            // 排序，按照正确率从低到高
            students.sort(Comparator.comparingDouble(s -> s.accuracy));

            // 输出成绩最差的三个学生的序号
            System.out.println("重点关注学生序号：");
            for (int i = 0; i < Math.min(3, students.size()); i++) {
                System.out.println("学生序号：" + students.get(i).index + "，正确率：" + students.get(i).accuracy + "%");
            }

        } catch (IOException e) {
            System.err.println("文件读取错误: " + e.getMessage());
        } catch (NumberFormatException e) {
            System.err.println("文件格式错误: " + e.getMessage());
        }
    }
}
