package telran.util.tests;

import static org.junit.Assert.assertEquals;

import java.util.Arrays;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import telran.util.Collection;
import telran.util.TreeSet;

public class TreeSetTests extends SetTests {
TreeSet <Integer> tree;
	@Override
	protected Collection<Integer> createCollection() {
		
		return new TreeSet<Integer>();
	}
	@Override
	@BeforeEach
	void setUp() throws Exception {
	super.setUp();
	tree = (TreeSet<Integer>)collection;
	}
@Test
@Override
void toArrayTest() {
Arrays.sort(expected);	
super.toArrayTest();
}
@Test
void firstTest() {
assertEquals(-5, (int)tree.first())	;
}

@Test
void lastTest() {
	assertEquals(40, (int)tree.last())	;	
}
}
