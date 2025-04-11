//Abdallah Najjar _1220864
package application;

import javafx.scene.control.*;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.GridPane;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.shape.Polygon;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class Driver extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		VBox vBox = new VBox(30);
		Image image = new Image(//
				new FileInputStream("C:/Users/abdal/OneDrive/Desktop/map.png"));
		double org_xMax = image.getWidth();
		ImageView imageView = new ImageView(image);
		int height = 750;
		int width = 1000;
		imageView.setFitHeight(height);
		imageView.setFitWidth(width);


		Label from = new Label("Source :");
		from.setFont(new Font("system", 14));
		from.setTextFill(Color.web("Black"));
		from.setStyle("-fx-font-weight: bold");
		from.setFont(Font.font("Baskerville Old Face", 22));
		from.setPrefWidth(100);
		from.setPrefHeight(30);

		ComboBox<String> comboBoxSource = new ComboBox<String>();

		comboBoxSource.setPrefHeight(30);
		comboBoxSource.setPrefWidth(200);
		comboBoxSource.setEditable(true);
		comboBoxSource.setPromptText("choose city .....");
		comboBoxSource.setStyle("-fx-background-color: black;");
		comboBoxSource.setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");
		
		Label filter = new Label("Filter :");
		 filter.setFont(new Font("system", 14));
		 filter.setTextFill(Color.web("Black"));
		 filter.setStyle("-fx-font-weight: bold");
		 filter.setFont(Font.font("Baskerville Old Face", 22));
		 filter.setPrefWidth(100);
		 filter.setPrefHeight(30);

		ComboBox<String> comboBoxfilter = new ComboBox<String>();
		comboBoxfilter.getItems().addAll("Distance", "Cost", "Time");
		comboBoxfilter.setValue("Distance");
		comboBoxfilter .setPrefHeight(30);
		comboBoxfilter .setPrefWidth(200);
		comboBoxfilter .setEditable(true);
		comboBoxfilter .setPromptText("Distance");
		comboBoxfilter .setStyle("-fx-background-color: black;");
		comboBoxfilter .setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");

		Label to = new Label("Target :");
		to.setFont(Font.font("Baskerville Old Face", 22));
		to.setTextFill(Color.web("Black"));
		to.setStyle("-fx-font-weight: bold");
		to.setPrefWidth(100);
		to.setPrefHeight(30);

		ComboBox<String> comboBoxTarget = new ComboBox<String>();

		comboBoxTarget.setPromptText("choose city .....");
		comboBoxTarget.setPrefHeight(30);
		comboBoxTarget.setPrefWidth(200);
		comboBoxTarget.setEditable(true);
		comboBoxTarget.setStyle("-fx-background-color: black;");
		comboBoxTarget.setStyle("-fx-border-color : black ; " + " -fx-border-width : 1;" + "-fx-text-fill: black");

		Label pathLabel = new Label("Path");
		pathLabel.setFont(Font.font("Baskerville Old Face", 22));
		pathLabel.setPrefWidth(100);
		pathLabel.setPrefHeight(30);

		pathLabel.setTextFill(Color.web("Black"));
		pathLabel.setStyle("-fx-font-weight: bold");

		TextArea path = new TextArea();
		path.setPrefHeight(200);
		path.setPrefWidth(300);
		path.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");

		Label DistanceLabel = new Label("Distance :");

		DistanceLabel.setFont(Font.font("Baskerville Old Face", 22));
		DistanceLabel.setTextFill(Color.web("Black"));
		DistanceLabel.setStyle("-fx-font-weight: bold");
		DistanceLabel.setPrefWidth(100);
		DistanceLabel.setPrefHeight(30);

		TextField distance = new TextField();
		distance.setPrefHeight(30);
		distance.setPrefWidth(200);
		distance.setAlignment(Pos.CENTER);
		distance.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");
		
		Label CostLabel = new Label("Cost :");

		CostLabel.setFont(Font.font("Baskerville Old Face", 22));
		CostLabel.setTextFill(Color.web("Black"));
		CostLabel.setStyle("-fx-font-weight: bold");
		CostLabel.setPrefWidth(100);
		CostLabel.setPrefHeight(30);

		TextField cost = new TextField();
		cost.setPrefHeight(30);
		cost.setPrefWidth(200);
		cost.setAlignment(Pos.CENTER);
		cost.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");

		Label timeLabel = new Label("Time :");

		timeLabel.setFont(Font.font("Baskerville Old Face", 22));
		timeLabel.setTextFill(Color.web("Black"));
		timeLabel.setStyle("-fx-font-weight: bold");
		timeLabel.setPrefWidth(100);
		timeLabel.setPrefHeight(30);

		TextField time = new TextField();
		time.setPrefHeight(30);
		time.setPrefWidth(200);
		time.setAlignment(Pos.CENTER);
		time.setStyle("-fx-border-color : black ; " + " -fx-border-width : 2;" + "-fx-text-fill: black");


		Button runButton = new Button("Run");
		runButton.setPrefHeight(20);
		runButton.setPrefWidth(100);
		runButton.setMnemonicParsing(false);

		runButton.setStyle(
				"-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 10 5 10;");

		Button reset = new Button("Reset");
		reset.setPrefHeight(20);
		reset.setPrefWidth(100);
		reset.setStyle(
				"-fx-background-color: black; -fx-text-fill: white; -fx-font-size: 14px; -fx-padding: 5 10 5 10;");

		Graph.yourWidth = 1000;
		Graph.yourHeight = 750;
		LinkedList<Vertex> cities = Graph.readcities(height, width);
		 GridPane gridPane = new GridPane();
		 gridPane.setPrefWidth(560);
	        gridPane.setPrefHeight(300);
	        gridPane.setHgap(10);
	        gridPane.setVgap(25);
	        gridPane.setAlignment(Pos.CENTER);
	        gridPane.setStyle("-fx-background-color: lightgray;");
	        // Add components to the GridPane ( column,row,)
	        gridPane.add(from, 1, 0); // From
	        gridPane.add(comboBoxSource, 2, 0); // To
	        gridPane.add(to, 1, 1); // Filter
	        gridPane.add(comboBoxTarget, 2,1 ); // Path
	        gridPane.add(filter, 1, 2); // Distance
	        gridPane.add(comboBoxfilter, 2, 2); // Cost
	        gridPane.add(pathLabel, 1, 3); // Time
	        gridPane.add(path, 2, 3);  // Run 
	        gridPane.add(CostLabel, 1, 5); // Time
	        gridPane.add(cost, 2, 5);  // Run 
	        gridPane.add(timeLabel, 1, 6); // Time
	        gridPane.add(time, 2, 6);  // Run 
	        gridPane.add(DistanceLabel, 1, 4); // Time
	        gridPane.add(distance, 2, 4);  // Run 
	        HBox hBox10 = new HBox(20);
			hBox10.getChildren().addAll(runButton, reset);
			hBox10.setAlignment(Pos.CENTER);
	        gridPane.add(hBox10, 2, 7); // Time
	  
           
		//pane.getChildren().addAll(vBox);
		HBox hb=new HBox(imageView, gridPane);
		Group g = new Group();
		g.getChildren().add(hb);

		for (int i = 0; i < cities.size(); i++) {
			comboBoxSource.getItems().add(cities.get(i).getcity().getName());
			comboBoxTarget.getItems().add(cities.get(i).getcity().getName());
		}

		ArrayList<RadioButton> array = new ArrayList<>();
		for (int i = 0; i < cities.size(); i++) {
			RadioButton radioButton = new RadioButton(cities.get(i).getcity().getName());
			radioButton.setOnMouseClicked(e -> {
				for (int j = 0; j < array.size(); j++) {
					array.get(j).setSelected(false);
				}
				if (comboBoxSource.getValue() == null || comboBoxSource.getValue().equals("")) {
					radioButton.setSelected(true);
					comboBoxSource.setValue(radioButton.getId());
				} else if (comboBoxTarget.getValue() == null || comboBoxTarget.getValue().equals("")) {
					radioButton.setSelected(true);
					comboBoxTarget.setValue(radioButton.getId());
				}
			});
			radioButton.setId(cities.get(i).getcity().getName());
			radioButton.setLayoutX(cities.get(i).getcity().getMercatorX()-30);
			radioButton.setLayoutY(730-(cities.get(i).getcity().getMercatorY()));
			radioButton.setTextFill(Color.BLACK);
			radioButton.setStyle("-fx-font-size:7px;" + "-fx-font-weight: bold");
			array.add(radioButton);
			g.getChildren().addAll(radioButton);

		}

		Scene scene = new Scene(g,1500, 750);
		primaryStage.setScene(scene);
		primaryStage.setTitle(" Dijkstra's algorithm ");
		primaryStage.show();

		reset.setOnAction(e -> {
			deleteLines(g);
			comboBoxSource.setValue("");
			comboBoxTarget.setValue("");
			comboBoxfilter.setValue("");
		    cost.clear();
		    time.clear();
			path.clear();
			distance.clear();
			comboBoxfilter.setValue("Distance");
		});

		runButton.setOnAction(e -> {
			Graph graph = new Graph();
			deleteLines(g);
			try {
				
				 if (comboBoxSource.getValue().isEmpty() ||comboBoxTarget.getValue().isEmpty()||
						 (comboBoxSource.getValue().isEmpty() && comboBoxTarget.getValue().isEmpty())) {
						 Alert alert = new Alert(AlertType.WARNING);
					        alert.setTitle("No selected ");
					        alert.setHeaderText("No selected");
					        alert.setContentText("Select a citye frome comoBox");
					        alert.showAndWait();
					}
				else if(comboBoxSource.getValue()==null ||comboBoxTarget.getValue()==null
						 ||(comboBoxSource.getValue()==null&& comboBoxTarget.getValue()==null)) {
					 Alert alert = new Alert(AlertType.WARNING);
				        alert.setTitle("No selected ");
				        alert.setHeaderText("No selected");
				        alert.setContentText("Select a citye frome comoBox");
				        alert.showAndWait();
				}
				
				
				else {
					Vertex v = Graph.Search(comboBoxSource.getValue().trim());
					Vertex v4 = Graph.Search(comboBoxTarget.getValue().trim());
					
                    if(comboBoxfilter.getValue().equalsIgnoreCase("Distance")) {
					graph.dijkstra(v, v4);

					ArrayList<String> ar = graph.PrintPath(v, v4);
			
					String s = "";
					for (int i = 0; i < ar.size(); i++) {
						s += "->" + ar.get(i) + " \n";
					}
					
					if (v4.getDistance() == Double.MAX_VALUE) {
						distance.setText(" No Distance");
					    cost.setText("No cost");
					    time.setText("No time");
					    path.setText(s);
					}
					else if(v.getcity().getName().equalsIgnoreCase(v4.getcity().getName())) {
						distance.setText(String.valueOf(0 + "  (Km)"));
						cost.setText(String.valueOf(0+ " ($)"));
						time.setText(String.valueOf(0+ " (houres)"));
						 path.setText(s);
					}
					else {
					distance.setText(String.valueOf(v4.getDistance() + "  (Km)"));
					cost.setText(String.valueOf(v4.getCost()+ " ($)"));
					time.setText(String.valueOf(v4.getTime()+ " (houres)"));
					 path.setText(s);
					recScenes(v4, g, height);}
				}
                   
                    else if(comboBoxfilter.getValue().equalsIgnoreCase("Time")) {
                    	graph.dijkstra_time(v, v4);

    					ArrayList<String> ar = graph.PrintPath(v, v4);
    					String s = "";
    					for (int i = 0; i < ar.size(); i++) {
    						s += "->" + ar.get(i) + " \n";
    					}
    					if (v4.getTime() == Double.MAX_VALUE) {
    						distance.setText(" No Distance");
    					    cost.setText("No cost");
    					    time.setText("No time");
    					    path.setText(s);
    					}
    					else if(v.getcity().getName().equalsIgnoreCase(v4.getcity().getName())) {
    						distance.setText(String.valueOf(0 + "  (Km)"));
    						cost.setText(String.valueOf(0+ " ($)"));
    						time.setText(String.valueOf(0+ " (houres)"));
    						path.setText(s);
    					}
    					else {
    						
    					distance.setText(String.valueOf(v4.getDistance() + "  (Km)"));
    					cost.setText(String.valueOf(v4.getCost()+ " ($)"));
    					time.setText(String.valueOf(v4.getTime()+ " (houres)"));
    					path.setText(s);

    					recScenes(v4, g, height);
    					}
                    }
                    else {
                    	graph.dijkstra_cost(v, v4);
    					ArrayList<String> ar = graph.PrintPath(v, v4);
    					String s = "";
    					for (int i = 0; i < ar.size(); i++) {
    						s += "->" + ar.get(i) + " \n";
    					}
    					if (v4.getCost() == Integer.MAX_VALUE) {
    						distance.setText(" No Distance");
    					    cost.setText("No cost");
    					    time.setText("No time");
    					    path.setText(s);
    					}
    					else if(v.getcity().getName().equalsIgnoreCase(v4.getcity().getName())) {
    						distance.setText(String.valueOf(0 + "  (Km)"));
    						cost.setText(String.valueOf(0+ " ($)"));
    						time.setText(String.valueOf(0+ " (houres)"));
    						path.setText(s);
    					}
    					else {
    					distance.setText(String.valueOf(v4.getDistance() + "  (Km)"));
    					cost.setText(String.valueOf(v4.getCost()+ " ($)"));
    					time.setText(String.valueOf(v4.getTime()+ " (houres)"));
    					path.setText(s);

    					recScenes(v4, g, height);
    					}
                    }
				}

			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			}

		});
	}
	
	static Group lineGroup = new Group();
	public static Group recScenes(Vertex target, Group myPane, int height) {
		
		lineGroup.getChildren().clear();
		myPane.getChildren().remove(lineGroup);

		myPane.getChildren().add(lineGroup);

		while (target != null) {
			Vertex prev = target.getPrev();
			if (prev == null)
				break;

			Line line = new Line(target.getcity().getX()-25 , height-15 - target.getcity().getY(),
			prev.getcity().getX()-25 , height-15 - prev.getcity().getY() );
			line.setStyle("-fx-stroke: #ffea00;" + "-fx-stroke-width: 2px;");
			addArrow(lineGroup, line);
			lineGroup.getChildren().add(line);
			line.getStyleClass().add("line");

			target = prev;
		}
		return myPane;
	}

	private static void addArrow(Group g, Line line) {
		double arrowSize = 10.0;

		double angle = Math.atan2(line.getEndY() - line.getStartY(), line.getEndX() - line.getStartX());

		double x1 = line.getStartX() + arrowSize * Math.cos(angle + Math.toRadians(30));
		double y1 = line.getStartY() + arrowSize * Math.sin(angle + Math.toRadians(30));

		double x2 = line.getStartX() + arrowSize * Math.cos(angle - Math.toRadians(30));
		double y2 = line.getStartY() + arrowSize * Math.sin(angle - Math.toRadians(30));

		Polygon arrowhead = new Polygon(line.getStartX(), line.getStartY(), x1, y1, x2, y2);
		arrowhead.setFill(Color.web("gray"));
		g.getChildren().add(arrowhead);
	}

	public void deleteLines(Group g) {
		lineGroup.getChildren().clear();
	}

}