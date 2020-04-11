package multi.android.datamanagermentpro.sqlite.exam;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.appcompat.app.AppCompatActivity;
import androidx.collection.SimpleArrayMap;

import java.util.ArrayList;
import java.util.HashMap;

import multi.android.datamanagermentpro.R;


public class MainActivity extends
		AppCompatActivity implements AdapterView.OnItemClickListener,OnClickListener {
	DBHandler handler;
	EditText edtName;
	EditText edtSu;
	EditText edtPrice;
	ListView listview;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		handler = DBHandler.open(this);

		findViewById(R.id.btnIns).setOnClickListener(this);
		findViewById(R.id.btnResult).setOnClickListener(this);
		findViewById(R.id.btnResult2).setOnClickListener(this);
		findViewById(R.id.btnSearch).setOnClickListener(this);
		listview = findViewById(R.id.list);
		listview.setOnItemClickListener(this);

		edtName = (EditText)findViewById(R.id.edtName);
		edtSu = (EditText)findViewById(R.id.edtSu);
		edtPrice = (EditText)findViewById(R.id.edtPrice);

	}


	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.btnIns) {
			ProductData data = new ProductData(
					edtName.getText().toString(),
					Integer.parseInt(edtPrice.getText().toString()),
					Integer.parseInt(edtSu.getText().toString()));
			handler.insert(data);
		} else if(v.getId() == R.id.btnResult) {
			ArrayList<ProductData> list = handler.selectAll();
			ArrayAdapter<ProductData> adapter = new ArrayAdapter<ProductData>(MainActivity.this,
					android.R.layout.simple_list_item_1,
					list);
			listview.setAdapter(adapter);
		} else if(v.getId() == R.id.btnResult2) {
			ArrayList<HashMap<String, String>> list = handler.selectAll2();
			SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
					list,
					android.R.layout.simple_list_item_2,
					new String[] {"name", "price"},
					new int[]{android.R.id.text1,android.R.id.text2});
			listview.setAdapter(adapter);
		} else if(v.getId() == R.id.btnSearch) {
			ProductData data = handler.search(edtName.getText().toString());
			ArrayList<HashMap<String, String>> list = new ArrayList<HashMap<String, String>>();
			HashMap<String, String> proMap = new HashMap<String, String>();
			proMap.put("name", data.name);
			proMap.put("price", Integer.toString(data.price));
			list.add(proMap);
			SimpleAdapter adapter = new SimpleAdapter(MainActivity.this,
					list,
					android.R.layout.simple_list_item_2,
					new String[] {"name", "price"},
					new int[]{android.R.id.text1,android.R.id.text2});
			listview.setAdapter(adapter);
		}
	}

	@Override
	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
		ProductData data = (ProductData)parent.getAdapter().getItem(position);
		Intent intent = new Intent(MainActivity.this, ReadActivity.class);
		intent.putExtra("data", data);
		startActivity(intent);
	}
}



















