package edu.citytech.abccounter;

import com.jbbwebsolutions.http.utility.JSONGet;
import edu.citytech.MainController;
import edu.citytech.abccounter.model.Mode;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.GridPane;
import java.net.URL;
import java.sql.Struct;
import java.util.*;

public class ABCController extends MainController implements Initializable {

    @FXML
    private GridPane gridABC;


    @FXML
    private Label lblTitle;

    @FXML
    private ToggleGroup tgMode;

    @FXML
    private ComboBox<Mode> cbHighlight;

    @FXML
    void selectionMode(ActionEvent event) {
        Node node = (Node) event.getSource();

        if (node.getUserData().toString().equals("Z")) {
            alphabets(true);
            lblTitle.setText("CBA");
            loadABCOptions();

        } else if (node.getUserData().toString().equals("A")) {
            alphabets(false);
            lblTitle.setText("ABC");
            loadABCOptions();

        } else if (node.getUserData().toString().equals("1")) {
            $123();
            load$123Options();
            lblTitle.setText("123");

        } else if (node.getUserData().toString().equals("3")) {
            var reverse = true;
            $123(reverse);
            load$123Options();
            lblTitle.setText("321");

        } else if (node.getUserData().toString().equals("5")) {
            $100();
            load$123Options();
            lblTitle.setText("123");

        } else if (node.getUserData().toString().equals("15")) {
            var reverse = true;
            $100(reverse);
            load$123Options();
            lblTitle.setText("123");

        }
        System.out.println(node.getUserData());

    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        alphabets(false);
        loadABCOptions();

    }

    private void loadABCOptions() {

        var list = new ArrayList<Mode>();
        list.add(new Mode("n", "none"));
        list.add(new Mode("v", "vowel"));
        list.add(new Mode("c", "consonant"));


        var abcList = FXCollections.observableArrayList(list);
        cbHighlight.setItems(abcList);


        var stage = super.anchorPane.getScene();
        super.anchorPane.setMinHeight(573);

    }
    private void load$123Options() {

        var list = new ArrayList<Mode>();
        list.add(new Mode("n", "none"));
        list.add(new Mode("o","Odd numbers"));
        list.add(new Mode("e","even numbers"));
        list.add(new Mode("6","every 6 numbers"));
        list.add(new Mode("#","contains #4 or 9"));



        var abcList = FXCollections.observableArrayList(list);
        cbHighlight.setItems(abcList);

    }

    public void alphabets(boolean reverse) {
        String sURL = "http://localhost:9615/alphabets/abc";
        var abc = JSONGet.submitGet(sURL, String[].class);

        if (reverse) {
            Arrays.sort(abc, Collections.reverseOrder());

        }
        System.out.println(Arrays.toString(abc));
        gridABC.getChildren().clear();
        int column = 0, row = 0;
        for (var x : abc) {
            Button btn = new Button(x);
            btn.getStyleClass().add("abc");
            gridABC.add(btn, column++, row);
            if (column > 3) {
                row++;
                column = 0;
            }


        }
    }


    public void $123(boolean... reverse) {
        String sURL = "http://localhost:9615/numbers/123";
        var $num = JSONGet.submitGet(sURL, Integer[].class);

        if (reverse.length > 0 && reverse[0] == true) {
            Arrays.sort($num, Collections.reverseOrder());

        }


            System.out.println(Arrays.toString($num));
            gridABC.getChildren().clear();
            int column = 0, row = 0;
            for (var x : $num) {
                Button btn = new Button(x + "");
                btn.getStyleClass().add("num");
                gridABC.add(btn, column++, row);
                if (column > 7) {
                    row++;
                    column = 0;
                }
            }

        }


    public void $100(boolean... reverse) {
        String sURL = "http://localhost:9615/numbers/123";
        var $num = JSONGet.submitGet(sURL, Integer[].class);

        if (reverse.length > 0 && reverse[0] == true) {
            Arrays.sort($num, Collections.reverseOrder());

        }
            System.out.println(Arrays.toString($num));
            gridABC.getChildren().clear();
            int column = 0, row = 0;
            for (var x : $num) {
                System.out.println();
                Button btn = new Button(x*5 +"");
                btn.getStyleClass().add("num");
                gridABC.add(btn, column++, row);
                if (column > 7) {
                    row++;
                    column = 0;
                }
            }

        }







    @FXML
    void highLightAction(ActionEvent event) {
        var selectedModel = cbHighlight.getSelectionModel();
        var current = selectedModel.getSelectedItem();
        if (current==null){
            System.out.println("Next Checkbox...");
            return;
        }
        System.out.println(current.getMode() + " " + current.getDescription());

        var buttons = gridABC.getChildren().stream().map(e -> (Button)e).toList();//{100,99,98,........0}

        for (Button b :buttons) {
            b.getStyleClass().removeAll("highLight");
        }

        if(current.getMode().equals("n"))
            for (Button b :buttons) {
                b.getStyleClass().removeAll("highLight");
            }

        if(current.getMode().equals("v"))
            for (Button b :buttons) {
                if(ABCService.isVowel(b.getText())){
                    b.getStyleClass().add("highLight");
                }
            }
        if(current.getMode().equals("c"))
            for (Button b :buttons) {
                if(ABCService.isConsonant(b.getText())){
                    b.getStyleClass().add("highLight");
                }
            }
        if(current.getMode().equals("e"))
            for (Button b :buttons) {
                if(ABCService.isEven(b.getText())){
                    b.getStyleClass().add("highLight");
                }
            }

        if(current.getMode().equals("o"))
            for (Button b :buttons) {
                if(ABCService.isOdd(b.getText())){
                    b.getStyleClass().add("highLight");
                }
            }

        if(current.getMode().equals("6"))
            for (Button b :buttons) {
                if(ABCService.isEvery$6(b.getText())){
                    b.getStyleClass().add("highLight");
                }
            }
        if(current.getMode().equals("#"))
            for (Button b :buttons) {
                if(ABCService.isContains$4or9(b.getText())){
                    b.getStyleClass().add("highLight");
                }
            }


    }
}
















