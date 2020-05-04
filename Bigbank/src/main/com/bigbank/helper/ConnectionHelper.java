package main.com.bigbank.helper;

import com.google.gson.Gson;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;


/**
 * Connection Utility class used for calling http post and get calls
 */
public class ConnectionHelper {
	public final Gson gson = new Gson();
	private static ConnectionHelper INSTANCE;

	private ConnectionHelper() {
	}

	public static ConnectionHelper getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionHelper();
		}
		return INSTANCE;
	}

	private Logger LOG = Logger.getLogger(ConnectionHelper.class);

	/**
	 * Returns a String Optional if response is ok or else empty optional {@code URL}.
	 *
	 * <P>This method is used to calling get method url.</P>
	 */
	public Optional<String> sendGet(String urlEndpoint) {
		try {
			URL url = new URL(urlEndpoint);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setRequestMethod("GET");
			if (request.getResponseCode() != HttpURLConnection.HTTP_OK) {
				LOG.error("Response status: " + request.getResponseCode() + " for url: " + urlEndpoint);
			}

			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
				String output;
				while ((output = in.readLine()) != null) {
					response.append(output);
				}
			}
			LOG.info("Response status: " + request.getResponseCode() + " for url: " + urlEndpoint + " for GET request to endpoint " + urlEndpoint);
			return Optional.of(response.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}

	/**
	 * Returns a String Optional if response is ok or else empty optional {@code URL}.
	 *
	 * <P>This method is used to calling post method url.</P>
	 */
	public Optional<String> sendPost(String urlEndpoint) {
		try {
			URL obj = new URL(urlEndpoint);
			HttpURLConnection request = (HttpURLConnection) obj.openConnection();
			request.setRequestMethod("POST");
			request.setDoOutput(true);
			if (request.getResponseCode() != HttpURLConnection.HTTP_OK) {
				LOG.error("Response status:" + request.getResponseCode() + "for url: " + urlEndpoint);
			}
			StringBuilder response = new StringBuilder();
			try (BufferedReader in = new BufferedReader(new InputStreamReader(request.getInputStream()))) {
				String output;
				while ((output = in.readLine()) != null) {
					response.append(output);
				}
			}
			LOG.info("Response status: " + request.getResponseCode() + " for url: " + urlEndpoint + " for POST request to endpoint " + urlEndpoint);
			return Optional.of(response.toString());
		} catch (Exception ex) {
			ex.printStackTrace();
			return Optional.empty();
		}
	}

}
