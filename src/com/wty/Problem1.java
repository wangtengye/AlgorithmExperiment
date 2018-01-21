package com.wty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class Problem1 {

	static void printArray(int[] arr, int n) {
		for (int i = 0; i < n; i++)
			System.out.print(arr[i] + " ");
		System.out.println();
	}

	static int minSum(int[] arr, int n) {
		int sum = 0;
		for (int i = 0; i < n / 2; i++)
			sum += arr[n - 1 - i] - arr[i];
		return sum;
	}

	static void quicksort(int[] arr, int l, int r) {
		int left = l;
		int right = r;
		if (left >= right)
			return;
		int pivot = arr[left];
		while (left < right) {
			while (arr[right] >= pivot && right > left)
				right--;
			if (left < right)
				arr[left++] = arr[right];
			while (arr[left] < pivot && right > left)
				left++;
			if (left < right)
				arr[right--] = arr[left];
		}
		arr[left] = pivot;
		quicksort(arr, left + 1, r);
		quicksort(arr, l, left - 1);
	}

	static int readFile(int[] arr, String path) {
		int total = 0;
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			int index = 0;
			total = Integer.valueOf(reader.readLine().trim());
			String string;
			while ((string = reader.readLine()) != null) {
				arr[index++] = Integer.valueOf(string.split(" ")[1].trim());

			}

		} catch (IOException ex) {
			ex.printStackTrace();
		}
		return total;
	}

	static void writeToFile(int sum, String path) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			writer.write(sum + "");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		int y[] = new int[10000];
		int total = readFile(y, "src/problem1/input.txt");
		quicksort(y, 0, total - 1);
		writeToFile(minSum(y, total), "src/problem1/output.txt");
	}
}
