package com.richardarcega.fix.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.richardarcega.fix.base.Message;
import com.richardarcega.fix.format.MessageFormatBuilder;
import com.richardarcega.fix.format.NewOrderSingleMessageFormat;
import com.richardarcega.fix.parse.InvalidMessageException;
import com.richardarcega.fix.parse.MessageParseStringSplitStrategy;
import com.richardarcega.fix.validation.MessageValidator;
import com.richardarcega.fix.validation.NewOrderSingleMessageValidator;

/**
 * Prompts the user for a FIX 4.4 NewOrderSingle message and parses the input for errors.
 */
public class ClientImpl
{
	private static final Logger	log	= LoggerFactory.getLogger(ClientImpl.class);

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
				catch (final InvalidMessageException | NumberFormatException e )
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
	 * the message as a Multimap.
	 *
	 * @param messageString the FIX message
	 * @return the message represented as a Multimap
	 */
	public static Message parseMessage(final String messageString)
	{
		final Message msg = new MessageParseStringSplitStrategy().parse(messageString);

		final MessageManager manager = new MessageManager();
		final MessageFormatBuilder newOrderSingleBuilder = new NewOrderSingleMessageFormat(msg);

		manager.setMessageFormatBuilder( newOrderSingleBuilder );
		manager.constructMessageFormat();

		final MessageValidator validator = new NewOrderSingleMessageValidator( manager.getMessageFormatBuilder() );

		validator.validate();

		return msg;
	}

}
