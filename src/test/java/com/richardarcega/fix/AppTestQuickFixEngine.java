package com.richardarcega.fix;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.regex.Pattern;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import quickfix.ConfigError;
import quickfix.InvalidMessage;

import com.richardarcega.fix.client.ClientQuickFixJImpl;

/**
 * Unit tests for parsing and validation functionality using QuickFix/J FIX Engine library.
 */

public class AppTestQuickFixEngine
{
	// pattern for replacing alternate message delimiters (used for readability)
	// e.g. <SOH> character, pipes, carets
	@SuppressWarnings("unused")
	private static final Pattern	MESSAGE_DELIMITER	= Pattern.compile("[\\x01\\|^]");

	// list of valid NewOrderSingleMessages
	private static List<String>		validMessages;

	@BeforeClass
	public static void setUp() throws IOException
	{
		final String sourcePath = new File(".").getCanonicalPath()
				+ "/src/test/resources/validNewOrderSingleMessages.txt".replace("/", File.separator);

		// construct a list of messages from a file
		validMessages = Files.readAllLines(Paths.get(sourcePath), Charset.defaultCharset());
	}

	@AfterClass
	public static void tearDown()
	{
	}

	@Test
	public void testValidMessages() throws StringIndexOutOfBoundsException, InvalidMessage, ConfigError
	{
		for ( final String message : validMessages )
		{
			ClientQuickFixJImpl.parseMessage(message);
		}
	}

	@Test(expected = quickfix.InvalidMessage.class)
	public void testInvalidMessage() throws StringIndexOutOfBoundsException, InvalidMessage, ConfigError
	{
		final String invalidMessage = "8=FIX.4.49=12235=D34=21549=CLIENT1252=20100225-19:41:57.31656=B1=Marcel11=1334621=140=255=MSFT44=554=159=060=20100225-19:39:52.02010=4";
		ClientQuickFixJImpl.parseMessage(invalidMessage);
	}

}
