package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Close fiscal check
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandCloseFiscalCheck extends Command {
    private static final int CODE = 0x65;

    /**
     * @param isPrintOnIndicator if pos has indicator
     */
    public CommandCloseFiscalCheck(boolean isPrintOnIndicator) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        if (isPrintOnIndicator) {
            addTextParameter("1");
        } else {
            addTextParameter("0");
        }
        super.mCommandList.add(this);
    }
}
