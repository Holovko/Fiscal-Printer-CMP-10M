package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Get network settings
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandGetNetworkSettings extends Command {
    private static final int CODE = 0xFA;
    /**
     * Main construct needed data for command
     */
    public CommandGetNetworkSettings() {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(this);
    }
}
