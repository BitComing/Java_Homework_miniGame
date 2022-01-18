package Link;


import javafx.scene.SnapshotParameters;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.image.WritableImage;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Figure {

    private Image im;
    private boolean flag = false;
    private static final Image Hide = new Image("file:pic/hide.jpg");
    private ImageView imv ;
    Figure(){
    }
    Figure(Image im){
        this.flag = true;
        this.im = im;
        this.imv = new ImageView(im);
        this.imv.setImage(Hide);
        this.imv.setFitWidth(50);
        this.imv.setFitHeight(50);
    }

    public ImageView getImv(){
        init_shape(imv);
        return imv;
    }
    public Image getIm(){
        return im;
    }
    public boolean getFlag(){
        return flag;
    }

    public void show(){
        imv.setImage(im);
        init_shape(imv);
    }
    public void hide(){
        imv.setImage(Hide);
        init_shape(imv);
    }
    public void OK(){
        imv.setImage(null);
        init_shape(imv);
        flag = false;
    }

    //调整图形
    public void init_shape(ImageView imageView) {
        Rectangle clip = new Rectangle(
                imageView.getFitWidth(), imageView.getFitHeight()
        );
        clip.setArcWidth(20);
        clip.setArcHeight(20);
        imageView.setClip(clip);
        SnapshotParameters parameters = new SnapshotParameters();
        parameters.setFill(Color.TRANSPARENT);
        WritableImage image = imageView.snapshot(parameters, null);
        imageView.setClip(null);
        imageView.setEffect(new DropShadow(20, Color.BLACK));
        imageView.setImage(image);
    }

}

