package assn05;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class MaxBinHeapER  <V, P extends Comparable<P>> implements BinaryHeap<V, P> {

    private List<Prioritized<V,P>> _heap;
//if(patients.isEmpty()){
//            return null;
//        }
//        else if(patients.size() == 1){
//            return patients.remove(0);
//        }else{
//            Patient<> retValue = patients.get(0);
//            patients.set(0,patients.remove(patients.size()-1));
//            bubbleDown(0);
//            return (retValue);
//        }
    /**
     * Constructor that creates an empty heap of hospital.Prioritized objects.
     */
    public MaxBinHeapER() {
        _heap = new ArrayList<>();
    }

    @Override
    public int size() {
        return _heap.size();
    }

    // TODO (Task 2A): enqueue
    public void enqueue(V value) {
        Patient pE = new Patient(value) ;
        if(_heap.isEmpty()){
            _heap.add(pE);
        }else{
            enqueue((V) pE.getValue(), (P) pE.getPriority());
        }


    }

    // TODO (Task 2A): enqueue
    @Override
    public void enqueue(V value, P priority) {
        Patient pE = new Patient(value, priority);
        if (_heap.isEmpty()){
            _heap.add(pE);
        }
        else {
            System.out.println("Enqueuing: " + value + ", " + priority);
            _heap.add(pE);
            bubbleUp(_heap.size()-1);
        }
    }
    int bubbleUp(int index){
        if (index == 0) {
            return(index);
        }
        else {
            Patient child = (Patient) _heap.get(index);    // child is the element that needs to be bubbled-up
            Patient parent = (Patient) _heap.get(parentInd(index));
            if (child.getPriority().compareTo(parent.getPriority()) == 1){
                _heap.set(parentInd(index), child);
                _heap.set(index, parent);
                return(bubbleUp(parentInd(index)));
            }
            else{
                return(index);
            }
        }
    }

    // TODO (Task 2A): dequeue
    @Override
    public V dequeue() {
        if(_heap.isEmpty()){
            return null;
        }else if(_heap.size() == 1){
            return _heap.remove(0).getValue();
        } else{
            Prioritized<V, P> retValue = _heap.get(0);
            _heap.set(0, _heap.remove(_heap.size() - 1));   // move last element to the top
            bubbleDown(0);      // bubbleDown the top element
            return retValue.getValue();
        }
    }
    void bubbleDown(int index) {
        int leftChildIndex = leftChildInd(index);
        int rightChildIndex = rightChildInd(index);

        // Case 1: Node is a leaf, do nothing
        if (!hasLeftChild(index) && !hasRightChild(index)) {
            return; // Node is a leaf, so we're done.
        }
        // Case 2: Node has only left child
        else if (hasLeftChild(index) && !hasRightChild(index)) {
            if (_heap.get(leftChildIndex).getPriority().compareTo(_heap.get(index).getPriority()) > 0) {
                swap(index, leftChildIndex);
                bubbleDown(leftChildIndex);
            }
        }
        // Case 3: Node has two children, and left child is larger than the right child
        else if (hasLeftChild(index) && hasRightChild(index) && _heap.get(leftChildIndex).getPriority().compareTo(_heap.get(rightChildIndex).getPriority()) > 0) {
            if (_heap.get(leftChildIndex).getPriority().compareTo(_heap.get(index).getPriority()) > 0) {
                swap(index, leftChildIndex);
                bubbleDown(leftChildIndex);
            }
        }
        // Case 4: Node has a right child; this covers the case where the right child is larger or there is no left child
        else {
            if (_heap.get(rightChildIndex).getPriority().compareTo(_heap.get(index).getPriority()) > 0) {
                swap(index, rightChildIndex);
                bubbleDown(rightChildIndex);
            }
        }
    }
    private void swap(int indexOne, int indexTwo) {
        Prioritized<V, P> temp = _heap.get(indexOne);
        _heap.set(indexOne, _heap.get(indexTwo));
        _heap.set(indexTwo, temp);
    }



    // TODO (Task 2A): getMax
    @Override
    public V getMax() {
        if(_heap.size() == 0) {
            return null;
        }else{
            return _heap.get(0).getValue();
        }
    }

    // TODO (part 2B) : updatePriority
    public void updatePriority(V value, P newPriority) {
        int index = -1;
        for (int i = 0; i < _heap.size(); i++){
            if(_heap.get(i).getValue().equals(value)) {
                index = i;
                break;
            }
        }
        if(index == -1){
            return;
        }else{
            P oldP = _heap.get(index).getPriority();
            _heap.set(index, new Patient<V, P>(value, newPriority));
            if(newPriority.compareTo(oldP) == 1){
                bubbleUp(index);
            }else{
                bubbleDown(index);
            }
        }
    }

    /**
     * Constructor that builds a heap given an initial array of hospital.Prioritized objects.
     */
    // TODO (Task 3): overloaded constructor
    public MaxBinHeapER(Prioritized<V, P>[] initialEntries ) {
    }

    @Override
    public Prioritized<V, P>[] getAsArray() {
        Prioritized<V,P>[] result = (Prioritized<V, P>[]) Array.newInstance(Prioritized.class, size());
        return _heap.toArray(result);
    }
    static int leftChildInd (int index){
        return (2*index+1);
    }
    static int rightChildInd (int index){
        return (2*index+2);
    }
    static int parentInd (int index){
        return ((index-1)/2);
    }
    boolean validIndex (int index){
        if((index >= 0) && (index <= _heap.size()-1)){
            return true;
        }else{
            return false;
        }
    }
    boolean hasLeftChild (int index){
        return (validIndex(leftChildInd(index)));
    }
    boolean hasRightChild (int index){
        return (validIndex(rightChildInd(index)));
    }

}
