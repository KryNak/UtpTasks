/**
 *
 *  @author Nakielski Krystian S20258
 *
 */

package zad3;


import javax.swing.*;

public class Main {

  public static void main(String[] args) {
    SwingUtilities.invokeLater(() -> {
      try {
        new TaskFrame();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    });
  }

}
