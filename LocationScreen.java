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

public class LocationScreen {
	private static LNode location;
	private static DNode district;
	private static String locationName;
	private LinkedList list = MyApp.getList();
	private Label lb0, lb1, res1, lb2, res2, lb3, res3, lb4, res4, lb5, res5, lb6, res6, lb7, res7, lb8, res8, lb9, res9, lb10, res10, loc,
	marName, marDate, marAge, marGender, marInsert, resInsert, lb12, res12, lb13, res13, lb14, res14, dres, slb1, sres1, slb2, sres2, slb3, sres3,
	slb4, sres4, slb5, sres5, slb6, sres6, slb7, slb8, slb9, slb10, sres7, slb0, lb15;
	private Button bt1, bt2, bt3, bt4, bt5, bt8, bt9, bt10, sbt, sbt1;
	private TextField txt1, txt2, txt3, txt4, tName, tAge, txt5, txt6, txt7, dtxt, stxt1, stxt2, txt8;
	private ComboBox<String> cmb, scmb;
	private GridPane gp, gp1, gp2, gp3, gp6, gp7, gp8, sgp, sgp1;
	private VBox vb, vb2, dvb, svb, svb1;
	private HBox hb, hb1, hb2, hb3, hb4, hb5, hb6, hb10, hb11, hb12, hb13, hb14, hb15, hbox, hbox1, hbox2, hbox3, shb, hbb;
	private DatePicker dp, sdp;
	private Alert alert;
	private Scene ss, ss1;
	private Stage stg, stg1;
	private MNode node;
	
