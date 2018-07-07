package ir.irancell.application;

import android.graphics.Typeface;
import android.support.annotation.NonNull;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.mikepenz.fastadapter.FastAdapter;
import com.mikepenz.fastadapter.items.AbstractItem;

import java.util.List;

public class SimpleItem extends AbstractItem<SimpleItem, SimpleItem.ViewHolder> {
    public String name;
    public String description;
    public Typeface tf;

    public SimpleItem(String name, String description, Typeface tf) {
        this.name = name;
        this.description = description;
        this.tf = tf;
    }

    //The unique ID for this type of item
    @Override
    public int getType() {
        return 0;
    }

    //The layout to be used for this type of item
    @Override
    public int getLayoutRes() {
        return R.layout.simple_item;
    }

    @Override
    public ViewHolder getViewHolder(@NonNull View v) {
        return new ViewHolder(v);
    }

    /**
     * our ViewHolder
     */
    protected static class ViewHolder extends FastAdapter.ViewHolder<SimpleItem> {

        TextView name;
        TextView description;
        View view;

        public ViewHolder(View view) {
            super(view);
            name = view.findViewById(R.id.key_textview);
            description = view.findViewById(R.id.value_textview);
            this.view = view;
        }

        @Override
        public void bindView(SimpleItem item, List<Object> payloads) {
            name.setText(item.name);
//            RelativeLayout rl = (RelativeLayout) description.getParent();
            RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) description.getLayoutParams();
            if (item.description.contains("\n")) {
                params.addRule(RelativeLayout.LEFT_OF, 0);
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT);
                description.setText(("\n" + item.description).replace("\n", "\n  "));
            } else {
                params.addRule(RelativeLayout.LEFT_OF, name.getId());
                params.addRule(RelativeLayout.ALIGN_PARENT_RIGHT, 0);
                description.setText(item.description);
            }

            name.setTypeface(item.tf);
            description.setTypeface(item.tf);
        }

        @Override
        public void unbindView(SimpleItem item) {
            name.setText(null);
            description.setText(null);
        }
    }

}