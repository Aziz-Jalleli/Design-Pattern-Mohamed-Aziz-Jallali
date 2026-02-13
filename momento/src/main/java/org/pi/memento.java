package org.pi;

public class memento {
    private int volume;
    private int brightness;
    private boolean darkmod;

    public memento(int volume, int brightness, boolean darkmod) {
        this.volume = volume;
        this.brightness = brightness;
        this.darkmod = darkmod;
    }
    public memento(Settings s) {
        this.volume = s.getVolume();
        this.brightness = s.getBrightness();
        this.darkmod = s.isDarkmod();
    }

    public int getVolume() {
        return volume;
    }

    public void setVolume(int volume) {
        this.volume = volume;
    }

    public int getBrightness() {
        return brightness;
    }

    public void setBrightness(int brightness) {
        this.brightness = brightness;
    }

    public boolean isDarkmod() {
        return darkmod;
    }

    public void setDarkmod(boolean darkmod) {
        this.darkmod = darkmod;
    }
}
