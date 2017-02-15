package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Get resource info
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandGetFreeResourceAndStatusInfo extends Command {
    private static final int CODE = 0xA0;
    /**
     * Main construct needed data for command
     */
    public CommandGetFreeResourceAndStatusInfo() {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }
}
