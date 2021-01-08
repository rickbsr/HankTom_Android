package homework.postbox;

import homework.postbox.vo.Box;
import homework.postbox.vo.Box3;
import homework.postbox.vo.Box5;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class BoxTester {

    public static void main(String[] args) {
        String[] sides = {"length", "width", "height"};
        while (true) {
            try {
                Map<String, Float> map = new HashMap<>();
                for (String s : sides) map.put(s, input(s));
                getBox(map.get(sides[0]), map.get(sides[1]), map.get(sides[2]));
                break;
            } catch (Exception e) {
                System.out.println("Error. Please try again.");
                continue;
            }
        }
    }

    private static Float input(String side) throws Exception {
        Scanner scan = new Scanner(System.in);
        System.out.printf("Please enter object's %s: ", side);
        float value = scan.nextFloat();
        if (value < 0) throw new Exception();
        return value;
    }

    private static void getBox(float length, float width, float height) {
        Box[] boxes = {new Box3(), new Box5()};
        for (Box box : boxes) {
            if (box.validate(length, width, height)) {
                box.printBox();
                return;
            }
        }
        System.out.println("No suitable box.");
    }
}
