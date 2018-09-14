package Test.Pact.App.Client;

import Newtonsoft.Json.*;
import Test.Pact.App.*;

public class UserApiClient
{
	private String BaseUri;
	public final String getBaseUri()
	{
		return BaseUri;
	}
	public final void setBaseUri(String value)
	{
		BaseUri = value;
	}


	public UserApiClient()
	{
		this(null);
	}

   public UserApiClient(string baseUri = null)
	public UserApiClient(String baseUri)
	{
		setBaseUri((baseUri != null) ? baseUri : "http://my-api");
	}

	public final User GetUsers(int id)
	{
		String reasonPhrase;

		HttpClient tempVar = new HttpClient();
		tempVar.BaseAddress = new Uri(getBaseUri());
		try (HttpClient client = tempVar)
		{
			HttpRequestMessage request = new HttpRequestMessage(HttpMethod.Get, "/user/" + id);
			request.Headers.Add("Accept", "application/json");


			var response = client.SendAsync(request);


			var content = response.Result.Content.ReadAsStringAsync().Result;

			var status = response.Result.StatusCode;

			reasonPhrase = response.Result.ReasonPhrase;
			//NOTE: any Pact mock provider errors will be returned here and in the response body

			request.Dispose();
			response.Dispose();

			if (status == HttpStatusCode.OK)
			{
				return !tangible.StringHelper.isNullOrEmpty(content) ? JsonConvert.<User>DeserializeObject(content) : null;
			}
		}

		throw new RuntimeException(reasonPhrase);
	}
}