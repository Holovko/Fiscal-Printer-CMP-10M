package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Set device date and time
 * Created by Andrey Holovko on 1/24/17.
 */
class CommandSetDateAndTime extends Command {
    private static final int CODE = 0x20;

    /**
     * Set the time in your device
     * @param date device date
     * @param time device time
     */
    CommandSetDateAndTime(String date, String time) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter(date);
        addTextParameter(time);
        super.mCommandList.add(this);
    }
}
