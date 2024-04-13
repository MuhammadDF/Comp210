package assn06;

public class AVLTree<T extends Comparable<T>> implements SelfBalancingBST<T> {
    // Fields
    private T _value;
    private AVLTree<T> _left;
    private AVLTree<T> _right;
    private int _height;
    private int _size;
    
    public AVLTree() {
        _value = null;
        _left = null;
        _right = null;
        _height = -1;
        _size = 0;
    }

    /**
     * Rotates the tree left and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateLeft() {
         // You should implement left rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         if (isEmpty() || _right == null) {
             return this;
         }

         AVLTree<T> newRoot = _right;

         this._right = newRoot._left;

         this.updateHeightAndSize();

         newRoot._left = this;

         newRoot.updateHeightAndSize();
         
         return newRoot;
     }

    private void updateHeightAndSize() {
        int leftHeight = -1;  // Initialize leftHeight to -1
        int rightHeight = -1; // Initialize rightHeight to -1
        int leftSize = 0;     // Initialize leftSize to 0
        int rightSize = 0;    // Initialize rightSize to 0

        // Check if the left child is not null and update leftHeight and leftSize accordingly
        if (_left != null) {
            leftHeight = _left.height();
            leftSize = _left._size;
        }

        // Check if the right child is not null and update rightHeight and rightSize accordingly
        if (_right != null) {
            rightHeight = _right.height();
            rightSize = _right._size;
        }

        // Set the height of the current node as one more than the maximum of leftHeight and rightHeight
        _height = 1 + Math.max(leftHeight, rightHeight);

        // Set the size of the current node as one plus the sum of the sizes of left and right subtrees
        _size = 1 + leftSize + rightSize;
    }

    /**
     * Rotates the tree right and returns
     * AVLTree root for rotated result.
     */
     private AVLTree<T> rotateRight() {
         // You should implement right rotation and then use this 
         // method as needed when fixing imbalances.
    	 // TODO
         if (isEmpty() || _left == null) {
             return this;
         }

         AVLTree<T> newRoot = _left;

         this._left = newRoot._right;

         this.updateHeightAndSize();

         newRoot._right = this;

         newRoot.updateHeightAndSize();

         return newRoot;
     }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int height() {
        return _height;
    }

    @Override
    public int size() {
        return _size;
    }

    @Override
    public SelfBalancingBST<T> insert(T element) {
    	// TODO

        if (isEmpty()) {
            _value = element;
            _left = new AVLTree<>();
            _right = new AVLTree<>();
            _height = 0;
            _size = 1;
            return this;
        }

        int comparison = element.compareTo(_value);

        if (comparison < 0) {
            _left = (AVLTree<T>) _left.insert(element);
        } else if (comparison > 0) {
            _right = (AVLTree<T>) _right.insert(element);
        } else {
            // Duplicate values are not allowed in this AVL tree implementation
            return this;
        }

        updateHeightAndSize();


        int balance = getBalance();

        if (balance > 1) {
            if (_left.getBalance() < 0) {
                _left = _left.rotateLeft();
            }
            return rotateRight();
        }

        else if (balance < -1) {
            if (_right.getBalance() > 0) {
                _right = _right.rotateRight();
            }
            return rotateLeft();
        }

        return this;
    }

    @Override
    public SelfBalancingBST<T> remove(T element) {
    	// TODO

        if (isEmpty()) {
            return this;
        }

        int comparison = element.compareTo(_value);

        if (comparison < 0) {
            _left = (AVLTree<T>) _left.remove(element);
        } else if (comparison > 0) {
            _right = (AVLTree<T>) _right.remove(element);
        } else {
            // Node with only one child or no child
            if (_left.isEmpty() || _right.isEmpty()) {
                AVLTree<T> temp = _left.isEmpty() ? _right : _left;
                if (temp.isEmpty()) {
                    return new AVLTree<>();
                } else {
                    return temp;
                }
            } else {
                _value = _right.findMin();
                _right = (AVLTree<T>) _right.remove(_value);
            }
        }

        updateHeightAndSize();

        int balance = getBalance();

        if (balance > 1) {
            if (_left.getBalance() < 0) {
                _left = _left.rotateLeft();
            }
            return rotateRight();
        }

        else if (balance < -1) {
            if (_right.getBalance() > 0) {
                _right = _right.rotateRight();
            }
            return rotateLeft();
        }

        return this;
    }

    @Override
    public T findMin() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        if(_left.isEmpty()) {
            return _value;
        } else {
            return _left.findMin();
        }
        //return null;
    }

    @Override
    public T findMax() {
        if (isEmpty()) {
            throw new RuntimeException("Illegal operation on empty tree");
        }
        // TODO
        if(_right.isEmpty()) {
            return _value;
        }else {
            return _right.findMax();
        }

        //return null;
    }

    @Override
    public boolean contains(T element) {
    	// TODO
        if(isEmpty()){
            return false;
        } else if (element.compareTo(_value) > 0) {
            return _right.contains(element);
        } else if (element.compareTo(_value) < 0) {
            return _left.contains(element);
        }else{
            return true;
        }
    }


    @Override
    public boolean rangeContain(T start, T end) {
        // TODO
        if(isEmpty()){
            return false;
        } else if (start.compareTo(_value) > 0) {
            return _right.rangeContain(start, end);
        } else if (end.compareTo(_value) < 0) {
            return _left.rangeContain(start, end);
        }else{
            return true;
        }
    }

    @Override
    public T getValue() {
        return _value;
    }

    @Override
    public SelfBalancingBST<T> getLeft() {
        if (isEmpty()) {
            return null;
        }
        return _left;
    }

    @Override
    public SelfBalancingBST<T> getRight() {
        if (isEmpty()) {
            return null;
        }
         return _right;
    }
    private int getBalance() {
        if (isEmpty()) {
            return 0;
        }

        int leftHeight = _left != null ? _left.height() : -1;
        int rightHeight = _right != null ? _right.height() : -1;

        return leftHeight - rightHeight;
    }

}

