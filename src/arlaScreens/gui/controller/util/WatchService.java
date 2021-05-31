package arlaScreens.gui.controller.util;

import java.io.IOException;
import java.nio.file.*;

public class WatchService extends Thread {



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