package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;
import java.util.Locale;

/**
 * Withdrawal money
 * Created by Andrey Holovko on 1/24/17.
 */
class CommandAddingWithdrawalAmounts extends Command {
    private static final int CODE = 0x77;

    /**
     * @param sum withdrawal sum
     */
    CommandAddingWithdrawalAmounts(float sum) {
        super(CODE);
        super.mCommandList = new ArrayList<>();
        addTextParameter("0");
        addTextParameter(String.format(Locale.US, "%.2f", sum));
        super.mCommandList.add(this);
    }
}
