package com.richardarcega.fix.validation;

import com.richardarcega.fix.base.FieldDefinition;
import com.richardarcega.fix.format.MessageFormatBuilder;
import com.richardarcega.fix.parse.InvalidMessageException;

/**
 * Validator for NewOrderSingle messages.
 */
public class NewOrderSingleMessageValidator extends MessageValidator
{
	public NewOrderSingleMessageValidator(final MessageFormatBuilder messageFormatBuilder)
	{
		super(messageFormatBuilder);
	}

	@Override
	public void validateBody()
	{
		for ( final FieldDefinition f : getMessageFormat().getBody() )
		{
			if (  getMessage().getFieldValue( f.getTag() ).isEmpty() )
			{
				throw new InvalidMessageException(getClass().getSimpleName() + " -  Missing required body tag - " + f.toString());
			}
		}

		// TODO: Refactor to simplify handling on multiple required fields and repeated groups

		/*
		 *  NOTE: According to the FIX 4.4 NewOrderSingle specification, OrderQtyData and Instrument
		 *  component blocks are required. However, none of the listed fields within these components
		 *  are designated as required. The implication is that the requirement of the parent component
		 *  blocks implies that at least one child field within the component  is required.
		 */

		// OrderQtyData
		if (  getMessage().getFieldValue( 38 ).isEmpty() && getMessage().getFieldValue( 152 ).isEmpty() && getMessage().getFieldValue( 516 ).isEmpty() )
		{
			throw new InvalidMessageException(getClass().getSimpleName() + " - Missing required field(s) for OrderQtyData component block");
		}

		// Instrument
		if ( getMessage().getFieldValue( 55 ).isEmpty() && getMessage().getFieldValue( 48 ).isEmpty() && getMessage().getFieldValue( 22 ).isEmpty()  )
		{
			throw new InvalidMessageException(getClass().getSimpleName() + " - Missing required field(s) for Instrument component block");
		}
	}
}
