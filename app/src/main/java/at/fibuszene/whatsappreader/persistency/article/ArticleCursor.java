package at.fibuszene.whatsappreader.persistency.article;

import java.util.Date;

import android.database.Cursor;

import at.fibuszene.whatsappreader.persistency.base.AbstractCursor;

/**
 * Cursor wrapper for the {@code article} table.
 */
public class ArticleCursor extends AbstractCursor {
    public ArticleCursor(Cursor cursor) {
        super(cursor);
    }

    /**
     * Get the {@code sender} value.
     * Can be {@code null}.
     */
    public String getSender() {
        Integer index = getCachedColumnIndexOrThrow(ArticleColumns.SENDER);
        return getString(index);
    }

    /**
     * Get the {@code ticker} value.
     * Can be {@code null}.
     */
    public String getTicker() {
        Integer index = getCachedColumnIndexOrThrow(ArticleColumns.TICKER);
        return getString(index);
    }

    /**
     * Get the {@code message} value.
     * Can be {@code null}.
     */
    public String getMessage() {
        Integer index = getCachedColumnIndexOrThrow(ArticleColumns.MESSAGE);
        return getString(index);
    }
}
