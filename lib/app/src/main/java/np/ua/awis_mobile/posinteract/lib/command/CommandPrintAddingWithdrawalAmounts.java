package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

/**
 * Print check withdrawal
 */
public class CommandPrintAddingWithdrawalAmounts extends Command {
    /**
     * @param cashBoxNumber number cash box initialized by {@link CommandPrintZeroCheck}
     * @param sum           withdrawal sum
     * @param comments      comment for withdrawal
     */
    public CommandPrintAddingWithdrawalAmounts(int cashBoxNumber, float sum, String comments) {
        super();
        super.mCommandList = new ArrayList<>();

        super.mCommandList.add(new CommandOpenFiscalCheck(
                String.valueOf(cashBoxNumber),
                CommandOpenFiscalCheck.DEPOSIT_WITHDRAWAL));
        super.mCommandList.add(new CommandAddingWithdrawalAmounts(sum));
        if (comments.trim().length() > 0) {
            super.mCommandList.add(new CommandPrintText(comments));
        }
        super.mCommandList.add(new CommandCloseFiscalCheck(true));

    }
}


