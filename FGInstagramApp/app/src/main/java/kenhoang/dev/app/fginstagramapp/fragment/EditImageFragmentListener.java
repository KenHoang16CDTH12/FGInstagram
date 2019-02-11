package kenhoang.dev.app.fginstagramapp.fragment;

public interface EditImageFragmentListener {

    void onBrightnessChanged(int brightness);
    void onStaturationChanged(float staturation);
    void onConstrantChanged(float constraint);
    void onEditStarted();
    void onEditCompleted();
}
