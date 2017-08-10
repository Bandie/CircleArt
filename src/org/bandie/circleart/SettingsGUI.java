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

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Slider;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

/**
 *
 * @author bandie
 */
public class SettingsGUI extends Application {

    public void start(Stage stage) {

        //stage.initOwner(CircleArtGUI.STAGE);
        GridPane gp = new GridPane();
        BorderPane bp = new BorderPane();
        gp.setMaxSize(640, 480);

        //Circle Size
        Label lSize = new Label(CircleArt.MSGS.getString("SETTINGS_CIRC_SIZE"));
        lSize.setStyle("-fx-font-weight: bold");
        Label lSizePX = new Label("px");
        Slider sSize = new Slider();
        TextField tfSize = new TextField(Integer.toString(CircleArtGUI.P.getSizeMode()));
        tfSize.setPrefColumnCount(2);
        tfSize.textProperty().addListener((ov, t, t1) -> {
            try {
                CircleArtGUI.P.setSizeMode(Integer.parseInt(ov.getValue()));
                sSize.setValue(Integer.parseInt(ov.getValue()));
            } catch (NumberFormatException e) {

            }
        });
        sSize.setMax(600);
        sSize.setMin(0);
        sSize.setShowTickLabels(true);
        sSize.setShowTickMarks(true);
        sSize.setMajorTickUnit(200);
        sSize.setMinorTickCount(100);
        sSize.setValue(CircleArtGUI.P.getSizeMode());
        sSize.valueProperty().addListener(cl -> {
            CircleArtGUI.P.setSizeMode((int) sSize.getValue());
            tfSize.setText(Integer.toString(CircleArtGUI.P.getSizeMode()));
        });

        //Circle sin rythm
        Label lDistance = new Label(CircleArt.MSGS.getString("SETTINGS_DIST"));
        lDistance.setStyle("-fx-font-weight: bold");
        Label lDistancePX = new Label("px");
        Slider sDistance = new Slider();
        TextField tfDistance = new TextField(Integer.toString(CircleArtGUI.P.getDistance()));
        tfDistance.setPrefColumnCount(2);
        tfDistance.textProperty().addListener((ov, t, t1) -> {
            try {
                CircleArtGUI.P.setDistance(Integer.parseInt(ov.getValue()));
                sDistance.setValue(Integer.parseInt(ov.getValue()));
            } catch (NumberFormatException e) {

            }
        });
        sDistance.setMax(600);
        sDistance.setMin(0);
        sDistance.setShowTickLabels(true);
        sDistance.setShowTickMarks(true);
        sDistance.setMajorTickUnit(200);
        sDistance.setMinorTickCount(100);
        sDistance.setValue(CircleArtGUI.P.getDistance());
        sDistance.valueProperty().addListener(cl -> {
            CircleArtGUI.P.setDistance((int) sDistance.getValue());
            tfDistance.setText(Integer.toString(CircleArtGUI.P.getDistance()));
        });

        //Y-Switch
        Label lDir=new Label(CircleArt.MSGS.getString("SETTINGS_DIR"));
        lDir.setStyle("-fx-font-weight: bold");
        CheckBox cbY = new CheckBox(CircleArt.MSGS.getString("SETTINGS_Y"));
        cbY.setSelected(CircleArtGUI.P.isIsUsingY());
        cbY.setOnAction(eh -> {
            CircleArtGUI.P.setIsUsingY(cbY.isSelected());
        });

        //COLORS
        Label lColor = new Label(CircleArt.MSGS.getString("SETTINGS_COLOR"));
        lColor.setStyle("-fx-font-weight: bold");
        Label lRed = new Label(CircleArt.MSGS.getString("RED"));
        lRed.setTextFill(Color.DARKRED);
        Label lGreen = new Label(CircleArt.MSGS.getString("GREEN"));
        lGreen.setTextFill(Color.DARKGREEN);
        Label lBlue = new Label(CircleArt.MSGS.getString("BLUE"));
        lBlue.setTextFill(Color.DARKBLUE);
        int[] cm = CircleArtGUI.P.getColorMode();
        //Red
        ToggleGroup red = new ToggleGroup();
        RadioButton[] r = new RadioButton[3];
        r[0] = new RadioButton("(x+y) mod 256");
        r[1] = new RadioButton("|x-y| mod 256");
        r[2] = new RadioButton("x*y mod 256");
        r[cm[0]].setSelected(true);
        for (int i = 0; i < 3; i++) {
            r[i].setToggleGroup(red);
        }
        r[0].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.RED, 0);
        });
        r[1].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.RED, 1);
        });
        r[2].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.RED, 2);
        });
        //Green
        ToggleGroup green = new ToggleGroup();
        RadioButton[] g = new RadioButton[3];
        g[0] = new RadioButton("(x+y) mod 256");
        g[1] = new RadioButton("|x-y| mod 256");
        g[2] = new RadioButton("x*y mod 256");
        g[cm[1]].setSelected(true);
        for (int i = 0; i < 3; i++) {
            g[i].setToggleGroup(green);
        }
        g[0].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.GREEN, 0);
        });
        g[1].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.GREEN, 1);
        });
        g[2].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.GREEN, 2);
        });
        //Blue
        ToggleGroup blue = new ToggleGroup();
        RadioButton[] b = new RadioButton[3];
        b[0] = new RadioButton("(x+y) mod 256");
        b[1] = new RadioButton("|x-y| mod 256");
        b[2] = new RadioButton("x*y mod 256");
        b[cm[2]].setSelected(true);
        for (int i = 0; i < 3; i++) {
            b[i].setToggleGroup(blue);
        }
        b[0].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.BLUE, 0);
        });
        b[1].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.BLUE, 1);
        });
        b[2].setOnAction(eh -> {
            CircleArtGUI.P.setColorMode(Pen.RGB.BLUE, 2);
        });

        gp.setMinSize(320, 160);
        gp.setVgap(10);
        gp.setHgap(10);

        //Size
        gp.add(lSize, 0, 0);
        gp.add(sSize, 0, 1);
        gp.add(tfSize, 1, 1);
        gp.add(lSizePX, 2, 1);
        //Distance
        gp.add(lDistance, 0, 2);
        gp.add(sDistance, 0, 3);
        gp.add(tfDistance, 1, 3);
        gp.add(lDistancePX, 2, 3);
        //Y-Switch
        gp.add(lDir, 0, 4);
        gp.add(cbY, 0, 5);
        //RGB
        gp.add(lColor, 0, 6);
        gp.add(lRed, 0, 7);
        gp.add(r[0], 0, 8);
        gp.add(r[1], 1, 8);
        gp.add(r[2], 2, 8);
        gp.add(lGreen, 0, 9);
        gp.add(g[0], 0, 10);
        gp.add(g[1], 1, 10);
        gp.add(g[2], 2, 10);
        gp.add(lBlue, 0, 11);
        gp.add(b[0], 0, 12);
        gp.add(b[1], 1, 12);
        gp.add(b[2], 2, 12);

        bp.setCenter(gp);
        bp.setPadding(new Insets(10));

        Scene settings = new Scene(bp);

        settings.addEventHandler(KeyEvent.KEY_PRESSED, eh -> {
            if (eh.getCode() == KeyCode.ESCAPE) {
                stage.close();
            }
        });

        stage.setScene(settings);
        stage.setTitle(CircleArt.MSGS.getString("SETTINGS_TITLE"));
        stage.show();

    }

}
