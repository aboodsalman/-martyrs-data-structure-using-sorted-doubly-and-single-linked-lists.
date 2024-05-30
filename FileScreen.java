package Phase1;

import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.FileChooser;

public class FileScreen {
	private FileChooser fc;
	private Label lbl, msg, lb2, lb3;
	private Button bt;
	private HBox hb;
	private GridPane gp;
	
	public FileScreen() {
		lb2 = new Label("FREE PALESTINE");
		lb2.setStyle("-fx-font-weight: bold; -fx-font-size: 30;");
		lb3 = new Label("This application is to manage your palestinian martyrs file and utalize many operations for them");
		lb3.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
		fc = new FileChooser(); 
		bt = new Button("Open File");
		bt.setStyle("-fx-font-size: 16; -fx-border-color: green; -fx-border-width: 2; -fx-font-weight: bold; -fx-font-color: green;");
		lbl = new Label("Choose a file");
		msg = new Label();
		msg.setStyle("-fx-font-weight: bold; -fx-font-size: 20;");
		lbl.setStyle("-fx-font-weight: bold; -fx-font-size: 18;");

		hb = new HBox(10);
		//hb.setAlignment(Pos.CENTER);
		hb.getChildren().addAll(lbl, bt, msg);
		
		gp = new GridPane();
		gp.add(lb2, 0, 0);
		gp.add(lb3, 0, 1);
		gp.add(hb, 0, 2);
		gp.setVgap(20);
		gp.setAlignment(Pos.CENTER);
		
	}
	
	public GridPane getGp() {
		return gp;
	}

	public Label getMsg() {
		return msg;
	}

	public void setMsg(Label msg) {
		this.msg = msg;
	}

	public Button getBt() {
		return bt;
	}

	public void setBt(Button bt) {
		this.bt = bt;
	}

	public FileChooser getFc() {
		return fc;
	}

	public void setFc(FileChooser fc) {
		this.fc = fc;
	}
	
	
}
