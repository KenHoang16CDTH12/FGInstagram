package kenhoang.dev.app.fginstagramapp.fragment;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SeekBar;

import kenhoang.dev.app.fginstagramapp.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class EditImageFragment extends Fragment implements SeekBar.OnSeekBarChangeListener {

    private EditImageFragmentListener listener;
    SeekBar seekbar_brightness;
    SeekBar seekbar_saturation;
    SeekBar seekbar_constrant;

    public void setListener(EditImageFragmentListener listener) {
        this.listener = listener;
    }

    public EditImageFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_edit_image, container, false);

        seekbar_brightness = view.findViewById(R.id.seekbar_brightness);
        seekbar_saturation = view.findViewById(R.id.seekbar_saturation);
        seekbar_constrant = view.findViewById(R.id.seekbar_constraint);

        seekbar_brightness.setMax(200);
        seekbar_brightness.setProgress(100);

        seekbar_saturation.setMax(30);
        seekbar_saturation.setProgress(10);

        seekbar_constrant.setMax(20);
        seekbar_constrant.setProgress(0);

        seekbar_brightness.setOnSeekBarChangeListener(this);
        seekbar_saturation.setOnSeekBarChangeListener(this);
        seekbar_constrant.setOnSeekBarChangeListener(this);

        return view;
    }

    @Override
    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        if (listener != null)
        {
            if (seekBar.getId() == R.id.seekbar_brightness) {
                listener.onBrightnessChanged(progress - 100);
            } else if (seekBar.getId() == R.id.seekbar_saturation) {
                float value = .10f * progress;
                listener.onStaturationChanged(value);
            } else if (seekBar.getId() == R.id.seekbar_constraint) {
                progress += 10;
                float value = .10f * progress;
                listener.onConstrantChanged(value);
            }
        }
    }

    @Override
    public void onStartTrackingTouch(SeekBar seekBar) {
        if (listener != null)
            listener.onEditStarted();
    }

    @Override
    public void onStopTrackingTouch(SeekBar seekBar) {
        if (listener != null)
            listener.onEditCompleted();
    }

    public void resetControls() {
        seekbar_brightness.setProgress(100);
        seekbar_saturation.setProgress(10);
        seekbar_constrant.setProgress(0);
    }
}
