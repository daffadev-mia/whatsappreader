package at.fibuszene.whatsappreader.persistency.article;

import java.util.HashSet;
import java.util.Set;

import android.net.Uri;
import android.provider.BaseColumns;

import at.fibuszene.whatsappreader.persistency.WhatsAppReaderProvider;

/**
 * Columns for the {@code article} table.
 */
public class ArticleColumns implements BaseColumns {
    public static final String TABLE_NAME = "article";
    public static final Uri CONTENT_URI = Uri.parse(WhatsAppReaderProvider.CONTENT_URI_BASE + "/" + TABLE_NAME);

    public static final String _ID = BaseColumns._ID;
    public static final String SENDER = "sender";
    public static final String TICKER = "ticker";
    public static final String MESSAGE = "message";

    public static final String DEFAULT_ORDER = TABLE_NAME + "." +_ID;

    // @formatter:off
    public static final String[] FULL_PROJECTION = new String[] {
            TABLE_NAME + "." + _ID + " AS " + BaseColumns._ID,
            TABLE_NAME + "." + SENDER,
            TABLE_NAME + "." + TICKER,
            TABLE_NAME + "." + MESSAGE
    };
    // @formatter:on

    private static final Set<String> ALL_COLUMNS = new HashSet<String>();
    static {
        ALL_COLUMNS.add(_ID);
        ALL_COLUMNS.add(SENDER);
        ALL_COLUMNS.add(TICKER);
        ALL_COLUMNS.add(MESSAGE);
    }

    public static boolean hasColumns(String[] projection) {
        if (projection == null) return true;
        for (String c : projection) {
            if (ALL_COLUMNS.contains(c)) return true;
        }
        return false;
    }
}
