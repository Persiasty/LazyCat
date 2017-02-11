package pl.com.knopers.lazycat.util.function;

import java.util.function.Consumer;

@FunctionalInterface
public interface ThrowableConsumer<T> extends Consumer<T>
{

	@Override
	default void accept(T t)
	{
		try
		{
			tryAccept(t);
		}
		catch(Throwable e)
		{
			throw new RuntimeException(e);
		}
	}

	void tryAccept(T t) throws Throwable;
}
