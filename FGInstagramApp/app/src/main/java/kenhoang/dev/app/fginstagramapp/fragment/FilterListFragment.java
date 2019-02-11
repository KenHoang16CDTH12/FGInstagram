package kenhoang.dev.app.fginstagramapp.fragment;


import android.graphics.Bitmap;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zomato.photofilters.FilterPack;
import com.zomato.photofilters.imageprocessors.Filter;
import com.zomato.photofilters.utils.ThumbnailItem;
import com.zomato.photofilters.utils.ThumbnailsManager;

import java.util.ArrayList;
import java.util.List;

import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kenhoang.dev.app.fginstagramapp.MainActivity;
import kenhoang.dev.app.fginstagramapp.R;
import kenhoang.dev.app.fginstagramapp.adapter.ThumbnailAdapter;
import kenhoang.dev.app.fginstagramapp.custom.SpacesItemDecoration;
import kenhoang.dev.app.fginstagramapp.utils.BitmapUtils;

/**
 * A simple {@link Fragment} subclass.
 */
public class FilterListFragment extends Fragment implements FilterListFragmentListener {

    RecyclerView recyclerThumbnail;
    ThumbnailAdapter adapter;
    List<ThumbnailItem> thumbnailItems;

    FilterListFragmentListener listener;

    public void setListener(FilterListFragmentListener listener) {
        this.listener = listener;
    }

    public FilterListFragment() {
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
        View view = inflater.inflate(R.layout.fragment_filter_list, container, false);
        thumbnailItems = new ArrayList<>();
        adapter = new ThumbnailAdapter(thumbnailItems, this, getActivity());
        recyclerThumbnail = view.findViewById(R.id.recycler_view);
        recyclerThumbnail.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        recyclerThumbnail.setItemAnimator(new DefaultItemAnimator());
        int space = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, getResources().getDisplayMetrics());
        recyclerThumbnail.addItemDecoration(new SpacesItemDecoration(space));
        recyclerThumbnail.setAdapter(adapter);
        displayThumbnail(null);
        return view;
    }

    public void displayThumbnail(final Bitmap bitmap) {
        Runnable r = new Runnable() {
            @Override
            public void run() {
                Bitmap thumbImg;
                if (bitmap == null)
                    thumbImg = BitmapUtils.getBitmapFromAssets(getActivity(), MainActivity.pictureName, 100, 100);
                else
                    thumbImg = Bitmap.createScaledBitmap(bitmap, 100, 100, false);

                if (thumbImg == null)
                    return;
                ThumbnailsManager.clearThumbs();
                thumbnailItems.clear();

                // add normal bitmap first
                ThumbnailItem thumbnailItem = new ThumbnailItem();
                thumbnailItem.image = thumbImg;
                thumbnailItem.filterName = "Normal";
                ThumbnailsManager.addThumb(thumbnailItem);

                List<Filter> filters = FilterPack.getFilterPack(getActivity());

                for (Filter filter: filters) {
                    ThumbnailItem item = new ThumbnailItem();
                    item.image = thumbImg;
                    item.filterName = filter.getName();
                    ThumbnailsManager.addThumb(item);
                }

                thumbnailItems.addAll(ThumbnailsManager.processThumbs(getActivity()));

                getActivity().runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        adapter.notifyDataSetChanged();
                    }
                });
            }
        };
        new Thread(r).start();
    }

    @Override
    public void onFilterSelected(Filter filter) {
        if (listener != null)
            listener.onFilterSelected(filter);
    }
}
