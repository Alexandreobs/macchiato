
package com.example.macchiato.model.pojos.heroi;

import com.google.gson.annotations.Expose;


@SuppressWarnings("unused")
public class Powerstats {

    @Expose
    private String combat;
    @Expose
    private String durability;
    @Expose
    private String intelligence;
    @Expose
    private String power;
    @Expose
    private String speed;
    @Expose
    private String strength;

    public String getCombat() {
        return combat;
    }

    public void setCombat(String combat) {
        this.combat = combat;
    }

    public String getDurability() {
        return durability;
    }

    public void setDurability(String durability) {
        this.durability = durability;
    }

    public String getIntelligence() {
        return intelligence;
    }

    public void setIntelligence(String intelligence) {
        this.intelligence = intelligence;
    }

    public String getPower() {
        return power;
    }

    public void setPower(String power) {
        this.power = power;
    }

    public String getSpeed() {
        return speed;
    }

    public void setSpeed(String speed) {
        this.speed = speed;
    }

    public String getStrength() {
        return strength;
    }

    public void setStrength(String strength) {
        this.strength = strength;
    }

}
