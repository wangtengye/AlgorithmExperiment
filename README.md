# 算法设计与分析 实验题目
## 第一题
##### 输油管道问题
Time Limit:	2000MS		Memory Limit:	32768K
#### Description
某石油公司计划建造一条由东向西的主输油管道。该管道要穿过一个有n 口油井的油田。从每口油井都要有一条输油管道沿最短路经(或南或北)与主管道相连。如果给定n口油井的位置,即它们的x 坐标（东西向）和y 坐标（南北向）,应如何确定主管道的最优位置, 即使各油井到主管道之间的输油管道长度总和最小的位置?证明可在线性时间内确定主管道的最优位置。 给定n 口油井的位置,计算各油井到主管道之间的输油管道最小长度总和。要求使用快速排序.
#### Input
输入的第1 行是油井数n，1<=n<=10000。接下来n 行是油井的位置，每行2个整数x和y，-10000<=x，y<=10000。
#### Output
输出油井到主管道之间的输油管道最小长度总和。
##### Sample Input
5 
1 2
2 2
1 3
3 -2
3 3
##### Sample Output
6
##### Hint
使用快速排序，找到中间数，如果有两个中间数，可任取一个。

### 解法
使用快速排序对实验给出的y轴数据进行排序，取中位数，然后再计算最小长度总和。

## 第二题
##### 石子合并问题

Time Limit:	2000MS		Memory Limit:	30000K
#### Description
在一个圆形操场的四周摆放N堆石子(N≤100)，现要将石子有次序地合并成一堆。规定每次只能选相邻的两堆合并成新的一堆，并将新的一堆的石子数，记为该次合并的得分。编一程序，读入堆数N及每堆石子数(≤100)选择一种合并石子的方案，分别得到合并这N堆石子为一堆，可以得到的最大得分和最小得分
#### Input
输入包含多个例子。第一行为N，即石子堆的数目，以下一行为N个整形，分别代表每堆石子的数目。当N=0时，输入结束。
#### Output
对每个例子，输出其最小得分和最大得分，这两个数值以空格间隔开，每个例子占一行。
##### Sample Input
6
30 35 15 5 10 20 
3
1 2 3333 
6
3 4 5 6 7 8 
0
##### Sample Output
275 475
3339 6671
84 125
##### Hint
动态规划 石子堆是环状的
### 解法
先把环状石子当成线状石子，则有公式
对于最小得分有f[i][j] = min(f[i][k]+f[k+1][j]+s[i][j])(i<=k<j),
对于最大得分有f[i][j] = max(f[i][k]+f[k+1][j]+s[i][j])(i<=k<j),
其中f[i][j]代表相应的得分，sum[i][j]代表第i堆到第j堆石子的总石子数。
然后让每一堆的石子都有打头的机会，循环一圈，取相应最大值和最小值即得到环状石子的结果。


## 第三题
##### 最优合并问题

Time Limit:	1000MS		Memory Limit:	30000K
#### Description
给定k个序列s1,s2,s3,...,sk,用二路合并方法将k个序列合并为一个。假设将任意两个长度分别为n和m的序列合并为一个需要的代价是m+n-1，设计一个算法来确定合并这些序列的合并为一个的最大代价和最小代价。
#### Input
输入含有多个例子，每例占有两行，第一行是一个整数N，表示序列共有N个，第二行为N个整数，代表各个序列的长度。N<1000,且每个序列长度都小于1000;输入以0结束。
#### Output
每个例子输出占用一行，输出包含最大代价和最小代价，以空格分离这两个数。
##### Sample Input
4 5 12 11 2 0
##### Sample Output
78 52
##### Hint
可以参考哈夫曼编码 (要求使用堆，减少时间复杂度)

### 解法
这道题可以用贪心算法，对于最大代价，用大顶堆储存每个序列，每次取其中两个序列最大的合并，合并完再放回大顶堆，直到大顶堆里面只有一个元素。求最小代价思想差不多，换成小顶堆来储存即可。




## 第四题
##### 列车问题

Time Limit:	2000MS		Memory Limit:	30000K
#### Description
从A到B有若干个车站，编号从0到m，列车的最大载客量是n。每次列车开车之前，会从各个车站收集订票信息。一共有t条订票信息，一条订票信息包括：起点站，终点站，人数。票价在数值上等于起点站与终点之间的车站数（包括终点站，不包括起点站）。由于列车的最大载客量是一定的，所以不一定能接受所有的订票。对于一条订票order，只能全部接受，或者是全部拒绝。现在选择接受订票使之利润最大，输出这个最大利润.
#### Input
含多个例子，每个例子第一行为三个整数n,m,t,分别代表最大载客量、除A外的车站数量、订票信息总量。接下去t行为订票信息，每一行代表一条订票信息，一条订票信息包括三个整数，分别是起点站编号、终点站编号、人数。以0 0 0结束输入.m<30,t<30。
#### Output
每个例子输出一行，代表最大利润值。
##### Sample Input
10 3 4 
0 2 1
1 3 5
1 2 7
2 3 10
10 5 4
3 5 10
2 4 9
0 2 5
2 5 8
0 0 0
##### Sample Output
19
34
##### Hint
剪枝

### 解法
显然这是一个DFS的问题，但必须进行剪枝操作。首先按起点站进行排序，如果起点站相同，则按终点站排。然后按着顺序DFS：每接受一个订单，就把沿线所在站载客量全部加上这个订单中的人数，如果发现哪个站的人数超过了最大载客量n，就必须停下来，这个订单是不能接受的，就把所在站载客量中加上的人数全部减下来。如果所有沿线所在站都没有超过n，就可以加上该订单的利润，开始测试下一个订单，测试完成之后取消沿线所在站加的人数回溯。每当开始测试订单时，就把已经能获得的利润用参数的形式传递过去，保存这个最大值max。最后把所有的情况都遍历之后，输出max即得结果。

***
## 01背包问题
背包问题可以用分支限界法来解决，先按每个物品的单位价值降序排序。
为每个搜索节点设置一个上界ub (upper bound)，方法是：把已经选择的物品总价值v， 加上背包剩余承重量C-w与剩下可选择的物品的最高“ 价值-重量
比” 的乘积， 即ub = v + (C-w)×(vi/wi)max，然后每次取出ub最大的结点，直到取出的结点已经遍历完所有的物品。
