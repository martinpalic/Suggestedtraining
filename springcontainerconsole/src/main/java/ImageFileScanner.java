import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ImageFileScanner {

    private String imageExtension;

    public ImageFileScanner(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public String getImageExtension() {
        return imageExtension;
    }

    public void setImageExtension(String imageExtension) {
        this.imageExtension = imageExtension;
    }

    public List<byte[]> scanCurrentFolder() {
        return scanDirectory(".");
    }

    public List<byte[]> scanDirectory(String path) {
        File currentFolder = new File(path);
        List fileContentList = new ArrayList<byte[]>();

        System.out.println("Scanning: " + currentFolder.getAbsolutePath());

        for (File file : currentFolder.listFiles()
        ) {
            if (file.isDirectory()) continue;
            if (FilenameUtils.getExtension(file.getName()).equals(imageExtension)) {
                try {
                    fileContentList.add(FileUtils.readFileToByteArray(file));
                } catch (IOException e) {
                    System.out.println("!! Unable to read File :\n" + file.getPath());
                }
            }
        }
        
        return fileContentList;
    }
}
