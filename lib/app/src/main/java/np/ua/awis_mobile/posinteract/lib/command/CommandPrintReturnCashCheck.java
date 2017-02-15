package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

import np.ua.awis_mobile.posinteract.lib.SaleGoodInfo;


/**
 * Print return sale
 */
public class CommandPrintReturnCashCheck extends Command {

    /**
     * @param cashBoxNumber number cash box initialized by {@link CommandPrintZeroCheck}
     * @param paymentSum    sale sum
     * @param goods         goods for return
     */
    public CommandPrintReturnCashCheck(int cashBoxNumber, float paymentSum, ArrayList<SaleGoodInfo> goods) {
        super();
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(new CommandOpenFiscalCheck(String.valueOf(cashBoxNumber),
                CommandOpenFiscalCheck.RETURN)
        );
        for (SaleGoodInfo good : goods) {
            super.mCommandList.add(new CommandSaleGood(good.getGoodCode(), good.getCount(), good.getPrice()));
            String tComment = good.getComment().trim();
            if (tComment.length() > 0) {
                super.mCommandList.add(new CommandPrintText(tComment));
            }
        }
        super.mCommandList.add(new CommandPrintSummaryData(true));
        super.mCommandList.add(new CommandPaymentCache(paymentSum));
        super.mCommandList.add(new CommandCloseFiscalCheck(true));
    }
}


