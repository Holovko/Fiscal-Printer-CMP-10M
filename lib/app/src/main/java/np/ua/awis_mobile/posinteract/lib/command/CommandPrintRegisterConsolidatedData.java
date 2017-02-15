package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;

import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.AMOUNTS_ADDING_WITHDRAWAL;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.AMOUNTS_IN_CACHEBOX;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.INCOME_TAX_A;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.INCOME_TAX_B;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.INCOME_TAX_C;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.PAYMENTS_CACHE;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.PAYMENTS_CACHELESS;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.TURNOVER_A;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.TURNOVER_B;
import static np.ua.awis_mobile.posinteract.lib.command.CommandGetAmountsPerDay.TURNOVER_C;

/**
 * Print cancel check
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPrintRegisterConsolidatedData extends Command {
    /**
     * Create command for consolidate info
     */
    public CommandPrintRegisterConsolidatedData() {
        super();
        super.mCommandList = new ArrayList<>();
        super.mCommandList.add(new CommandGetVersionSoftware());
        super.mCommandList.add(new CommandGetSerialNumber());
        super.mCommandList.add(new CommandGetNetworkSettings());
        super.mCommandList.add(new CommandGetFreeResourceAndStatusInfo());
        super.mCommandList.add(new CommandGetFiscalNumber());
        super.mCommandList.add(new CommandGetDateAndTime());
        super.mCommandList.add(new CommandGetTaxIdNumber());
        super.mCommandList.add(new CommandGetAmountsPerDay(PAYMENTS_CACHE));
        super.mCommandList.add(new CommandGetAmountsPerDay(PAYMENTS_CACHELESS));
        super.mCommandList.add(new CommandGetAmountsPerDay(AMOUNTS_IN_CACHEBOX));
        super.mCommandList.add(new CommandGetAmountsPerDay(AMOUNTS_ADDING_WITHDRAWAL));
        super.mCommandList.add(new CommandGetAmountsPerDay(TURNOVER_A));
        super.mCommandList.add(new CommandGetAmountsPerDay(TURNOVER_B));
        super.mCommandList.add(new CommandGetAmountsPerDay(TURNOVER_C));
        super.mCommandList.add(new CommandGetAmountsPerDay(INCOME_TAX_A));
        super.mCommandList.add(new CommandGetAmountsPerDay(INCOME_TAX_B));
        super.mCommandList.add(new CommandGetAmountsPerDay(INCOME_TAX_C));
    }
}


