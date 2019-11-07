
package com.example.macchiato.model.pojos.heroi;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Work {

    @Expose
    private String base;
    @Expose
    private String occupation;

    public String getBase() {
        return base;
    }

    public void setBase(String base) {
        this.base = base;
    }

    public String getOccupation() {
        return occupation;
    }

    public void setOccupation(String occupation) {
        this.occupation = occupation;
    }

}
