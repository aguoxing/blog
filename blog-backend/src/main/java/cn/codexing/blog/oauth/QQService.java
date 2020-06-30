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
 * @date 2020/5/13 13:47
 */
@Component
public class QQService {
    @Value("${qq.client_id}")
    private String clientId;
    @Value("${qq.client_secret}")
    private String clientSecret;
    @Value("${qq.userAuthorizationUri}")
    private String userAuthorizationUri;
    @Value("${qq.accessTokenUri}")
    private String accessTokenUri;
    @Value("${qq.redirect_uri}")
    private String redirectUri;
    @Value("qq.state")
    private String state;
    @Value("${qq.response_type}")
    private String responseType;
    @Value("${qq.grant_type}")
    private String grantType;
    @Value("${qq.userInfoUri}")
    private String userInfoUri;

    /**
     * 获取登录连接
     *
     * @return
     */
    public String getUrl() {
        return userAuthorizationUri + "?response_type=" + responseType + "&client_id=" + clientId + "&redirect_uri=" + redirectUri + "&state=" + state;
    }

    /**
     * 回调：获取用户信息
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
     * 根据code获取accessToken
     *
     * @param code
     * @return
     */
    private String getAccessToken(String code) {
        String url = accessTokenUri + "?grant_type=" + grantType + "&client_id=" + clientId + "&client_secret=" + clientSecret + "&code=" + code + "&redirect_uri=" + redirectUri;
        // 构建请求头
        HttpHeaders httpHeaders = new HttpHeaders();
        // 指定响应返回json格式
        httpHeaders.add("accept", "application/json");
        // 构建请求实体
        HttpEntity<String> requestEntity = new HttpEntity<>(httpHeaders);
        RestTemplate restTemplate = new RestTemplate();
        // post 请求方式
        ResponseEntity<String> response = restTemplate.postForEntity(url, requestEntity, String.class);
        String responseStr = response.getBody();

        // 解析响应json字符串
        JSONObject jsonObject = JSONObject.parseObject(responseStr);
        return jsonObject.getString("access_token");
    }

    /**
     * 根据accessToken获取用户信息
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
