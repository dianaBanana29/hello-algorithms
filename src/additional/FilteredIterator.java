package additional;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

public class FilteredIterator<T> implements Iterator<T> {
	private Iterator<T> srcIterator;
	private Predicate<T> filter;
	private T nextElement;
	private boolean hasNext;
	 public FilteredIterator(Iterator<T> srcIterator, Predicate<T> filter) {
        this.srcIterator = srcIterator;
        this.filter = filter;
        nextMatch();
     }
private T nextMatch() {
		T oldMatch = nextElement;
		while(srcIterator.hasNext()) {
			T obj = srcIterator.next();
			if(filter.test(obj)) {
				hasNext = true;
				nextElement = obj;
				return oldMatch;
			}
		}
		hasNext = false;
		return oldMatch;	
	}

	@Override
	public boolean hasNext() {
		
		return hasNext;
	}

	@Override
	public T next() {
		if(!hasNext()) {
			throw new NoSuchElementException();
		}
		return nextMatch();
	}
	
	
	public Iterator<T> iterator() {
		
		return new FilteredIterator<T>(srcIterator, filter);
	}
	
}
