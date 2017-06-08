package driver;

import connection.Connection;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Optional;

public class Controller {
    private ObservableList<Order> usersData = FXCollections.observableArrayList();
    private static final String allDB = "SELECT * FROM bisnes";
    private static final String delete = "DELETE FROM bisnes WHERE ID=(?)";
    private static final String accept = "UPDATE request SET status='Виконується' WHERE id=(?)";
    private static final String decline = "UPDATE request SET status='Відхилено водієм' WHERE id=(?)";
    private static final String done = "UPDATE request SET status='Виконано' WHERE id=(?)";
    private static final String statusV = "UPDATE drivers SET status='Вільний' WHERE driver_id=(?)";
    private static final String statusZ = "UPDATE drivers SET status='Занятий' WHERE driver_id=(?)";
    static String temp;
    //прив'язка розмітки в FXML таблиці до змінних
    @FXML
    private TableView<Order> tableView;
    @FXML
    private TableColumn<Order, Integer> idColumn;
    @FXML
    private TableColumn<Order, String> nameColumn;
    @FXML
    private TableColumn<Order, String> numberColumn;
    @FXML
    private TableColumn<Order, String> streetColumn;
    @FXML
    private TableColumn<Order, String> housenumberColumn;
    @FXML
    private TableColumn<Order, String> podezdColumn;
    @FXML
    private TableColumn<Order, String> commentColumn;
    @FXML
    private RadioButton rdy;
    @FXML
    private RadioButton busy;
    @FXML
    public void init() {
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        numberColumn.setCellValueFactory(new PropertyValueFactory<>("number"));
        streetColumn.setCellValueFactory(new PropertyValueFactory<>("street"));
        housenumberColumn.setCellValueFactory(new PropertyValueFactory<>("housenumber"));
        podezdColumn.setCellValueFactory(new PropertyValueFactory<>("podezd"));
        commentColumn.setCellValueFactory(new PropertyValueFactory<>("comment"));

        tableView.setItems(usersData);

        try {
            Statement statement = Connection.getConnection().createStatement();
            ResultSet resultSet = statement.executeQuery(allDB);
            usersData.clear();

            while (resultSet.next()) {
                Order order = new Order();
                order.setId(resultSet.getInt(1));
                order.setName(resultSet.getString(2));
                order.setNumber(resultSet.getString(3));
                order.setStreet(resultSet.getString(4));
                order.setHousenumber(resultSet.getString(5));
                order.setPodezd(resultSet.getString(6));
                order.setComment(resultSet.getString(7));
                usersData.add(order);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }//метод для виведення БД у таблицю
    @FXML
    public void rdy(){
        if(rdy.isSelected()){
            temp="Вільний";
            Connection connection = new Connection();
            connection.connection();
            PreparedStatement st;
            try {
                st = connection.getConnection().prepareStatement(statusV);
                st.setInt(1,3);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            init();
        }
        if(busy.isSelected()){
            temp="Занятий";
            Connection connection = new Connection();
            connection.connection();
            PreparedStatement st;
            try {
                st = connection.getConnection().prepareStatement(statusZ);
                st.setInt(1,3);
                st.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }//зміна стану водія(вільний, занятий)
    public void noSelect(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Помилка");
        alert.setHeaderText(null);
        alert.setContentText("Ви не вибрали жодного рядка");
        alert.showAndWait();
    }//вікно, яке викликається коли не вибрано жодного рядка
    @FXML
    public void accept(){
        if (tableView.getSelectionModel().isEmpty() != true) {
            String id = String.valueOf(tableView.getSelectionModel().getSelectedItem().getId());
            int i = Integer.parseInt(id);

            Connection connection = new Connection();
            connection.connection();
            PreparedStatement st;

            try {
                st = connection.getConnection().prepareStatement(accept);
                st.setInt(1, i);
                st.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Виконання...");
            alert.setHeaderText(null);
            alert.setContentText("При завершенні замовлення натисніть ОК");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == ButtonType.OK){
                try {
                    st = connection.getConnection().prepareStatement(done);
                    st.setInt(1, i);
                    st.executeUpdate();

                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        else{
            noSelect();
        }
    }//метод, що виконується коли водій прийняв заявку
    @FXML
    public void decline(){
        if (tableView.getSelectionModel().isEmpty() != true) {
            String id = String.valueOf(tableView.getSelectionModel().getSelectedItem().getId());
            int i = Integer.parseInt(id);

            Connection connection = new Connection();
            connection.connection();
            PreparedStatement st;
            PreparedStatement st2;

            try {
                st = connection.getConnection().prepareStatement(decline);
                st.setInt(1, i);
                st.executeUpdate();

                st2 = connection.getConnection().prepareStatement(delete);
                st2.setInt(1, i);
                st2.executeUpdate();

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        else{
            noSelect();
        }
    }//метод, що виконується коли водій відхилив заявку
}
