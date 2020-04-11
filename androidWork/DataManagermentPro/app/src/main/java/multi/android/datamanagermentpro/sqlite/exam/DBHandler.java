package multi.android.datamanagermentpro.sqlite.exam;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

public class DBHandler {
    private static DBHandler handler;
    private static ExamDBHelper helper;
    private static SQLiteDatabase db;

    public static DBHandler open(Context context) {
        if(handler == null) {
            handler = new DBHandler();
            helper = new ExamDBHelper(context);
            db = helper.getWritableDatabase();
        }
        return handler;
    }

    public void insert(ProductData data) {
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", data.name);
        contentValues.put("price", data.price);
        contentValues.put("su", data.su);
        contentValues.put("totPrice", data.price * data.su);

        db.insert("product", null, contentValues);
    }

    public ArrayList<ProductData> selectAll() {
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        int count = cursor.getCount();

        ArrayList<ProductData> productList = new ArrayList<ProductData>();
        while(cursor.moveToNext()) {
            int _id = cursor.getInt(0);
            String name = cursor.getString(1);
            int price = cursor.getInt(2);
            int su = cursor.getInt(3);
            int totPrice = cursor.getInt(4);

            ProductData data = new ProductData(_id, name, price, su, totPrice);

            productList.add(data);
        }

        return productList;
    }

    public ArrayList<HashMap<String, String>> selectAll2() {
        Cursor cursor = db.query("product", null, null, null, null, null, null);
        int count = cursor.getCount();

        ArrayList<HashMap<String, String>> productList = new ArrayList<HashMap<String, String>>();
        while(cursor.moveToNext()) {
            String name = cursor.getString(1);
            int price = cursor.getInt(2);

            HashMap<String, String> product = new HashMap<String, String>();
            product.put("name", name);
            product.put("price", Integer.toString(price));

            productList.add(product);
        }

        return productList;
    }

    public ProductData search(String pName) {
        String selection = "name like ?";
        StringBuffer sb = new StringBuffer();
        sb.append("%").append(pName).append("%");

        Cursor cursor = db.query("product", null, "name like ?", new String[] {sb.toString()},
                null, null, null);

        cursor.moveToNext();
        String name = cursor.getString(1);
        int price = cursor.getInt(2);

        ProductData data = new ProductData(name, price);
        return data;
    }
}
