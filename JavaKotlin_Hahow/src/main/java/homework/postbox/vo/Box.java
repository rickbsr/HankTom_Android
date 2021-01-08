package homework.postbox.vo;

public class Box {

    private float length, width, height;

    public Box(float length, float width, float height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public boolean validate(float length, float width, float height) {
        return (this.length >= length && this.width >= width && this.height >= height);
    }

    public void printBox(){
        System.out.println(this.getClass().getSimpleName());
    }
}
