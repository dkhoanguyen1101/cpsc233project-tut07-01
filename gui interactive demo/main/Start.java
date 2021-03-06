package main;




import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.image.*;
import javafx.stage.Popup;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import javax.print.attribute.standard.PrinterName;

import characters.BiomedMajor;
import characters.Chara;
import characters.ChemistryMajor;
import characters.EngMajor;
import characters.KinesiologyMajor;
import characters.PhilosophyMajor;
import characters.ZoologyMajor;

import java.awt.*;
import java.util.ArrayList;

import java.io.File;
//method for start the game
public class Start extends Application {
    public static void main(String[] args){launch(args);}




//method Override and setting all the scene
    public void start(Stage stage) throws Exception {
        
        ArrayList<Chara> selectedPlayers = new ArrayList<Chara>();

        Pane root = new Pane();
        Scene scene = new Scene(root,800,600);//set the scene size
        stage.setScene(scene);
        stage.show();

        //Chara Scene
        Pane CharaRoot = new Pane();
        Scene CharaScene = new Scene(CharaRoot,800,600);//set Chara scene size



        //set up start scene
        BackgroundImage bg = new BackgroundImage(new Image("start.png"), BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        root.setBackground(new Background(bg));

        Image duck = new Image("duck.png");//inset the duck image
        ImageView dk = new ImageView(duck);
        dk.setLayoutX(53);
        dk.setLayoutY(55);
        root.getChildren().add(dk);
	
	//set up the button "About"
        Button button1 = new Button("About");//inset the About image
        button1.setMinSize(120,60);
        button1.setLayoutX(340);
        button1.setLayoutY(230);
        button1.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        button1.setStyle("-fx-background-color: #A9A9A9");
        root.getChildren().add(button1);
        //load the team member name image in About button
        Label label = new Label();
        Popup popup = new Popup();
        label.setLayoutX(100);
        label.setLayoutY(200);
        popup.getContent().add(label);
        label.setGraphic(new ImageView(new Image("name.png")));//inset the team member name image
	//set up click event handler
        EventHandler<ActionEvent> event =
                new EventHandler<ActionEvent>() {
                //method for click back button and go back main menu
                    public void handle(ActionEvent e)
                    {

                        if (!popup.isShowing()){
                            popup.show(stage);
                            button1.setText("BACK");}
                        else{
                            popup.hide();
                            button1.setText("About");}
                    }
                };
                
        

        //what happen when button is pressed
        button1.setOnAction(event);
        //set up the button load
        Button LOAD = new Button("load");
        LOAD.setMinSize(120,60);
        LOAD.setLayoutX(340);
        LOAD.setLayoutY(330);
        LOAD.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        LOAD.setStyle("-fx-background-color: #A9A9A9");
        LOAD.setOnAction(new EventHandler<ActionEvent>() {
                //method for handle different action event     
		public void handle(ActionEvent e) {
                //check the file save or not
                File tmpDir = new File("output.bin");

                boolean exists = tmpDir.exists();
            	if(exists)
                {
                    Game game = new Game(stage, null, true);

                    game.setButtonTextPlayerActionChoice();
                    game.btn1.setOnAction(null);
                    game.btn2.setOnAction(null);
                    game.btn3.setOnAction(null);
                    game.btn4.setOnAction(new EndTurnHandler(game));
                    game.btn5.setOnAction(new SaveHandler(game));
                    game.scene.setOnMouseClicked(new PlayerActionSelection(game));

                }else{
                    System.out.println("No save file found.");
                }
            }
        });
        
        //The PLAY button, if pressed the character selection scene will comes out
        Button PLAY = new Button("play");
        PLAY.setMinSize(120,60);
        PLAY.setLayoutX(340);
        PLAY.setLayoutY(430);
        PLAY.setStyle("-fx-border-color: #000000; -fx-border-width: 5px;");
        PLAY.setStyle("-fx-background-color: #A9A9A9");
        PLAY.setOnAction(new EventHandler<ActionEvent>() {
            @Override public void handle(ActionEvent e) {
            	
                stage.setScene(CharaScene);
                stage.show();
            	 
            	
            }
        });
        root.getChildren().add(PLAY);
        root.getChildren().add(LOAD);
        //Character selection scene
        Button finish = new Button();
        finish.setMinSize(80, 40);
        finish.setLayoutX(300);
        finish.setLayoutY(400);
        finish.setText("Finish selection");
        finish.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		Game game = new Game(stage, selectedPlayers, false);
            	game.setButtonTextPlayerActionChoice();
        		game.btn1.setOnAction(null);
        		game.btn2.setOnAction(null);
        		game.btn3.setOnAction(null);
        		game.btn4.setOnAction(new EndTurnHandler(game));
        		game.btn5.setOnAction(new SaveHandler(game));
        		game.scene.setOnMouseClicked(new PlayerActionSelection(game));
        	}
		});
         //set different image view
        ImageView sel1 = new ImageView();
        sel1.setLayoutX(250);
        sel1.setLayoutY(150);
        CharaRoot.getChildren().add(sel1);
        
        ImageView sel2 = new ImageView();
        sel2.setLayoutX(350);
        sel2.setLayoutY(150);
        CharaRoot.getChildren().add(sel2);
        
        ImageView sel3 = new ImageView();
        sel3.setLayoutX(450);
        sel3.setLayoutY(150);
        CharaRoot.getChildren().add(sel3);
        
        Button reselect = new Button();
        reselect.setMinSize(80, 40);
        reselect.setLayoutX(300);
        reselect.setLayoutY(300);
        reselect.setText("Clear selection");
        reselect.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		selectedPlayers.clear();
        		sel1.setImage(null);
        		sel2.setImage(null);
        		sel3.setImage(null);
        		if(CharaRoot.getChildren().contains(finish)) {
        			CharaRoot.getChildren().remove(finish);
        		}
        	}
		});
        CharaRoot.getChildren().add(reselect);
        
        //button for Medical and set action event
        Button BioMed = new Button("Medical Student");
        BioMed.setMinSize(80,40);
        BioMed.setLayoutX(100);
        BioMed.setLayoutY(100);
        BioMed.setGraphic(new ImageView(new Image("BioMed.png")));
        BioMed.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		int selectedNo = selectedPlayers.size();
        		ImageView toSwitch = new ImageView();
        		Boolean canAdd = false;
        		switch(selectedNo) {
        		case 0:
        			toSwitch = sel1;
        			canAdd = true;
        			break;
        		case 1:
        			toSwitch = sel2;
        			canAdd = true;
        			break;
        		case 2:
        			toSwitch = sel3;
        			canAdd = true;
        			CharaRoot.getChildren().add(finish);
        			break;
        		}
        		if(canAdd) {
        			Chara toAdd = new BiomedMajor(selectedNo + 1);
        			selectedPlayers.add(toAdd);
        			toSwitch.setImage(new Image(toAdd.getImageUrl()));
        		}
        	}
		});
        CharaRoot.getChildren().add(BioMed);

        //button for Zoology and set action event
        Button Zoology = new Button("Zoology Major");
        Zoology.setMinSize(80,40);
        Zoology.setLayoutX(100);
        Zoology.setLayoutY(50);
        Zoology.setGraphic(new ImageView(new Image("Zoology.png")));
        Zoology.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		int selectedNo = selectedPlayers.size();
        		ImageView toSwitch = new ImageView();
        		Boolean canAdd = false;
        		switch(selectedNo) {
        		case 0:
        			toSwitch = sel1;
        			canAdd = true;
        			break;
        		case 1:
        			toSwitch = sel2;
        			canAdd = true;
        			break;
        		case 2:
        			toSwitch = sel3;
        			canAdd = true;
        			CharaRoot.getChildren().add(finish);
        			break;
        		}
        		if(canAdd) {
        			Chara toAdd = new ZoologyMajor(selectedNo + 1);
        			selectedPlayers.add(toAdd);
        			toSwitch.setImage(new Image(toAdd.getImageUrl()));
        		}
        	}
		});
        CharaRoot.getChildren().add(Zoology);

        //button for Kinesiology and set action event
        Button Kinesiology = new Button("Kinesiology Major");
        Kinesiology.setMinSize(80,40);
        Kinesiology.setLayoutX(300);
        Kinesiology.setLayoutY(100);
        Kinesiology.setGraphic(new ImageView(new Image("Kinesiology.png")));
        Kinesiology.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		int selectedNo = selectedPlayers.size();
        		ImageView toSwitch = new ImageView();
        		Boolean canAdd = false;
        		switch(selectedNo) {
        		case 0:
        			toSwitch = sel1;
        			canAdd = true;
        			break;
        		case 1:
        			toSwitch = sel2;
        			canAdd = true;
        			break;
        		case 2:
        			toSwitch = sel3;
        			canAdd = true;
        			CharaRoot.getChildren().add(finish);
        			break;
        		}
        		if(canAdd) {
        			Chara toAdd = new KinesiologyMajor(selectedNo + 1);
        			selectedPlayers.add(toAdd);
        			toSwitch.setImage(new Image(toAdd.getImageUrl()));
        		}
        	}
		});
        CharaRoot.getChildren().add(Kinesiology);



        //button for Engineering and set action event
        Button Engineering = new Button("Engineering Major");
        Engineering.setMinSize(80,40);
        Engineering.setLayoutX(300);
        Engineering.setLayoutY(50);
        Engineering.setGraphic(new ImageView(new Image("Engineering.png")));
        Engineering.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		int selectedNo = selectedPlayers.size();
        		ImageView toSwitch = new ImageView();
        		Boolean canAdd = false;
        		switch(selectedNo) {
        		case 0:
        			toSwitch = sel1;
        			canAdd = true;
        			break;
        		case 1:
        			toSwitch = sel2;
        			canAdd = true;
        			break;
        		case 2:
        			toSwitch = sel3;
        			canAdd = true;
        			CharaRoot.getChildren().add(finish);
        			break;
        		}
        		if(canAdd) {
        			Chara toAdd = new EngMajor(selectedNo + 1);
        			selectedPlayers.add(toAdd);
        			toSwitch.setImage(new Image(toAdd.getImageUrl()));
        		}
        	}
		});
        CharaRoot.getChildren().add(Engineering);

        //button for Philosophy and set action event
        Button Philosophy = new Button("Philosophy Major");
        Philosophy.setMinSize(80,40);
        Philosophy.setLayoutX(500);
        Philosophy.setLayoutY(100);
        Philosophy.setGraphic(new ImageView(new Image("Philosophy.png")));
        Philosophy.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		int selectedNo = selectedPlayers.size();
        		ImageView toSwitch = new ImageView();
        		Boolean canAdd = false;
        		switch(selectedNo) {
        		case 0:
        			toSwitch = sel1;
        			canAdd = true;
        			break;
        		case 1:
        			toSwitch = sel2;
        			canAdd = true;
        			break;
        		case 2:
        			toSwitch = sel3;
        			canAdd = true;
        			CharaRoot.getChildren().add(finish);
        			break;
        		}
        		if(canAdd) {
        			Chara toAdd = new PhilosophyMajor(selectedNo + 1);
        			selectedPlayers.add(toAdd);
        			toSwitch.setImage(new Image(toAdd.getImageUrl()));
        		}
        	}
		});
        CharaRoot.getChildren().add(Philosophy);

        //button for Chemistry and set action event
        Button Chemistry = new Button("Chemistry Major");
        Chemistry.setMinSize(80,40);
        Chemistry.setLayoutX(500);
        Chemistry.setLayoutY(50);
        Chemistry.setGraphic(new ImageView(new Image("BioChem.png")));
        Chemistry.setOnAction(new EventHandler<ActionEvent>() {
        	@Override
        	public void handle(ActionEvent e) {
        		int selectedNo = selectedPlayers.size();
        		ImageView toSwitch = new ImageView();
        		Boolean canAdd = false;
        		switch(selectedNo) {
        		case 0:
        			toSwitch = sel1;
        			canAdd = true;
        			break;
        		case 1:
        			toSwitch = sel2;
        			canAdd = true;
        			break;
        		case 2:
        			toSwitch = sel3;
        			canAdd = true;
        			CharaRoot.getChildren().add(finish);
        			break;
        		}
        		if(canAdd) {
        			Chara toAdd = new ChemistryMajor(selectedNo + 1);
        			selectedPlayers.add(toAdd);
        			toSwitch.setImage(new Image(toAdd.getImageUrl()));
        		}
        	}
		});
        CharaRoot.getChildren().add(Chemistry);
        
    }
}
