package stat.khdanapp.com.bookreader.fragments_book_catalog;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import stat.khdanapp.com.bookreader.R;
import stat.khdanapp.com.bookreader.adapter.CustomRVAdapter;
import stat.khdanapp.com.bookreader.controler.DataController;
import stat.khdanapp.com.bookreader.model.BookCardView;

/**
 * Created by android on 3/16/18.
 */

public class PopularFragment extends RecommendedFragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_recommended, container, false);

        rv = rootView.findViewById(R.id.mRecyclerViewRecommended);
        if (rv != null){
            rv.setHasFixedSize (true);
        }

        GridLayoutManager layoutManager = new GridLayoutManager (getContext(),2);
        //layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rv.setLayoutManager(layoutManager);

        mCustModelCardsList = new ArrayList<>();
        int imgID;
        for (int i = 101; i <= 109; i++) {
            if (i%2 == 0){
                imgID = R.drawable.a3;
            }else{
                imgID = R.drawable.a4;
            }
            int favoriteIdBuf =  R.drawable.ic_favorite_border_black_24dp;
            String bookTitle = "Название книги " + i;
            if (DataController.containsSharedPref(getActivity(),bookTitle)) favoriteIdBuf = R.drawable.ic_favorite_black_24dp;
            mCustModelCardsList.add(new BookCardView(imgID,bookTitle, "Автор " + i,favoriteIdBuf));
        }

        customRVAdapter = new CustomRVAdapter(mCustModelCardsList,this);
        rv.setAdapter(customRVAdapter);

        return rootView;
    }

    public static PopularFragment newInstance() {
        //RecommendedFragment fragment = new RecommendedFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return new PopularFragment();
    }
}
