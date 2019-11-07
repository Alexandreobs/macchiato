
package com.example.macchiato.model.pojos.heroi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


@SuppressWarnings("unused")
public class SuperHero {

    @Expose
    private String response;
    @Expose
    private List<Result> results;
    @SerializedName("results-for")
    private String resultsFor;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public Result getResults() {
        return (Result) results;
    }

    public void setResults(List<Result> results) {
        this.results = results;
    }

    public String getResultsFor() {
        return resultsFor;
    }

    public void setResultsFor(String resultsFor) {
        this.resultsFor = resultsFor;
    }

}
