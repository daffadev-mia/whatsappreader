package at.fibuszene.whatsappreader.adapter;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ResourceCursorAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import at.fibuszene.whatsappreader.R;
import at.fibuszene.whatsappreader.persistency.article.ArticleColumns;
import at.fibuszene.whatsappreader.persistency.article.ArticleCursor;

/**
 * Created by benedikt.
 */
public class StringListAdapter extends ResourceCursorAdapter {
    private Context context;

    public StringListAdapter(Context context, ArticleCursor cursor) {
        super(context, R.layout.message_item, cursor, false);
        this.context = context;
    }


    @Override
    public void bindView(View view, final Context context, Cursor cursor) {
        ArticleCursor cursor1 = (ArticleCursor) cursor;
        String item = cursor1.getMessage();
        TextView txtView = (TextView) view.findViewById(R.id.whatsAppMessageTextView);
        txtView.setText(item);
        txtView = (TextView) view.findViewById(R.id.whatsAppSenderTextView);
        item = cursor1.getSender();
        txtView.setText(item);
        ImageView imgView = (ImageView) view.findViewById(R.id.whatsAppRemoveButton);
        final long id = cursor1.getId();
        
        imgView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.getContentResolver().delete(ArticleColumns.CONTENT_URI, ArticleColumns._ID + " = ?", new String[]{id + ""});
            }
        });
    }


}
