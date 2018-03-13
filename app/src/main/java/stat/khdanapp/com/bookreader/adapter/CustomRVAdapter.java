package stat.khdanapp.com.bookreader.adapter;


import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import stat.khdanapp.com.bookreader.R;
import stat.khdanapp.com.bookreader.model.BookCardView;

public class CustomRVAdapter extends RecyclerView.Adapter<CustomRVAdapter.CardViewHolder> {

    private List<BookCardView> mListCard = new ArrayList<>();
    CustomRVAdapterListener adapterListener;

    public CustomRVAdapter(List<BookCardView> listCard, CustomRVAdapterListener contListener){
        mListCard = listCard;
        adapterListener = contListener;
    }

    @Override
    public CardViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_card_view, parent, false);
        return new CardViewHolder(v);
    }
    @Override
    public void onBindViewHolder(CardViewHolder holder, int position) {
        BookCardView currentCustModelCard = mListCard.get(position);
        holder.bookTitle.setText(currentCustModelCard.getBookTitle());
        holder.bookAuthor.setText(currentCustModelCard.getAuthor());
        holder.imgView.setImageResource(currentCustModelCard.getImageId());
        holder.imageId = currentCustModelCard.getImageId();
        holder.pos = position;
    }
    @Override
    public int getItemCount() {
        return mListCard.size();
    }

    public class CardViewHolder extends RecyclerView.ViewHolder {
        public ImageView imgView;
        public TextView bookTitle;
        public TextView bookAuthor;

        public int imageId; // bad solution
        public int pos; //bad solution


        public CardViewHolder(View view) {
            super(view);
            imgView = (ImageView) view.findViewById(R.id.image_card_view);
            bookTitle = (TextView) view.findViewById(R.id.title_card_view);
            bookAuthor = (TextView) view.findViewById(R.id.author_card_view);

            imgView.setOnClickListener (new View.OnClickListener () {
                @Override
                public void onClick(View v) {
                    adapterListener.detailedActivityOpen(imageId,bookTitle.getText().toString());
                }
            });

            imgView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    adapterListener.showDialogOnLongTap(pos);
                    return true;
                }
            });
        }
    }

     public interface CustomRVAdapterListener{
         void detailedActivityOpen(int imageId, String bookTitle);
         void showDialogOnLongTap(int position);
    }


}
