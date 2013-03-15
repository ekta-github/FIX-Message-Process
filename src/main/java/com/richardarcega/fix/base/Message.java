package com.richardarcega.fix.base;

import static com.google.common.base.Preconditions.checkNotNull;

import java.util.List;

import com.google.common.collect.LinkedListMultimap;

/**
 * Represents a FIX message
 */
public class Message
{
	/**
	 * A Multimap which associates the field's tag with a List of corresponding values
	 */
	private final LinkedListMultimap<Integer, String> fields;

	/**
	 * The original, unparsed message as a String
	 */
	private String messageString;

	public Message(final LinkedListMultimap<Integer, String> fields)
	{
		this.fields = fields;
	}

	public Message(final LinkedListMultimap<Integer, String> fields, final String messageString)
	{
		this.fields = fields;
		this.messageString = checkNotNull(messageString);
	}

	public String getField(final int tag)
	{
		return tag + '-' + fields.get(tag).toString();
	}

	public List<String> getFieldValue(final int tag)
	{
		return fields.get(tag);
	}

	@Override
	public String toString()
	{
		return "Message [fields=" + fields + ", messageString=" + messageString + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fields == null) ? 0 : fields.hashCode());
		result = prime * result + ((messageString == null) ? 0 : messageString.hashCode());
		return result;
	}

	@Override
	public boolean equals(final Object obj)
	{
		if ( this == obj )
		{
			return true;
		}
		if ( obj == null )
		{
			return false;
		}
		if ( getClass() != obj.getClass() )
		{
			return false;
		}
		final Message other = (Message) obj;
		if ( fields == null )
		{
			if ( other.fields != null )
			{
				return false;
			}
		}
		else if ( !fields.equals(other.fields) )
		{
			return false;
		}
		if ( messageString == null )
		{
			if ( other.messageString != null )
			{
				return false;
			}
		}
		else if ( !messageString.equals(other.messageString) )
		{
			return false;
		}
		return true;
	}

	public LinkedListMultimap<Integer, String> getFields()
	{
		return fields;
	}

	public String getMessageString()
	{
		return messageString;
	}

	public void setMessageString(final String messageString)
	{
		this.messageString = messageString;
	}



}
