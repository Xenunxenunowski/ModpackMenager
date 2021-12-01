package openmanager;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.PixelWriter;
import javafx.scene.image.WritableImage;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import openmanager.fxml.Controller;

import java.awt.image.BufferedImage;
import java.io.File;

public class Main extends Application {
    private double xOffset = 0;
    private double yOffset = 0;
    @Override
    public void start(Stage primaryStage) throws Exception {
        System.out.println(System.getenv("HOME"));
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/main.fxml"));
        Parent root = loader.load();
        root.getStylesheets().add("/css/mainStyle.css");
        primaryStage.setTitle("OpenManager");
        primaryStage.setScene(new Scene(root));
        System.out.println(File.separator);

        Controller newProjectController = loader.getController();

        newProjectController.setStage(primaryStage);
        primaryStage.setResizable(false);
        Scene mainScene = primaryStage.getScene();
        primaryStage.initStyle(StageStyle.UNDECORATED);
        primaryStage.show();
        root.setOnMousePressed(event -> {
            xOffset = event.getSceneX();
            yOffset = event.getSceneY();
        });
        //VERY IMPORTANT LEAVE IT THERE
        //URL urlInput = new URL("https://memorynotfound.com/wp-content/uploads/java-duke.png");
        //BufferedImage urlImage = ImageIO.read(urlInput);
        //Image im = convertToFxImage(urlImage);
        //move around here
        root.setOnMouseDragged(event -> {
            primaryStage.setX(event.getScreenX() - xOffset);
            primaryStage.setY(event.getScreenY() - yOffset);
        });

    }
    private static Image convertToFxImage(BufferedImage image) {
        WritableImage wr = null;
        if (image != null) {
            wr = new WritableImage(image.getWidth(), image.getHeight());
            PixelWriter pw = wr.getPixelWriter();
            for (int x = 0; x < image.getWidth(); x++) {
                for (int y = 0; y < image.getHeight(); y++) {
                    pw.setArgb(x, y, image.getRGB(x, y));
                }
            }
        }

        return new ImageView(wr).getImage();
    }
    public static void main(String[] args) {
        launch(args);
    }
}