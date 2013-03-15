package com.richardarcega.fix.parse;

import com.richardarcega.fix.base.Message;

/**
 * This interface abstracts the strategy for parsing a message into
 * its object representation, making them interchangeable according
 * to needs.
 */
public interface MessageParseStrategy
{
	static final char FIELD_DELIMITER = (char) 1;
	static final char TAG_VALUE_SEPARATOR = '=';

	Message parse(String message);
}
