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

import javafx.geometry.Insets;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 *
 * @author bandie
 */
public abstract class About {

    static protected void show(Stage stage) {

        boolean fscr = stage.isFullScreen();

        String progname = "CircleArt";
        String copyright = "\n\u00a9 2017 by Bandie Canis <bandie@bandie.org>.";
        String licensetext
                = "CirceArt is licensed under BSD-2-Clause:\n\n\n"
                + "Copyright \u00a9 2017, Bandie Canis"
                + "\nAll rights reserved.\n\n"
                + "Redistribution and use in source and binary forms, with or without modification, \n"
                + "are permitted provided that the following conditions are met:\n\n"
                + "1.\tRedistributions of source code must retain the above copyright notice, \n"
                + "\tthis list of conditions and the following disclaimer.\n"
                + "2.\tRedistributions in binary form must reproduce the above copyright notice, \n"
                + "\tthis list of conditions and the following disclaimer in the documentation \n"
                + "\tand/or other materials provided with the distribution.\n\n"
                + "THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.";

        ImageView imgBandie = new ImageView(About.class.getResource("BandieCanis.gif").toString());
        GridPane gp = new GridPane();
        BorderPane bp = new BorderPane();

        Label prog = new Label(progname);
        prog.setStyle("-fx-font-weight: bold");
        Label copy = new Label(copyright);
        TextArea license = new TextArea(licensetext);
        license.setEditable(false);
        license.setWrapText(true);

        gp.setHgap(20.0);
        gp.addColumn(0, prog, copy);

        gp.addColumn(1, imgBandie);
        gp.setPadding(new Insets(20, 20, 20, 20));

        bp.setTop(gp);
        bp.setBottom(license);

        Alert alert = new Alert(AlertType.INFORMATION);
        alert.setHeight(630.0);
        alert.setWidth(680.0);
        alert.setResizable(false);
        alert.setTitle("About CircleArt");
        alert.setHeaderText("About CircleArt");
        alert.getDialogPane().setContent(bp);

        /*
        alert.widthProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            System.out.println("Width:" + ov.getValue());
        });

        alert.heightProperty().addListener((ObservableValue<? extends Number> ov, Number t, Number t1) -> {
            System.out.println("Height:" + ov.getValue());
        });
         */
        if (fscr) {
            stage.setFullScreen(false);
        }

        alert.showAndWait();

        if (fscr) {
            stage.setFullScreen(true);
        }

    }

}
