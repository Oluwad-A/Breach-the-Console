import java.util.Scanner;

abstract class EncounterBase implements Encounter {


  StringImages img = new StringImages();
  Scanner test = new Scanner(System.in);
  int randomizer = 0;
  int subRandomizer; // For certain aspects of different encounters.
  String encounterType;
  int itemRandomizer;
  String gainedItem;
  boolean won; // Determines whether or not the encounter was won
  String guess = "";
  String statusGained; // Negative/Positive Status effects gained
  int newStam; // To be given to player after spire(Swarm) encounter


  public String getGainedItem() {
    return gainedItem;
  }

  public String getEncounterType() {
    return encounterType;
  }

  public void makeGuess(String g) {
    guess = g;
  }

  public boolean getWon() {
    return won;
  }

  public int getNewStamina() {
    return newStam;
  }

  public String getRandomStatus() {
    String[] statuses = new String[] { "Poisoned", "Disheartened", "Slowed" };

    return statuses[(int) (Math.random() * 3)];
  }

  public void runEncounter(int stam, String item, Player flee, String playerStatus, int difficulty,
                           int seerCount, int swarmCount, int cageCount) { }

}
