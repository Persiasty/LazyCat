package pl.com.knopers.lazycat.util.function;

import java.util.function.Function;

@FunctionalInterface
public interface ThrowableFunction<T, R> extends Function<T, R>
{
	@Override
	default R apply(T t)
	{
		try
		{
			return tryApply(t);
		}
		catch(Throwable e)
		{
			throw new RuntimeException(e);
		}
	}
	
	R tryApply(T t) throws Throwable;
}
