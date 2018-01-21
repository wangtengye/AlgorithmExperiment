package com.wty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class Problem3 {

	static int[] calculate(int[] series) {
		int max = 0;
		int min = 0;
		PriorityQueue<Integer> maxQueue = new PriorityQueue<>((a, b) -> b - a);
		
		PriorityQueue<Integer> minQueue = new PriorityQueue<>((a, b) -> a - b);
		for (int num : series) {
			maxQueue.add(num);
			minQueue.add(num);
		}
		while (maxQueue.size() != 1) {
			int max1 = maxQueue.poll();
			int max2 = maxQueue.poll();
			max += max1 + max2 - 1;
			maxQueue.add(max1 + max2);
			int min1 = minQueue.poll();
			int min2 = minQueue.poll();
			min += min1 + min2 - 1;
			minQueue.add(min1 + min2);
		}
		return new int[] { max, min };
	}

	static void readFile(List<int[]> seriesList, String path) {
		int total = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			while (true) {
				total = Integer.valueOf(reader.readLine().trim());
				if (total == 0)
					break;
				String string = reader.readLine();
				String[] series = string.split(" ");
				int arr[] = new int[total];
				for (int i = 0; i < total; i++)
					arr[i] = Integer.valueOf(series[i].trim());
				seriesList.add(arr);
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
		List<int[]> seriesList = new ArrayList<>();
		List<int[]> resultList = new ArrayList<>();
		readFile(seriesList, "src/problem3/input.txt");
		for (int i = 0; i < seriesList.size(); i++) {
			int[] series = seriesList.get(i);
			resultList.add(calculate(series));
			writeToFile(resultList, "src/problem3/output.txt");
		}
	}
}
