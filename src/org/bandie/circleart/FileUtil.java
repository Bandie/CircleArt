/*
 * Copyright (c) 2017, bandie
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * * Redistributions of source code must retain the above copyright notice, this
 *   list of conditions and the following disclaimer.
 * * Redistributions in binary form must reproduce the above copyright notice,
 *   this list of conditions and the following disclaimer in the documentation
 *   and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */
package org.bandie.circleart;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Paths;
import javafx.embed.swing.SwingFXUtils;
import javafx.scene.SnapshotParameters;
import javafx.scene.control.Alert;
import javafx.scene.image.WritableImage;
import javafx.scene.layout.Pane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

/**
 *
 * @author bandie
 */
public abstract class FileUtil {

    /**
     *
     * @param stage The main stage
     * @param drawPane The pane which needs to be saved.
     */
    static protected void saveFile(Stage stage, Pane drawPane) {
        //FileChooser
        FileChooser fc = new FileChooser();
        fc.setTitle(CircleArt.MSGS.getString("SAVE_IMG"));
        fc.getExtensionFilters().add(new FileChooser.ExtensionFilter("PNG File", "*.png"));

        //Prevent overwriting older files
        File initFile = new File("CircleArt.png");
        long i = 0;
        while (initFile.exists()) {
            i++;
            initFile = new File("CircleArt_" + i + ".png");

        }
        fc.setInitialFileName(initFile.getName());
        File file = fc.showSaveDialog(stage);

        if (file != null) {

            //Screenshot
            WritableImage wi = drawPane.snapshot(new SnapshotParameters(), null);
            BufferedImage bi = SwingFXUtils.fromFXImage(wi, null);

            try {
                //Check Permissions
                File path = Paths.get(file.getAbsolutePath()).getParent().toFile();
                if (!path.canWrite()) {
                    throw new FileNotFoundException(file.getAbsoluteFile() + " (Permission denied)");
                }

                ImageIO.write(bi, "png", file);
            } catch (IOException e) {

                boolean fscr = stage.isFullScreen();

                if (fscr) {
                    stage.setFullScreen(false);
                }

                Alert a = new Alert(Alert.AlertType.ERROR);
                a.setTitle("Error");
                a.setHeaderText("Error1!1");
                a.setContentText(e.getLocalizedMessage());

                a.showAndWait();

                if (fscr) {
                    stage.setFullScreen(true);
                }
            }

        }
    }
}
