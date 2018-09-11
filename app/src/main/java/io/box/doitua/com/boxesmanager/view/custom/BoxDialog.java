package io.box.doitua.com.boxesmanager.view.custom;

import android.app.Dialog;
import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import io.box.doitua.com.boxesmanager.R;
import io.box.doitua.com.boxesmanager.api.models.Box;
import io.box.doitua.com.boxesmanager.databinding.NewBoxItemBinding;
import io.box.doitua.com.boxesmanager.interfaces.OnBoxAddListener;

public class BoxDialog extends Dialog {

    private OnBoxAddListener listener;
    private int size;

    public BoxDialog(@NonNull Context context, OnBoxAddListener listener) {
        super(context);
        this.listener = listener;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        NewBoxItemBinding binding = DataBindingUtil.inflate(LayoutInflater.from(getContext()), R.layout.new_box_item, null, false);
        setContentView(binding.getRoot());

        binding.boxParams.sizesGroup.setOnCheckedChangeListener((group, checkedId) -> {
            switch (checkedId) {
                case R.id.small: {
                    size = Box.SMALL;
                    binding.boxParams.firstColor.setBackgroundColor(Box.SMALL_BOX_COLORS[0]);
                    binding.boxParams.firstColor.setText(Box.SMALL_COLOR_LABELS[0]);

                    binding.boxParams.secondColor.setBackgroundColor(Box.SMALL_BOX_COLORS[1]);
                    binding.boxParams.secondColor.setText(Box.SMALL_COLOR_LABELS[1]);

                    binding.boxParams.thirdColor.setBackgroundColor(Box.SMALL_BOX_COLORS[2]);
                    binding.boxParams.thirdColor.setText(Box.SMALL_COLOR_LABELS[2]);

                    binding.boxParams.colorsGroup.findViewById(R.id.fourth_color).setVisibility(View.INVISIBLE);
                    break;
                }

                case R.id.medium: {
                    size = Box.MEDIUM;
                    binding.boxParams.firstColor.setBackgroundColor(Box.MEDIUM_BOX_COLORS[0]);
                    binding.boxParams.firstColor.setText(Box.MEDIUM_COLOR_LABELS[0]);

                    binding.boxParams.secondColor.setBackgroundColor(Box.MEDIUM_BOX_COLORS[1]);
                    binding.boxParams.secondColor.setText(Box.MEDIUM_COLOR_LABELS[1]);

                    binding.boxParams.thirdColor.setBackgroundColor(Box.MEDIUM_BOX_COLORS[2]);
                    binding.boxParams.thirdColor.setText(Box.MEDIUM_COLOR_LABELS[2]);

                    binding.boxParams.fourthColor.setBackgroundColor(Box.MEDIUM_BOX_COLORS[3]);
                    binding.boxParams.fourthColor.setText(Box.MEDIUM_COLOR_LABELS[3]);

                    binding.boxParams.colorsGroup.findViewById(R.id.fourth_color).setVisibility(View.VISIBLE);
                    break;
                }

                case R.id.large: {
                    size = Box.LARGE;
                    binding.boxParams.firstColor.setBackgroundColor(Box.LARGE_BOX_COLORS[0]);
                    binding.boxParams.firstColor.setText(Box.LARGE_COLOR_LABELS[0]);

                    binding.boxParams.secondColor.setBackgroundColor(Box.LARGE_BOX_COLORS[1]);
                    binding.boxParams.secondColor.setText(Box.LARGE_COLOR_LABELS[1]);

                    binding.boxParams.thirdColor.setBackgroundColor(Box.LARGE_BOX_COLORS[2]);
                    binding.boxParams.thirdColor.setText(Box.LARGE_COLOR_LABELS[2]);

                    binding.boxParams.colorsGroup.findViewById(R.id.fourth_color).setVisibility(View.INVISIBLE);
                    break;
                }
            }
        });

        binding.boxParams.small.setChecked(true);
        binding.boxParams.firstColor.setChecked(true);
        binding.okBtn.setOnClickListener(v -> {
                    Box box = new Box(size);
                    RadioGroup group = binding.boxParams.colorsGroup;
                    RadioButton btn = group.findViewById(group.getCheckedRadioButtonId());
                    String color = btn.getText().toString();
                    box.setColor(color);
                    box.setNamed(binding.checkName.isChecked());
                    listener.addBox(box);
                    this.dismiss();
                }
        );
    }

}
