package Test.Pact.App.MockService;

import Newtonsoft.Json.*;
import PactNet.*;
import PactNet.Mocks.MockHttpService.*;
import Test.Pact.App.*;

public class ConsumerMyApiPact
{
	private static final String ProviderName = "Userservice";
	private static final String ClientName = "Userclient";

	private IPactBuilder PactBuilder;
	public final IPactBuilder getPactBuilder()
	{
		return PactBuilder;
	}
	private void setPactBuilder(IPactBuilder value)
	{
		PactBuilder = value;
	}
	private IMockProviderService MockProviderService;
	public final IMockProviderService getMockProviderService()
	{
		return MockProviderService;
	}
	private void setMockProviderService(IMockProviderService value)
	{
		MockProviderService = value;
	}

	public final int getMockServerPort()
	{
		return 1234;
	}
	public final String getMockProviderServiceBaseUri()
	{
		return String.format("url/api/", getMockServerPort());
	}

	public ConsumerMyApiPact()
	{
		setPactBuilder(new PactBuilder(new PactConfig());
		getPactBuilder().PactDir = "D:\\Pact";
		getPactBuilder().LogDir = "D:\\Pact";
		;

		getPactBuilder().ServiceConsumer(ClientName).HasPactWith(ProviderName); // Provider Name

		setMockProviderService(getPactBuilder().MockService(getMockServerPort()));
		//Configure the http mock server

		setMockProviderService(getPactBuilder().MockService(getMockServerPort(), false));
		// By passing true as the second param, you can enabled SSL. 
		


                MockProviderService = PactBuilder.MockService(MockServerPort, bindOnAllAdapters: false);
		setMockProviderService(getPactBuilder().MockService(getMockServerPort(), bindOnAllAdapters: false));
		//By passing true as the bindOnAllAdapters parameter the http mock server will be 
		// able to accept external network requests, but will require admin privileges in order to run

		setMockProviderService(getPactBuilder().MockService(getMockServerPort(), new JsonSerializerSettings()));
		//You can also change the default Json serialization settings using this overload		
	}

	public final void Dispose()
	{
		getPactBuilder().Build();
		//NOTE: Will save the pact file once finished
	}
}