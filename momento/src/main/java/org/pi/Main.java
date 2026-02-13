package org.pi;

import org.pi.Settings;
import org.pi.caretaker;

public class Main {

    public static void main(String[] args) {

        Settings settings = new Settings(50, 70, false);
        caretaker caretaker = new caretaker();

        System.out.println("state");
        settings.DisplayState();

        System.out.println("\n Update :");
        settings.updateSettings(30, 40, true);
        settings.DisplayState();

        System.out.println("\n save");
        caretaker.save(settings.save());

        System.out.println("\n update");
        settings.updateSettings(80, 100, false);
        settings.DisplayState();

        System.out.println("\n restauring");
        settings.restore(caretaker.getSavedState());

        System.out.println("\n Paramètres restaurés :");
        settings.DisplayState();
    }
}
