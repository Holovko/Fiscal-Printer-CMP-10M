package np.ua.awis_mobile.posinteract.lib.command;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import static np.ua.awis_mobile.posinteract.lib.utils.ByteUtils.addByteToEndArr;

/**
 * Common command parts
 * Created by Andrey Holovko on 1/24/17.
 */

public abstract class Command {
    private static final String CHARSET_CODE = "windows-1251";
    protected List<Command> mCommandList;
    private final byte[] mPassword = {0x30, 0x30, 0x30, 0x30, 0x30, 0x30};
    private final Integer mCode;
    private final List<byte[]> mCommandParameters = new ArrayList<>();

    /**
     * Create message
     *
     * @param code command code
     */
    public Command(Integer code) {
        mCode = code;
    }

    /**
     * Create command without code
     */
    public Command() {
        this(0);
    }

    public List<Command> getCommandList() {
        return mCommandList;
    }

    public byte[] getPassword() {
        return mPassword;
    }

    public Integer getCode() {
        return mCode;
    }

    public List<byte[]> getCommandParameters() {
        return mCommandParameters;
    }

    /**
     * Add text parameter
     *
     * @param parameter to add
     */
    @SuppressWarnings("EmptyCatchBlock")
    public void addTextParameter(String parameter) {
        byte[] resArr = {};
        try {
            byte[] bytes = parameter.getBytes(CHARSET_CODE);
            resArr = addByteToEndArr(resArr, bytes);
        } catch (UnsupportedEncodingException ex) {
        }
        mCommandParameters.add(resArr);
    }
}
