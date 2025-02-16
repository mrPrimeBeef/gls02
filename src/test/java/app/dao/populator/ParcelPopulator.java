package app.dao.populator;

import app.dao.ParcelDao;
import app.entities.Parcel;
import app.enums.Status;


public class ParcelPopulator {
    public static Parcel[] populate(ParcelDao parcelDao) {
        Parcel p1 = new Parcel("5678", "Rolf", "Peter", Status.IN_TRANSIT);
        p1 = parcelDao.createParcel(p1);

        Parcel p2 = new Parcel("1234", "Peter", "julemanden", Status.IN_TRANSIT);

        p2 = parcelDao.createParcel(p2);
        return new Parcel[]{p1, p2};
    }
}