package additional;

import java.util.function.Predicate;

public class FilterPredicate<T> implements Predicate<T>{

	@Override
	public boolean  test(Object t) {
		
		return  ((Integer) t % 3 == 0);
	}

}
