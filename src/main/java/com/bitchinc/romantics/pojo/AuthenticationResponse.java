package com.bitchinc.romantics.pojo;

import com.bitchinc.romantics.enums.Status;
import com.bitchinc.romantics.user.domain.UserJSON;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

import static com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * User: prayagparmar
 * Date: 7/24/14
 * Time: 11:01 PM
 */
@JsonInclude(Include.NON_NULL)
public class AuthenticationResponse {
    private Long userId;
    private UserJSON userJSON;
    private Status statusCode;

    public AuthenticationResponse(Status statusCode){
        this.statusCode = statusCode;
    }

    public AuthenticationResponse(Long userId, Status statusCode) {
        this.statusCode = statusCode;
        this.userId = userId;
    }

    public AuthenticationResponse(UserJSON userJSON, Status statusCode){
        this.userJSON = userJSON;
        this.statusCode = statusCode;
    }

    @JsonProperty("user_id")
    public Long getUserId() {
        return userId;
    }

    @JsonProperty("status_code")
    public String getStatusCode() {
        return statusCode.getStatus();
    }

    @JsonProperty("user_profile")
    public UserJSON getUserJSON() {
        return userJSON;
    }
}
