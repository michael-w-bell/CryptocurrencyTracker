package application;

import java.io.IOException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;


public class Main extends Application {
	
	private TableView<Crypto> tableView = new TableView<Crypto>();
	private static ArrayList<Crypto> cryptoData = new ArrayList<Crypto>();
	private static ObservableList<Crypto> data = FXCollections.observableArrayList(cryptoData);
	
	@Override
	public void start(Stage primaryStage) {
		try {
			GridPane root = new GridPane();

			//title text
			Text titleText = new Text();
			titleText.setText("Mike's Crypto Portfolio");
			titleText.setY(600);
			titleText.setFont(Font.font("Veranda", FontWeight.BOLD, FontPosture.REGULAR,40));

			//refresh button
			Button refresh = new Button("Refresh Prices");
			refresh.setOnAction(e -> {
				//ADD button refresh
			});

			//total amount text
			Text totalDollar = new Text();
			totalDollar.setText(getCoinValues.getTotalDollar());

			//table columns
			TableColumn coinColumn = new TableColumn("Coin");
			coinColumn.setCellValueFactory(new PropertyValueFactory<>("coinName"));

			TableColumn costColumn = new TableColumn("Cost");
			costColumn.setCellValueFactory(new PropertyValueFactory<>("totalSpent"));

			TableColumn coinBoughtColumn = new TableColumn("Coins Bought");
			coinBoughtColumn.setCellValueFactory(new PropertyValueFactory<>("coinsBought"));

			TableColumn costPerCoinColumn = new TableColumn("Cost per Coin");
			costPerCoinColumn.setCellValueFactory(new PropertyValueFactory<>("costPerCoin"));

			TableColumn currentPriceColumn = new TableColumn("Current Coin Price");
			currentPriceColumn.setCellValueFactory(new PropertyValueFactory<>("currentPrice"));

			TableColumn currentValueColumn = new TableColumn("Curren Value");
			currentValueColumn.setCellValueFactory(new PropertyValueFactory<>("currentValue"));

			TableColumn profitColumn = new TableColumn("Profit");
			profitColumn.setCellValueFactory(new PropertyValueFactory<>("profit"));

			TableColumn roiColumn = new TableColumn("ROI");
			roiColumn.setCellValueFactory(new PropertyValueFactory<>("roi"));


			tableView.setItems(data);
			tableView.getColumns().addAll(coinColumn, costColumn, coinBoughtColumn, costPerCoinColumn, currentPriceColumn, currentValueColumn, profitColumn, roiColumn);


			Scene scene = new Scene(root,1200,900);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			root.setHgap(10);
			root.setVgap(10);

			//sets gridLines visible for debug
			root.setGridLinesVisible(true);

			primaryStage.setScene(scene);
			primaryStage.setTitle("Mike's Crypto Portfolio");
			root.add(titleText, 0,0);
			root.add(refresh, 3, 0);
			root.add(tableView, 0, 1);

			

			primaryStage.show();

			new Thread () {
				@Override
				public void run() {
					try {
						readCSV.readFile("crypto.csv" , cryptoData , data);
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				};
			}.start();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}//*****************END Start***************************


	

	

	//***************MAIN******************
	public static void main(String[] args) throws IOException {

		launch(args);
	}//***************END MAIN*************


	
}//END MAIN CLASS
