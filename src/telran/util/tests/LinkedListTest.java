package telran.util.tests;
import telran.util.Collection;
import telran.util.LinkedList;

class LinkedListTest extends ListTests{

@Override
protected Collection<Integer> createCollection() {
	
	return new LinkedList<>();
}


	

}
