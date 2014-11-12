package at.fibuszene.whatsappreader.persistency.article;

import java.util.Date;

import android.content.ContentResolver;
import android.net.Uri;

import at.fibuszene.whatsappreader.persistency.base.AbstractContentValues;

/**
 * Content values wrapper for the {@code article} table.
 */
public class ArticleContentValues extends AbstractContentValues {
    @Override
    public Uri uri() {
        return ArticleColumns.CONTENT_URI;
    }

    /**
     * Update row(s) using the values stored by this object and the given selection.
     *
     * @param contentResolver The content resolver to use.
     * @param where The selection to use (can be {@code null}).
     */
    public int update(ContentResolver contentResolver, ArticleSelection where) {
        return contentResolver.update(uri(), values(), where == null ? null : where.sel(), where == null ? null : where.args());
    }

    public ArticleContentValues putSender(String value) {
        mContentValues.put(ArticleColumns.SENDER, value);
        return this;
    }

    public ArticleContentValues putSenderNull() {
        mContentValues.putNull(ArticleColumns.SENDER);
        return this;
    }


    public ArticleContentValues putTicker(String value) {
        mContentValues.put(ArticleColumns.TICKER, value);
        return this;
    }

    public ArticleContentValues putTickerNull() {
        mContentValues.putNull(ArticleColumns.TICKER);
        return this;
    }


    public ArticleContentValues putMessage(String value) {
        mContentValues.put(ArticleColumns.MESSAGE, value);
        return this;
    }

    public ArticleContentValues putMessageNull() {
        mContentValues.putNull(ArticleColumns.MESSAGE);
        return this;
    }

}
