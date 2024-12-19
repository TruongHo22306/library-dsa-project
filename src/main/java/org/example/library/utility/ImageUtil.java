package org.example.library.utility;

import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import org.example.library.common.FilePath;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

public class ImageUtil {
    public static String saveAndLoadImage(File selectedFile, String id, Label lb, ImageView img) {
        String targetDir = FilePath.BASE_PATH;

        File directory = new File(targetDir);
        if (!directory.exists()) {
            directory.mkdirs();
        }

        String extension = selectedFile.getName().substring(selectedFile.getName().lastIndexOf("."));
        File destinationFile = new File(targetDir + id + extension);

        try {
            Files.copy(selectedFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);

            lb.setVisible(false);
            img.setImage(new Image(destinationFile.toURI().toString()));

            return destinationFile.getPath().replace("\\", "/").substring(targetDir.length());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static File fileChooser(){
        FileChooser fileChooser = new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("Image Files","*.jpg")
        );

        return fileChooser.showOpenDialog(new Stage());
    }
}
