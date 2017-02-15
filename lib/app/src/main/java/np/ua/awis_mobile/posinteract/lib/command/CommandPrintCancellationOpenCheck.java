package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Print cancel check
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPrintCancellationOpenCheck extends Command {

    /**
     */
    public CommandPrintCancellationOpenCheck() {
        super();
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(new CommandCancellationOpenCheck());
        super.mCommandList.add(new CommandPrintCoupon(CommandPrintCoupon.PRINT_AND_RETURN));
        super.mCommandList.add(new CommandCloseFiscalCheck(false));
    }
}


