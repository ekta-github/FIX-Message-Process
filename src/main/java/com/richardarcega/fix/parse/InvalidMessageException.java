package com.richardarcega.fix.parse;

/**
 * Signals that a message is invalid according to the FIX specifications.
 */
public class InvalidMessageException extends RuntimeException
{
	private static final long	serialVersionUID	= 7938580409891655166L;

	public InvalidMessageException()
	{
		super();
	}

	public InvalidMessageException(final String message)
	{
		super(message);
	}
}
