package com.manhdn.social;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.fluent.Form;
import org.apache.http.client.fluent.Request;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.manhdn.entity.userEntity;

@Component
public class GoogleUtils {
	
	public static String GOOGLE_CLIENT_ID = "733898855305-qq55bo8bqmokdrq0bkl41prqlkiiumri.apps.googleusercontent.com";
	public static String GOOGLE_CLIENT_SECRET = "TSX38l10qJykia1Jyn33yYfD";
	public static String GOOGLE_REDIRECT_URI = "http://localhost:8088/SpringMVC/app-view/login-google";
	public static String GOOGLE_LINK_GET_TOKEN = "https://accounts.google.com/o/oauth2/token";
	public static String GOOGLE_LINK_GET_USER_INFO = "https://www.googleapis.com/oauth2/v1/userinfo?access_token=";
	public static String GOOGLE_GRANT_TYPE = "authorization_code";

	public String getToken(final String code) throws ClientProtocolException, IOException {
		String response = Request.Post(GOOGLE_LINK_GET_TOKEN)
				.bodyForm(Form.form().add("client_id", GOOGLE_CLIENT_ID)
						.add("client_secret", GOOGLE_CLIENT_SECRET)
						.add("redirect_uri", GOOGLE_REDIRECT_URI).add("code", code)
						.add("grant_type", GOOGLE_GRANT_TYPE).build())
				.execute().returnContent().asString();

		ObjectMapper mapper = new ObjectMapper();
		JsonNode node = mapper.readTree(response).get("access_token");
		return node.textValue();
	}

	public GooglePojo getUserInfo(final String accessToken) throws ClientProtocolException, IOException {
		String link = GOOGLE_LINK_GET_USER_INFO + accessToken;
		String response = Request.Get(link).execute().returnContent().asString();
		Gson gson = new GsonBuilder().create();
		GooglePojo googlePojo = gson.fromJson(response, GooglePojo.class);
		return googlePojo;

	}

	public UserDetails buildUser(GooglePojo googlePojo) {
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNonExpired = true;
		boolean accountNonLocked = true;
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		authorities.add(new SimpleGrantedAuthority("ROLE_USER"));
		UserDetails userDetail = new User(googlePojo.getEmail(),
				"", enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		return userDetail;
	}
	
	public userEntity toUserEntity(GooglePojo googlePojo) {
		userEntity user = new userEntity();
		user.setEmail(googlePojo.getEmail());
		user.setFullName(googlePojo.getName());
		user.setPath(googlePojo.getPicture());
		user.setPassword("123");
		String []img = googlePojo.getPicture().split("/");
		String imgName = img[img.length-1]+".png";
		user.setAvatar(imgName);
//		user.setBirthDate("0000-00-00");
//		user.set
		return user;
	}
	
}
