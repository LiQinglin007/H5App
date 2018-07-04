package h5app.comxiaomi.h5app.event;

/**
 * Description: <br>
 * User: dell - XiaomiLi<br>
 * Date: 2018-07-03<br>
 * Time: 10:25<br>
 * UpdateDescription：<br>
 */
public class MessageEvent {
    public final static int setLauncherTypr = 0x1;
    int type;
    int NumberMesssage;


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getNumberMesssage() {
        return NumberMesssage;
    }

    public void setNumberMesssage(int numberMesssage) {
        NumberMesssage = numberMesssage;
    }
}
