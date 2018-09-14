package Provider.Test;

import PactNet.*;
import PactNet.Reporters.Outputters.*;
import FluentAssertions.*;
import java.util.*;

public class Program
{
	private static final HttpClient _httpClient = new HttpClient();
	private static final String ProviderName = "Userservice";
	private static final String ClientName = "Userclient";

	public static void main(String[] args)
	{
		CustomOutputter outputter = new CustomOutputter();
		PactVerifierConfig config = new PactVerifierConfig();
		config.ReportOutputters.Add(outputter);
		IPactVerifier pactVerifier = new PactVerifier(() ->
		{
		}, () ->
		{
			}, config);

		pactVerifier.ProviderState("Get user with id '1'");
		_httpClient.DefaultRequestHeaders.Accept.Add(new MediaTypeWithQualityHeaderValue("application/json"));
		_httpClient.BaseAddress = new System.Uri("http://localhost:61131/api/");

		//Act
		pactVerifier.ServiceProvider(ProviderName, _httpClient).HonoursPactWith(ClientName).PactUri(String.format("D:/pact/%1$s-%2$s.json",ClientName, ProviderName)).Verify();


		// Assert
		outputter.Should().NotBeNull();
		outputter.getOutput().Should().NotBeNullOrWhiteSpace();
		outputter.getOutput().Should().Contain(String.format("Verifying a Pact between %1$s and %2$s", ClientName, ProviderName));
		outputter.getOutput().Should().Contain("status code 200");
		new Scanner(System.in).nextLine();
	}

	private static class CustomOutputter implements IReportOutputter
	{
		private String Output;
		public final String getOutput()
		{
			return Output;
		}
		private void setOutput(String value)
		{
			Output = value;
		}

		public final void Write(String report)
		{
			setOutput(getOutput() + report);
		}
	}
}