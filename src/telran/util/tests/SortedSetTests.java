package telran.util.tests;

import static org.junit.Assert.*;
import java.util.Arrays;

import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.SortedSet;

public abstract class SortedSetTests extends SetTests {
	@Test
	@Override
	void toArrayTest() {
		Arrays.sort(expected);
		super.toArrayTest();
	}
	@Test
	void firstTest() {
		assertEquals((Integer)(-5), ((SortedSet<Integer>)collection).first());
	}
	@Test
	void lastTest() {
		assertEquals((Integer)(40), ((SortedSet<Integer>)collection).last());
	}
	
void ceilingTest() {
	assertEquals(40, (int)((SortedSet<Integer>)collection).ceiling(40));
	assertEquals(40, (int)((SortedSet<Integer>)collection).ceiling(35));
	assertNull(null, (int)((SortedSet<Integer>)collection).ceiling(41));
}
void floorTest() {
	assertEquals(-5, (int)((SortedSet<Integer>)collection).floor(-5));
	assertEquals(-5, (int)((SortedSet<Integer>)collection).floor(0));
	assertNull(null, (int)((SortedSet<Integer>)collection).floor(-6));
}

}