package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Get version software
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandGetVersionSoftware extends Command {
    private static final int CODE = 0x80;
    /**
     * Main construct needed data for command
     */
    public CommandGetVersionSoftware() {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }
}
