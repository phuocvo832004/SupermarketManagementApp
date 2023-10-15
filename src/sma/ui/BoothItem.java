package sma.ui;

public class BoothItem {
	  private int id;
	  private String description;

	  public BoothItem(int id, String description) {
	    this.id = id;
	    this.description = description;
	  }

	  public int getId() {
	    return id;
	  }

	  public String getDescription() {
	    return description;
	  }

	  @Override
	  public String toString() {
	    return description;
	  }

}
