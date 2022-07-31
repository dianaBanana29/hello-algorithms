package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;
import java.util.function.Predicate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;

abstract class CollectionTests {
protected Collection<Integer> collection;
protected abstract Collection<Integer> createCollection();
private static final Integer[] NUMBERS = {0,1,2,3,4,5,6,7,8,9};
protected static int[] NUMBERS_100 = new int[100];

	@BeforeEach
	void setUp() throws Exception {
		collection = createCollection();
		for(int i: NUMBERS) {
			collection.add(i);
		}
		
	}

	@Test
	void addTest() {
		assertEquals(collection.size(), 10);
		assertTrue(collection.add(11));
		assertTrue(collection.add(5));
		Arrays.fill(NUMBERS_100, 1);
		for(int i: NUMBERS_100) {
			collection.add(i);
		}
		assertEquals(collection.size(), 112);
	}
	@Test
	void removeTest() {
		assertTrue(collection.remove(1));
		assertEquals(collection.size(), 9);
		assertFalse(collection.remove(15));
		assertEquals(collection.size(), 9);
	}
	@Test
	void removeIfTest() {
		assertFalse(collection.removeIf(new FalsePredicate()));
		assertEquals(collection.size(), 10);
		assertTrue(collection.removeIf(new FalsePredicate().negate()));
		assertEquals(collection.size(), 0);
	}
	@Test
	void containsTest() {
		assertTrue(collection.contains(2));
		assertFalse(collection.contains(12));
		
	}
	@Test
	void sizeTest() {
		assertEquals(collection.size(), 10);
	}
	@Test
	void toArrayTest() {
		Integer[] expected1 = {0,1,2,3,4,5,6,7,8,9};
		assertArrayEquals(collection.toArray(new Integer[0]), expected1);
		assertArrayEquals(expected1, collection.toArray(expected1));
		Integer [] expected2 = new Integer[100];
		assertTrue(expected2 == collection.toArray(expected2));
		assertArrayEquals(expected1, Arrays.copyOf(expected2, collection.size()));
		
		
	}

}
