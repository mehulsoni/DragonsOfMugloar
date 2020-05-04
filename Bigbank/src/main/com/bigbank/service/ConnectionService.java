package main.com.bigbank.service;

import com.google.gson.Gson;

import org.apache.log4j.Logger;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Optional;

import main.com.bigbank.utility.Constant;


/**
 * Connection Utility class used for calling http post and get calls
 */
public class ConnectionService {
	private static ConnectionService INSTANCE;
	public final Gson gson = new Gson();
	private Logger LOG = Logger.getLogger(ConnectionService.class);

	private ConnectionService() {
		super();
	}

	public static ConnectionService getInstance() {
		if (INSTANCE == null) {
			INSTANCE = new ConnectionService();
		}
		return INSTANCE;
	}

	/**
	 * Returns a String Optional if response is ok or else empty optional {@code URL}.
	 *
	 * <P>This method is used to calling get method url.</P>
	 */
	public Optional<String> sendGet(String urlEndpoint) {
		return executeService(Constant.GET, urlEndpoint);
	}

	/**
	 * This method based on into method type and url it execute request
	 *
	 * @param method
	 * @param urlEndpoint
	 * @return
	 */
	private Optional<String> executeService(String method, String urlEndpoint) {
		try {
			URL url = new URL(urlEndpoint);
			HttpURLConnection request = (HttpURLConnection) url.openConnection();
			request.setRequestMethod(method);
			request.setDoOutput(true);
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
			LOG.info("Response status: " + request.getResponseCode() + " for url: "
					+ urlEndpoint + " for " + method + " request to endpoint " + urlEndpoint);
			return Optional.of(response.toString());
		} catch (Exception ex) {
			LOG.error(ex.getMessage(), ex);
			return Optional.empty();
		}
	}

	/**
	 * Returns a String Optional if response is ok or else empty optional {@code URL}.
	 *
	 * <P>This method is used to calling post method url.</P>
	 */
	public Optional<String> sendPost(String urlEndpoint) {
		return executeService(Constant.POST, urlEndpoint);
	}

}
