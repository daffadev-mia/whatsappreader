package at.fibuszene.whatsappreader.persistency.article;

import java.util.Date;

import android.content.ContentResolver;
import android.database.Cursor;
import android.net.Uri;

import at.fibuszene.whatsappreader.persistency.base.AbstractSelection;

/**
 * Selection for the {@code article} table.
 */
public class ArticleSelection extends AbstractSelection<ArticleSelection> {
    @Override
    public Uri uri() {
        return ArticleColumns.CONTENT_URI;
    }

    /**
     * Query the given content resolver using this selection.
     *
     * @param contentResolver The content resolver to query.
     * @param projection A list of which columns to return. Passing null will return all columns, which is inefficient.
     * @param sortOrder How to order the rows, formatted as an SQL ORDER BY clause (excluding the ORDER BY itself). Passing null will use the default sort
     *            order, which may be unordered.
     * @return A {@code ArticleCursor} object, which is positioned before the first entry, or null.
     */
    public ArticleCursor query(ContentResolver contentResolver, String[] projection, String sortOrder) {
        Cursor cursor = contentResolver.query(uri(), projection, sel(), args(), sortOrder);
        if (cursor == null) return null;
        return new ArticleCursor(cursor);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null}.
     */
    public ArticleCursor query(ContentResolver contentResolver, String[] projection) {
        return query(contentResolver, projection, null);
    }

    /**
     * Equivalent of calling {@code query(contentResolver, projection, null, null}.
     */
    public ArticleCursor query(ContentResolver contentResolver) {
        return query(contentResolver, null, null);
    }


    public ArticleSelection id(long... value) {
        addEquals(ArticleColumns._ID, toObjectArray(value));
        return this;
    }


    public ArticleSelection sender(String... value) {
        addEquals(ArticleColumns.SENDER, value);
        return this;
    }

    public ArticleSelection senderNot(String... value) {
        addNotEquals(ArticleColumns.SENDER, value);
        return this;
    }

    public ArticleSelection senderLike(String... value) {
        addLike(ArticleColumns.SENDER, value);
        return this;
    }

    public ArticleSelection ticker(String... value) {
        addEquals(ArticleColumns.TICKER, value);
        return this;
    }

    public ArticleSelection tickerNot(String... value) {
        addNotEquals(ArticleColumns.TICKER, value);
        return this;
    }

    public ArticleSelection tickerLike(String... value) {
        addLike(ArticleColumns.TICKER, value);
        return this;
    }

    public ArticleSelection message(String... value) {
        addEquals(ArticleColumns.MESSAGE, value);
        return this;
    }

    public ArticleSelection messageNot(String... value) {
        addNotEquals(ArticleColumns.MESSAGE, value);
        return this;
    }

    public ArticleSelection messageLike(String... value) {
        addLike(ArticleColumns.MESSAGE, value);
        return this;
    }
}
