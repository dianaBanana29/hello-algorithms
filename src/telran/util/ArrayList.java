package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;
public class ArrayList<T> implements List<T> {
private static final int DEFAULT_CAPACITY = 16;
private T[] array;
private int size;
@SuppressWarnings("unchecked")
public ArrayList(int capacity) {
	array = (T[]) new Object[capacity];
}
public ArrayList() {
	this(DEFAULT_CAPACITY);
}

private class ArrayListIterator implements Iterator<T> {
int current = 0;
	@Override
	public boolean hasNext() {
		
		return current < size;
	}

	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return array[current++];
	}
	}
	
	@Override
	public boolean add(T obj) {
		ensureCapacity();
		array[size++] = obj;
		return true;
	}

	@Override
	public boolean remove(Object pattern) {
		int index = indexOf(pattern);
		if(index < 0) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	public boolean removeIf(Predicate<T> predicate) {
		boolean isRemoved = false;
		for(int i = 0; i < size; i++) {
			if(predicate.test(array[i])) {
				System.arraycopy(array, i +1, array, i ,size - i -1);
				size--;
				i--;
				isRemoved = true;
			}
		}
		return isRemoved;
	}

	@Override
	public boolean contains(Object pattern) {
//		
		return indexOf(pattern) >= 0;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator();
	}

	@Override
	public boolean add(int index, T obj) {
		ensureCapacity();
		if (index >= 0 && index <= size) {
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = obj;
			size++;
	
			return true;
		}
		return false;
	}
	private void ensureCapacity() {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
	}

	@Override
	public T remove(int index) {
		if(index < 0) {
			return null;
		}
		   T res = array[index];
			System.arraycopy(array, index +1, array, index ,array.length - index -1);	
		size--;
		array[size] = null;
		return res;
	}

	@Override
	public int indexOf(Object pattern) {
		for(int i = 0; i < size;i++) {
			if(pattern.equals(array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		for(int i = size -1; i > 0; i--) {
			if(pattern.equals(array[i])) {
				return i;
		}
		}
		return -1;
	}
	@Override
	public T get(int index) {
		if(index >= 0 && index < size) {
			return array[index];
		}
		return null;
	}

}