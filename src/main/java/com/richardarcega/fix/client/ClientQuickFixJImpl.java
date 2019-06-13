package com.richardarcega.fix.client;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import quickfix.ConfigError;
import quickfix.DataDictionary;
import quickfix.InvalidMessage;
import quickfix.MessageUtils;
import quickfix.StringField;
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
					Message message = parseMessage(input);
					System.out.println("FIX message is valid!");
					
					String fixMessage = message.toString();
					
					System.out.println("FIX Message" +fixMessage);
					
					System.out.println("Origional FIX message:-->"+fixMessage+"\n");
					System.out.println(MessageUtils.getStringField(fixMessage, 664));
					System.out.println(MessageUtils.getStringField(fixMessage, 772));
					System.out.println(MessageUtils.getStringField(fixMessage, 666));	
					System.out.println(MessageUtils.getStringField(fixMessage, 467));
					System.out.println(MessageUtils.getStringField(fixMessage, 60));
					System.out.println(MessageUtils.getStringField(fixMessage, 448));
					System.out.println(MessageUtils.getStringField(fixMessage, 523));
					System.out.println(MessageUtils.getStringField(fixMessage, 54));
					System.out.println(MessageUtils.getStringField(fixMessage, 48));
					System.out.println(MessageUtils.getStringField(fixMessage, 80));
					System.out.println(MessageUtils.getStringField(fixMessage, 854));
					System.out.println(MessageUtils.getStringField(fixMessage, 75));
					System.out.println(MessageUtils.getStringField(fixMessage, 863));
					System.out.println(MessageUtils.getStringField(fixMessage, 6));
					System.out.println(MessageUtils.getStringField(fixMessage, 423));
					System.out.println(MessageUtils.getStringField(fixMessage, 64));
					System.out.println(MessageUtils.getStringField(fixMessage, 155));
					System.out.println(MessageUtils.getStringField(fixMessage, 159));
					System.out.println(MessageUtils.getStringField(fixMessage, 223));
					System.out.println(MessageUtils.getStringField(fixMessage, 782));
					
					
					
					
					
					
				
					//StringField msgType = new StringField(FixMessageTypeFields.BEG.getValue(), MessageUtils1.getStringField(fixMessage, 8));
					StringField msgType = new StringField(FixMessageTypeFields.MSGTYPE.getValue(), MessageUtils.getStringField(fixMessage, 664));
					StringField qty = new StringField(FixMessageTypeFields.BODYLENGTH.getValue(), MessageUtils.getStringField(fixMessage, 773));
					StringField q = new StringField(FixMessageTypeFields.SENDERCOMPID.getValue(), MessageUtils.getStringField(fixMessage, 666));
					StringField w = new StringField(FixMessageTypeFields.TARGETCOMPID.getValue(), MessageUtils.getStringField(fixMessage, 467));
					StringField e = new StringField(FixMessageTypeFields.MSGSEQNUM.getValue(), MessageUtils.getStringField(fixMessage, 60));
					StringField r = new StringField(FixMessageTypeFields.SENDINGTIME.getValue(), MessageUtils.getStringField(fixMessage, 448));
					StringField t = new StringField(FixMessageTypeFields.EMAIILTYPE.getValue(), MessageUtils.getStringField(fixMessage, 523));
					StringField y = new StringField(FixMessageTypeFields.SUBJECT.getValue(), MessageUtils.getStringField(fixMessage, 54));
					StringField u = new StringField(FixMessageTypeFields.EMAILTHREADID.getValue(), MessageUtils.getStringField(fixMessage, 48));
					StringField i = new StringField(FixMessageTypeFields.NOLINESOFTEXT.getValue(), MessageUtils.getStringField(fixMessage, 80));
					StringField O = new StringField(FixMessageTypeFields.TEXT.getValue(), MessageUtils.getStringField(fixMessage, 80));
					StringField P = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 75));
					StringField a = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 863));
					StringField s = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 6));
					StringField d = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 423));
					StringField f = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 64));
					StringField g = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 155));
					StringField h = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 155));
					StringField j = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 155));
					StringField c = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 781));
					StringField k = new StringField(FixMessageTypeFields.CHECKSUM.getValue(), MessageUtils.getStringField(fixMessage, 782));
					
					
						
					
					System.out.println("Fix Message:--> "+message.toString()+"\n");	
					System.out.println("XML conversion:-->\n");
					
					System.out.println(message.toXML());
					
				
					
					
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
		
		
		String withSOH = messageString.replace('|', '\001');
		System.out.print("withSOH" + withSOH);
		
		return (Message) MessageUtils.parse(new MessageFactory(), new DataDictionary(fix44Xml), withSOH);

		//return (Message) MessageUtils.parse( new MessageFactory(), new DataDictionary(fix44Xml), messageString );
	}
}
