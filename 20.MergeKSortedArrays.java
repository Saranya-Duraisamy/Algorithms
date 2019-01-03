/*
 * Merge k-sorted integer lists
 */
import java.util.*;
class Solution {
    int[] ArrayListCompare(ArrayList<Integer> list) {
        int min = Integer.MAX_VALUE;
        int listIndex = Integer.MIN_VALUE;
        for(int i=0; i<list.size(); i++){
            if(min > list.get(i)){
                min = list.get(i);
                listIndex = i;
            }
        }
        return new int[]{min, listIndex};
    }

    public ArrayList<Integer> mergeSortedLists(ArrayList<ArrayList<Integer>> input, int n, int m){
        int[] returnValue;

        ArrayList<Integer> firstElementList = new ArrayList<Integer>();
        ArrayList<Integer> output = new ArrayList<Integer>(m);

        for(int outer = 0; outer < m; outer++) {
            for(ArrayList<Integer> listIterator: input) {
                if( !(listIterator.isEmpty())) { //Do only if list has some more elements
                    firstElementList.add(listIterator.get(0)); //Pick first element from each list to find min
                }
                else { //This is done to maintain list order
                    firstElementList.add(Integer.MAX_VALUE);
                }
            }
            returnValue = ArrayListCompare(firstElementList);
            output.add(returnValue[0]); //Store  min value to output list
            (input.get(returnValue[1])).remove(0);//Remove minimum element from corresponding list
            System.out.println(output+ " \t " + input);
            firstElementList.clear();
        }
        return output;
    }
    static ArrayList<Integer> mergeSortedListsPriorityQueue(ArrayList<ArrayList<Integer>> input, int n, int m){
        ArrayList<Integer> output = new ArrayList<Integer>(m);
        PriorityQueue<Integer> queue = new PriorityQueue<Integer>(n);
        for(int i=0; i<m; i++) {
            for (int loop = 0; loop < n; loop++) {
                if (!input.get(loop).isEmpty()) {
                    int elt = input.get(loop).remove(0);
                    queue.add(elt);
                }
                else {
                    input.remove(loop);
                    n--;
                }
            }
            output.add(queue.poll());
        }
        return output;
    }
    public static void main(String args[]) {
        int n = 3;
        int m = 10;
        ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
        ArrayList<Integer> sublist1 = new ArrayList<Integer>();
        ArrayList<Integer> sublist2 = new ArrayList<Integer>();
        ArrayList<Integer> sublist3 = new ArrayList<Integer>();
        sublist1.add(2);
        sublist1.add(4);
        sublist1.add(10);
        list.add(sublist1);
        sublist2.add(5);
        sublist2.add(14);
        sublist2.add(21);
        list.add(sublist2);
        sublist3.add(1);
        sublist3.add(3);
        sublist3.add(18);
        sublist3.add(30);
        list.add(sublist3);
        System.out.println(list);
        Solution solution = new Solution();
        ArrayList<Integer> output = mergeSortedListsPriorityQueue(list, n, m);//solution.mergeSortedLists(list, n, m);
        System.out.println(output);
    }
}