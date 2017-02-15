package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Get device serial number
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandGetSerialNumber extends Command {
    private static final int CODE = 0x42;
    /**
     * Main construct needed data for command
     */
    public CommandGetSerialNumber() {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }
}
