package Phase1;

import java.time.LocalDate;
import java.util.Locale;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class DistricScreen {
	private Label lb1, lb2, res2, lb3, res3, lb4, res4, lb5, res5, lb6, res6, lb7, res7, lb8, res8, lb9, res9, lb10, res10, lb11, res11, dres, lbs;
	private Button bt1, bt2, bt3, bt4, bt5, bt6, bt7, bts1, bts2;
	private TextField txt1, txt2, txt3, dtxt;
	private GridPane gp, gp1, gp2, gp3, gp4, gp5;
	private VBox vb, vb2, dvb, vbs;
	private HBox hb, hb1, hb2, hb3, hb4, hb5, hb6, hb7, hb8, hb9, hbox, hbox1, hbs;
	private DatePicker dp;
	private LinkedList list = MyApp.getList();
	private DNode district = MyApp.getList().getFirst();
	private String cityName = ((String)MyApp.getList().getFirst().getKey());
	private Alert alert;
	private Stage stg;
	private Scene sc;
	
	public DistricScreen() {
		
		lb7 = new Label("Enter district name to insert: ");
		lb7.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb7.setTextFill(Color.WHITE);
		res7  = new Label();
		res7.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		txt1 = new TextField();
		bt3 = new Button("Insert");
		bt3.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb1 = new HBox(5);
		hb2 = new HBox(5);
		hb1.getChildren().addAll(lb7, txt1);
		hb2.getChildren().addAll(bt3, res7);
		gp1 = new GridPane();
		gp1.add(hb1, 0, 0);
		gp1.add(hb2, 0, 1);
		gp1.setVgap(5);
		gp1.setPadding(new Insets(10, 10, 10, 10));
		gp1.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		bt3.setOnAction(e->{
			if(txt1.getText().isEmpty()) {
				res7.setTextFill(Color.RED);
				res7.setText("Enter a name");
			}
			else if(list.addCity(txt1.getText())) {
				res7.setTextFill(Color.LIGHTGREEN);
				res7.setText("Added successfully");
			}else {
				res7.setTextFill(Color.RED);
				res7.setText("Exist before");
			}
		});
		
		lb8 = new Label("Enter district name to delete: ");
		lb8.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb8.setTextFill(Color.WHITE);
		txt2 = new TextField(); 
		res8 = new Label();
		res8.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		bt4 = new Button("Delete");
		bt4.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb3 = new HBox(5);
		hb4 = new HBox(5);
		hb3.getChildren().addAll(lb8, txt2);
		hb4.getChildren().addAll(bt4, res8);
		gp2 = new GridPane();
		gp2.add(hb3, 0, 0);
		gp2.add(hb4, 0, 1);
		gp2.setVgap(5);
		gp2.setPadding(new Insets(10, 10, 10, 10));
		gp2.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		lbs = new Label("Are you sure to delete? all data will be removed");
		lbs.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lbs.setTextFill(Color.RED);
		bts1 = new Button("Yes");
		bts2 = new Button("No");
		hbs = new HBox(10);
		vbs = new VBox(10);
		hbs.getChildren().addAll(bts1, bts2);
		vbs.getChildren().addAll(lbs, hbs);
		vbs.setAlignment(Pos.CENTER);
		hbs.setAlignment(Pos.CENTER);
		stg = new Stage();
		sc = new Scene(vbs, 400, 250);
		stg.setScene(sc);
		bts1.setOnAction(e->{
			district = district.getPrev();
			cityName = ((String)district.getKey());
			LocationScreen.setLocation(district.getLocHead());
			LocationScreen.setDistrict(district);
			LocationScreen ls = new LocationScreen();
			res10.setText("Loaded successfully");
			res10.setTextFill(Color.LIGHTGREEN);
			MyApp.getTp2().setContent(ls.getVb());
			lb1.setText(cityName);
			res2.setText(list.totalDistrictFemales(cityName)+list.totalDistrictMales(cityName)+"");
			res3.setText(list.totalDistrictMales(cityName)+"");
			res4.setText(list.totalDistrictFemales(cityName)+"");
			res5.setText(list.averageAge(cityName)+"");
			res6.setText(list.maxDate(cityName)+"");
			list.deleteCity(txt2.getText());
			MyApp.writeToFile();
			res8.setTextFill(Color.LIGHTGREEN);
			res8.setText("Deleted successfully");
			stg.close();
		});
		bts2.setOnAction(e->{
			res8.setTextFill(Color.RED);
			res8.setText("Not deleted");
			stg.close();
		});
		bt4.setOnAction(e->{
			if(txt2.getText().isEmpty()) {
				res8.setTextFill(Color.RED);
				res8.setText("Enter a name");
			}
			else if(list.findCity(txt2.getText())!=null) {
				stg.show();
			}else {
				res8.setTextFill(Color.RED);
				res8.setText("Isn't exist");
			}
		});
		
		lb9 = new Label("Enter district name to update: ");
		lb9.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb9.setTextFill(Color.WHITE);
		txt3 = new TextField(); 
		res9 = new Label();
		res9.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		bt5 = new Button("Update");
		bt5.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb5 = new HBox(5);
		hb6 = new HBox(5);
		hb5.getChildren().addAll(lb9, txt3);
		hb6.getChildren().addAll(bt5, res9);
		gp3 = new GridPane();
		gp3.add(hb5, 0, 0);
		gp3.add(hb6, 0, 1);
		gp3.setVgap(5);
		gp3.setPadding(new Insets(10, 10, 10, 10));
		gp3.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		  
		alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Update district name");
        alert.setHeaderText("Please enter the new name:");
		dres = new Label();
		dres.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        dtxt = new TextField();
        dtxt.setPromptText("Enter new name");

		dvb = new VBox(10);
		dvb.getChildren().addAll(dtxt, dres);
        alert.getDialogPane().setContent(dvb);

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        bt5.setOnAction(e->{
        	if(txt3.getText().isBlank()) {
        		res9.setText("Please fill the name");
        		res9.setTextFill(Color.RED);
        	}
        	else if(list.findCity(txt3.getText())==null) {
        		res9.setText("Not exist");
        		res9.setTextFill(Color.RED);
        	}
        	else {
        		res9.setText("");
        		alert.showAndWait().ifPresent(buttonType -> {
                    if (buttonType == ButtonType.OK) {
                    	alert.show();

                        if(dtxt.getText().isBlank()) {
                        	dres.setText("Please enter a name");
                        	dres.setTextFill(Color.RED);
                        } else if(list.findCity(dtxt.getText())!=null && !dtxt.getText().toLowerCase().equals(txt3.getText().toLowerCase())) {
                        	dres.setText("This name is exist");
                        	dres.setTextFill(Color.RED);
                        } else {
                        	DNode temp = list.findCity(txt3.getText());
                        	list.deleteCity(txt3.getText());
                        	temp.setKey(dtxt.getText());
                        	list.addCityNode(temp);
                        	district = list.getFirst();
                        	list.changeMartyrsDistrict(temp);
            				cityName = ((String)district.getKey());
            				MyApp.writeToFile();
            				lb1.setText(cityName);
            				res2.setText(list.totalDistrictFemales(cityName)+list.totalDistrictMales(cityName)+"");
            				res3.setText(list.totalDistrictMales(cityName)+"");
            				res4.setText(list.totalDistrictFemales(cityName)+"");
            				res5.setText(list.averageAge(cityName)+"");
            				res6.setText(list.maxDate(cityName)+"");
                        	dres.setText("Updated successfully");
                        	dres.setTextFill(Color.GREEN);
                        }
                    }
        		});
        	}
        });
		
		lb10 = new Label("Load the first location to the locations screen");
		lb10.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb10.setTextFill(Color.WHITE);
		bt6 = new Button("Load");
		bt6.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		res10 = new Label();
		res10.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		hb7 = new HBox(10);
		hb7.getChildren().addAll(bt6, res10);
		gp4 = new GridPane();
		gp4.add(lb10, 0, 0);
		gp4.add(hb7, 0, 1);
		gp4.setVgap(5);
		gp4.setPadding(new Insets(10, 10, 10, 10));
		gp4.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		bt6.setOnAction(e->{
			if(district.getLocHead()!=null) {
				LocationScreen.setLocation(district.getLocHead());
				LocationScreen.setDistrict(district);
				LocationScreen ls = new LocationScreen();
				res10.setText("Loaded successfully");
				res10.setTextFill(Color.LIGHTGREEN);
				MyApp.getTp2().setContent(ls.getVb());
			}else {
				res10.setText("There is no locations");
				res10.setTextFill(Color.RED);
			}
		});
		
		Locale.setDefault(Locale.ENGLISH);
		dp = new DatePicker();
		dp.getEditor().setDisable(true);
		dp.setValue(null);
		lb11 = new Label("Choose a date");
		lb11.setTextFill(Color.WHITE);
		lb11.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		res11 = new Label();
		res11.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		bt7 = new Button("Count");
		bt7.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb8 = new HBox(10);
		hb8.getChildren().addAll(lb11, dp);
		hb9 = new HBox(10);
		hb9.getChildren().addAll(bt7, res11);
		gp5 = new GridPane();
		gp5.add(hb8, 0, 0);
		gp5.add(hb9, 0, 1);
		gp5.setVgap(5);
		gp5.setPadding(new Insets(10, 10, 10, 10));
		gp5.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		bt7.setOnAction(e->{
			String date="";
			if(dp.getValue() != null && !dp.getValue().isAfter(LocalDate.now())) {
				String[] s = dp.getValue().toString().split("-");
				date = Integer.parseInt(s[1])+"/"+Integer.parseInt(s[2])+"/"+Integer.parseInt(s[0]);
				res11.setTextFill(Color.LIGHTGREEN);
				res11.setText(list.countByDate(cityName, date)+" martyrs");
			}else {
				res11.setTextFill(Color.RED);
				res11.setText("Invalid date");
			}
		});
		
		lb1 = new Label(cityName);
		lb1.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
		lb2 = new Label("Total martyrs: ");
		lb2.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res2 = new Label(list.totalDistrictFemales(cityName)+list.totalDistrictMales(cityName)+"");
		res2.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb3 = new Label("Total males: ");
		lb3.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res3 = new Label(list.totalDistrictMales(cityName)+"");
		res3.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb4 = new Label("Total females");
		lb4.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res4 = new Label(list.totalDistrictFemales(cityName)+"");
		res4.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb5 = new Label("Age's average");
		lb5.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res5 = new Label(list.averageAge(cityName)+"");
		res5.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb6 = new Label("Date with maximum martyrs number: ");
		lb6.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res6 = new Label();
		res6.setText(list.maxDate(cityName)+"");
		res6.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		
		bt1 = new Button("Previous");
		bt1.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		bt2 = new Button("Next");
		bt2.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		bt1.setOnAction(e->{
			clearAll();
			district = district.getPrev();
			cityName = ((String)district.getKey());
			lb1.setText(cityName);
			res2.setText(list.totalDistrictFemales(cityName)+list.totalDistrictMales(cityName)+"");
			res3.setText(list.totalDistrictMales(cityName)+"");
			res4.setText(list.totalDistrictFemales(cityName)+"");
			res5.setText(list.averageAge(cityName)+"");
			res6.setText("");
			if(district.getLocHead()!=null) res6.setText(list.maxDate(cityName)+"");
		});
		bt2.setOnAction(e->{
			clearAll();
			district = district.getNext();
			cityName = (String)(district.getKey());
			lb1.setText(cityName);
			res2.setText(list.totalDistrictFemales(cityName)+list.totalDistrictMales(cityName)+"");
			res3.setText(list.totalDistrictMales(cityName)+"");
			res4.setText(list.totalDistrictFemales(cityName)+"");
			res5.setText(list.averageAge(cityName)+"");
			res6.setText("");
			if(district.getLocHead()!=null) res6.setText(list.maxDate(cityName)+"");
		});
		hb = new HBox(10);
		hb.getChildren().addAll(bt1, bt2);
		hb.setAlignment(Pos.CENTER);
		
		gp = new GridPane();
		gp.add(lb2, 0, 0);
		gp.add(res2, 1, 0);
		gp.add(lb3, 0, 1);
		gp.add(res3, 1, 1);
		gp.add(lb4, 0, 2);
		gp.add(res4, 1, 2);
		gp.add(lb5, 0, 3);
		gp.add(res5, 1, 3);
		gp.add(lb6, 0, 4);
		gp.add(res6, 1, 4);
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setAlignment(Pos.CENTER);
		
		hbox = new HBox(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(gp1, gp2, gp3);
		
		hbox1 = new HBox(20);
		hbox1.setAlignment(Pos.CENTER);
		hbox1.getChildren().addAll(gp4, gp5);
		
		vb2 = new VBox(10);
		vb2.getChildren().addAll(gp, hb);
		vb = new VBox(10);
		vb.getChildren().addAll(hbox, lb1, vb2, hbox1);
		vb.setAlignment(Pos.CENTER);
		vb2.setStyle("-fx-border-color: black;");
		vb2.setPadding(new Insets(10, 10, 10, 10));
	}

	public VBox getVb() {
		return vb;
	}

	public void setVb(VBox vb) {
		this.vb = vb;
	}
	public void clearAll() {
		res7.setText("");
		res8.setText("");
		res9.setText("");
		res10.setText("");
		res11.setText("");
		txt1.setText("");
		txt2.setText("");
		txt3.setText("");
		dp.setValue(null);
	}
	
	
}
