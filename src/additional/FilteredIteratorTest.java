package additional;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Iterator;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class FilteredIteratorTest<T> implements Iterable<T> {
	
	private static final Integer[] NUMBERS = {0,1,2,3,4,5,6,7,8,9};
	 Iterator<T> it = iterator();
	FilteredIterator <T> iter = new FilteredIterator<T>(it, new FilterPredicate<>());
	
	@Test
	void test() {
	while(it.hasNext()) {
		
	}
	}

	@Override
	public Iterator<T> iterator() {
		
		return null;
	} 


}
