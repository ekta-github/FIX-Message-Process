package com.richardarcega.fix.client;

import com.richardarcega.fix.format.MessageFormatBuilder;

/**
 * Responsible for invoking the message format builder for constructing
 * a representation of a FIX message format.
 */
public class MessageManager
{
	private MessageFormatBuilder messageFormatBuilder;

	public void setMessageFormatBuilder(final MessageFormatBuilder messageFormatBuilder)
	{
		this.messageFormatBuilder = messageFormatBuilder;
	}

	public MessageFormatBuilder getMessageFormatBuilder()
	{
		return messageFormatBuilder;
	}

	public void constructMessageFormat()
	{
		messageFormatBuilder.createNewMessageFormat();
		messageFormatBuilder.setHeader();
		messageFormatBuilder.setBody();
		messageFormatBuilder.setTrailer();
	}
}
