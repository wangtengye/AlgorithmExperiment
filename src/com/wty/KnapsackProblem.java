package com.wty;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;

class Item implements Comparable<Item> {
	private int weight;
	private int value;
	private double valuePerWeight;

	public Item(int weight, int value) {
		this.weight = weight;
		this.value = value;
		this.valuePerWeight = (double) value / weight;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	public double getValuePerWeight() {
		return valuePerWeight;
	}

	@Override
	public int compareTo(Item o) {
		if (this.valuePerWeight < o.valuePerWeight)
			return 1;
		if (this.valuePerWeight > o.valuePerWeight)
			return -1;
		return 0;
	}
}

class Node implements Comparable<Node> {
	private double upperBound;
	private int weight;
	private int value;
	private int level;

	public Node(double upperBound, int weight, int value, int level) {
		this.upperBound = upperBound;
		this.weight = weight;
		this.value = value;
		this.level = level;
	}

	public double getUpperBound() {
		return upperBound;
	}

	public int getWeight() {
		return weight;
	}

	public int getValue() {
		return value;
	}

	public int getLevel() {
		return level;
	}

	@Override
	public int compareTo(Node o) {
		if (this.upperBound < o.upperBound)
			return 1;
		if (this.upperBound > o.upperBound)
			return -1;
		return 0;
	}

}

public class KnapsackProblem {

	static void addItem(Node node, int capacity, PriorityQueue<Node> priorityQueue, Item[] items) {
		int current = node.getLevel() + 1;
		Item currentItem = items[current];
		int leftWeight = capacity - node.getWeight();
		double upperBound;
		int weight;
		int value;
		int level;
		if (leftWeight < currentItem.getWeight()) {
			upperBound = node.getValue();
			weight = node.getWeight();
			value = node.getValue();
			level = items.length - 1;
		} else {
			weight = node.getWeight() + currentItem.getWeight();
			value = node.getValue() + currentItem.getValue();
			level = node.getLevel() + 1;
			if (level == items.length - 1)
				upperBound = (double) value;
			else
				upperBound = value + (capacity - weight) * items[current + 1].getValuePerWeight();
		}

		Node newNode = new Node(upperBound, weight, value, level);
		priorityQueue.add(newNode);

	}

	static void notAddItem(Node node, int capacity, PriorityQueue<Node> priorityQueue, Item[] items) {
		int current = node.getLevel() + 1;
		int weight = node.getWeight();
		int value = node.getValue();
		int level = node.getLevel() + 1;
		double upperBound;
		if (level == items.length - 1)
			upperBound = (double) value;
		else
			upperBound = value + (capacity - weight) * items[current + 1].getValuePerWeight();
		Node newNode = new Node(upperBound, weight, value, level);
		priorityQueue.add(newNode);
	}

	static int readFile(ArrayList<Item> itemList) {
		int capacity = 0;

		try (FileReader fileReader = new FileReader("src/knapsackProblem/input.txt");
				BufferedReader bufferedReader = new BufferedReader(fileReader)) {
			capacity = Integer.valueOf(bufferedReader.readLine().trim());
			String string;
			while ((string = bufferedReader.readLine()) != null) {
				String[] content = string.split(" ");
				int weight = Integer.valueOf(content[0].trim());
				int value = Integer.valueOf(content[1].trim());
				itemList.add(new Item(weight, value));
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return capacity;
	}

	static void writeToFile(int value) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter("src/knapsackProblem/output.txt"))) {
			writer.write(value + "");
		} catch (IOException ex) {
			ex.printStackTrace();
		}
	}

	public static void main(String[] args) {
		ArrayList<Item> itemList = new ArrayList<>();
		int capacity = readFile(itemList);
		Item[] items = new Item[1];
		items = itemList.toArray(items);
		PriorityQueue<Node> priorityQueue = new PriorityQueue<>();
		Arrays.sort(items);
		Node root = new Node(0, 0, 0, -1);
		priorityQueue.add(root);
		while (priorityQueue.size() != 0) {
			Node node = priorityQueue.poll();
			if (node.getLevel() == items.length - 1) {
				writeToFile(node.getValue());
				break;
			}
			addItem(node, capacity, priorityQueue, items);
			notAddItem(node, capacity, priorityQueue, items);
		}
	}

}
