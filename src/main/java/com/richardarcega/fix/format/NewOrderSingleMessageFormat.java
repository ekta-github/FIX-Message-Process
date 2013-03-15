package com.richardarcega.fix.format;

import static com.google.common.base.Preconditions.checkNotNull;

import com.google.common.collect.ImmutableList;
import com.richardarcega.fix.base.FieldDefinition;
import com.richardarcega.fix.base.Message;

/**
 * Represents the FIX NewOrderSingle message format.
 */
public class NewOrderSingleMessageFormat extends MessageFormatBuilder
{
	public static final ImmutableList<FieldDefinition>	BODY	=
			ImmutableList.<FieldDefinition> builder()
				.add(new FieldDefinition(11, "ClOrdID"))
				.add(new FieldDefinition(54, "Side"))
				.add(new FieldDefinition(60, "TransactTime"))
				.add(new FieldDefinition(40, "OrdType"))
				.build();

	public NewOrderSingleMessageFormat(final Message message)
	{
		this.message = checkNotNull(message, "Message is null");
	}

	@Override
	public void setBody()
	{
		messageFormat.setBody(BODY);
	}
}
