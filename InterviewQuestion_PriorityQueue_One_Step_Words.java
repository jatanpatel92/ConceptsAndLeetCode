/*

Given two strings s1 and s2, we will call (s1, s2) a "step" if you can form s2 by adding exactly one letter to s1 and possibly rearranging the letters of s1.

For example:
(OF, FOR) is a step
(OF, IF) is not a step
(OF, OCT) is not a step
(ERA, EAR) is not a step
(SHE, SHEEP) is not a step
(TEE, TEST) is not a step

Given a wordlist, produce an index
   w -> {  w1 | (w, w1) is a step }
that associates to each word all the words in the wordlist that are a step away from it.

index = step_index(wordlist)

# Expected output (pseudocode, unordered):

NO     : [ ONE, NOT, NOW ]
INTO   : [ POINT ]
LEFT   : []
FORM   : [ FORMS ]
ONE    : []
FOUR   : []
FOR    : [ FORM, FOUR, FROM ]
FROM   : [ FORMS ]
OFF    : []
FORMS  : []
NOT    : [ INTO ]
OF     : [ FOR, OFF ]
NOW    : []
POINT  : []
ON     : [ ONE, NOT, NOW ]

Complexity analysis variables:
n: The number of words in the word list.
k: The maximum length of any word in the word list
*/

import java.io.*;
import java.util.*;

public class Solution {
  public static void main(String[] argv) {
    String[] counts = {
      "POINT,333858038",
      "NOT,4522732626",
      "INTO,1144226142",
      "ON,4594521081",
      "FOR,6545282031",
      "NOW,679337516",
      "ONE,2148983086",
      "BEHAVIOR,104177552",
      "WAITS,2911079",
      "PEOPLE,658716166",
      "HI,15453893",
      "FORM,352032932",
      "OF,30966074232",
      "THROUGH,647091198",
      "BETWEEN,744064796",
      "FOUR,262968583",
      "LEFT,306802162",
      "OFF,302535533",
      "FROM,3469207674",
      "NO,1400645478",
      "FORMS,136468034",
      "A,45916054218"
    };
    System.out.println(get_list(counts, 15, 5));
  }

  //Write your function here
  private static Map<String, List<String>> step_index(List<String> words){
      Map<String, List<String>> map = new HashMap<>();
      Map<String, Map<Character, Integer>> anagramMap = new HashMap<>();
      Set<String> set = new HashMap<>();
      for(String word: words){
        String[] arr = word.split(":");
        Map<Character, Integer> freqMap = new HashMap<>();
        for(char letter:arr[0].toCharArray()){
          freqMap.put(letter, freqMap.getOrDefault(letter, 0)+1);
        }
        anagramMap.put(arr[0], freqMap);
        set.add(arr[0]);
      }
      for(Map.Entry<String, Map<Character, Integer>> entry : anagramMap.entrySet()){
          for(String word: set){
            if(!entry.getKey().equals(word)){
                if(isOneStepAway(anagramMap, entry.getKey(), word)){
                    List<String> value = map.getOrDefault(entry.getKey(), new LinkedList<>());
                    value.add(word);
                    map.put(entry.getKey(),value);
                }
            }
          }
      }
      return map;
  }
  private static boolean isOneStepAway(Map<String, Map<Character, Integer>> anagramMap, String str1, String str2){
      if(anagramMap.get(str1).equals(anagramMap.get(str2))) return false;
      Map<Character, Integer> map1 = anagramMap.get(str1);
      Map<Character, Integer> map2 = anagramMap.get(str2);
      int step = 0;
      for(Character key1 : map1.keySet()){
          if(map2.keySet().contains(key1) && (map2.get(key1) == map1.get(key1)+1 || (map1.get(key1) == map2.get(key1)+1))  ){
              step++;
          }
      }
      return step == 1?true:(map1.entrySet().containsAll(map2.entrySet()) || map2.entrySet().containsAll(map1.entrySet()));
  }
  private static List<String> get_list(String[] counts, int capacity, int length){
    List<String> result = new LinkedList<>();
    PriorityQueue<Entity> priorityQueue = new PriorityQueue<>();
    for(String count: counts){
      String[] arr = count.split(",");
      String key = arr[0];
      String strVal = arr[1];
      priorityQueue.offer(new Entity(key, Long.valueOf(strVal)));
    }
    int count = 0;
    while(!priorityQueue.isEmpty()){
      Entity entity = priorityQueue.poll();
      if(entity.key.length()<=length && entity.key.length()>=2){
        result.add(entity.key + ": "+ entity.frequency);
        count++;
      }
      if(count == capacity)
        break;
    }
    return result;

  }
  public static class Entity implements Comparable<Entity>{
    String key;
    int len;
    long frequency;
    public Entity(String k, long l){
      this.key = k;
      this.frequency = l;
    }
    @Override
    public int compareTo(Entity entity){
        if(this.frequency == entity.frequency)
          return this.key.length()-entity.key.length();
        return this.frequency >= entity.frequency ? -1:1;
    }
  }
}
