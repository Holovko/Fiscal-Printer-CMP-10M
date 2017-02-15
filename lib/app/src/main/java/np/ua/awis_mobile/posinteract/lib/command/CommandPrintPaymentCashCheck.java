package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

import np.ua.awis_mobile.posinteract.lib.SaleGoodInfo;

import static np.ua.awis_mobile.posinteract.lib.command.CommandOpenFiscalCheck.SALE;

/**
 * Print payment cash
 * create add two commands
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPrintPaymentCashCheck extends Command {

    /**
     * @param cashBoxNumber number cash box initialized by {@link CommandPrintZeroCheck}
     * @param paymentSum    sale sum
     * @param goods         goods for sale
     */
    public CommandPrintPaymentCashCheck(int cashBoxNumber, float paymentSum, ArrayList<SaleGoodInfo> goods) {
        super();
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(new CommandOpenFiscalCheck(String.valueOf(cashBoxNumber), SALE));
        for (SaleGoodInfo good : goods) {
            super.mCommandList.add(new CommandSaleGood(good.getGoodCode(),
                    good.getCount(),
                    good.getPrice())
            );
            String tComment = good.getComment().trim();
            if (tComment.length() > 0) {
                super.mCommandList.add(new CommandPrintText(tComment));
            }
        }
        super.mCommandList.add(new CommandPrintSummaryData(false));
        super.mCommandList.add(new CommandPaymentCache(paymentSum));
        super.mCommandList.add(new CommandCloseFiscalCheck(false));
    }
}


