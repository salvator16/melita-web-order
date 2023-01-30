package com.melita.weborder.util;

import com.melita.weborder.exception.CommonAuthException;
import org.springframework.http.HttpStatus;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

public class AuthUtil {

    public static String[] parseCredentials(String header) throws CommonAuthException {

	if (!header.startsWith("Basic "))
	    throw new CommonAuthException("Authorization header is invalid", HttpStatus.UNAUTHORIZED);

	String[] hdrPartition = header.split(" ");
	if (hdrPartition.length == 2) {
	    String decodedCredential;

	    try {
		decodedCredential = new String(Base64.getDecoder().decode(hdrPartition[1]));
		String[] credentialArray = decodedCredential.split(":");

		if (credentialArray.length != 2)
		    throw new CommonAuthException("Authorization header is invalid", HttpStatus.UNAUTHORIZED);

		return credentialArray;

	    } catch (Exception e) {
		throw new CommonAuthException("Authorization header is invalid", HttpStatus.UNAUTHORIZED);
	    }
	}
	throw new CommonAuthException("Authorization header is invalid", HttpStatus.UNAUTHORIZED);
    }

    public static void serializeErrorResponse(HttpServletResponse response, String message, HttpStatus httpStatus) throws IOException {
	Map<String, Object> map = new HashMap<>();
	map.put("message", message);

	response.setStatus(httpStatus.value());
	response.getWriter().print(JsonConverter.getInstance().convertToJson(map));
    }

}
