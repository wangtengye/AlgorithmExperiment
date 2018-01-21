package com.wty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Order implements Comparable<Order> {
	private int begin;
	private int end;
	private int person;

	public Order(int begin, int end, int person) {
		this.begin = begin;
		this.end = end;
		this.person = person;
	}

	public int getBegin() {
		return begin;
	}

	public int getEnd() {
		return end;
	}

	public int getPerson() {
		return person;
	}

	@Override
	public int compareTo(Order o) {
		if (begin == o.getBegin())
			return end - o.getEnd();
		return begin - o.getBegin();
	}

}

public class Problem4 {
	static int result;
	static int capacity;

	static void dfs(int i, int person, int money, Order[] orders, int[] down) {
		if (i == orders.length) {
			result = Math.max(result, money);
			return;
		}
		if (i > 0) {
			for (int j = orders[i - 1].getBegin() + 1; j <= orders[i].getBegin(); j++)
				person -= down[j];
		}
		if (person + orders[i].getPerson() <= capacity) {
			person += orders[i].getPerson();
			money += (orders[i].getEnd() - orders[i].getBegin()) * orders[i].getPerson();
			down[orders[i].getEnd()] += orders[i].getPerson();
			dfs(i + 1, person, money, orders, down);
			person -= orders[i].getPerson();
			money -= (orders[i].getEnd() - orders[i].getBegin()) * orders[i].getPerson();
			down[orders[i].getEnd()] -= orders[i].getPerson();
		}
		dfs(i + 1, person, money, orders, down);
	}

	static void readFile(List<Integer> capacityList, List<Order[]> ordersList, String path) {
		try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
			while (true) {
				String[] strings = reader.readLine().split(" ");
				int capacity = Integer.valueOf(strings[0].trim());
				if (capacity == 0)
					break;
				capacityList.add(capacity);
				int total = Integer.valueOf(strings[2].trim());
				Order[] orders = new Order[total];
				for (int i = 0; i < total; i++) {
					String[] strings2 = reader.readLine().split(" ");
					int begin = Integer.valueOf(strings2[0].trim());
					int end = Integer.valueOf(strings2[1].trim());
					int person = Integer.valueOf(strings2[2].trim());
					orders[i] = new Order(begin, end, person);
				}
				ordersList.add(orders);
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	static void writeToFile(List<Integer> resultList, String path) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
			for (int result : resultList) {
				writer.write(result + "");
				writer.newLine();
			}
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		List<Integer> capacityList = new ArrayList<>();
		List<Order[]> ordersList = new ArrayList<>();
		List<Integer> resultList = new ArrayList<>();
		readFile(capacityList, ordersList, "src/problem4/input.txt");
		for (int i = 0; i < capacityList.size(); i++) {
			int[] down = new int[30];
			result = 0;
			capacity = capacityList.get(i);
			Order[] orders = ordersList.get(i);
			Arrays.sort(orders);
			dfs(0, 0, 0, ordersList.get(i), down);
			resultList.add(result);
			writeToFile(resultList, "src/problem4/output.txt");
		}
	}
}
