package org.pi;

public class Settings {
    private int volume;
    private int brightness;
    private boolean darkmod;

    public Settings(int volume, int brightness, boolean darkmod) {
        this.volume = volume;
        this.brightness = brightness;
        this.darkmod = darkmod;
    }
    public void DisplayState (){
        System.out.println("Volume : " + volume);
        System.out.println("Volume : " + brightness);
        System.out.println("Volume : " + darkmod);
    }
    public void updateSettings (int volume, int brightness, boolean darkmod){
        this.volume = volume;
        this.brightness = brightness;
        this.darkmod = darkmod;
    }
    public memento save(){
        return new memento(this.volume, this.brightness, this.darkmod);
    }
    public void restore(memento m){
        this.volume = m.getVolume();
        this.brightness = m.getBrightness();
        this.darkmod = m.isDarkmod();
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
