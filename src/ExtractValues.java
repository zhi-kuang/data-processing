import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ExtractValues {
    public static void main(String[] args) {
        String inputFile = "D:\\inner\\JavaLearn\\project\\learn-demo\\learn-demo\\src\\知识掌握程度.txt"; // 输入文件路径
        String outputFile = "your_output_file.txt"; // 输出文件路径

        try (BufferedReader reader = new BufferedReader(new FileReader(inputFile));
             BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            boolean insideBrackets = false; // 是否在方括号内

            while ((line = reader.readLine()) != null) {
                // 检查是否包含方括号
                if (line.contains("[")) {
                    int startIndex = line.indexOf("[");
                    stringBuilder.append(line.substring(startIndex + 1).trim()); // 提取左边的内容
                    insideBrackets = true; // 标记为在方括号内
                }

                if (insideBrackets) {
                    stringBuilder.append(" "); // 添加空格分隔
                }

                // 检查是否包含右方括号
                if (line.contains("]")) {
                    int endIndex = line.indexOf("]");
                    stringBuilder.append(line.substring(0, endIndex).trim()); // 提取右边的内容
                    // 将结果写入输出文件
                    String extractedValue = stringBuilder.toString().trim();
                    // 去掉多余的空格
                    extractedValue = extractedValue.replaceAll("\\s+", " ");
                    writer.write("[" + extractedValue + "]");
                    writer.newLine(); // 换行
                    // 重置 StringBuilder 和标记
                    stringBuilder.setLength(0);
                    insideBrackets = false;
                } else if (insideBrackets) {
                    // 如果在方括号内，但没有右方括号，继续读取下一行
                    stringBuilder.append(line.trim()); // 收集下一行内容
                }
            }
            System.out.println("提取完成，结果已写入 " + outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
