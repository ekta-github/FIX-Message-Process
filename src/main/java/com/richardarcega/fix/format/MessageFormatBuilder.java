package com.richardarcega.fix.format;

import com.richardarcega.fix.base.Components;
import com.richardarcega.fix.base.Message;

/**
 * The MessageFormatBuilder constructs the message format
 * for validation.
 */
public abstract class MessageFormatBuilder
{
	protected MessageFormat	messageFormat;
	protected Message message;

	public MessageFormat getMessageFormat()
	{
		return messageFormat;
	}

	public Message getMessage()
	{
		return message;
	}

	public void createNewMessageFormat()
	{
		messageFormat = new MessageFormat();
	}

	public void setHeader()
	{
		messageFormat.setHeader(Components.STANDARD_HEADER);
	}

	public void setTrailer()
	{
		messageFormat.setTrailer(Components.STANDARD_TRAILER);
	}

	public abstract void setBody();

}
