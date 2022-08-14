package telran.util;

import java.util.Comparator;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class TreeSet<T> implements SortedSet<T> {
	private static class Node<T> {
		T obj;
		Node<T> parent;
		Node<T> left;
		Node<T> right;

		Node(T obj) {
			this.obj = obj;
		}
	}

	private Node<T> root;
	int size;
	Comparator<T> comp;

	private Node<T> getLeastNodeFrom(Node<T> node) {
		while (node.left != null) {
			node = node.left;
		}
		return node;
	}

	private class TreeSetIterator implements Iterator<T> {
		Node<T> current = root == null ? null : getLeastNodeFrom(root);
		boolean flNext;
		T prevObj;
		Node<T> prevNode;

		@Override
		public boolean hasNext() {

			return current != null;
		}

		@Override
		public T next() {
			if (!hasNext()) {
				throw new NoSuchElementException();
			}
			prevObj = current.obj;
			updateCurrent();
			flNext = true;
			return prevObj;
		}

		private void updateCurrent() {
			current = current.right != null ? getLeastNodeFrom(current.right) : getGreaterParent(current);

		}

		private Node<T> getGreaterParent(Node<T> node) {

			while (node.parent != null && node.parent.left != node) {
				node = node.parent;
			}
			return node.parent;
		}

		@Override
		public void remove() {
			if (!flNext) {
				throw new IllegalStateException();
			}
			TreeSet.this.remove(prevObj);
			flNext = false;
		}

	}

	public TreeSet(Comparator<T> comp) {
		this.comp = comp;
	}

	@SuppressWarnings("unchecked")
	public TreeSet() {
		this((Comparator<T>) Comparator.naturalOrder());
	}

	@Override
	public boolean add(T obj) {
		Node<T> parent = getNodeOrParent(obj);
		boolean res = false;
		int compRes = 0;
		if (parent == null || (compRes = comp.compare(obj, parent.obj)) != 0) {
			// obj doesn't exist
			Node<T> newNode = new Node<>(obj);
			if (parent == null) {
				// added first element that is the root
				root = newNode;
			} else if (compRes > 0) {
				parent.right = newNode;
			} else {
				parent.left = newNode;
			}
			res = true;
			newNode.parent = parent;
			size++;
		}
		return res;
	}

	private Node<T> getNodeOrParent(T obj) {
		Node<T> current = root;
		Node<T> parent = null;
		int compRes = 0;
		while (current != null) {
			parent = current;
			compRes = comp.compare(obj, current.obj);
			if (compRes == 0) {
				break;
			}
			current = compRes > 0 ? current.right : current.left;
		}
		return parent;
	}

	@Override
	public boolean remove(Object pattern) {
		if (getNode(pattern) != null) {
			removeNode(getNode(pattern));
			size--;
			return true;
		}
		return false;
	}

	private void removeNode(Node<T> node) {
		if (isJunction(node)) {
			removeJunctionNode(node);
		} else {
			removeNonJunctionNode(node);
		}

	}

	private boolean isJunction(Node<T> node) {
		boolean res = false;
		if (node.left != null && node.right != null) {
			res = true;
		}
		return res;
	}

	private void removeJunctionNode(Node<T> node) {
		Node<T> curr = getLeastNodeFrom(node.right);
		node.obj = curr.obj;
		removeNonJunctionNode(curr);
	}

	private void removeNonJunctionNode(Node<T> node) {
		if (node.left == null && node.right == null) {
			if (node != root) {

				Node<T> child = node.left != null ? node.left : node.right;
				if (node != root) {
					if (node == node.parent.left) {
						node.parent.left = child;
					} else {
						node.parent.right = child;
					}

				} else {
					root = null;
				}
			}

		}
	}

	public Node<T> getNode(Object pattern) {
		Iterator<T> it = new TreeSetIterator();
		Node<T> current = root;
		while (it.hasNext()) {
			if (current.obj.equals(pattern)) {
				return current;
			}
			current.obj = it.next();
		}
		return null;
	}

	@Override
	public boolean contains(Object pattern) {
		return getNode(pattern) != null;
	}

	@Override
	public int size() {

		return size;
	}

	@Override
	public Iterator<T> iterator() {

		return new TreeSetIterator();
	}

	@Override
	public T first() {
		if (root == null) {
			return null;
		}
		return getLeastNodeFrom(root).obj;
	}

	@Override
	public T last() {
		if (root == null) {
			return null;
		}
		return getMostNodeFrom(root).obj;
	}

	private Node<T> getMostNodeFrom(Node<T> node) {
		while (node.right != null) {
			node = node.right;
		}
		return node;
	}

}