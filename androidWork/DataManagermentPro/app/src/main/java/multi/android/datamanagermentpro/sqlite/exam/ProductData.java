package multi.android.datamanagermentpro.sqlite.exam;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductData implements Parcelable {
    int _id;
    String name;
    int price;
    int su;
    int totPrice;

    public ProductData(String name, int price) {
        this.name = name;
        this.price = price;
    }

    public ProductData(String name, int price, int su) {
        this.name = name;
        this.price = price;
        this.su = su;
    }

    public ProductData(int _id, String name, int price, int su, int totPrice) {
        this._id = _id;
        this.name = name;
        this.price = price;
        this.su = su;
        this.totPrice = totPrice;
    }

    @Override
    public String toString() {
        return _id + "," + name + "," + price + "," + su + "," + totPrice;
    }

     protected ProductData(Parcel in) {
         this._id = in.readInt();
         this.name = in.readString();
         this.price = in.readInt();
     }

    public static final Creator<ProductData> CREATOR = new Creator<ProductData>() {
        @Override
        public ProductData createFromParcel(Parcel in) {
            return new ProductData(in);
        }

        @Override
        public ProductData[] newArray(int size) {
            return new ProductData[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_id);
        dest.writeString(name);
        dest.writeInt(price);
    }
}
