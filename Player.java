import java.util.Scanner;
import java.util.ArrayList;
// This class contains player information that allows for other aspects of the game to take place.

public class Player {

  int stamina = 5; // Probability of action success increases with stamina
  int survivalScore; // Overall survival score. Increases chances of discovering items
  int survivalScoreBonus = 2;
  String status = ""; // To be decided<------------------
  boolean actionSu; // Whether or not an action succeeded or failed
  String act; // What kind of action specifically
  int proximity; // If 0, an event is guarenteed to occur.
  int stayCount = 0; // Counts the amount of times the player has waited. Used in Encounter method
  // as a paramenter entry.
  ArrayList<String> items = new ArrayList<String>();
  String itemInUse = "";
  boolean used = false;
  int difficulty = 0;
  boolean hasFlower = false;

  public Player() {
    stamina = 5;
    survivalScore = 0;
  }

// Below are player actions. This includes 'flee' if in an encounter and 'move'/'stay' if otherwise. 

  public String Flee() { // Determines the success of the user running and returns the result as a
    // string
    act = "flee";
    if (stamina > 0) {
      stamina--;
      if ((int) (Math.random() * 10) > ((stamina / 2) - .5)) {
        actionSu = true;
        stamina--;
        return "You will your legs to move once more. Your heartbeat hastens and your feet tread onwards.";
      }
      actionSu = false;
      return "You compel your legs to move... but it's not enough. You fail to escape your encounter and remain ever weakened.";
    }
    else {
      actionSu = false;
      return "As the drumming of your heart persists, the anthem of your life reaches its climax; Running is not an option, for you lack the stamina.";
    }
  }

  public String Move() {
    act = "move";
    if ((Math.random() * 10) > ((stamina / 2) - .5)) {
      actionSu = true;
      survivalScore++;
      return "Slowly but surely, you limp forwards towards your freedom... ";
    }
    else {
      actionSu = false;
      return "You try to move but stumble repeatedly with every attempt of taking a step. Your legs are not prepared to yet, it seems.";
    }
  }

  public String Stay() {
    act = "stay";
    if ((Math.random() * 10) < ((Math.random() * 12 - stayCount))) {
      stamina++;
      actionSu = true;
      stayCount++;
      return "You remain still and recover a fraction of your stamina. But you know you cannot do this for long.";
    }
    else {
      actionSu = false;
      stayCount = 0;
      return "You attempt to rest when suddenly a terrible dread washes over you...";

    }
  }

  public String View() {
    if (items.size() == 0) {
      return "Every action, down to your steps, is a trial in its own. Your 'score' is currently '"
              + survivalScore + "' and you have " + stamina + " stamina and " + items.size()
              + " items. ";
    }
    else {
      String ret;
      if (items.size() == 1) {
        ret = "Every action, down to your steps, is a trial in its own. Your 'score' is currently '"
                + survivalScore + "' and you have " + stamina + " stamina and " + items.size()
                + " item - ";
      }
      else {
        ret = "Every action, down to your steps, is a trial in its own. Your 'score' is currently '"
                + survivalScore + "' and you have " + stamina + " stamina and " + items.size()
                + " items - ";
      }

      for (int i = 0; i < items.size(); i++) {
        ret = ret + "'" + items.get(i) + "' ";
      }
      ret = ret + ".";
      return ret;
    }

  }

  public String Use(String obj) {
    boolean found = false;
    String ret = "";
    if (items.size() == 0) {
      ret = "But recall you have no items to use...";
    }
    else {
      int i = 0;
      while (i < items.size() - 1 && obj != items.get(i)) {
        i++;
        if (items.get(i).equals(obj)) {
          found = true;
        }
      }
      if (i == items.size() && found == false) {
        ret = "But remember that you do not have this item...";
      }
      else {
        if (used == false) {
          if (items.get(i) == "Crystalline Flower") {
            hasFlower = true;
            System.out.print(
                    "You raise the object up to your face and it promptly bursts into a glittering light. The light hangs over you, albeit briefly. You feel as though you've gained nothing and that you can still try and gain something...");
          }
          else {
            used = true;
            itemInUse = items.get(i);
            ret = "You raise the object up to your face and it promptly bursts into glittering light. You've gained something - although you're not sure what...";
          }
        }
        else {
          ret = "You raise the object up to your face but nothing happens... Whatever you 'gained' before hasn't been used yet...";
        }
      }

    }
    return ret;
  }

  public String getAct() {
    return act;
  }

  public boolean getActionSu() {
    return actionSu;
  }

  public int getStamina() {
    return stamina;
  }

  public void setStamina(int s) {
    stamina = s;
  }

  public boolean getHasFlower() {
    return hasFlower;
  }

  public void loseStamina() {
    stamina--;
  }

  public void gainStamina(int gain) {
    stamina += gain;
  }

  public ArrayList<String> getItems() {
    return items;
  }

  public void itemsAdd(String thing) {
    items.add(thing);
  }

  public void gainSurvivalScore(int gain) {
    survivalScore += gain;
  }

  public int getSurvivalScore() {
    return survivalScore;
  }

  public void setStatus(String sta) {
    status = sta;
  }

  public String getStatus() {
    return status;
  }

  public void toggleUsed() {
    used = false;
  }

  public void setItemInUse() {
    itemInUse = "";
  }

  public String getItemInUse() {
    return itemInUse;
  }

  public void setSurvivalScoreBonus(int set) {
    survivalScoreBonus = (int) (set * 0.5 * difficulty);
  }

  public int getSurvivalScoreBonus() {
    return survivalScoreBonus;
  }

  public int getDifficulty() {
    difficulty = (int) (survivalScore / 50) + 1;
    return difficulty;
  }

}