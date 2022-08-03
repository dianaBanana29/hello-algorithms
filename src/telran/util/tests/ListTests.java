package telran.util.tests;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import telran.util.Collection;
import telran.util.List;

abstract class ListTests extends CollectionTests {
	List<Integer> list;
	@BeforeEach
	@Override
	void setUp() throws Exception {
		super.setUp(); // content of the collection is {10, -5, 13, 20, 40, 15} from the setup 
		list = (List<Integer>)collection; //
	}

	@Test
	void addIndexTest() {
		List<Integer> list = (List<Integer>) collection;
		assertTrue(list.add(0,10));
		assertEquals(list.get(0), 10);
		Arrays.fill(NUMBERS_100, 1);
		for(int i: NUMBERS_100) {
			list.add(i);
		}
		assertEquals(list.size(), 111);
		assertTrue(list.add(list.size(), 12));
		assertEquals(12, list.get(list.size() - 1));
		assertFalse(list.add(-1, 15));
		assertEquals(112, list.size());
		
		
	}
@Test
void removeIndexTest() {
	List<Integer> list = (List<Integer>) collection;
	assertEquals(0, list.remove(0));
    assertEquals(9, list.size());
	assertEquals(1, list.get(0));
	assertEquals(3, list.remove(2));
	assertEquals(8, list.size());
	assertEquals(4, list.get(2));
	assertEquals(9, list.remove(list.size() - 1));
	assertEquals(7, list.size());
	assertEquals(8, list.get(list.size() - 1));
	assertNull(list.remove(-1));
}

@Test
void indexOfTest() {
	List<Integer> list = (List<Integer>) collection;
	list.add(10, 1);
	assertEquals(list.indexOf(1), 1);
}

@Test
void lastIndexOfTest() {
	List<Integer> list = (List<Integer>) collection;
	list.add(10, 2);
	assertEquals(list.lastIndexOf(2), 10);
}
@Test
void getTest() {
	List<Integer> list = (List<Integer>) collection;
	assertEquals(0, list.get(0));
	assertNull(list.get(-1));
}
}
