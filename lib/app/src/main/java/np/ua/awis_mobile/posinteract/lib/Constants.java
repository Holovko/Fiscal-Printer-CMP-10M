package np.ua.awis_mobile.posinteract.lib;

/**
 * Constants thanks to galacen
 * Created by Andrey Holovko on 1/24/17.
 */

@SuppressWarnings({"CheckStyle", "WeakerAccess"})
public final class Constants {
    private Constants(){

    }
    public static final byte[] DLE = {0x10}; //Контрольный байт
    public static final byte[] STX = {0x02}; //Начало блока данных
    public static final byte[] ETX = {0x03}; //Контрольный байт

    public static final byte[] ACK = {0x06}; //Подтверждение получения сообщения
    public static final byte[] NAK = {0x15}; //Отрицание получения сообщения
    public static final byte[] SYN = {0x16}; //Занят
    public static final byte[] ENQ = {0x05}; //Запрос
    public static final byte[] SOH = {0x01}; //Открывающая скобка
    public static final byte[] EOT = {0x04}; //Разделитель

    public static final byte[] REQ = {0x1B};
    public static final byte[] CAN = {0x18};
    public static final byte[] NEXT = {0x0C};
    public static final byte[] REP = {0x05};
    public static final byte[] ACK1 = {0x16};
    public static final byte[] ACK2 = {0x1D};
    public static final byte[] NAK1 = {0x04};

}
