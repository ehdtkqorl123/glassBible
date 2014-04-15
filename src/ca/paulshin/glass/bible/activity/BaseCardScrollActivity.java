package ca.paulshin.glass.bible.activity;

import android.view.View;
import android.widget.AdapterView;
import butterknife.InjectView;
import ca.paulshin.glass.bible.R;

import com.google.android.glass.widget.CardScrollAdapter;
import com.google.android.glass.widget.CardScrollView;

/**
 * Base activity which handles card scrolling.
 */
abstract public class BaseCardScrollActivity extends BaseProgressActivity {

    @InjectView(R.id.card_scroll)
    CardScrollView vCardScroll;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_card_scroll;
    }

    public void showContent(final CardScrollAdapter adapter, final CardSelectedListener listener) {
    	vCardScroll = (CardScrollView)findViewById(R.id.card_scroll);
    	
        vCardScroll.setAdapter(adapter);
        vCardScroll.activate();
        if (listener != null) {
            vCardScroll.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    listener.onCardSelected(adapter.getItem(position));
                }
            });
        }
        hideProgress();
    }

    public interface CardSelectedListener {
        void onCardSelected(Object item);
    }

}