	public LocationScreen() {
		lb0 = new Label((String)district.getKey());
		lb0.setStyle("-fx-font-weight: bold; -fx-font-size: 26;");
		lb7 = new Label("Enter Location name to insert: ");
		lb7.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb7.setTextFill(Color.WHITE);
		lb15 = new Label("Enter Distrct name to insert to: ");
		lb15.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb15.setTextFill(Color.WHITE);
		res7  = new Label();
		res7.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		txt1 = new TextField();
		txt8 = new TextField();
		bt3 = new Button("Insert");
		bt3.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb1 = new HBox(5);
		hb2 = new HBox(5);
		hbb = new HBox(5);
		hb1.getChildren().addAll(lb7, txt1);
		hbb.getChildren().addAll(lb15, txt8);
		hb2.getChildren().addAll(bt3, res7);
		gp1 = new GridPane();
		gp1.add(hb1, 0, 0);
		gp1.add(hbb, 0, 1);
		gp1.add(hb2, 0, 2);
		gp1.setVgap(5);
		gp1.setPadding(new Insets(10, 10, 10, 10));
		gp1.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		bt3.setOnAction(e->{
			if(txt1.getText().isBlank() || txt8.getText().isBlank()) {
				res7.setTextFill(Color.RED);
				res7.setText("Fill the blanks");
			} else if(list.findCity(txt8.getText())==null) {
				res7.setTextFill(Color.RED);
				res7.setText("The district is not exist");
			} else if(list.addRegion(new Location(txt1.getText()), txt8.getText())) {
				res7.setTextFill(Color.LIGHTGREEN);
				res7.setText("Added successfully");
			} else {
				res7.setTextFill(Color.RED);
				res7.setText("Exist before");
			}
		});
		
		loc = new Label(locationName);
		lb8 = new Label("Enter location name to delete: ");
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
		bt4.setOnAction(e->{
			if(txt2.getText().isBlank()) {
				res8.setTextFill(Color.RED);
				res8.setText("Enter a name");
			}
			else if(list.findLoc(txt2.getText(), ((String)district.getKey()))!=null) {
				LNode temp;
				if(locationName.toLowerCase().equals(txt2.getText().toLowerCase())){
					temp = location.getNext();
					list.deleteLocation(location, (String)district.getKey());
					location = temp;
					locationName = ((Location)temp.getKey()).getName();
					loc.setText(locationName);
				}
				else list.deleteLocation(list.findLoc(txt2.getText(), ((String)district.getKey())), (String)district.getKey());
				MyApp.writeToFile();
				res8.setTextFill(Color.LIGHTGREEN);
				res8.setText("Deleted successfully");
			}else {
				res8.setTextFill(Color.RED);
				res8.setText("Isn't exist");
			}
		});
		
		lb1 = new Label("Search about a location: ");
		lb1.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		res1 = new Label();
		res1.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		txt4 = new TextField();
		lb2 = new Label("Total martyrs: ");
		lb2.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res2 = new Label();
		res2.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb3 = new Label("Total males: ");
		lb3.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res3 = new Label();
		res3.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb4 = new Label("Total females: ");
		lb4.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res4 = new Label();
		res4.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb5 = new Label("Ages' average: ");
		lb5.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res5 = new Label();
		res5.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb6 = new Label("Youngest martyr: ");
		lb6.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res6 = new Label();
		res6.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		lb10 = new Label("Oldest martyr: ");
		lb10.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		res10 = new Label();
		res10.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		txt4.setOnAction(e->{
			LNode node = list.findLoc(txt4.getText(), (String)district.getKey());
			if(node!=null) {
				res2.setText(((Location)node.getKey()).getMales()+((Location)node.getKey()).getFemales()+"");
				res3.setText(((Location)node.getKey()).getMales()+"");
				res4.setText(((Location)node.getKey()).getFemales()+"");
				res5.setText(list.agesAverageLocation(node)+"");
				res6.setText(list.youngestOldestMartyr(node).split(",")[0]);
				res10.setText(list.youngestOldestMartyr(node).split(",")[1]);
				res1.setText("");
			}else {
				res1.setTextFill(Color.RED);
				res1.setText("The location isn't exist");
				clearAll1();
			}
		});
		gp = new GridPane();
		gp.add(lb1, 0, 0);
		gp.add(txt4, 1, 0);
		gp.add(res1, 2, 0);
		gp.add(lb2, 0, 1);
		gp.add(res2, 1, 1);
		gp.add(lb3, 0, 2);
		gp.add(res3, 1, 2);
		gp.add(lb4, 0, 3);
		gp.add(res4, 1, 3);
		gp.add(lb5, 0, 4);
		gp.add(res5, 1, 4);
		gp.add(lb6, 0, 5);
		gp.add(res6, 1, 5);
		gp.add(lb10, 0, 6);
		gp.add(res10, 1, 6);
		gp.setVgap(10);
		gp.setHgap(10);
		gp.setAlignment(Pos.CENTER);
		gp.setPadding(new Insets(10, 10, 10, 10));
		gp.setStyle("-fx-border-color: black;");
		
		lb9 = new Label("Enter Location name to update: ");
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
        alert.setTitle("Update location name");
        alert.setHeaderText("Please enter the new name:");
		dres = new Label();
		dres.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
        dtxt = new TextField();
        dtxt.setPromptText("Enter the name");

		dvb = new VBox(10);
		dvb.getChildren().addAll(dtxt, dres);
        alert.getDialogPane().setContent(dvb);

        alert.getButtonTypes().setAll(ButtonType.OK, ButtonType.CANCEL);
        bt5.setOnAction(e->{
        	if(txt3.getText().isBlank()) {
        		res9.setText("Please fill the name");
        		res9.setTextFill(Color.RED);
        	}
        	else if(list.findLoc(txt3.getText(), (String)district.getKey())==null) {
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
                        } else if(list.findLoc(dtxt.getText(), (String)district.getKey())!=null && !dtxt.getText().toLowerCase().
                        		equals(txt3.getText().toLowerCase())) {
                        	dres.setText("This name is exist");
                        	dres.setTextFill(Color.RED);
                        } else {
                        	dres.setText("Updated successfully");
                        	dres.setTextFill(Color.GREEN);
                        	LNode temp = list.findLoc(txt3.getText(), (String)district.getKey());
                        	list.deleteLocation(temp, (String)district.getKey());
                        	((Location)temp.getKey()).setName(dtxt.getText());
                        	list.addLocationNode(temp, district);
                        	list.changeMartyrsLocation(temp);
                        	location = district.getLocHead();
                        	locationName = ((Location)location.getKey()).getName();
                        	MyApp.writeToFile();
                        	loc.setText(locationName);
                        	res1.setText("");
                        	res2.setText("");
                        	res3.setText("");
                        	res4.setText("");
                        	res5.setText("");
                        	res6.setText("");
                        	res10.setText("");
                        }
                    }
        		});
        	}
        });
		
		
		loc.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");
		bt1 = new Button("Next");
		bt1.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		bt1.setOnAction(e->{
			location = location.getNext();
			locationName = ((Location)location.getKey()).getName();
			loc.setText(locationName);
			clearAll2();
		});
		
		marInsert = new Label("Insert a martyr for this location: ");
		marInsert.setStyle("-fx-font-weight: bold; -fx-font-size: 16;");
		resInsert = new Label();
		resInsert.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		marName = new Label("Name: ");
		marName.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		marName.setTextFill(Color.WHITE);
		tName = new TextField();
		marDate = new Label("Date: ");
		marDate.setTextFill(Color.WHITE);
		marDate.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		Locale.setDefault(Locale.ENGLISH);
		dp = new DatePicker();
		dp.getEditor().setDisable(true);
		dp.setValue(null);
		marAge = new Label("Age: ");
		marAge.setTextFill(Color.WHITE);
		marAge.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		tAge = new TextField();
		marGender = new Label("Gender: ");
		marGender.setTextFill(Color.WHITE);
		marGender.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		cmb = new ComboBox<>();
		cmb.getItems().addAll("Male", "Female");
		bt2 = new Button("Insert");
		bt2.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		bt2.setOnAction(e->{
			String date="";
			try {
				if(dp.getValue()!=null && !dp.getValue().isAfter(LocalDate.now())) {
					String[] s = dp.getValue().toString().split("-");
					date = Integer.parseInt(s[1])+"/"+Integer.parseInt(s[2])+"/"+Integer.parseInt(s[0]);
					if(Integer.parseInt(tAge.getText())<0 || Integer.parseInt(tAge.getText())>130) throw new NumberFormatException();
					if(tName.getText().isBlank() || tAge.getText().isBlank() || dp.getValue()==null || cmb.getValue()==null) {
						resInsert.setTextFill(Color.RED);
						resInsert.setText("Please fill the blanks");
					} else if(list.findMartyr(new Martyr(tName.getText(), date, Integer.parseInt(tAge.getText()), cmb.getValue(), 
							(String)district.getKey(), locationName), locationName, (String)district.getKey())!=null) {
						resInsert.setTextFill(Color.RED);
						resInsert.setText("Sorry, the martyr is added before");
					} else if(tName.getText().isBlank() || tAge.getText().isBlank() || cmb.getValue()==null){
						resInsert.setTextFill(Color.RED);
						resInsert.setText("Please fill the blanks");
					} else {
						list.addMartyr(new Martyr(tName.getText(), date, Integer.parseInt(tAge.getText()), cmb.getValue(), 
								(String)district.getKey(), locationName), ((Location)location.getKey()), (String)district.getKey());
						MyApp.writeToFile();
						resInsert.setTextFill(Color.GREEN);
						resInsert.setText("Added successfully");
					}
				}
				else {
					resInsert.setTextFill(Color.RED);
					resInsert.setText("Invalid date");
				}
			}catch(NumberFormatException ex) {
				resInsert.setTextFill(Color.RED);
				resInsert.setText("Invalid age, integer 0 to 130 required");
			}
		});
		hbox1 = new HBox(15);
		hbox1.setMaxWidth(950);
		hbox1.getChildren().addAll(marName, tName, marDate, dp, marAge, tAge, marGender, cmb, bt2);
		hbox1.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		hbox1.setPadding(new Insets(10,10,10,10));
		hbox2 = new HBox(15);
		hbox2.setAlignment(Pos.CENTER);
		hbox2.getChildren().addAll(marInsert, resInsert);
		vb2 = new VBox(10);
		vb2.getChildren().addAll(hbox2, hbox1);
		vb2.setAlignment(Pos.CENTER);
		
		hb = new HBox(10);
		hb.getChildren().addAll(bt1);
		hb.setAlignment(Pos.CENTER);
		
		lb12 = new Label("Enter name of martyr to delete: ");
		lb12.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb12.setTextFill(Color.WHITE);
		res12  = new Label();
		res12.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		txt5 = new TextField();
		bt8 = new Button("Delete");
		bt8.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb10 = new HBox(5);
		hb11 = new HBox(5);
		hb10.getChildren().addAll(lb12, txt5);
		hb11.getChildren().addAll(bt8, res12);
		gp6 = new GridPane();
		gp6.add(hb10, 0, 0);
		gp6.add(hb11, 0, 1);
		gp6.setVgap(5);
		gp6.setPadding(new Insets(10, 10, 10, 10));
		gp6.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		bt8.setOnAction(e->{
			if(txt5.getText().isBlank()) {
				res12.setTextFill(Color.RED);
				res12.setText("Fill the blank");
			}
			else {
				Martyr mar = new Martyr(txt5.getText(), "", 0, "", "", "");
				if(list.deleteMartyr(mar, locationName, (String)district.getKey())) {
					MyApp.writeToFile();
					res12.setTextFill(Color.GREEN);
					res12.setText("Deleted successfully");
				} else {
					res12.setTextFill(Color.RED);
					res12.setText("Not exist");
				}
			}
		});
		
		lb13 = new Label("Enter name of martyr to update: ");
		lb13.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb13.setTextFill(Color.WHITE);
		res13  = new Label();
		res13.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		txt6 = new TextField();
		bt9 = new Button("Update");
		bt9.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb12 = new HBox(5);
		hb13 = new HBox(5);
		hb12.getChildren().addAll(lb13, txt6);
		hb13.getChildren().addAll(bt9, res13);
		gp7 = new GridPane();
		gp7.add(hb12, 0, 0);
		gp7.add(hb13, 0, 1);
		gp7.setVgap(5);
		gp7.setPadding(new Insets(10, 10, 10, 10));
		gp7.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		
		slb0 = new Label();
		slb0.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb7 = new Label("Name: ");
		slb7.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb8 = new Label("Age: ");
		slb8.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb9 = new Label("Date: ");
		slb9.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb10 = new Label("Gender: ");
		slb10.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		stxt1 = new TextField();
		stxt2 = new TextField();
		sdp = new DatePicker();
		sdp.getEditor().setDisable(true);
		scmb = new ComboBox<>();
		scmb.getItems().addAll("Male", "Female");
		sres7 = new Label();
		sres7.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sgp1 = new GridPane();
		sgp1.setAlignment(Pos.CENTER);
		sgp1.setVgap(5);
		sgp1.setHgap(5);
		sgp1.add(slb7, 0, 0);
		sgp1.add(stxt1, 1, 0);
		sgp1.add(slb8, 0, 1);
		sgp1.add(stxt2, 1, 1);
		sgp1.add(slb9, 0, 2);
		sgp1.add(sdp, 1, 2);
		sgp1.add(slb10, 0, 3);
		sgp1.add(scmb, 1, 3);
		shb = new HBox(10);
		sbt1 = new Button("Update");
		shb.getChildren().addAll(sbt1, sres7);
		svb1 = new VBox(10);
		svb1.setAlignment(Pos.CENTER);
		shb.setAlignment(Pos.CENTER);
		svb1.getChildren().addAll(slb0, sgp1, shb);
		ss1 = new Scene(svb1, 800, 600);
		stg1 = new Stage();
		stg = new Stage();
		stg1.setScene(ss1);
		stg1.setTitle("Update martyr");
		bt9.setOnAction(e->{
			if(txt6.getText().isBlank()) {
				res13.setText("Fill the blank");
				res13.setTextFill(Color.RED);
			} else if(list.findMartyr(new Martyr(txt6.getText(), "", 0, "", "", ""), locationName, (String)district.getKey())==null) {
				res13.setText("Not exist");
				res13.setTextFill(Color.RED);
			} else {
				res13.setText("");
				slb0.setText(((Martyr)list.findMartyr(new Martyr(txt6.getText(), "", 0, "", "", ""), locationName, (String)district.getKey())
						.getKey()).toString());
				stg1.show();
				stg.close();
			}
		});
		sbt1.setOnAction(e->{
			try {
				String date="";
				if(sdp.getValue()!=null && !sdp.getValue().isAfter(LocalDate.now())) {
					String[] s = sdp.getValue().toString().split("-");
					date = Integer.parseInt(s[1])+"/"+Integer.parseInt(s[2])+"/"+Integer.parseInt(s[0]);
					if(Integer.parseInt(stxt2.getText())<0 || Integer.parseInt(stxt2.getText())>130) throw new NumberFormatException();
					if(stxt1.getText().isBlank() || stxt2.getText().isBlank() || sdp.getValue()==null || scmb.getValue()==null) {
						sres7.setTextFill(Color.RED);
						sres7.setText("Please fill the blanks");
					} else if(list.findMartyr(new Martyr(stxt1.getText(), date, Integer.parseInt(stxt2.getText()), scmb.getValue(), 
							(String)district.getKey(), locationName), locationName, (String)district.getKey())!=null && 
							!txt6.getText().equals(stxt1.getText())) {
						sres7.setTextFill(Color.RED);
						sres7.setText("Sorry, the martyr is added before");
					} else {
						list.deleteMartyr(new Martyr(txt6.getText(), "", 0, "", "", ""), locationName, (String)district.getKey());
						list.addMartyr(new Martyr(stxt1.getText(), date, Integer.parseInt(stxt2.getText()), scmb.getValue(), 
								(String)district.getKey(), locationName), ((Location)location.getKey()), (String)district.getKey());
						MyApp.writeToFile();
						sres7.setTextFill(Color.GREEN);
						sres7.setText("Updated successfully");
					}
				}
				else {
					sres7.setTextFill(Color.RED);
					sres7.setText("Invalid date");
				}
			}catch(NumberFormatException ex) {
				sres7.setTextFill(Color.RED);
				sres7.setText("Invalid age, integer 0 to 130 required");
			}
		});
		
		
		
		lb14 = new Label("Enter name of martyr to search: ");
		lb14.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		lb14.setTextFill(Color.WHITE);
		res14  = new Label();
		res14.setStyle("-fx-font-weight: bold; -fx-font-size: 12;");
		txt7 = new TextField();
		bt10 = new Button("Search");
		bt10.setStyle("-fx-font-weight: bold; -fx-font-size: 13;");
		hb14 = new HBox(5);
		hb15 = new HBox(5);
		hb14.getChildren().addAll(lb14, txt7);
		hb15.getChildren().addAll(bt10, res14);
		gp8 = new GridPane();
		gp8.add(hb14, 0, 0);
		gp8.add(hb15, 0, 1);
		gp8.setVgap(5);
		gp8.setPadding(new Insets(10, 10, 10, 10));
		gp8.setStyle("-fx-background-color: #2e2b2b; -fx-background-radius: 10px;");
		
		slb1 = new Label("Name: ");
		slb1.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sres1 = new Label();
		sres1.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb2 = new Label("Age: ");
		slb2.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sres2 = new Label();
		sres2.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb3 = new Label("Date: ");
		slb3.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sres3 = new Label();
		sres3.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb4 = new Label("District: ");
		slb4.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sres4 = new Label();
		sres4.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb5 = new Label("Location: ");
		slb5.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sres5 = new Label();
		sres5.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		slb6 = new Label("Gender: ");
		slb6.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sres6 = new Label();
		sres6.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		sgp = new GridPane();
		sgp.add(slb1, 0, 0);
		sgp.add(sres1, 1, 0);
		sgp.add(slb2, 0, 1);
		sgp.add(sres2, 1, 1);
		sgp.add(slb3, 0, 2);
		sgp.add(sres3, 1, 2);
		sgp.add(slb4, 0, 3);
		sgp.add(sres4, 1, 3);
		sgp.add(slb5, 0, 4);
		sgp.add(sres5, 1, 4);
		sgp.add(slb6, 0, 5);
		sgp.add(sres6, 1, 5);
		sgp.setAlignment(Pos.CENTER);
		sbt = new Button("Next");
		sbt.setStyle("-fx-font-weight: bold; -fx-font-size: 14;");
		svb = new VBox(10);
		svb.setAlignment(Pos.CENTER);
		svb.setPadding(new Insets(20, 20, 20 ,20));
		svb.getChildren().addAll(sgp, sbt);
		ss = new Scene(svb, 800, 600);
		stg.setScene(ss);
		
		bt10.setOnAction(e->{
			if(txt7.getText().isBlank()) {
				res14.setTextFill(Color.RED);
				res14.setText("Fill the blank");
			}
			else {
				node = list.searchPartName(txt7.getText(), (String)district.getKey());
				if(node==null) {
					res14.setTextFill(Color.RED);
					res14.setText("Not exist");
				} else {
					res14.setText("");
					stg.setTitle("Martyr names which contains "+txt7.getText());
					stg.show();
					sres1.setText(((Martyr)node.getKey()).getName());
					sres2.setText(((Martyr)node.getKey()).getAge()+"");
					sres3.setText(((Martyr)node.getKey()).getDate());
					sres4.setText(((Martyr)node.getKey()).getDistrict());
					sres5.setText(((Martyr)node.getKey()).getLocation());
					sres6.setText(((Martyr)node.getKey()).getGender());
				}
			}
		});
		sbt.setOnAction(e->{
			node = node.getNext();
			sres1.setText(((Martyr)node.getKey()).getName());
			sres2.setText(((Martyr)node.getKey()).getAge()+"");
			sres3.setText(((Martyr)node.getKey()).getDate());
			sres4.setText(((Martyr)node.getKey()).getDistrict());
			sres5.setText(((Martyr)node.getKey()).getLocation());
			sres6.setText(((Martyr)node.getKey()).getGender());
		});
		
		hbox3 = new HBox(10);
		hbox3.setAlignment(Pos.CENTER);
		hbox3.getChildren().addAll(gp6, gp7, gp8);
		
		hbox = new HBox(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(gp1, gp2, gp3);
		
		vb = new VBox(10);
		vb.getChildren().addAll(lb0, hbox, gp, loc, vb2, bt1, hbox3);
		vb.setAlignment(Pos.CENTER);
	}
	public static LNode getLocation() {
		return location;
	}

	public static void setLocation(LNode loc) {
		location = loc;
		locationName = ((Location)location.getKey()).getName();
	}
	public static DNode getDistrict() {
		return district;
	}
	public static void setDistrict(DNode district) {
		LocationScreen.district = district;
	}
	public VBox getVb() {
		return vb;
	}
	public void clearAll1() {
		res2.setText("");
		res3.setText("");
		res4.setText("");
		res5.setText("");
		res6.setText("");
		res10.setText("");
	}
	public void clearAll2() {
		resInsert.setText("");
		tName.setText("");
		tAge.setText("");
		dp.setValue(null);
		cmb.setValue(null);
		res14.setText("");
		res12.setText("");
		res13.setText("");
		txt7.setText("");
		txt6.setText("");
		txt5.setText("");
	}
}
