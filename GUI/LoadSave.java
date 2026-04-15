import java.io.*;
import javax.swing.*;
import java.awt.Component;

public class LoadSave{
  public static void saveGame(Board board, Piece.Color currentTurn, Component parent) {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Save Game");
    chooser.setSelectedFile(new File("chess_save.ser"));

    int result = chooser.showSaveDialog(parent);
    if (result != JFileChooser.APPROVE_OPTION) return; 

    File file = chooser.getSelectedFile();

    if (!file.getName().endsWith(".ser")) {
        file = new File(file.getAbsolutePath() + ".ser");
      
  }
  GameState state = new GameState(board, currentTurn);

    try (FileOutputStream fos = new FileOutputStream(file);
         ObjectOutputStream oos = new ObjectOutputStream(fos)) {

        oos.writeObject(state); 
        JOptionPane.showMessageDialog(parent, "Game saved to:\n" + file.getAbsolutePath());

    } catch (IOException e) {
        JOptionPane.showMessageDialog(parent,
            "Failed to save: " + e.getMessage(),
            "Save Error", JOptionPane.ERROR_MESSAGE);
    }
}

  public static GameState loadGame(Component parent) {
    JFileChooser chooser = new JFileChooser();
    chooser.setDialogTitle("Load Game");

    int result = chooser.showOpenDialog(parent);
    if (result != JFileChooser.APPROVE_OPTION) return null;

    File file = chooser.getSelectedFile();

    try (FileInputStream fis = new FileInputStream(file);
         ObjectInputStream ois = new ObjectInputStream(fis)) {

        GameState state = (GameState) ois.readObject();
        JOptionPane.showMessageDialog(parent, "Game loaded successfully!");
        return state;

    } catch (IOException | ClassNotFoundException e) {
        JOptionPane.showMessageDialog(parent,
            "Failed to load: " + e.getMessage(),
            "Load Error", JOptionPane.ERROR_MESSAGE);
        return null;
    }
  }
}
