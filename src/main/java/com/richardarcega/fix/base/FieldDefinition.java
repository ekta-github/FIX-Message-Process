package com.richardarcega.fix.base;

/**
 * Represents a FIX message field
 */
public class FieldDefinition
{
	/**
	 * Field tag
	 */
	private int		tag;

	/**
	 * Field name
	 */
	private String	name;

	/**
	 * Indicates whether the field is required
	 */
	private boolean	required;

	public FieldDefinition() {};

	public FieldDefinition(final int tag)
	{
		this.tag = tag;
	}

	public FieldDefinition(final int tag, final String name)
	{
		this.tag = tag;
		this.name = name;
	}

	public FieldDefinition(final int tag, final String name, final boolean required)
	{
		this.tag = tag;
		this.name = name;
		this.required = required;
	}

	@Override
	public String toString()
	{
		return "FieldSpecification [tag=" + tag + ", name=" + name + ", required=" + required + "]";
	}

	@Override
	public int hashCode()
	{
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + (required ? 1231 : 1237);
		result = prime * result + tag;
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
		final FieldDefinition other = (FieldDefinition) obj;
		if ( name == null )
		{
			if ( other.name != null )
			{
				return false;
			}
		}
		else if ( !name.equals(other.name) )
		{
			return false;
		}
		if ( required != other.required )
		{
			return false;
		}
		if ( tag != other.tag )
		{
			return false;
		}
		return true;
	}

	public int getTag()
	{
		return tag;
	}

	public void setTag(final int tag)
	{
		this.tag = tag;
	}

	public String getName()
	{
		return name;
	}

	public void setName(final String name)
	{
		this.name = name;
	}

	public boolean isRequired()
	{
		return required;
	}

	public void setRequired(final boolean required)
	{
		this.required = required;
	}
}
