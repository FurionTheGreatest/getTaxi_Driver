package driver;

import connection.Connection;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {        //Точка входу в програму
        Parent root = FXMLLoader.load(getClass().getResource("driver.fxml"));//Відрисовка вікна
        primaryStage.setTitle("getTaxi_Driver");
        primaryStage.setScene(new Scene(root, 900, 500));
        primaryStage.setResizable(false);
        primaryStage.show();
        Connection connection;//встановлення з'єднання з сервером
        connection = new Connection();
        connection.connection();
        }
}



