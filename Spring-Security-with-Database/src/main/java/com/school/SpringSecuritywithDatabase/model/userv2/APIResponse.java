package com.school.SpringSecuritywithDatabase.model.userv2;

import java.util.List;

public class APIResponse {
    List<UserAPI> results;

    public APIResponse() {
    }

    public APIResponse(List<UserAPI> results) {
        this.results = results;
    }

    public List<UserAPI> getResults() {
        return results;
    }

    public void setResults(List<UserAPI> results) {
        this.results = results;
    }
}
