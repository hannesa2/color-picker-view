package com.github.danielnilsson9.colorpickerview.demo;

import com.github.danielnilsson9.colorpickerview.view.ColorPanelView;
import com.github.danielnilsson9.colorpickerview.view.ColorPickerView;
import com.github.danielnilsson9.colorpickerview.view.ColorPickerView.OnColorChangedListener;

import android.app.Activity;
import android.content.SharedPreferences;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

public class ColorPickerActivity extends Activity implements OnColorChangedListener, View.OnClickListener {

    private ColorPickerView mColorPickerView;
    private ColorPanelView mNewColorPanelView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFormat(PixelFormat.RGBA_8888);

        setContentView(R.layout.activity_color_picker);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        int initialColor = prefs.getInt("color_3", 0xFF000000);

        mColorPickerView = findViewById(com.github.danielnilsson9.colorpickerview.R.id.colorpickerview__color_picker_view);
        ColorPanelView mOldColorPanelView = findViewById(com.github.danielnilsson9.colorpickerview.R.id.colorpickerview__color_panel_old);
        mNewColorPanelView = findViewById(com.github.danielnilsson9.colorpickerview.R.id.colorpickerview__color_panel_new);

        Button mOkButton = findViewById(R.id.okButton);
        Button mCancelButton = findViewById(R.id.cancelButton);


        ((LinearLayout) mOldColorPanelView.getParent()).setPadding(
                mColorPickerView.getPaddingLeft(), 0,
                mColorPickerView.getPaddingRight(), 0);


        mColorPickerView.setOnColorChangedListener(this);
        mColorPickerView.setColor(initialColor, true);
        mOldColorPanelView.setColor(initialColor);

        mOkButton.setOnClickListener(this);
        mCancelButton.setOnClickListener(this);

    }

    @Override
    public void onColorChanged(int newColor) {
        mNewColorPanelView.setColor(mColorPickerView.getColor());
    }

    @Override
    public void onClick(View v) {

        if (v.getId() == R.id.okButton) {
            SharedPreferences.Editor edit = PreferenceManager.getDefaultSharedPreferences(this).edit();
            edit.putInt("color_3", mColorPickerView.getColor());
            edit.apply();
            finish();
        } else if (v.getId() == R.id.cancelButton) {
            finish();
        }

    }


}
