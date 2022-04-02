package com.interview.question.implementations;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

public class LRUCache {
	int capacity;
	Map<Integer, String> cache;
	Deque<Integer> lruQueue;
	
	public LRUCache(int capacity) {
		this.capacity = capacity;
		this.cache = new HashMap<>();
		this.lruQueue = new LinkedList<>();
	}
	public String get (int key) {
		if(cache.containsKey(key)) {
			lruQueue.remove(key);
		}
		lruQueue.push(key);
		return cache.containsKey(key)?cache.get(key):null;
	}
	public String put (int key,String value) {
		if (!cache.containsKey(key)) {
			if(this.capacity == cache.size()) {
				int tempKey = lruQueue.removeLast();
				cache.remove(tempKey);
			}
		}else {
			lruQueue.remove(key);
		}
		cache.put(key, value);
		lruQueue.push(key);
		return cache.get(key);
	}
	public void display () {
		System.out.println("Printing Map");
		for(Map.Entry<Integer, String> entry: cache.entrySet() ) {
			System.out.println(entry);
		}
		System.out.println("Printing Queue");
		for (int key : lruQueue) {
			System.out.println(key);
		}
	}
}
