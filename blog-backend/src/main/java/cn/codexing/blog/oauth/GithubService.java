package cn.codexing.blog.oauth;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;

/**
 * @author GuoXing
 * @date 2020/5/13 10:39
 */
@Component
public class GithubService {

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.client.accessTokenUri}")
    private String accessTokenUri;
    @Value("${github.client.userAuthorizationUri}")
    private String userAuthorizationUri;
    @Value("${github.client.redirectUri}")
    private String redirectUri;
    @Value("${github.resource.userInfoUri}")
    private String userInfoUri;

    /**
     * 获取登录连接
     *
     * @return
     */
    public String getUrl() {
        return userAuthorizationUri + "?client_id=" + clientId + "&redirect_uri=" + redirectUri;
    }

    /**
     * 登录成功后获取用户信息
     *
     * @param code
     * @return
     */
    public Object callback(@RequestParam("code") String code) {
        String accessToken = getAccessToken(code);
        String userInfo = getUserInfo(accessToken);
        return JSONObject.parseObject(userInfo);
    }

    /**
     * 获取token
     *
     * @param code
     * @return
     */
    private String getAccessToken(String code) {
        String url = accessTokenUri + "?client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code;
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // post 请求方式
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String responseStr = response.getBody();
        // 解析响应json字符串
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        return jsonObject.getString("access_token");
    }

    /**
     * 获取用户信息
     *
     * @param accessToken
     * @return
     */
    private String getUserInfo(String accessToken) {
        String url = userInfoUri;
        // 构建请求头
        HttpHeaders requestHeaders = new HttpHeaders();
        // 指定响应返回json格式
        requestHeaders.add("accept", "application/json");
        // AccessToken放在请求头中
        requestHeaders.add("Authorization", "token " + accessToken);
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(requestHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // get请求方式
        ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.GET, requestEntity, String.class);
        return response.getBody();
    }
}
