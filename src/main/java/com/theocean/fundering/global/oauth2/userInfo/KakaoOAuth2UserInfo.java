package com.theocean.fundering.global.oauth2.userInfo;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    public Long getId() {
        return Long.valueOf(String.valueOf(attributes.get("id")));
    }

    @Override
    public String getNickname() {
        return getNestedAttribute("kakao_account.profile.nickname");
    }

    @Override
    public String getImageUrl() {
        return getNestedAttribute("kakao_account.profile.thumbnail_image_url");
    }

    private String getNestedAttribute(String nestedAttributePath) {
        String[] attributesArray = nestedAttributePath.split("\\.");
        Map<String, Object> nestedAttributes = attributes;

        for (String attribute : attributesArray) {
            if (nestedAttributes == null) {
                return null;
            }
            nestedAttributes = (Map<String, Object>) nestedAttributes.get(attribute);
        }

        return (nestedAttributes != null) ? String.valueOf(nestedAttributes) : null;
    }

    /*@Override
    public String getNickname() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("nickname");
    }

    @Override
    public String getImageUrl() {
        Map<String, Object> account = (Map<String, Object>) attributes.get("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) account.get("profile");

        if (account == null || profile == null) {
            return null;
        }

        return (String) profile.get("thumbnail_image_url");
    }*/
}