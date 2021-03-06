package bumbums.puzzlepiece.ui.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import bumbums.puzzlepiece.R;
import bumbums.puzzlepiece.model.Friend;
import bumbums.puzzlepiece.task.FirebaseTasks;
import bumbums.puzzlepiece.util.Utils;
import bumbums.puzzlepiece.model.Puzzle;
import bumbums.puzzlepiece.ui.PuzzleLogFragment;
import io.realm.OrderedRealmCollection;
import io.realm.Realm;
import io.realm.RealmRecyclerViewAdapter;

/**
 * Created by 한승범 on 2017-02-10.
 */

public class LogRecyclerViewAdpater extends
        RealmRecyclerViewAdapter<Puzzle, LogRecyclerViewAdpater.MyViewHolder> {




    private final PuzzleLogFragment puzzleLogFragment;
    public static final String EXTRA_ID = "id";

    public LogRecyclerViewAdpater(PuzzleLogFragment puzzleLogFragment, OrderedRealmCollection<Puzzle> data) {
        super(puzzleLogFragment.getContext(), data, true);
        this.puzzleLogFragment = puzzleLogFragment;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.row_puzzle_log, parent, false);
        //itemView.setMinimumWidth(parent.getMeasuredWidth()/2);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        Puzzle obj = getData().get(position);
        holder.data = obj;
        Realm realm = Realm.getDefaultInstance();
        Friend friend = realm.where(Friend.class).equalTo(Friend.FRIEND_ID,obj.getFriendId()).findFirst();
        FirebaseTasks.loadFriendPhoto(puzzleLogFragment.getContext(),friend,holder.photo);
       // holder.colorView.setBackgroundResource(Utils.colors[((int)obj.getFriendId())%15]);

        holder.name.setText(obj.getFriendName());
        holder.time.setText(Utils.dateToCurrentFormat(obj.getDate()));
        holder.text.setText(obj.getText());

    }

    class MyViewHolder extends RecyclerView.ViewHolder implements
            View.OnClickListener,
            View.OnLongClickListener{

        public TextView name;
        public TextView time;
        public TextView text;

        public ImageView photo;
        public Puzzle data;
        //public View colorView;
        public MyViewHolder(View view) {
            super(view);
           // colorView = (View)view.findViewById(R.id.color_view);
            photo = (ImageView)view.findViewById(R.id.iv_friend_photo);
            name = (TextView)view.findViewById(R.id.tv_row_log_name);
            time = (TextView)view.findViewById(R.id.tv_row_log_time);
            text = (TextView)view.findViewById(R.id.tv_row_log_text);
           
            view.setOnClickListener(this);
            view.setOnLongClickListener(this);
        }

        @Override
        public void onClick(View v) {

               /*  Intent intent = new Intent(tabFriendsFragment.getContext(),FriendDetailActivity.class);
                    intent.putExtra(EXTRA_ID,data.getId());
                    tabFriendsFragment.getContext().startActivity(intent);*/
            // Log.d("###","click");
        }

        @Override
        public boolean onLongClick(View v) {

            return true;
        }



       /* @Override
        public void onClick(View v) {
            for(int i=0;i<data.getDogs().size();i++){
                Log.d("###",data.getDogs().get(i).getName());
            }
        }*/

    }
}
