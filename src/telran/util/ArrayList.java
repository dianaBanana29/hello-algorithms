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
@SuppressWarnings("hiding")
private class ArrayListIterator<T> implements Iterator<T> {
int current = 0;
	@Override
	public boolean hasNext() {
		
		return current < array.length;
	}

	@SuppressWarnings("unchecked")
	@Override
	public T next() {
		if (!hasNext()) {
			throw new NoSuchElementException();
		}
		return (T) array[current++];
	}
	}
	
	@Override
	public boolean add(T obj) {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		array[size++] = obj;
		return true;
	}

	@Override
	public boolean remove(Object pattern) {
		boolean flag = false;
		for(int i = 0; i < size; i++) {
			if(pattern.equals(array[i])) {
				System.arraycopy(array, i +1, array, i ,array.length - i -1);
				size--;
				flag = true;
			}
		}
		return flag;
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
		for(T num: array) {
			if(pattern.equals(num)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public int size() {
		
		return size;
	}

	@Override
	public Iterator<T> iterator() {
		
		return new ArrayListIterator<>();
	}

	@Override
	public boolean add(int index, T obj) {
		if (array.length == size) {
			array = Arrays.copyOf(array, size * 2);
		}
		if (index >= 0 && index <= size) {
			System.arraycopy(array, index, array, index + 1, size - index);
			array[index] = obj;
			size++;
			return true;
		}
		return false;
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
		int res = -1;
		for(int i = 0; i < size;i++) {
			if(pattern.equals(array[i])) {
				res = i;
			}
		}
		return res;
	}

	@Override
	public int lastIndexOf(Object pattern) {
		int res = -1;
		for(int i = size -1; i > 0; i--) {
			if(pattern.equals(array[i])) {
				res = i;
		}
		}
		return res;
	}
	@Override
	public T get(int index) {
		if(index >= 0 && index < size) {
			return array[index];
		}
		return null;
	}

}