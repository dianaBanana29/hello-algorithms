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
			T o = srcIterator.next();
			if(filter.test(o)) {
				hasNext = true;
				nextElement = o;
				return oldMatch;
			}
		}
		hasNext = false;
		return oldMatch;
		
	}
int current = 0;
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
