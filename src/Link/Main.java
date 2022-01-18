package Link;

import javafx.application.Application;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.File;


public class Main extends Application {
    TextArea News = new TextArea();
    int fig_num = 33;
    File file = new File("music/bgm1.mp3");
    String url = file.toURI().toString();
    Media media = new Media(url);
    MediaPlayer mplayer = new MediaPlayer(media);
    public void start(Stage stage){
        //-------------------------------定义面板------------------------------------

        VBox vBox  = new VBox();
        vBox.setAlignment(Pos.CENTER);
        BorderPane bp = new BorderPane();
        BorderPane Note = new BorderPane();

        News.setMinHeight(190);
        News.setMaxWidth(150);
        Note.setTop(News);

        //播放bgm
        mplayer.setCycleCount(MediaPlayer.INDEFINITE);
        mplayer.play();

        //------------------------------------开始界面，设置中心面板和背景图----------------------------------

        bp.setCenter(vBox);
        bp.setStyle("-fx-background-image: url(" + "file:pic/bg3.jpeg" + "); " +
                        "-fx-background-position: center center; " +
                        "-fx-background-repeat: stretch;" +
                        "-fx-background-color:  transparent;");
        //------------------------------进入连连看面板--------------------------
        Board bo = new Board(4,6,fig_num);//默认界面
        vBox.getChildren().add(bo.getVbox());//获取连连看面板
        bp.setCenter(vBox);

        //------------------------------------上方菜单----------------------------------
        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("正常模式难度");
        Menu menu1 = new Menu("记忆模式难度");
        Menu menu2 = new Menu("图片数量");
        Menu menu3 = new Menu("其他");
        MenuItem bt_ease = new MenuItem("简单（4*4）");
        MenuItem bt_mid = new MenuItem("中等（6*6）");
        MenuItem bt_high = new MenuItem("困难（8*8）");
        MenuItem bt_ghost = new MenuItem("炼狱（10*10）");
        MenuItem bt_free = new MenuItem("自定义");
        MenuItem bt_one = new MenuItem("简单（4*4）");
        MenuItem bt_two = new MenuItem("中等（6*6）");
        MenuItem bt_free1 = new MenuItem("自定义");
        MenuItem bt_other1 = new MenuItem("NOTE");

        //-----------------------------正常模式的按钮设置--------------------------
        bt_ease.setOnAction(e->{
            Board bo_ease = new Board(4,4,fig_num);
            reset(bp,vBox,bo_ease);
        });
        bt_mid.setOnAction(e->{
            Board bo_mid = new Board(6,6,fig_num);
            reset(bp,vBox,bo_mid);
        });
        bt_high.setOnAction(e->{
            Board bo_high = new Board(8,8,fig_num);
            reset(bp,vBox,bo_high);
        });
        bt_ghost.setOnAction(e->{
            Board bo_ghost = new Board(10,10,fig_num);
            reset(bp,vBox,bo_ghost);
        });

        bt_free.setOnAction(e->{
            Stage stage1 = new Stage();
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 380, 180, Color.WHITE);
            GridPane in_put = new GridPane();
            in_put.setPadding(new Insets(5));
            in_put.setHgap(5);
            in_put.setVgap(5);
            ColumnConstraints column1 = new ColumnConstraints(100);
            ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
            column2.setHgrow(Priority.ALWAYS);
            in_put.getColumnConstraints().addAll(column1, column2);

            Label in_row = new Label("行");
            Label in_column = new Label("列");
            TextField in_row_num = new TextField();
            TextField in_column_num = new TextField();
            Button be_Sure = new Button("确认");

            GridPane.setHalignment(in_row, HPos.RIGHT);
            GridPane.setHalignment(in_column, HPos.RIGHT);
            GridPane.setHalignment(in_row_num, HPos.LEFT);
            GridPane.setHalignment(in_column_num, HPos.LEFT);
            GridPane.setHalignment(be_Sure, HPos.RIGHT);
            in_put.add(in_row, 0, 0);
            in_put.add(in_column, 0, 1);
            in_put.add(in_row_num, 1, 0);
            in_put.add(in_column_num, 1, 1);
            in_put.add(be_Sure, 1, 2);

            be_Sure.setOnMouseClicked(e1->{
                int real_r = Integer.parseInt(in_row_num.getText());
                int real_c = Integer.parseInt(in_column_num.getText());
                if(real_c % 2 == 0 || real_r % 2 ==0){
                    Board free_bo = new Board(real_r,real_c,fig_num);
                    reset(bp,vBox,free_bo);
                    stage1.close();
                }
            });
            root.setCenter(in_put);
            stage1.setScene(scene);
            stage1.show();
        });
        //-----------------------------记忆模式的按钮设置----------------------------------
        bt_one.setOnAction(e->{
            Board_Remember br = new Board_Remember(4,4,fig_num);
            reset(bp,vBox,br);
        });
        bt_two.setOnAction(e->{
            Board_Remember br = new Board_Remember(6,6,fig_num);
            reset(bp,vBox,br);
        });
        bt_free1.setOnAction(e->{
            Stage stage1 = new Stage();
            BorderPane root = new BorderPane();
            Scene scene = new Scene(root, 380, 150, Color.WHITE);
            GridPane in_put = new GridPane();
            in_put.setPadding(new Insets(5));
            in_put.setHgap(5);
            in_put.setVgap(5);
            ColumnConstraints column1 = new ColumnConstraints(100);
            ColumnConstraints column2 = new ColumnConstraints(50, 150, 300);
            column2.setHgrow(Priority.ALWAYS);
            in_put.getColumnConstraints().addAll(column1, column2);

            Label in_row = new Label("行");
            Label in_column = new Label("列");
            TextField in_row_num = new TextField();
            TextField in_column_num = new TextField();
            Button be_Sure = new Button("确认");

            GridPane.setHalignment(in_row, HPos.RIGHT);
            GridPane.setHalignment(in_column, HPos.RIGHT);
            GridPane.setHalignment(in_row_num, HPos.LEFT);
            GridPane.setHalignment(in_column_num, HPos.LEFT);
            GridPane.setHalignment(be_Sure, HPos.RIGHT);
            in_put.add(in_row, 0, 0);
            in_put.add(in_column, 0, 1);
            in_put.add(in_row_num, 1, 0);
            in_put.add(in_column_num, 1, 1);
            in_put.add(be_Sure, 1, 2);

            be_Sure.setOnMouseClicked(e1->{
                int real_r = Integer.parseInt(in_row_num.getText());
                int real_c = Integer.parseInt(in_column_num.getText());
                if(real_c % 2 == 0 || real_r % 2 ==0){
                    Board_Remember free_bo = new Board_Remember(real_r,real_c,fig_num);
                    reset(bp,vBox,free_bo);
                    stage1.close();
                }
            });
            root.setCenter(in_put);
            stage1.setScene(scene);
            stage1.show();
        });

