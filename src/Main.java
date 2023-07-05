import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Main {
    public static void main(String[] args) {
        try (ZipInputStream zin = new ZipInputStream(new
                FileInputStream("I://Games/savegames/zip_output.zip"))) {
            ZipEntry entry;
            String name;
            while ((entry = zin.getNextEntry()) != null) {
                name = entry.getName(); // получим название файла// распаковка
                FileOutputStream fout = new FileOutputStream("I://Games/savegames/" + name);
                for (int c = zin.read(); c != -1; c = zin.read()) {
                    fout.write(c);
                }
                fout.flush();
                zin.closeEntry();
                fout.close();
            }
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }

        GameProgress gameProgress = null;
// откроем входной поток для чтения файла
        try (FileInputStream fis = new FileInputStream("I://Games/savegames/save1.dat");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
// десериализуем объект и скастим его в класс
            gameProgress = (GameProgress) ois.readObject();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        System.out.println(gameProgress);
    }
}