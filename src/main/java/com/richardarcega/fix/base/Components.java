package com.richardarcega.fix.base;

import com.google.common.collect.ImmutableList;

/**
 * Defines constants that are used for validating required fields in messages and component blocks.
 */
public class Components
{
	public static final ImmutableList<FieldDefinition>	STANDARD_HEADER	=
			ImmutableList.<FieldDefinition> builder()
				.add(new FieldDefinition(8,  "BeginString"))
				.add(new FieldDefinition(9,  "BodyLength"))
				.add(new FieldDefinition(35, "MessageType"))
				.add(new FieldDefinition(34, "MsgSeqNum"))
				.add(new FieldDefinition(52, "SendingTime"))
				.build();

	public static final ImmutableList<FieldDefinition> 	STANDARD_TRAILER =
			ImmutableList.<FieldDefinition> builder()
				.add(new FieldDefinition(10, "CheckSum"))
				.build();

	public static final ImmutableList<FieldDefinition>	INSTRUMENT =
			ImmutableList.<FieldDefinition> builder()
				.add(new FieldDefinition(55, "Symbol"))
				.add(new FieldDefinition(65, "SymbolSfx"))
				.add(new FieldDefinition(48, "SecurityID"))
				.add(new FieldDefinition(22, "SecurityIDSource"))
				.build();

	public static final ImmutableList<FieldDefinition>	ORDER_QTY_DATA =
			ImmutableList.<FieldDefinition> builder()
				.add(new FieldDefinition(38,  "OrderQty"))
				.add(new FieldDefinition(152, "SymbolSfx"))
				.add(new FieldDefinition(516, "SecurityID"))
				.build();

}
