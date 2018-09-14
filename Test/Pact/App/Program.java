package Test.Pact.App;

import PactNet.Mocks.MockHttpService.*;
import PactNet.Mocks.MockHttpService.Models.*;
import Test.Pact.App.Client.*;
import Test.Pact.App.MockService.*;
import FluentAssertions.*;
import java.util.*;

public class Program
{
	private static IMockProviderService _mockProviderService;
	private static String _mockProviderServiceBaseUri;

	static void main(String[] args)
	{
		ConsumerMyApiPact consumerPact = new ConsumerMyApiPact();
		_mockProviderService = consumerPact.getMockProviderService();
		_mockProviderServiceBaseUri = consumerPact.getMockProviderServiceBaseUri();
		consumerPact.getMockProviderService().ClearInteractions();
		//NOTE: Clears any previously registered interactions before the test is run


		ProviderServiceRequest tempVar = new ProviderServiceRequest(); 
		tempVar.Method = HttpVerb.Get;
		tempVar.Path = "/user/1";
		tempVar.Headers = new HashMap<String, String>(Map.ofEntries(Map.entry("Accept", "application/json")));
		ProviderServiceResponse tempVar2 = new ProviderServiceResponse();
		tempVar2.Status = 200;
		tempVar2.Headers = new HashMap<String, String>(Map.ofEntries(Map.entry("Content-Type", "application/json; charset=utf-8")));
		class AnonymousType
		{
			public int id;
			public String firstName;
			public String lastName;

			public AnonymousType(int _id, String _firstName, String _lastName)
			{
				id = _id;
				firstName = _firstName;
				lastName = _lastName;
			}
		}
		tempVar2.Body = AnonymousType(1, "Abhishek", "D");
		_mockProviderService.Given("Get user with id '1'").UponReceiving("A GET request to retrieve the user").With(tempVar).WillRespondWith(tempVar2);

		UserApiClient consumer = new UserApiClient(_mockProviderServiceBaseUri);

		//Act
		Test.Pact.App.Client.User result = consumer.GetUsers(1);

		//Assert
		result.Should().NotBeNull();
		_mockProviderService.VerifyInteractions();
		//NOTE: Verifies that interactions registered on the mock provider are called once and only once

		consumerPact.Dispose();
	}
}