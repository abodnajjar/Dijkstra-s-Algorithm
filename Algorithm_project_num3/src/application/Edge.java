//Abdallah Najjar _1220864
package application;

public class Edge {

    private double dist;
    private Vertex startVertex;
    private Vertex targetVertex;
    private double time ;
    private int cost;
    public Edge( Vertex startVertex, Vertex targetVertex,double weight) {
        this.dist = weight;
        this.startVertex = startVertex;
        this.targetVertex = targetVertex;
    }
    

    public Edge( Vertex startVertex, Vertex targetVertex,double dist, double houres, int cost) {
		super();
		this.dist = dist;
		this.startVertex = startVertex;
		this.targetVertex = targetVertex;
		this.time = houres;
		this.cost = cost;
	}


	public double getWeight() {
        return dist;
    }

    public void setWeight(double weight) {
    }

    public Vertex getStartVertex() {
        return startVertex;
    }

    public void setStartVertex(Vertex startVertex) {
        this.startVertex = startVertex;
    }

    public Vertex getTargetVertex() {
        return targetVertex;
    }

    public void setTargetVertex(Vertex targetVertex) {
        this.targetVertex = targetVertex;
    }

 

	public double getTime() {
		return time;
	}


	public void setTime(double time) {
		this.time = time;
	}


	public int getCost() {
		return cost;
	}


	public void setCost(int cost) {
		this.cost = cost;
	}


	@Override
	public String toString() {
		return "Edge [dist=" + dist + ", startVertex=" + startVertex + ", targetVertex=" + targetVertex + ", time="
				+ time + ", cost=" + cost + "]";
	}



}