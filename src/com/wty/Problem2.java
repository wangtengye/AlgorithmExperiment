package com.wty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Problem2 {
	static final int MAX = 999999999;
	static final int MIN = 0;

	static int[] merge(int total, int[] stones) {
		int[][] max = new int[total][total];
		int[][] min = new int[total][total];
		for (int i = 0; i < total; i++) {
			max[i][i] = 0;
			min[i][i] = 0;
		}

		for (int mergeCount = 2; mergeCount <= total; mergeCount++) {
			for (int begin = 0; begin <= total - mergeCount; begin++) {
				int end = begin + mergeCount - 1;
				int sum = 0;
				for (int i = begin; i <= end; i++)
					sum += stones[i];
				max[begin][end] = MIN;
				min[begin][end] = MAX;
				for (int k = begin; k < end; k++) {
					int ma = max[begin][k] + max[k + 1][end] + sum;
					int mi = min[begin][k] + min[k + 1][end] + sum;
					if (ma > max[begin][end])
						max[begin][end] = ma;
					if (mi < min[begin][end])
						min[begin][end] = mi;
				}

			}
		}

		return new int[] { min[0][total - 1], max[0][total - 1] };
	}

	static int[] calculate(int total, int[] stones) {
		int max = MIN;
		int min = MAX;
		for (int i = 0; i < total; i++) {
			int temp = stones[0];
			for (int j = 0; j < total - 1; j++)
				stones[j] = stones[j + 1];
			stones[total - 1] = temp;
			int[] result = merge(total, stones);
			if (min > result[0])
				min = result[0];
			if (max < result[1])
				max = result[1];
		}
		return new int[] { min, max };
	}

	static void readFile(List<Integer> totalList, List<int[]> stonesList, String path) {
		int total = 0;

		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			while (true) {
				total = Integer.valueOf(reader.readLine().trim());
				if (total == 0)
					break;
				String string = reader.readLine();
				String[] stones = string.split(" ");
				int arr[] = new int[total];
				for (int i = 0; i < total; i++)
					arr[i] = Integer.valueOf(stones[i].trim());
				totalList.add(total);
				stonesList.add(arr);

			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	static void writeToFile(List<int[]> resultList, String path) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			for (int[] result : resultList) {
				writer.write(result[0] + " " + result[1]);
				writer.newLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		List<Integer> totalList = new ArrayList<>();
		List<int[]> stonesList = new ArrayList<>();
		List<int[]> resultList = new ArrayList<>();
		readFile(totalList, stonesList, "src/problem2/input.txt");
		for (int i = 0; i < totalList.size(); i++) {
			int total = totalList.get(i);
			int[] stones = stonesList.get(i);
			resultList.add(calculate(total, stones));
			writeToFile(resultList, "src/problem2/output.txt");
		}
	}
}
