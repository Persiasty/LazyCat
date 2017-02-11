package pl.com.knopers.lazycat.util.function;

import java.util.function.Predicate;

@FunctionalInterface
public interface ThrowablePredicate<T> extends Predicate<T>
{
	@Override
	default boolean test(T t)
	{
		try
		{
			return tryTest(t);
		}
		catch(Throwable e)
		{
			throw new RuntimeException(e);
		}
	}

	boolean tryTest(T t) throws Throwable;
}
