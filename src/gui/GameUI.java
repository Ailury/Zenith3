package gui;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import logic.Difficulty;
import logic.GameManager;
import logic.GameMode;
import logic.GameState;
import logic.HomeScreen;
import logic.KeyHandler;
import logic.SceneManager;
import entity.Background;
import entity.Powerup;

public class GameUI extends Pane {
	
	private Healthbar healthbar;
	private StackPane healthbarPane;
	private Text health;
	private Text floor;
	public Pane pausePane;
	public Pane blackPane;
	public EndgamePane endgamePane;
	private static GameUI instance = null;
	private boolean win;
	private boolean lose;
	private boolean continuee;
	
	public GameUI() {
		// TODO Auto-generated constructor stub
		pausePane = new Pane();
		endgamePane = new EndgamePane();
		health = new Text("");
		
		floor = new Text("Floor 1");
		floor.setFont(FontHolder.getInstance().getFont().get(40));
		floor.setLayoutX(600);floor.setLayoutY(40);
		
		healthbarPane = new StackPane();
		healthbarPane.setAlignment(Pos.CENTER);
		
		blackPane = new Pane();
		blackPane.setStyle("-fx-border-color: transparent;-fx-border-width: 0;-fx-background-color:black;");
		blackPane.setOpacity(0.80);
		blackPane.setVisible(false);
		blackPane.setPrefSize(1280, 720);
		
//		pausePane.setOpacity(0.5);
		pausePane.setVisible(false);
		pausePane.setLayoutX(445);pausePane.setLayoutY(120);
		pausePane.setMinSize(390,460);pausePane.setMaxSize(390, 460);
		pausePane.setStyle("-fx-border-width: 10;-fx-border-color:black;-fx-background-color:gray;-fx-border-radius: 20;-fx-background-radius: 25;");
		
		health.setFont(FontHolder.getInstance().getFont().get(30));
		
		pausePane.getChildren().addAll(new UIButton("Continue",50,50),new UIButton("Restart",50,150),new UIButton("Main Menu",50,250),new UIButton("Quit",50,350));
		healthbar = new Healthbar();
		healthbarPane.getChildren().addAll(healthbar,health);
		getChildren().add(floor);
		getChildren().add(blackPane);
		getChildren().add(pausePane);
		getChildren().add(endgamePane);
		getChildren().add(healthbarPane);
		getChildren().add(InventoryPane.getInstance());
		getChildren().add(PowerupPane.getInstance());
//		endgamePane.setGameText(true, true);
	}
	
	public static GameUI getInstance() {
		if(instance == null) instance = new GameUI();
		return instance;
	}
	
	public void update() {
		healthbar.update();
		health.setText(SceneManager.getInstance().getPlayer().getHp() + " / " + SceneManager.getInstance().getPlayer().getMaxHp());
		if(lose) {
			blackPane.setVisible(true);
			endgamePane.setGameText(false,continuee);
		}else if(win && !continuee) {
			blackPane.setVisible(true);
			endgamePane.setGameText(true,continuee);
		}
	}

	public GameUI(Node arg0) {
		super(arg0);
		// TODO Auto-generated constructor stub
	}

	public GameUI(Node arg0, Node arg1, Node arg2, Node arg3, Node arg4) {
		super(arg0, arg1, arg2, arg3, arg4);
		// TODO Auto-generated constructor stub
	}

	public void setPausePane(boolean visible) {
		blackPane.setVisible(visible);
		pausePane.setVisible(visible);
	}
	
	public void clear() {
		InventoryPane.getInstance().clear();
		PowerupPane.getInstance().clear();
		instance = null;
	}

	public void win() {
		if(continuee)return;
		win = true;
		SceneManager.getInstance().endgame(true);
	}
	
	public void lose() {
		lose = true;
		SceneManager.getInstance().endgame(true);
	}
	
	public void setFloor(int x) {
		floor.setText("Floor "+x);
	}
	
	public void setContinue() {
		continuee = true;
	}
}
