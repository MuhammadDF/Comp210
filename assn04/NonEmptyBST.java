package assn04;
import java.util.LinkedList;
import java.util.Queue;

public class NonEmptyBST<T extends Comparable<T>> implements BST<T> {
	private T _element;
	private BST<T> _left;
	private BST<T> _right;

	public NonEmptyBST(T element) {
		_left = new EmptyBST<T>();
		_right = new EmptyBST<T>();
		_element = element;
	}

	// TODO: insert
	@Override
	public BST<T> insert(T element){
		if (element.compareTo(this._element) == 0){
			return this;
		}
		else if (element.compareTo(this._element) == 1){
			if (this._right.isEmpty()){
				this._right = new NonEmptyBST<>(element);
			}
			else{
				this._right.insert(element);
			}
		}
		else if (element.compareTo(this._element) == -1) {
			if (this._left.isEmpty()) {
				this._left = new NonEmptyBST<>(element);
			} else {
				this._left.insert(element);
			}
		}

        return this;
    }
	
	// TODO: remove
	@Override
	public BST<T> remove(T element) {
		if (element.compareTo(this._element) == 0){
			if(this.getRight().isEmpty() && this.getLeft().isEmpty()){
				return new EmptyBST<>();
			} else if ((!this.getLeft().isEmpty() && this.getRight().isEmpty())){
				return this._left;
			} else if ( (this.getLeft().isEmpty() && !this.getRight().isEmpty())) {
				return this._right;
			} else{
				T min = this.getRight().findMin();
				this._element = min;
				this._right = this._right.remove(min);
			}

		}
		else if (element.compareTo(this._element) == 1){
			this._right = this._right.remove(element);
		}
		else {
			this._left = this._left.remove(element);
		}
		return this;

	}



	// TODO: remove all in range (inclusive)
	@Override
	public BST<T> remove_range(T start, T end) {
		// Base condition to stop recursion
		if (this.isEmpty()) {
			return this; // Return empty tree or this if it's already empty
		}

		// Recursively remove nodes in the left subtree if their value is greater than or equal to start
		if (this._element.compareTo(start) >= 0) {
			this._left = this._left.remove_range(start, end);
		}

		// Recursively remove nodes in the right subtree if their value is less than or equal to end
		if (this._element.compareTo(end) <= 0) {
			this._right = this._right.remove_range(start, end);
		}

		// Now, check if the current node itself is within the range and should be removed
		if (this._element.compareTo(start) >= 0 && this._element.compareTo(end) <= 0) {
			// Utilize the existing remove method to remove the current node
			return this.remove(this._element);
		}

		// Return the modified tree
		return this;
	}


	// TODO: printPreOrderTraversal
	@Override
	public void printPreOrderTraversal() {
		if (!this.isEmpty()) {
			System.out.print(this.getElement() + " ");
			if (!this.getLeft().isEmpty()) {
				this._left.printPreOrderTraversal();
			}
			if (!this.getRight().isEmpty()) {
				this._right.printPreOrderTraversal();

			}
		}


	}

	// TODO: printPostOrderTraversal
	@Override
	public void printPostOrderTraversal() {
		if (!this.isEmpty()) {

			if (!this.getLeft().isEmpty()) {
				this._left.printPostOrderTraversal();
			}
			if (!this.getRight().isEmpty()) {
				this._right.printPostOrderTraversal();

			}
			System.out.print(this.getElement() + " ");
		}
	}

	// The findMin method returns the minimum value in the tree.
	@Override
	public T findMin() {
		if(_left.isEmpty()) {
			return _element;
		}
		return _left.findMin();
	}

	@Override
	public int getHeight() {
		   return Math.max(_left.getHeight(), _right.getHeight())+1;
	}

	@Override
	public BST<T> getLeft() {
		return _left;
	}

	@Override
	public BST<T> getRight() {
		return _right;
	}

	@Override
	public T getElement() {
		return _element;
	}

	@Override
	public boolean isEmpty() {
		return false;
	}

}
