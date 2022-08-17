package additional;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class SubStringSearchTest {

	@Test
	void isSubStringTest() {
		String introduce = "My name is Diana";
		String name = "Diana";
		assertTrue(SubStringSearch.isSubString(introduce, name));
	}

}
