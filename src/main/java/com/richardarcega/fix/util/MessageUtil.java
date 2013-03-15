package com.richardarcega.fix.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Provides re-usable utility methods for Message related operations
 */
public class MessageUtil
{
	private static final Pattern	FILTER_CHECKSUM	= Pattern.compile("(.*?)(10=(?!=)([^\\x01]+)\\x01)$");

	/**
	 * Returns the checksum of a FIX message. The checksum is calculated by
	 * summing every byte of the message up to but not including the checksum
	 * field itself.
	 *
	 * @param message
	 * @return the checksum
	 */
	public static int generateChecksum(String message)
	{
		final Matcher matcher = FILTER_CHECKSUM.matcher(message);

		if ( matcher.find() )
		{
			// exclude the checksum field from the message
			message = matcher.group(1);
		}

		int checkSum = 0;

		for ( final char msgChar : message.toCharArray() )
		{
			checkSum += msgChar;
		}

		return checkSum % 256;
	}

	/**
	 * Returns the value of the CheckSum field.
	 *
	 * @param message
	 * @return the checksum value, or -1 if the checksum does not occur.
	 */
	public static int getChecksumValue(final String message)
	{
		final Matcher matcher = FILTER_CHECKSUM.matcher(message);

		int value = -1;

		if ( matcher.find() )
		{
			value = Integer.valueOf( matcher.group(3) );
		}

		return value;
	}

	private MessageUtil() {}
}
