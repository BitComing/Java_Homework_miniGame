package Link;

import javafx.animation.AnimationTimer;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.File;


public class Board {
    private int row ;
    private int column ;
    public HBox[] hboxes ;
    private VBox vbox = new VBox(10);

    private Figure[][] pts;
    private Figure f1,f2;

    public String str_score;
    File file = new File("music/cmusic.mp3");
    Media media = new Media(file.toURI().toString());

    private Image[] ims ;
    private boolean isCilck = true;
    private boolean First = true;
    private int tme = 0;
    private int real_time = 0;
    private int fig_num = 0;
    private Text step = new Text("点击：0");
    private Font str = new Font(50);
    private VBox Note = new VBox(10);
    private BorderPane All = new BorderPane();

    public Board(){
    }
    public Board(int row,int column,int fig_num){
        this.row = row;
        this.column = column;
        this.fig_num  =fig_num;
        pts = new Figure[row][column];
        hboxes = new HBox[row];
        ims = new Image[100];

        Text note_time = new Text();
        DoubleProperty time = new SimpleDoubleProperty();
        note_time.textProperty().bind(time.asString("时间：%.3f s"));
        BooleanProperty running = new SimpleBooleanProperty();
        AnimationTimer timer = new AnimationTimer() {
            private long startTime ;
            @Override
            public void start() {
                startTime = System.currentTimeMillis();
                running.set(true);
                super.start();
            }
            @Override
            public void stop() {
                running.set(false);
                super.stop();
            }
            @Override
            public void handle(long timestamp) {
                long now = System.currentTimeMillis();
                time.set((now - startTime) / 1000.0);
            }
        };

        Note.setPadding(new Insets(30));
        step.setFont(new Font("STHupo",50));
        note_time.setFont(new Font("STHupo",50));
        step.setFill(Color.WHITE);
        note_time.setFill(Color.WHITE);
        Note.getChildren().addAll(step,note_time);

        for(int i = 1 ; i <= fig_num ; i++){
            ims[i-1] = new Image("file:pic/"+i+".jpg");
        }

        int[][] exi = new int[row][column];
        for(int i = 0 ; i < row ;i++){
            for (int j = 0 ; j < column ; j++){
                exi[i][j]=0;
            }
        }
        for(int t = 0 ; t < (int)(row*column*0.5); t++){
            int card = (int) (Math.random() * fig_num) ;
            int rowx = (int) (Math.random() * (row)) ;
            int columnx = (int) (Math.random() * (column)) ;
            for(int i = 0 ; i < 2 ; i++){
                while (exi[rowx][columnx] != 0){
                    rowx = (int) (Math.random() * (row)) ;
                    columnx = (int) (Math.random() * (column)) ;
                }
                pts[rowx][columnx] = new Figure(ims[card]);
                exi[rowx][columnx] = 1;
            }
        }


        for(int i = 0 ; i < row ; i ++){
            hboxes[i] = new HBox(10);
            hboxes[i].setAlignment(Pos.CENTER);
            int n = i;
            for(int j = 0 ; j < column ; j++){
                int m = j;
                pts[i][j].getImv().setOnMouseClicked(e->{
                    if(First){
                        First = false;
                        timer.start();
                    }
                    if(tme == row*column || !pts[n][m].getFlag())return;
                    real_time++;
                    MediaPlayer one_m = new MediaPlayer(media);
                    one_m.play();
                    step.setText("点击：" + real_time);
                    step.setFont(new Font("STHupo",50));

                    pts[n][m].show();
                    if(isCilck && pts[n][m].getFlag()){
                        f1 = pts[n][m];tme++;
                        if(f2 != null){
                            if(f1.getIm() == f2.getIm()&& f1!=f2){
                                isCilck = true;tme++;
                                f1.OK();f2.OK();
                                f2=null;f1=null;
                            }else {
                                f2=null;
                                isCilck = false;
                            }
                        }else {
                            isCilck = false;
                        }
                    }else if(!isCilck && pts[n][m].getFlag() && f1 != pts[n][m]){
                        f2 = pts[n][m];
                        if(f1.getIm() == f2.getIm()){
                            tme++;
                            f1.OK();f2.OK();
                            f1=null;f2=null;
                        }else {
                            tme--;f1=null;
                        }
                        isCilck = true;
                    }
                    if(tme == row*column && !pts[n][m].getFlag()){
                        timer.stop();
                        Stage stage1 = new Stage();
                        BorderPane bpx = new BorderPane();

                        str_score = step.toString() + " " + note_time.toString();

                        Text tip = new Text("You finish it!");
                        bpx.setCenter(tip);

                        Scene scene1 = new Scene(bpx, 300, 200);
                        stage1.setTitle("Next");
                        stage1.setScene(scene1);
                        stage1.show();
                    }
                });
                hboxes[i].getChildren().add(pts[i][j].getImv());
            }

            vbox.getChildren().add(hboxes[i]);
            All.setCenter(vbox);
            All.setRight(Note);
        }
    }
    public BorderPane getVbox(){
        return All;
    }

    public String getScore(){
        return str_score;
    }

}
