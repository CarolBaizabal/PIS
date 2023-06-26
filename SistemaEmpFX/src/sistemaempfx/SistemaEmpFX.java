package sistemaempfx;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.image.Image;

public class SistemaEmpFX extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        //Mandamos a llamar a la ventana de login
        Parent login = FXMLLoader.load(getClass().getResource("/sistemaempfx/gui/view/LoginFXML.fxml"));

        Scene scene = new Scene(login);
        //Nombramos la ventana
        stage.setTitle("Financiera Ramírez S.A DE C.V");
        //agregamos un icono a la ventana
        stage.getIcons().add(new Image("/sistemaempfx/gui/img/icon.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

}
