package additional;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
class IntRangeIterator implements Iterator<Integer> {
	int max;
	int nextValue;

	public IntRangeIterator(int min, int max) {
		super();
		if (max < min || max + 1 <= min) { // avoid invalid order and overflow
			throw new IllegalArgumentException();
		}
		this.nextValue = min;
		this.max = max;
	}

	@Override
	public boolean hasNext() {
		return nextValue < max;
	}

	@Override
	public Integer next() {
		if (!hasNext())
			throw new NoSuchElementException();
		return nextValue++;
	}
}

class EvenPredicate implements Predicate<Integer> {
	@Override
	public boolean test(Integer val) {
		return (val % 2 == 0);
	}
}

class TruePredicate implements Predicate<Integer> {
	@Override
	public boolean test(Integer val) {
		return true;
	}
}
class FilteredIteratorTests {
	private IntRangeIterator srcIter;
	private Predicate<Integer> filter;
	private FilteredIterator<Integer> filteredIter;
	
//	@BeforeEach
	public Integer[] createActual(FilteredIterator<Integer>filteredIter) {
		Integer[] actual = {};
		int i = 0;
		while(filteredIter.hasNext()) {
			actual = Arrays.copyOf(actual, actual.length + 1);
			actual[i] = filteredIter.next();
			i++;
		}
	return actual;	
	}
	
	@Test
	void EmptyTest() {
		srcIter = new IntRangeIterator(1, 1);
		filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(srcIter, filter);
		Integer[] expected = {};
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);
	}	
	@Test
	void oneUnfiltered() {
		srcIter = new IntRangeIterator(1, 3);
	    filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(srcIter, filter);
		Integer[] expected = {2};
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void allPassedTest() {
		srcIter = new IntRangeIterator(0, 10);
	    filter = new TruePredicate();
		filteredIter = new FilteredIterator<>(srcIter, filter);
		Integer[] expected = {0,1,2,3,4,5,6,7,8,9};
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);	
	}
	@Test
	void firstNLastPassedTest() {
	    srcIter = new IntRangeIterator(0, 11);
		filter = new EvenPredicate();
		 filteredIter = new FilteredIterator<>(srcIter, filter);
		Integer[] expected = { 0, 2, 4, 6, 8, 10 };
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);
	}
	@Test
	void FirstPassedTest() {
		srcIter = new IntRangeIterator(0, 10);
		filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(srcIter, filter);
		Integer[] expected = {0, 2, 4, 6, 8};
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void LastPassedTest(){
		srcIter = new IntRangeIterator(1, 11);
		filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(srcIter, filter);
		Integer[] expected = { 2, 4, 6, 8, 10};
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);	
	}
	@Test
	void onlyMiddlePassedTest() {
		srcIter = new IntRangeIterator(1, 10);
		filter = new EvenPredicate();
		filteredIter = new FilteredIterator<>(srcIter, filter);
		Integer[] expected = { 2, 4, 6, 8 };
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);
	}
	
	@Test
	void nullSupportTest() {
		Iterator<Integer> Iter = Arrays.asList(1, null).iterator();
		filter = new TruePredicate();
		filteredIter = new FilteredIterator<>(Iter, filter);
		Integer[] expected = { 1, null };
		Integer[] actual = createActual(filteredIter);
		assertArrayEquals(expected, actual);
	}
}
