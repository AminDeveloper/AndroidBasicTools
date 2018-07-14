package ir.mo.amin.hosseini.formtools.CustomListItems;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.ArrayRes;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import ir.mo.amin.hosseini.commontools.Utils.Utils;
import ir.mo.amin.hosseini.formtools.CustomListItems.Listeners.CustomSelectItemListener;
import ir.mo.amin.hosseini.formtools.CustomListItems.Model.ProfileData;
import ir.mo.amin.hosseini.formtools.R;


/**
 *
 */

public class SpinnerCustomListItem extends CustomListItem {
    private Spinner spinner;
    private TextView titleTextView;

    private String title;
    @ArrayRes
    int textArrayResId;
    ViewDataUpdater spinnerDataUpdater;
    private CustomSelectItemListener customSelectItemListener = new CustomSelectItemListener() {
        @Override
        protected void setData(AdapterView<?> parent, View view, int position, long id) {
            spinnerDataUpdater.setData(position, getData());
            if (position == 0)
                errorRes = 1;
            else
                errorRes = 0;
        }
    };

    public SpinnerCustomListItem(String title, @ArrayRes int textArrayResId, ViewDataUpdater spinnerDataUpdater) {
        super(R.layout.complete_profile_spinner_item);
        customSelectItemListener.setCustomListItem(this);
        this.textArrayResId = textArrayResId;
        this.title = title;
        this.spinnerDataUpdater = spinnerDataUpdater;
    }

//    @Override
//    public int getViewType() {
//        return 2;
//    }

    @Override
    public void initializeView(View view) {
        titleTextView = (TextView) view.findViewById(R.id.title);
        titleTextView.setText(title);
        spinner = (Spinner) view.findViewById(R.id.spinner);

        spinner.setOnItemSelectedListener(customSelectItemListener);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(spinner.getContext(), textArrayResId, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(adapter);
        spinner.setDropDownWidth((int) (getScreenWidthSize(spinner.getContext()) - Utils.pxFromDP(10, spinner.getContext())));
        resetData(getData());
    }


    @Override
    public EditText requestFocus() {
        return null;
    }

    @Override
    public void resetData(ProfileData profileData) {
        Integer item = (Integer) spinnerDataUpdater.getData(profileData);
        if (item == null)
            item = 0;
        if (spinner != null)
            spinner.setSelection(item);

    }

    public int getScreenWidthSize(Context context) {
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();

        Point size = new Point();
        display.getSize(size);
        int width = size.x;
        int height = size.y;

        return width;
    }
}
