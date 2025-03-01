public interface Encounter {


  public String getGainedItem();

  public String getEncounterType();

  public void makeGuess(String g);

  public boolean getWon();

  public int getNewStamina();

  public void runEncounter(int stam, String item, Player flee, String playerStatus, int difficulty,
                           int seerCount, int swarmCount, int cageCount);



}
