package ir.irancell.application;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.view.View;
import android.widget.TextView;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class ModemItem extends AbstractItem<ModemItem, ModemItem.ViewHolder> {
    public String name;
    public Context context;
    public String id;
    public Dialog dialog;


    public ModemItem(String name, Context context, String id, Dialog dialog) {
        this.name = name;
        this.context = context;
        this.id = id;
        this.dialog = dialog;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return 0;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.modem_selector_item;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<ModemItem> {

        TextView name;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.modem_name_tv);
        }

        @Override
        public void bindView(final ModemItem item, List<Object> payloads) {
            Typeface tf = Typeface.createFromAsset(item.context.getAssets(), "font.ttf");
            name.setText(item.name);
            name.setTypeface(tf);
            name.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    SharedPreferences.Editor editor = item.context.getSharedPreferences(Constant.MY_PREFS_NAME, MODE_PRIVATE).edit();
                    editor.putString(Constant.MODEM_ID, item.id);
                    editor.apply();
                    item.dialog.dismiss();
                }
            });
        }

        @Override
        public void unbindView(ModemItem item) {
            name.setText(null);
        }

    }

}