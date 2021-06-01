package arlaScreens.gui.controller.util;

import java.io.IOException;
import java.nio.file.*;

public class WatchService extends Thread {


    /**
     * Metode der Overrider run metoden klassen arver fra Thread.
     * Metoden opretter en ny Watch Service, opretter en path, og registrerer en watckey til den oprettede path.
     * Derefter køres et uendeligt loop så metoden konstant holder øje med ændringer i det registrerede path.
     */
    public void run() {
        try {
             java.nio.file.WatchService watchService = FileSystems.getDefault().newWatchService();
             Path path = Paths.get("src/files");
             WatchKey watchKey = path.register(watchService, StandardWatchEventKinds.ENTRY_MODIFY);

            while (true) {
             for (WatchEvent<?> event : watchKey.pollEvents()) {
                 System.out.println(event.kind());
                 Path file = (Path) event.context();
                 System.out.println(file);
                 watchKey.reset(); }
            }
      } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}