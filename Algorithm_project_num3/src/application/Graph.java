//Abdallah Najjar _1220864
package application;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class Graph {
	
	public static LinkedList<Vertex> cities=new LinkedList<>();
	
	static double org_xMin = -180.0;
	static double org_xMax =  180.0;
	static double org_yMin =  -90.0;
	static double org_yMax =  90.0; 
	static int yourWidth;
	static int yourHeight;

	public void dijkstra(Vertex sourceVertex, Vertex target) {
		for (int i = 0; i < cities.size(); i++) {
			cities.get(i).setDistance(Double.MAX_VALUE);
			cities.get(i).setKnown(false);
			cities.get(i).setPrev(null);
		}
//v(v+E)=(v2+E.v)= o(v2)
		sourceVertex.setDistance(0);
		while (true) { //v
	        // Find the vertex with the smallest distance that is not yet known
	        Vertex currVertex = null;
	        double smallestDistance = Double.MAX_VALUE;
	        for (int i=0;i<cities.size();i++) { //v
	        	 Vertex vertex=cities.get(i);
	            if (!vertex.isKnown() && vertex.getDistance() < smallestDistance) {
	                smallestDistance = vertex.getDistance();
	                currVertex = vertex;
	            }
	        }

	        // If no such vertex exists or the target is reached, break
	        if (currVertex == null || currVertex == target) {
	            break;
	        }

	        // Process neighbors of the current vertex
	        for (Edge edge : currVertex.getAdj()) { //E
	            Vertex neighbor = edge.getTargetVertex();

	            // Update the distance if the new path is shorter
	            if (!neighbor.isKnown()) {
	                double newDistance = currVertex.getDistance() + edge.getWeight();
	                if (newDistance < neighbor.getDistance()) {
	                    neighbor.setDistance(newDistance);
	                    neighbor.setCost(currVertex.getCost() + edge.getCost());
	                    neighbor.setTime(currVertex.getTime() + edge.getTime());
	                    neighbor.setPrev(currVertex);
	                }
	            }
	        }

	        // Mark the current vertex as known
	        currVertex.setKnown(true);
	    }
		
	}

	public void dijkstra_cost(Vertex sourceVertex, Vertex target) {
		for (int i = 0; i < cities.size(); i++) {// v
			cities.get(i).setCost(Integer.MAX_VALUE);
			cities.get(i).setKnown(false);
			cities.get(i).setPrev(null);
		}

		sourceVertex.setCost(0);
		
		// v2
		while (true) { // v
	        // Find the vertex with the smallest distance that is not yet known
	        Vertex currVertex = null;
	        int smallestCost = Integer.MAX_VALUE;

	        for (int i=0;i<cities.size();i++) { // v
	        	Vertex vertex=cities.get(i);
	            if (!vertex.isKnown() && vertex.getCost() < smallestCost) {
	                smallestCost = vertex.getCost();
	                currVertex = vertex;
	            }
	        }
	        // If no such vertex exists or the target is reached, break
	        if (currVertex == null || currVertex == target) {
	            break;
	        }

	        // Process neighbors of the current vertex
	        for (Edge edge : currVertex.getAdj()) {  //E
	            Vertex neighbor = edge.getTargetVertex();

	            // Update the distance if the new path is shorter
	            if (!neighbor.isKnown()) {
	                int newCost = currVertex.getCost() + edge.getCost();
	                if (newCost< neighbor.getCost()) {
	                    neighbor.setDistance(currVertex.getDistance() + edge.getWeight());
	                    neighbor.setCost(newCost);
	                    neighbor.setTime(currVertex.getTime() + edge.getTime());
	                    neighbor.setPrev(currVertex);
	                }
	            }
	        }
	        // Mark the current vertex as known
	        currVertex.setKnown(true);
	    }
	
	}
	
	public void dijkstra_time(Vertex sourceVertex, Vertex target) {
		for (int i = 0; i < cities.size(); i++) {
			cities.get(i).setTime(Double.MAX_VALUE);
			cities.get(i).setKnown(false);
			cities.get(i).setPrev(null);
		}
		sourceVertex.setTime(0);
		
		//v2
		while (true) { //v
	        // Find the vertex with the smallest distance that is not yet known
	        Vertex currVertex = null;
	        double smallestTime = Double.MAX_VALUE;

	        for (int i=0;i<cities.size();i++) {//v
	        	Vertex vertex=cities.get(i);
	            if (!vertex.isKnown() && vertex.getTime() < smallestTime) {
	                smallestTime = vertex.getTime();
	                currVertex = vertex;
	            }
	        }
	        // If no such vertex exists or the target is reached, break
	        if (currVertex == null || currVertex == target) {
	            break;
	        }

	        // Process neighbors of the current vertex
	        for (Edge edge : currVertex.getAdj()) {//E
	            Vertex neighbor = edge.getTargetVertex();
	            // Update the distance if the new path is shorter
	            if (!neighbor.isKnown()) {
	                double newTime = currVertex.getTime()+ edge.getTime();
	                if (newTime< neighbor.getTime()) {
	                    neighbor.setDistance(currVertex.getDistance() + edge.getWeight());
	                    neighbor.setCost(currVertex.getCost()+ edge.getCost());
	                    neighbor.setTime(newTime);
	                    neighbor.setPrev(currVertex);
	                }
	            }
	        }

	        // Mark the current vertex as known
	        currVertex.setKnown(true);
	    }
	
	}
	
	public static LinkedList<Vertex> readcities(int size1, int size2)  {
		Scanner scanner;
		try {
			File file =new File("C:/Users/abdal/OneDrive/Desktop/city.txt");
			scanner = new Scanner(file);
			 String line = scanner.nextLine();
		        String[] firstLineTokens = line.split(" ");
		        int numberOfCities = Integer.parseInt(firstLineTokens[0]);
		        int numberOfEdges = Integer.parseInt(firstLineTokens[1]);
		        for (int i = 0; i < numberOfCities; i++) {
		            line = scanner.nextLine();
		            String[] tokens = line.split(",");
		            String name = tokens[0]; 
		            double lat = Double.parseDouble(tokens[1]);
		            double lon = Double.parseDouble(tokens[2]);

		            // Mercator projection
		            double mercatorX = (((lon - org_xMin) / (org_xMax - org_xMin)) * yourWidth);
		            double mercatorY = ((((lat - org_yMin) / (org_yMax - org_yMin))) * yourHeight);

		            cities.insert(new Vertex(new city(name, mercatorX, mercatorY, lat, lon)));
		        }
		        // Read edge data
		        for (int i = 0; i < numberOfEdges; i++) {
		            line = scanner.nextLine();
		            String[] tokens = line.split("-");
		            String city1Name = tokens[0];
		            String city2Name = tokens[1];
		            int cost = Integer.parseInt(tokens[3].trim());
		            Double time = Double.parseDouble(tokens[2].trim());

		            Vertex v1 = Search(city1Name);
		            Vertex v2 = Search(city2Name);

		            Double distance = Double.parseDouble(String.format("%.3f", getDistance(v1, v2)));

		            Edge e = new Edge(v1, v2, distance,time , cost);
		            v1.addNeighbour(e);
		        }

		        scanner.close();
		        return cities;

		    } catch (FileNotFoundException e) {
		        System.out.println("File not found: " +"C:/Users/abdal/OneDrive/Desktop/city.txt");
		        e.printStackTrace();
		    }
		    return cities;
	}


	public static Vertex Search(String name) throws FileNotFoundException {
		Vertex v = null;
		for (int i = 0; i < cities.size(); i++) {
			if (name.compareTo(cities.get(i).getcity().getName()) == 0) {
				v = cities.get(i);
			}
		}
		return v;
	}

	public ArrayList<String> PrintPath(Vertex source, Vertex targetVertex) {
		ArrayList<String> path = new ArrayList<>();
		if (source.getcity().getName().equals(targetVertex.getcity().getName())) {
			path.add(source.getcity().getName() + " " + source.getDistance());
		} else if (targetVertex.getPrev() == null) {
			path.add("No Path");
		} else {
			for (Vertex vertex = targetVertex; vertex != null; vertex = vertex.getPrev()) {
				path.add(vertex.getcity().getName() + " (" + vertex.getDistance()+" (Km)"+" / "+vertex.getCost()+" ($)"+" / "+vertex.getTime()+" (Houres) )");
			}
			// عشان يصيرو بالعكس
			Collections.reverse(path);
		}
		return path;
	}

	public static Double getDistance(Vertex A, Vertex B) {
		//Latitude and Longitude:
		double lat1 = Math.toRadians(A.getcity().lat);
		double lat2 = Math.toRadians(B.getcity().lat);
		double lon1 = Math.toRadians(A.getcity().lon);
		double lon2 = Math.toRadians(B.getcity().lon);

		double dlon = lon2 - lon1;
		double dlat = lat2 - lat1;
		double a = Math.pow(Math.sin(dlat / 2), 2) + Math.cos(lat1) * Math.cos(lat2) * Math.pow(Math.sin(dlon / 2), 2);
		double c = 2 * Math.asin(Math.sqrt(a)); //the center angle between two points in radian
		double r = 6371;// the radius of earth
		return (c * r);
		
		
		
	}
}

