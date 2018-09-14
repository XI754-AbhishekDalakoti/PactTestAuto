package WebApi2.Controllers;

import WebApi2.Models.*;
import WebApi2.*;

public class UserController extends ApiController
{
	public final User Get(int id)
	{
		User tempVar = new User();
		tempVar.setid(id);
		tempVar.setfirstName("Abhishek");
		tempVar.setlastName("D");
		return tempVar;
	}
}