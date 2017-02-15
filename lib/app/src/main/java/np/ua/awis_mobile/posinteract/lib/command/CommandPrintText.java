package np.ua.awis_mobile.posinteract.lib.command;

import java.util.ArrayList;
import java.util.List;

/**
 * Print Text if string length more LINE_SYMBOL_LENGTH
 * create add two commands
 * Created by Andrey Holovko on 1/24/17.
 */
public class CommandPrintText extends Command {
    private static final int CODE = 0x61;
    private static final int LINE_SYMBOL_LENGTH = 32;

    /**
     * @param text to print
     */
    public CommandPrintText(String text) {
        super(CODE);
        super.mCommandList = new ArrayList<>();

        if (text.length() <= LINE_SYMBOL_LENGTH) {
            super.addTextParameter(text);
            super.mCommandList.add(this);
        } else {
            List<String> lines = splitEqually(text, LINE_SYMBOL_LENGTH);
            for (String line : lines) {
                super.mCommandList.add(new CommandPrintText(line));
            }
        }
    }

    /**
     * Split string equal by symbol length
     * @param text split string
     * @param size quantity length
     * @return list of lines
     */
    private List<String> splitEqually(String text, int size) {
        // Give the list the right capacity to start with. You could use an array
        // instead if you wanted.
        List<String> ret = new ArrayList<>((text.length() + size - 1) / size);

        for (int start = 0; start < text.length(); start += size) {
            ret.add(text.substring(start, Math.min(text.length(), start + size)));
        }
        return ret;
    }
}
