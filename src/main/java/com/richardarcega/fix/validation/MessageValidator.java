package com.richardarcega.fix.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import com.richardarcega.fix.base.FieldDefinition;
import com.richardarcega.fix.base.Message;
import com.richardarcega.fix.format.MessageFormat;
import com.richardarcega.fix.format.MessageFormatBuilder;
import com.richardarcega.fix.parse.InvalidMessageException;
import com.richardarcega.fix.util.MessageUtil;

/**
 * Validates messages using the provided message format. The validator will check that
 * the syntax adheres to the format as described in the FIX specification, the checksum
 * is valid and that all required fields are included within the message.
 */
public abstract class MessageValidator implements com.richardarcega.fix.validation.Validator
{
	public static final Pattern MESSAGE_SYNTAX = Pattern.compile("^([1-9]\\d*=(?!=)[^\\x01]+\\x01)+$");

	private final  MessageFormatBuilder  messageFormatBuilder;

	public MessageValidator(final MessageFormatBuilder  messageFormatBuilder)
	{
		this.messageFormatBuilder = messageFormatBuilder;
	}

	public Message getMessage()
	{
		return messageFormatBuilder.getMessage();
	}

	public MessageFormat getMessageFormat()
	{
		return messageFormatBuilder.getMessageFormat();
	}

	@Override
	/**
	 * Validates the message
	 */
	public void validate()
	{
		validateSyntax();
		validateChecksum();
		validateHeader();
		validateBody();
		validateTrailer();
	}

	public void validateHeader()
	{
		final List<Integer> messageFields = new ArrayList<Integer>(getMessage().getFields().keySet());

		if ( getMessage().getFields().size() < getMessageFormat().getHeader().size() ||
				messageFields.get(0) != getMessageFormat().getHeader().get(0).getTag() ||  messageFields.get(1) != getMessageFormat().getHeader().get(1).getTag() || messageFields.get(2) != getMessageFormat().getHeader().get(2).getTag())
		{
			throw new InvalidMessageException("Missing required header tag(s) or tags in wrong order");
		}

		for( final FieldDefinition f : getMessageFormat().getHeader() )
		{
			if (  getMessage().getFieldValue( f.getTag() ) == null )
			{
				throw new InvalidMessageException("Missing required header tag(s): " + f.toString());
			}
		}
	}

	public abstract void validateBody();

	public void validateTrailer()
	{
		final List<Integer> messageFields = new ArrayList<Integer>(getMessage().getFields().keySet());

		if ( messageFields.get(messageFields.size()-1) != getMessageFormat().getTrailer().get(getMessageFormat().getTrailer().size() -1).getTag() )
		{
			throw new InvalidMessageException("Invalid trailer component");
		}
	}

	/**
	 * Validates the syntax of the message, checking that it
	 * conforms to the repeating pattern of tag1=value1<SOH>tag2=value2 and so on.
	 */
	public void validateSyntax()
	{
		if ( ! MessageValidator.MESSAGE_SYNTAX.matcher( getMessage().getMessageString() ).matches() )
		{
			throw new InvalidMessageException("Message does not match the syntax of the FIX specification, tag=value<SOH>");
		}
	}

	/**
	 * Computes the checksum of the message and compares it to the
	 * CheckSum contained within the standard trailer.
	 */
	public void validateChecksum()
	{
		final int field    = MessageUtil.getChecksumValue( getMessage().getMessageString() );
		final int computed = MessageUtil.generateChecksum( getMessage().getMessageString() );

		if ( field != computed )
		{
			throw new InvalidMessageException("Checksum does match. Values=[Field: " + field + ", " + "Computed: " + computed  +"]");
		}
	}
}
