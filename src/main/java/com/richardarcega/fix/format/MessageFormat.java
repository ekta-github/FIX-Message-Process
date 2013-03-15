package com.richardarcega.fix.format;

import java.util.List;

import com.richardarcega.fix.base.FieldDefinition;

/**
 * The MessageFormat class provides a container for the message header,
 * body, and trailer field definitions.
 */
public class MessageFormat
{
	private List<FieldDefinition>	header;
	private List<FieldDefinition>	body;
	private List<FieldDefinition>	trailer;

	public List<FieldDefinition> getHeader()
	{
		return header;
	}

	public void setHeader(final List<FieldDefinition> header)
	{
		this.header = header;
	}

	public List<FieldDefinition> getBody()
	{
		return body;
	}

	public void setBody(final List<FieldDefinition> body)
	{
		this.body = body;
	}

	public List<FieldDefinition> getTrailer()
	{
		return trailer;
	}

	public void setTrailer(final List<FieldDefinition> trailer)
	{
		this.trailer = trailer;
	}
}
