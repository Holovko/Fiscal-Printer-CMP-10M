package np.ua.awis_mobile.posinteract.lib.utils;

/**
 * Created by Andrey Holovko on 1/24/17.
 */

@SuppressWarnings("CheckStyle")
public enum Result {
    NO_ERRORS("Без помилок", 0x0000),
    UNSPECIFIED_ERROR_EQUIPMENT("Невизначена помилка обладнання", 0x0001),
    PRINTER_ERROR("Помилка принтера", 0x0101),
    ERROR_RAM("Помилка RAM", 0x0201),
    ERROR_CHECKSUM_PROGRAM_MEMORY("Помилка Контрольна сума пам'яті програм", 0x0301),
    ERROR_FLASH_MEMORY("Помилка flash пам'яті", 0x0401),
    ERROR_DISPLAY("Помилка Дисплея", 0x0501),
    ERROR_HOURS_SHOULD_BE_ADJUSTED_TIME("Помилка годин, необхідно скоригувати час", 0x0601),
    ERROR_OCCURRED_BECAUSE_OF_LOWER_FOOD("Помилка виникла внаслідок зниження харчування", 0x0701),
    SIM_EQUIPMENT_FAILURE("SIM: відмова обладнання", 0x0801),
    MMC_EQUIPMENT_FAILURE("MMC: відмова обладнання", 0x0901),
    THERE_IS_NO_TEAM_WITH_THIS_CODE("Немає такої команди з таким кодом", 0x0002),
    THIS_FEATURE_IS_NOT_SUPPORTED("Ця функція не підтримується", 0x0102),
    THE_REPORT_NOT_AVAILABLE("У повідомленні немає даних", 0x0202),
    BUFFER_R_T("Переповнення буфера приймача / передавача", 0x0302),
    INCORRECT_FIELD_FORMAT("Формат поля %s невірний", 0x0003),
    INCORRECT_FIELD_VALUE("Значення поля %s невірний", 0x0004),
    THERE_IS_NO_SPACE_TO_RECORD_IN_AF("Немає вільного місця для запису в ФП", 0x0005),
    ERROR_WRITING_TO_AF("Помилка запису в ФП", 0x0105),
    SERIAL_NUMBER_IS_NOT_SET("Заводський номер не встановлено", 0x0205),
    LAST_POST_IS_LESS_THAN_THAT_TRY_TO_ESTABLISH("Дата останнього запису менше ніж та, що намагаємося встановити", 0x0305),
    YOU_CAN_NOT_GO_BEYOND_THE_DAY("Не можна виходити за межі доби", 0x0405),
    ERROR_FISCAL_MEMORY("Помилка фіскальної пам'яті", 0x0505),
    FISCAL_MEMORY_IS_EXHAUSTED("Фіскальна пам'ять вичерпана", 0x0605),
    THIS_COMMAND_IS_EXECUTED_ONLY_IN_FISCAL_MODE("Дана команда виконується тільки у фіскальному режимі", 0x0705),
    DATE_AND_TIME_HAVE_NOT_BEEN_SET_SINCE_THE_LAST_RESET_RAM_EMERGENCY("дата й час не були встановлені з моменту останнього аварійного обнулення ОЗУ", 0x0805),
    FROM_THE_BEGINNING_OF_THE_CHANGE_HAS_BEEN_MORE_THAN_24X_HOURS("З початку зміни пройшло більше 24х годин", 0x0905),
    IT_IS_NECESSARY_TO_ADJUST_THE_TIME("Необхідно скоригувати час", 0x0A05),
    ERROR_IN_TABLE_FORMAT("Помилка у форматі таблиці", 0x0B05),
    THE_ERROR_IN_THE_DATE_FORMAT("Помилка в форматі дати", 0x0C05),
    THE_ERROR_IN_THE_TIME_FORMAT("Помилка в форматі часу", 0x0D05),
    TIME_THAT_TRY_TO_INSTALL_LESS_THAN_THE_CURRENT("Час що намагаємося встановити менше поточного", 0x0E05),
    THERE_ARE_NO_DOCUMENTS_SENT_ACQUIRER_FOR_72_HOURS_OR_MORE("Є не відправлені еквайру документи протягом 72 годин або більше", 0x0F05),
    YOUR_PASSWORD_ACCESS("Невірний пароль доступу", 0x0006),
    YOUR_DEVICE_MODE_JUMPER("Невірний режим апарату (перемичка)", 0x0007),
    IN_THIS_STATE_CHANGE_IMPOSSIBLE("В даному стані зміни нездійсненна", 0x0107),
    YOU_MUST_UNLOAD_CONTROL_TAPE_PAPER_SIGNED("Необхідно вивантажити контрольну стрічку (паперову / підписану)", 0x0207),
    THE_DEVICE_IS_OFFLINE("Апарат в автономному режимі", 0x0307),
    CHANGING_CLOSED_BUT_NEOBNULENA("Зміна закрита але необнулена", 0x0407),
    PERSONALIZE_FAILED("Персоналізація не виконано", 0x0507),
    NOT_ALL_DATA_ARE_TRANSFERRED_TO_THE_ACQUIRER_CHANGING_TAX_NUMBER("Не всі дані передані еквайру перед зміною податкових номерів", 0x0607),
    PRINTER_NOT_ACTIVATED("Версія не активовано", 0x0707),
    YOUR_ACTIVATION_KEY_VERSION("Невірний ключ активації версії", 0x0907),
    OVERFLOW_MATHEMATICS("Переповнення математики", 0x0008),
    NOT_CLEANED("Не очищене", 0x0009),
    READ_WRITE_ON_UNINITIALIZED_POINTER("Читання / запис по неініціалізованих вказівником", 0x0109),
    ADDRESS_SETTING_OUTSIDE_AREA("Адреса / параметр за межами зони", 0x0209),
    HIGHLIGHT_INSUFFICIENT_BUFFER("Виділено недостатній буфер", 0x0309),
    ERROR_DATA("Помилка даних", 0x0409),
    NOT_ENOUGH_FREE_SPACE_FOR_THE_COMMAND("Недостатньо вільного місця для виконання команди", 0x000A),
    MAXIMUM_RECORD_LENGTH_GREATER_MORE_255("Довжина запису більше максимуму (> 255)", 0x010A),
    MODEL_CASHIER_DATA_SOURCE_NOT_FOUND("Артикул / Касир / .. з даними кодом не найден", 0x020A),
    THE_INDEX_BASE_OUTSIDE("Індекс за межами бази", 0x030A),
    REFERENCE_DATA_SOURCE_EXISTS("Артикул з даними кодом існує", 0x040A),
    TAX_FORBIDDEN("Податок заборонений", 0x050A),
    SALES_TAX_GROUP_WAS_NOT("Продажів по податковій групі не було", 0x060A),
    USING_THIS_TYPE_OF_TAX_PROHIBITED("Використання такого типу податку заборонено", 0x070A),
    INCORRECT_STATUS_DOCUMENT("Невірне стан документа", 0x000B),
    NOT_ENOUGH_FREE_SPACE_FOR_THE_COMMAND2("Недостатньо вільного місця для виконання команди", 0x010B),
    UNKNOWN_TYPE_OF_RECORD_SALES("Невідомий тип запису продажу", 0x020B),
    CANCELLATION_CAN_NOT_START_WITH_THIS_TRANSACTION("Ануляція: не може починатися з даної операції", 0x030B),
    CANCELLATION_THIS_OPERATION_IS_NOT_FOUND_IN_THE_CHECK("Ануляція: дана операція в чеку не знайдено", 0x040B),
    CANCELLATION_INCOMPLETE_SEQUENCE("Ануляція: послідовність неповна", 0x050B),
    ANNUL_NOTHING("Анулювати нічого", 0x060B),
    A_COPY_OF_THE_SET_OF_CHECKS_AVAILABLE("Копія заданих чеків недоступна", 0x070B),
    NOT_ENOUGH_CASH_FOR_THE_OPERATION("Недостатньо готівки для виконання операції", 0x080B),
    THIS_TYPE_OF_PAYMENT_IN_THIS_CHECK_IS_FORBIDDEN("Даний вид оплати в цьому чеку заборонений", 0x090B),
    DELIVERY_OF_THIS_TYPE_OF_PAYMENT_IS_PROHIBITED("Здача з даного виду оплати заборонена", 0x0A0B),
    THE_VALUE_OF_DISCOUNTS_EXTENDED_BEYOND("Значення знижки вийшло за межі", 0x0B0B),
    OVERFLOW_RESULT_OF_A_CHECK("Переповнення підсумку по чеку", 0x0C0B),
    OVERFLOW_TO_PAY("Переповнення по оплатах", 0x0D0B),
    WENT_BEYOND_THE_BUFFER("Вийшли за межі буфера", 0x0E0B),
    SALE_MISTAKE_IN_THE_NUMBER("Продаж: помилка в кількості", 0x0F0B),
    SALE_PRICE_ERROR("Продаж: помилка в ціні", 0x100B),
    CANCELLATION_DELETED_SEQUENCE_IS_BLOCKED("Ануляція: видаляється послідовність заблокована", 0x110B),
    SALE_REACHED_THE_MAXIMUM_NUMBER_OF_ITEMS_ON_THE_CHECK_COMMENTS_CONSIDERED_AS_POSITION("Продаж: досягнуто максимальну кількість позицій в чеку (коментарі вважаються як позиції)", 0x120B),
    TMPBUFF_DOES_NOT_MATCH_TITLE("TMPBuff: Не відповідає заголовок", 0x00C0),
    TMPBUFF_DATA_NOT_COINCIDE_WITH_THE_PREVIOUSLY_SAVED("TMPBuff: дані не співпали з раніше збереженими", 0x01C0),
    TMPBUFF_OUTSIDE_BUFFER("TMPBuff: за межами буфера", 0x02C0),
    SIM_INCORRECT_METHOD_OF_CALCULATING_HASH("SIM: неправильний порядок обчислення хеша", 0x00D0),
    SIM_SIGNATURE_DOES_NOT_MATCH("SIM: підпис не збігається", 0x01D0),
    SIM_CURRENT_PUBLIC_KEY_DOES_NOT_MATCH_SAVED_IN_AF("SIM: поточний відкритий ключ не відповідає збереженому в ФП", 0x02D0),
    SIM_ERROR_PRIVATE_KEY("SIM: Помилка закритого ключа", 0x03D0),
    SIM_ID_DEV_NOT_INSTALLED("SIM: ID_DEV не встановлений", 0x04D0),
    SIM_INVALID_CARD_STATUS("SIM: Неправильне стан карти", 0x05D0),
    SIM_ERROR_SIGNATURE("SIM: Помилка підписування", 0x06D0),
    SIM_ERROR_XML_FORMAT("SIM: Помилка формату XML", 0x07D0),
    SIM_ERROR_VERSIONS_OF_WORKS("SIM: Помилка версії ключа", 0x08D0),
    SIM_DOCUMENT_DESTROYED("SIM: Документ знищений", 0x09D0),
    SIM_DOCUMENT_MODIFIED("SIM: Документ змінений", 0x0AD0),
    SIM_INVALID_DOCUMENT_SIZE("SIM: Невірний розмір документа", 0x0BD0),
    SIM_DOCUMENT_COPY_NOT_AVAILABILITY("SIM: Копія документа тимчасово недоступна. Йде передача данних", 0x0CD0),
    MMC_CARD_SHOULD_BE_FORMATTED("MMC: картку необхідно форматувати", 0x00D1),
    MMC_NOT_ENOUGH_FREE_SPACE_FOR_THE_COMMAND("MMC: Недостатньо вільного місця для виконання команди", 0x01D1),
    MMC_ERROR_CREATING_FILE_A_FILE_WITH_THAT_NAME_ALREADY_EXISTS("MMC: Помилка створення файлу, файл з таким ім'ям вже існує", 0x02D1),
    MMC_ERROR_READING_FILE_SIZE_REQUESTED_TO_READING_MORE_OF_THE_ACTUAL_FILE_SIZE("MMC: Помилка читання файлу, запитаний розмір на читання більше фактичного розміру файлу", 0x03D1),
    MMC_TRYING_TO_HOLD_OPEN_FILE_OPERATION("MMC: Спроба провести операції з відкритим файлом", 0x04D1),
    MMC_FILE_WRITE_ERROR_NOT_FOUND_FILE_OR_DIRECTORY("MMC: помилка відкриття файлу - не знайдений файл або директорію", 0x05D1),
    MMC_ACCESSMODE("MMC: AccessMode", 0x06D1),
    MMC_RERRSD_PATHLENGTH("MMC: rErrSD_PathLength", 0x07D1),
    MMC_FAILED_TO_OPEN_CREATE_FILE("MMC: Помилка відкриття / створення файлу", 0x08D1),
    MMC_ERROR_WRITING_FILE("MMC: Помилка запису файлу", 0x09D1),
    FSTROKA_OPTIONS("ФСтрока: Параметри", 0x00F0),
    FSTROKA_DATA_TYPE("ФСтрока: Тип даних", 0x01F0),
    FSTROKA_FONT_SELECTION("ФСтрока: Вибір шрифту", 0x02F0),
    FSTROKA_ALIGNMENT("ФСтрока: Вирівнювання", 0x03F0),
    FSTROKA_POSITIONS_IN_A_ROW("ФСтрока: Позиції в рядку", 0x04F0),
    DBF_FILE_FORMAT_IS_INCORRECT("DBF: Формат файлу невірний", 0x05F0),
    DBF_MAXIMUM_NUMBER_OF_FIELDS_MORE("DBF: Кількість полів більше максимального", 0x06F0),
    DBF_APPEAL_TO_NONEXISTENT_RECORDS("DBF: Звернення до неіснуючої записи", 0x07F0),
    DBF_INVALID_FIELD_TYPE("DBF: Неприпустимий тип поля", 0x08F0),
    DBF_INVALID_FIELD_IN("DBF: Неправильне значення в поле", 0x09F0),
    DBF_THE_VALUE_IS_NOT_DEFINED_SEARCH("DBF: Значення для пошуку не задано", 0x0AF0),
    DBF_DO_NOT_OPEN("DBF: do not open", 0x0BF0),
    LINE_INPUT_SIZE_OUTSIDE("Рядок введення: Величина за межами", 0x00F1),
    LINE_INPUT_YOU_CAN_NOT_CHANGE("Рядок введення: Не можна змінювати", 0x01F1),
    USER_CANCELED("Скасовано користувачем", 0x02F1),
    UNKNOWN("Невідомий результат", -1),
    ERROR_PARSE_DATA_RECEIVING("Помилка розбору данних", -2),
    ERROR_SERIAL_PORT_CONNECT("Не вдалося з'єднатися з пристроєм", -3),
    ERROR_COMMAND_EXECUTE("Помилка виконання команди", -4),
    OPERATION_REJECTED("Операція відхилена", -5),
    MACHINE_SERIAL_NUMBER_NOT_MATCH("Не співпадає заводський номер апарату", -6),
    SERVICE_MESSAGE("Службове повідомлення", -7),
    ERROR_SERIAL_PORT_CONNECT_BY_TIME("Не отримано відповіді протягом 3 сек. ", -8),
    ERROR_VERSIONS_NOT_EQUALS("Версія застарівша, перезавантажте браузер", -9),
    ERROR_SHIFT_IS_NOT_OPEN("Зміна не відкрита", -10),
    ERROR_SHIFT_IS_OPEN("Зміна вже відкрита", -11),
    ERROR_PARSE_VERSION_DATE("Помилка визначення версії ПЗ", -12),
    ERROR_EW_PRINT_SOFT_VERSION("Друк не доступний (необхідне оновлення ПЗ)", -13),
    ERROR_DATA_PARSING("Помилка розбору данних", -14);

    private static int invalidFormatFieldNumber = -1;
    private static int invalidValueFieldNumber = -1;
    private String message;
    private int code;

    private Result(String message, int code) {
        this.message = message;
        this.code = code;
    }

    static public Result getByCode(int pCode) {
        for (Result type : Result.values()) {
            if (type.getCode() == pCode) {
                return type;
            }
        }
        String hex = String.format("%04X", (0xFFFF & pCode));
        if (hex.substring(2).equals("03")) {
            invalidFormatFieldNumber = Integer.parseInt(hex.substring(0, 2), 16);
            return INCORRECT_FIELD_FORMAT;
        }
        if (hex.substring(2).equals("04")) {
            invalidValueFieldNumber = Integer.parseInt(hex.substring(0, 2), 16);
            return INCORRECT_FIELD_VALUE;
        }
        return Result.getByCode(UNKNOWN.getCode());
    }

    public String getMessage() {
        return message;
    }

    public int getCode() {
        return code;
    }


}
