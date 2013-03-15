package com.richardarcega.fix.parse;

import com.google.common.base.Splitter;
import com.google.common.collect.Iterables;
import com.google.common.collect.LinkedListMultimap;
import com.richardarcega.fix.base.Message;

/**
 * Parses a message into a Multimap representation by splitting it into
 * its component parts.
 */
public class MessageParseStringSplitStrategy implements MessageParseStrategy
{
	@Override
	public Message parse(final String message)
	{
		final LinkedListMultimap<Integer, String> fields = LinkedListMultimap.create();

		for (final String field : Splitter.on( FIELD_DELIMITER ).omitEmptyStrings().split(message))
		{
			final String[] map = Iterables.toArray( Splitter.on( TAG_VALUE_SEPARATOR ).split(field), String.class );
			fields.put(Integer.valueOf(map[0]), map[1]);
		}

		return new Message(fields, message);
	}
}
