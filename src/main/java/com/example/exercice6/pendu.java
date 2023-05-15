package com.example.exercice6;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class pendu extends Application {
    private String motSecret = "PENDU";
    private int viesRestantes = 7;
    private StringBuilder motActuel;

    private Label labelMotActuel;
    private Label labelViesRestantes;
    private TextField textFieldLettre;
    private ImageView imageViewPendu;

    @Override
    public void start(Stage primaryStage) {
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));

        // Création image pendu
        Image imagePendu = new Image(getClass().getResourceAsStream("pendu7.png"));
        imageViewPendu = new ImageView(imagePendu);
        root.setCenter(imageViewPendu);

        // Création d'un new label


        labelMotActuel = new Label();
        labelMotActuel.setTextFill(Color.BLACK);
        root.setTop(labelMotActuel);

        // Création du TextField


        textFieldLettre = new TextField();
        textFieldLettre.setPrefWidth(200);
        textFieldLettre.setOnAction(event -> {
            String lettre = textFieldLettre.getText().toUpperCase();
            textFieldLettre.clear();
            verifierLettre(lettre);
        });
        root.setLeft(textFieldLettre);

        // label nombre vie restantes

        labelViesRestantes = new Label("Vies restantes : " + viesRestantes);
        root.setRight(labelViesRestantes);

        // Affi fenêtre

        Scene scene = new Scene(root, 500, 300);
        primaryStage.setTitle("Jeu du Pendu");
        primaryStage.setScene(scene);
        primaryStage.show();

        // Init mot actuels

        motActuel = new StringBuilder();
        for (int i = 0; i < motSecret.length(); i++) {
            motActuel.append("-");
        }
        labelMotActuel.setText(motActuel.toString());
    }

    private void verifierLettre(String lettre) {
        boolean lettreTrouvee = false;
        for (int i = 0; i < motSecret.length(); i++) {
            if (Character.toString(motSecret.charAt(i)).equals(lettre)) {
                motActuel.setCharAt(i, lettre.charAt(0));
                lettreTrouvee = true;
            }
        }

        if (!lettreTrouvee) {
            viesRestantes--;
            mettreAJourImagePendu();
        }

        if (viesRestantes == 0) {
            finPartie(false);
        } else if (motActuel.indexOf("-") == -1) {
            finPartie(true);
        } else {
            labelMotActuel.setText(motActuel.toString());
            labelViesRestantes.setText("Vies restantes : " + viesRestantes);
        }
    }

    private void mettreAJourImagePendu() {
        int indexImage =0 + viesRestantes;
        Image imagePendu = new Image(getClass().getResourceAsStream("pendu" + indexImage + ".png"));
        imageViewPendu.setImage(imagePendu);

    }

    private void finPartie(boolean victoire) {
        textFieldLettre.setDisable(true);

        if (victoire) {
            labelMotActuel.setText("Bravo ! Vous avez trouvé le mot : " + motActuel.toString());
            imageViewPendu.setImage(new Image(getClass().getResourceAsStream("penduWin.png")));
        } else {
            labelMotActuel.setText("Dommage ! Le mot était : " + motSecret);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}