        bt_other1.setOnAction(e->{
            Stage stage1 = new Stage();
            Pane pane = new Pane();

            pane.getChildren().addAll(News);
            Scene scene = new Scene(pane,400,200);
            stage1.setScene(scene);
            stage1.show();
        });

        //----------------------------------菜单布局-----------------------------

        menu.getItems().addAll(bt_ease,bt_mid,bt_high,bt_ghost,bt_free);
        menu1.getItems().addAll(bt_one,bt_two,bt_free1);

        //改变全局变量的图片数量，要重新设置。
        for(int i = 10 ; i <= 33; i++){
            MenuItem bts = new MenuItem(""+i);
            int k = i;
            bts.setOnAction(e->fig_num=k);
            menu2.getItems().add(bts);
        }
        menu3.getItems().addAll(bt_other1);
        menuBar.getMenus().addAll(menu,menu1,menu2,menu3);

        //------------------------------------布置场景--------------------------------
        bp.setTop(menuBar);

        final Scene scene = new Scene(bp,1000, 600);
        stage.setScene(scene);
        stage.setTitle("LiNK_LiNK");
        stage.getIcons().add(new Image("file:pic/"+(int)(1+Math.random()*33)+".jpg"));
        stage.show();
    }

    //-----------------------------------------刷新游戏界面---------------------------
    public void reset(BorderPane bp,VBox vBox,Board bo){
        vBox.getChildren().clear();
        vBox.getChildren().add(bo.getVbox());
        bp.setCenter(vBox);
    }
}

