package com.pangpangzhu.pullrefreshlibrary;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.LinkedList;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.example.pullrefreshlibrary.R;
import com.pangpangzhu.pullrefreshlibrary.PullToRefreshBase.OnRefreshListener;



public class MainActivity extends Activity {

    private ListView mListView;
    private PullToRefreshListView mPullListView;
    private ArrayAdapter<String> mAdapter;
    private LinkedList<String> mListItems;
    private boolean mIsStart = true;
    private int mCurIndex = 0;
    private static final int mLoadDataCount = 100;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
               
        setContentView(R.layout.fragment_main);        
        mPullListView = (PullToRefreshListView) findViewById(R.id.pull_to_refresh_listview);
        
        mPullListView.setPullLoadEnabled(false);
        mPullListView.setScrollLoadEnabled(true);
        
        mCurIndex = mLoadDataCount;
        mListItems = new LinkedList<String>();
        mListItems.addAll(Arrays.asList(mStrings).subList(0, mCurIndex));
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, mListItems);
        
        mPullListView.setAdapter(mAdapter);
        mPullListView.setOnItemClickListener(new OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View view, int position, long id) {
                String text = mListItems.get(position) + ", index = " + (position + 1);
                Toast.makeText(MainActivity.this, text, Toast.LENGTH_SHORT).show();
            }
        });
        
        mPullListView.setOnRefreshListener(new OnRefreshListener<ListView>() {
            @Override
            public void onPullDownToRefresh(PullToRefreshBase<ListView> refreshView) {
                mIsStart = true;
                new GetDataTask().execute();
            }

            @Override
            public void onPullUpToRefresh(PullToRefreshBase<ListView> refreshView) {
                mIsStart = false;
                new GetDataTask().execute();
            }
        });
        
        mPullListView.doPullRefreshing(true, 500);
    }
    
    private class GetDataTask extends AsyncTask<Void, Void, String[]> {

        @Override
        protected String[] doInBackground(Void... params) {
            // Simulates a background job.
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
            }
            return mStrings;
        }

        @Override
        protected void onPostExecute(String[] result) {
            boolean hasMoreData = true;
            if (mIsStart) {
                mListItems.addFirst("Added after refresh...");
            } else {
                int start = mCurIndex;
                int end = mCurIndex + mLoadDataCount;
                if (end >= mStrings.length) {
                    end = mStrings.length;
                    hasMoreData = false;
                }
                
                for (int i = start; i < end; ++i) {
                    mListItems.add(mStrings[i]);
                }
                
                mCurIndex = end;
            }
            
            mAdapter.notifyDataSetChanged();
            mPullListView.onPullDownRefreshComplete();
            mPullListView.onPullUpRefreshComplete();
            mPullListView.setHasMoreData(hasMoreData);

            super.onPostExecute(result);
        }
    }
    



    
    public static final String[] mStrings = {
        "Abbaye de Belloc", "Abbaye du Mont des Cats", "Abertam", "Abondance", "Ackawi",
        "Acorn", "Adelost", "Affidelice au Chablis", "Afuega'l Pitu", "Airag", "Airedale",
        "Aisy Cendre", "Allgauer Emmentaler", "Alverca", "Ambert", "American Cheese",
        "Ami du Chambertin", "Anejo Enchilado", "Anneau du Vic-Bilh", "Anthoriro", "Appenzell",
        "Aragon", "Ardi Gasna", "Ardrahan", "Armenian String", "Aromes au Gene de Marc",
        "Asadero", "Asiago", "Aubisque Pyrenees", "Autun", "Avaxtskyr", "Baby Swiss",
        "Babybel", "Baguette Laonnaise", "Bakers", "Baladi", "Balaton", "Bandal", "Banon",
        "Barry's Bay Cheddar", "Basing", "Basket Cheese", "Bath Cheese", "Bavarian Bergkase",
        "Baylough", "Beaufort", "Beauvoorde", "Beenleigh Blue", "Beer Cheese", "Bel Paese",
        "Bergader", "Bergere Bleue", "Berkswell", "Beyaz Peynir", "Bierkase", "Bishop Kennedy",
        "Blarney", "Bleu d'Auvergne", "Bleu de Gex", "Bleu de Laqueuille",
        "Bleu de Septmoncel", "Bleu Des Causses", "Blue", "Blue Castello", "Blue Rathgore",
        "Blue Vein (Australian)", "Blue Vein Cheeses", "Bocconcini", "Bocconcini (Australian)",
        "Boeren Leidenkaas", "Bonchester", "Bosworth", "Bougon", "Boule Du Roves",
        "Boulette d'Avesnes", "Boursault", "Boursin", "Bouyssou", "Bra", "Braudostur",
        "Breakfast Cheese", "Brebis du Lavort", "Brebis du Lochois", "Brebis du Puyfaucon",
        "Bresse Bleu", "Brick", "Brie", "Brie de Meaux", "Brie de Melun", "Brillat-Savarin",
        "Brin", "Brin d' Amour", "Brin d'Amour", "Brinza (Burduf Brinza)",
        "Briquette de Brebis", "Briquette du Forez", "Broccio", "Broccio Demi-Affine",
        "Brousse du Rove", "Bruder Basil", "Brusselae Kaas (Fromage de Bruxelles)", "Bryndza",
        "Buchette d'Anjou", "Buffalo", "Burgos", "Butte", "Butterkase", "Button (Innes)",
        "Buxton Blue", "Cabecou", "Caboc", "Cabrales", "Cachaille", "Caciocavallo", "Caciotta",
        "Caerphilly", "Cairnsmore", "Calenzana", "Cambazola", "Camembert de Normandie",
        "Canadian Cheddar", "Canestrato", "Cantal", "Caprice des Dieux", "Capricorn Goat",
        "Capriole Banon", "Carre de l'Est", "Casciotta di Urbino", "Cashel Blue", "Castellano",
        "Castelleno", "Castelmagno", "Castelo Branco", "Castigliano", "Cathelain",
        "Celtic Promise", "Cendre d'Olivet", "Cerney", "Chabichou", "Chabichou du Poitou",
        "Chabis de Gatine", "Chaource", "Charolais", "Chaumes", "Cheddar",
        "Cheddar Clothbound", "Cheshire", "Chevres", "Chevrotin des Aravis", "Chontaleno",
        "Civray", "Coeur de Camembert au Calvados", "Coeur de Chevre", "Colby", "Cold Pack",
        "Comte", "Coolea", "Cooleney", "Coquetdale", "Corleggy", "Cornish Pepper",
        "Cotherstone", "Cotija", "Cottage Cheese", "Cottage Cheese (Australian)",
        "Cougar Gold", "Coulommiers", "Coverdale", "Crayeux de Roncq", "Cream Cheese",
        "Cream Havarti", "Crema Agria", "Crema Mexicana", "Creme Fraiche", "Crescenza",
        "Croghan", "Crottin de Chavignol", "Crottin du Chavignol", "Crowdie", "Crowley",
        "Cuajada", "Curd", "Cure Nantais", "Curworthy", "Cwmtawe Pecorino"
    };
}
