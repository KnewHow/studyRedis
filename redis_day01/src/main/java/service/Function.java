package service;

/**
 * A interface to execute call back method
 * 
 * @author ygh
 *
 * @param <T>
 *            The return type of method
 * @param <E>
 *            The parameter type of method
 */
public interface Function<T, E> {
	public T callback(E e);
}
