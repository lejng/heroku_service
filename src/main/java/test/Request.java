package test;

import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.List;

public class Request {

    public static String getToken(String url, String phone, String password) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
        parametersMap.add("phone", phone);
        parametersMap.add("password", password);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parametersMap, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        HttpHeaders responseHeaders = response.getHeaders();
        List<String> list = responseHeaders.get("Authorization");
        return list == null || list.isEmpty() ? null : list.get(0);
    }

    public static String callGetRequest(String url, String authorizationString) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorizationString);
        headers.setAccept(Arrays.asList(new MediaType[] { MediaType.APPLICATION_JSON }));
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<String>(headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url,HttpMethod.GET, entity, String.class);
        String result = response.getBody();
        return result;
    }
}
