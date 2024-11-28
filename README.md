# 数据处理
用于任务
- 评估学生的学习兴趣和认知水平
- 自动生成学情报告与分析

## 使用技术
java jdk1.8

## 说明
目前提供的数据，由于数据不完整，缺失一些必要的信息，所以数据处理过程中仍需要人为介入

缺少：
* 学生姓名
* 知识点的名称没有给出来，现阶段处理方式是：用知识点1、知识点2、... 、知识点n替代

## 文件说明：
* class_problem_sequence.txt   班级里每个学生的答题序列
* ClassAccuracyCalculator.java	 	计算班级的答题正确率
* FocusStudentsFinder.java			 计算重点关注的前3个学生
* KnowledgePointProcessor.java 	处理mastery_knowledge_points.txt文件，转换为json
* KnowledgePointTrendSimulator.java  计算学生知识点掌握情况的变化趋势，目前模型只能获取知识点掌握情况的最后一个状态，需要获取知识点掌握情况的中间状态
* mastery_knowledge_points.txt  知识点掌握状态数组，默认学生ID为0,1,...,n
* ScoreRangeCalculator.java  计算不同分数段的人数分布
* StudentGradeCalculator.java  计算优、良、中、差人数分布
同时，其中的class_problem_sequence.txt是模型的输入数据，mastery_knowledge_points.txt是模型的输出数据
