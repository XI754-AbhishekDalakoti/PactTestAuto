package WebApi2;

public class WebApiApplication extends System.Web.HttpApplication
{
	protected final void Application_Start()
	{
		GlobalConfiguration.Configure(WebApiConfig.Register);
	}
}