package test;

import com.utils.JsonUtils;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;

public class Request {

    public static String getToken(String url, String phone, String password) {
        HttpHeaders headers = new HttpHeaders();
        MultiValueMap<String, String> parametersMap = new LinkedMultiValueMap<String, String>();
        parametersMap.add("phone", phone);
        parametersMap.add("password", password);
        HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(parametersMap, headers);
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.POST, requestEntity, String.class);
        System.out.println(String.format("======[ body: %s]========", response.getBody()));
        return (String)JsonUtils.convertJsonToMap(response.getBody()).get("Authorization");
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
        System.out.println(String.format("======[ body: %s]========", result));
        return result;
    }
}
