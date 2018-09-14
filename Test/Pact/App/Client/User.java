package Test.Pact.App.Client;

import Test.Pact.App.*;

public class User
{
	private int id;
	public final int getid()
	{
		return id;
	}
	public final void setid(int value)
	{
		id = value;
	}

	private String lastName;
	public final String getlastName()
	{
		return lastName;
	}
	public final void setlastName(String value)
	{
		lastName = value;
	}

	private String firstName;
	public final String getfirstName()
	{
		return firstName;
	}
	public final void setfirstName(String value)
	{
		firstName = value;
	}
}