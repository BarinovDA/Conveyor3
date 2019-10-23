package Main;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Conveyor {
    public int arrayLength;
    public LinkedList<Integer> arrList = new LinkedList<>();

    public Conveyor(int length) {
        arrayLength = length;
    }

    public void add(int index){
        arrList.addFirst(index);
    }
    public  void add(int index, int elem){
        arrList.add(index, elem);
    }
    public void addAll (List<Integer> list){
        arrList.addAll(list);
    }
    public  int size(){
        return arrList.size();
    }
    public  void remove(int index){
        arrList.remove(index);
    }
    public  int get (int index){
        return arrList.get(index);
    }

    public void set(int index, int elem) {
        arrList.set(index, elem);
    }

    @Override
    public String toString() {
        return String.valueOf(arrList);
    }

}
