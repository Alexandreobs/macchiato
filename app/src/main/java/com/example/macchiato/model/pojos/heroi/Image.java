
package com.example.macchiato.model.pojos.heroi;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Image {

    @Expose
    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

}
