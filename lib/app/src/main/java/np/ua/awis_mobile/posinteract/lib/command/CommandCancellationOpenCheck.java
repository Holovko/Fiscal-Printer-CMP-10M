package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Cancel check
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandCancellationOpenCheck extends Command {
    private static final int CODE = 0x6B;

    /**
     * Cancel open check
     */
    public CommandCancellationOpenCheck() {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter("0");
        super.mCommandList.add(this);
    }
}
