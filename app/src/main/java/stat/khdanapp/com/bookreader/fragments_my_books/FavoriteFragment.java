package stat.khdanapp.com.bookreader.fragments_my_books;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import stat.khdanapp.com.bookreader.R;
import stat.khdanapp.com.bookreader.adapter.CustomRVAdapter;
import stat.khdanapp.com.bookreader.controler.DataManager;
import stat.khdanapp.com.bookreader.fragments_book_catalog.RecommendedFragment;


public class FavoriteFragment extends RecommendedFragment {

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
        mCustModelCardsList.addAll(DataManager.readSharedPref(getActivity()));
//        int imgID;
//        for (int i = 1; i <= 9; i++) {
//            if (i%2 == 0){
//                imgID = R.drawable.a3;
//            }else{
//                imgID = R.drawable.a4;
//            }
//            int favoriteIdBuf =  R.drawable.ic_favorite_border_black_24dp;
//            String bookTitle = "Название книги " + i;
//            if (DataManager.containsSharedPref(getActivity(),bookTitle)) favoriteIdBuf = R.drawable.ic_favorite_black_24dp;
//            mCustModelCardsList.add(new BookCardView(imgID,bookTitle, "Автор " + i,favoriteIdBuf));
//        }

        customRVAdapter = new CustomRVAdapter(mCustModelCardsList,this);
        rv.setAdapter(customRVAdapter);

        return rootView;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == Activity.RESULT_OK) {
//            switch (requestCode) {
//                case REQUEST_WEIGHT:
//                    int weight = data.getIntExtra(CustomDialogFragment.TAG_WEIGHT_SELECTED, -1)
//                    //используем полученные результаты
//                    //...
//                    break;
//                case REQUEST_ANOTHER_ONE:
//                    //...
//                    break;
//                //обработка других requestCode
//            }
//            //updateUI();
            DataManager.removeSharedPref(getActivity(),mCustModelCardsList.get(onOrderToDelete).getBookTitle());
        }
        super.onActivityResult(requestCode, resultCode, data);
    }


    @Override
    public void favoriteButtonClickedOnItem(int position, int tag) {

        if(tag == R.drawable.ic_favorite_border_black_24dp) {
            mCustModelCardsList.get(position).setFavoriteId(R.drawable.ic_favorite_black_24dp);
            DataManager.writeSharedPref(getActivity(),mCustModelCardsList.get(position).getAuthor(),mCustModelCardsList.get(position).getBookTitle());
            //DataManager.readSharedPref(getActivity());
        }
        else {
            mCustModelCardsList.get(position).setFavoriteId(R.drawable.ic_favorite_border_black_24dp);
            DataManager.removeSharedPref(getActivity(),mCustModelCardsList.get(position).getBookTitle());
            mCustModelCardsList.remove(position);
        }
        customRVAdapter.notifyDataSetChanged();
    }


    public static FavoriteFragment newInstance() {
        //RecommendedFragment fragment = new RecommendedFragment();
        //Bundle args = new Bundle();
        //args.putString(ARG_PARAM1, param1);
        //args.putString(ARG_PARAM2, param2);
        //fragment.setArguments(args);
        return new FavoriteFragment();
    }

}
