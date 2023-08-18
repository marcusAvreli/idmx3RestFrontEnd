package idmx3RestFrontEnd.client;

public interface RetryPolicy {
	long periodBetweenRetries();

	long maxRetries();

	boolean onException(Throwable t);
}