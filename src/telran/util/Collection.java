package telran.util;

import java.util.Arrays;
import java.util.Iterator;
import java.util.function.Predicate;

public interface Collection<T> extends Iterable<T> {
	/**
	 * adds object of type T in collection
	 * @param obj
	 * @return true if added
	 */
boolean add(T obj);

/**
 * removes object equaled to the given pattern
 * @param pattern - any object
 * @return true if removed
 */
boolean remove(Object pattern);

/**
 * removes all objects matching predicate
 * @param predicate
 * @return true if collection has been updated
 */
boolean removeIf(Predicate<T> predicate);

/**
 * 
 * @param predicate
 * @return true if there is an object equaled to the given pattern
 */
boolean contains(Object predicate);

/**
 * returns amount of the objects
 * @return
 */
int size();

/**
 * 
 * @param ar
 * @return regular Java Array containing all the collection object
 */
default T[] toArray(T[] ar) {
	
Iterator<T> it = iterator();
if(ar.length < size()) {
ar = Arrays.copyOf(ar, size());
	return ar;
}
	int i = 0;
if(ar.length > size()) {
while(it.hasNext()) {
ar[i++]	= it.next();
 }
  Arrays.fill(ar, i, ar.length - 1, null);
  return ar;
}	
return ar;
}
}

	//TODO fill array by iterating
	//if ar.length < size then you should create new array
	//of type <T>(consider method Arrays.copyOf
	//if ar.length == size then you just fill array and reference to the same array
	//if ar.length > size - then you fill the given array and rest part should be filled by nulls
	//reference to the same array will be returned





