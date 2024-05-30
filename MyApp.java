package Phase1;

import java.io.*;
import java.util.*;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.*;

public class MyApp extends Application {
	private static LinkedList list = new LinkedList();
	private static File file;
	private TabPane tp;
	private static Tab tp0, tp1, tp2;

	@Override
	public void start(Stage stg) throws Exception {
				
		tp = new TabPane();
		tp.setStyle("-fx-tab-min-width: 100px;");
		tp0 = new Tab("File Screen");
		tp0.setStyle("-fx-background-color: #2e2b2b; -fx-text-base-color: white;");
		tp1 = new Tab("Districts Screen");
		tp1.setStyle("-fx-background-color: #a11823; -fx-text-base-color: white;");
		Label noDistrict = new Label("Please choose a file from file screen");
		VBox vb = new VBox();
		Image im = new Image(new FileInputStream("image.png"));
		ImageView imv = new ImageView(im);
		imv.setFitWidth(200);
		imv.setFitHeight(200);
		vb.getChildren().addAll(imv,noDistrict);
		vb.setAlignment(Pos.CENTER);
		noDistrict.setStyle("-fx-font-size: 26; -fx-font-weight: bold;");
		tp1.setContent(vb);
		tp2 = new Tab("Location Screen");
		tp2.setStyle("-fx-background-color: #29851e; -fx-text-base-color: white;");
		Label noLocation = new Label("Please choose a file from file screen and then load a location from districts screen");
		VBox vb1 = new VBox();
		ImageView imv1 = new ImageView(im);
		imv1.setFitWidth(200);
		imv1.setFitHeight(200);
		vb1.getChildren().addAll(imv1,noLocation);
		vb1.setAlignment(Pos.CENTER);
		noLocation.setStyle("-fx-font-size: 26; -fx-font-weight: bold;");
		tp2.setContent(vb1);
		
		
		FileScreen s1 = new FileScreen();
		s1.getBt().setOnAction(e -> {
			s1.getFc().getExtensionFilters().addAll(new FileChooser.ExtensionFilter("CSV File", "*.csv")); 
			
			file = s1.getFc().showOpenDialog(stg);
			try {
				if (readFromFile()) {
					stg.setMaximized(true);
					tp1.setDisable(false);
					DistricScreen ds = new DistricScreen();
					tp1.setContent(ds.getVb());
					s1.getMsg().setTextFill(Color.GREEN);
					s1.getMsg().setText("The file is loaded successfully");
				} else {
					s1.getMsg().setTextFill(Color.RED);
					s1.getMsg().setText("The files format doesn't follow the requirments");
				}
			} catch (NullPointerException ex) {
				s1.getMsg().setTextFill(Color.RED);
				s1.getMsg().setText("Please choose a file");
			}
		});
		tp0.setContent(s1.getGp());
		
		tp.getTabs().addAll(tp0, tp1, tp2);
		StackPane sp = new StackPane();
		ImageView background = new ImageView(new Image(new FileInputStream("gazaback.jpg")));
		sp.getChildren().add(background);
		sp.getChildren().add(tp);

		Scene s = new Scene(sp, 450, 300);
		background.fitWidthProperty().bind(stg.widthProperty());
		background.fitHeightProperty().bind(stg.heightProperty());
		background.setOpacity(0.4);
		stg.setScene(s);
		stg.setMaximized(true);
		Image icon = new Image(new FileInputStream("palestine.png"));
		stg.getIcons().add(icon);
		stg.setTitle("Free Palestine");
		stg.show();

	}

	public static void main(String[] args) {
		launch(args);

	}
	
	// to read data from the file
	public boolean readFromFile() {
		try {
			list = new LinkedList();
			Scanner scan = new Scanner(new File(file.toString()));
			if (scan.hasNext())
				scan.nextLine();
			while (scan.hasNext()) {
				String[] data = scan.nextLine().split(",");
				if(data.length==6 && !data[2].isEmpty()) {
					list.addCity(data[4]);
					list.addRegion(new Location(data[3]), data[4]);
					list.addMartyr(new Martyr(data[0], data[1], Integer.parseInt(data[2]), data[5], data[4], data[3]), new Location(data[3]), data[4]);
				}
			}
			scan.close();
			return true;
		} catch (IOException e) {
			System.out.println("Something wrong occured");
			return false;
		}
	}
	
	// to write the new data to the file
	public static void writeToFile() {
		try {
			PrintWriter pw = new PrintWriter(file);
			pw.println("Name,event,Age,location,District,Gender");
			DNode temp = list.getFirst();
			LNode loc;
			MNode mar;
			do {
				loc=temp.getLocHead();
				if(loc==null) continue;
				do {
					mar=loc.getMarHead();
					if(mar==null) continue;
					do {
						pw.println(((Martyr)mar.getKey()).getData());
						mar=mar.getNext();
					} while(mar!=loc.getMarHead());
					loc=loc.getNext();
				} while(loc!=temp.getLocHead());
				temp=temp.getNext();
			} while(temp!=list.getFirst());
			pw.close();
		} catch(IOException ex) {
			System.out.println("Something wrong occured");
		}
	}
	public static LinkedList getList() {
		return list;
	}

	public static void setList(LinkedList list) {
		MyApp.list = list;
	}

	public static Tab getTp2() {
		return tp2;
	}
	
}
