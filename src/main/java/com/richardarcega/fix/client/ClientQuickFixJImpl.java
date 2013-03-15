package com.richardarcega.fix.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.InvalidMessage;
import quickfix.MessageUtils;
import quickfix.fix44.Message;
import quickfix.fix44.MessageFactory;

/**
 * Prompts the user for a FIX 4.4 NewOrderSingle message and parses the input for errors
 * using the QuickFix/J FIX Engine.
 */
public class ClientQuickFixJImpl
{
	private static final String APPLICATION_PROPERTIES = "application.properties";
	private static final Logger	log			           = LoggerFactory.getLogger(ClientImpl.class);

	public static void main(final String[] args)
	{
		System.out.println("Type 'exit' to quit");

		try ( BufferedReader br = new BufferedReader(new InputStreamReader( System.in ) ) )
		{
			while ( true )
			{
				System.out.print("Enter FIX 4.4 NewOrderSingle message >>> ");

				final String input = br.readLine();

				if ( "exit".equals(input) )
				{
					break;
				}
				else if ( "".equals(input) )
				{
					System.out.println("Please enter a valid message or 'exit' to quit.");
					continue;
				}

				try
				{
					parseMessage(input);
					System.out.println("FIX message is valid!");
				}
				catch (InvalidMessage | ConfigError | StringIndexOutOfBoundsException e)
				{
					log.error("Message parsing failed", e);
				}

			}
		}
		catch (final IOException e)
		{
			log.error("Error while reading user input", e);
		}
	}

	/**
	 * Parses and validates the FIX message string and returns
	 * the message as a quickfix.fix44.Message.
	 *
	 * @param messageString the FIX message
	 * @return the message represented as a quickfix Message object
	 */
	public static quickfix.fix44.Message parseMessage(final String messageString) throws quickfix.InvalidMessage, ConfigError, StringIndexOutOfBoundsException
	{
		final Properties props = new Properties();
		final InputStream in = ClientImpl.class.getClassLoader().getResourceAsStream( APPLICATION_PROPERTIES );

		try
		{
			props.load(in);
			in.close();
		}
		catch (final IOException e)
		{
			log.error("Failed to load application.properties", e);
			System.exit(1);
		}

		// Load the FIX 4.4 Data Dictionary
		final InputStream fix44Xml = ClientImpl.class.getClassLoader().getResourceAsStream(props.getProperty("fix44DataDictionary"));

		return (Message) MessageUtils.parse( new MessageFactory(), new DataDictionary(fix44Xml), messageString );
	}
}
