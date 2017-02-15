package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

import np.ua.awis_mobile.posinteract.lib.GoodInfo;

import static np.ua.awis_mobile.posinteract.lib.command.CommandOpenFiscalCheck.SALE;

/**
 * Print zero check
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPrintZeroCheck extends Command {
    /**
     * Print zero check
     *
     * @param date          current date
     * @param time          current time
     * @param operatorName  operator name
     * @param cashBoxNumber number of cashbox
     * @param nomenclatures list of items for sale
     */
    public CommandPrintZeroCheck(String date,
                                 String time,
                                 String operatorName,
                                 String cashBoxNumber,
                                 ArrayList<GoodInfo> nomenclatures) {
        super();
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(new CommandSetDateAndTime(date, time));
        super.mCommandList.add(new CommandSetOperatorProperties(operatorName));

        for (GoodInfo nomenclature : nomenclatures) {
            super.mCommandList.add(new CommandAddGood(
                    nomenclature.getGoodCode(),
                    nomenclature.getGoodName(),
                    nomenclature.getTaxGroup())
            );
        }
        super.mCommandList.add(new CommandOpenFiscalCheck(cashBoxNumber, SALE));
        super.mCommandList.add(new CommandPrintSummaryData(false));
        super.mCommandList.add(new CommandPaymentCache(0f));
        super.mCommandList.add(new CommandCloseFiscalCheck(false));
    }
}


