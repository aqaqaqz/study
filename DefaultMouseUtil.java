package util.utils.def;


import java.awt.MouseInfo;
import java.awt.PointerInfo;
import java.awt.Robot;


public class DefaultMouseUtil {

    public void preventScreenSaver() {

        try {
            Robot robot = new Robot();
            PointerInfo info;
            int move = 1;

            while (true) {
                info = MouseInfo.getPointerInfo();
                robot.mouseMove(info.getLocation().x + move, info.getLocation().y + move);
                move *= -1;
                Thread.sleep(1000);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
