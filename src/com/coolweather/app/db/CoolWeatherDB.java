package com.coolweather.app.db;

import java.util.ArrayList;
import java.util.List;

import com.coolweather.app.model.City;
import com.coolweather.app.model.County;
import com.coolweather.app.model.Province;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class CoolWeatherDB {

	private static CoolWeatherDB coolWeatherDB;
	private SQLiteDatabase db;

	private CoolWeatherDB(Context context) {
		CoolWeatherOpenHelper dbHelper = new CoolWeatherOpenHelper(context,
				DBHelper.DB_NAME, null, DBHelper.VERSION);
		db = dbHelper.getWritableDatabase();
	}

	// ��ȡCoolWeatherDBʵ��
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	// �洢ʡ����Ϣ
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.COLUMN_PROVINCE_NAME, province.getProvinceName());
			values.put(DBHelper.COLUMN_PROVINCE_CODE, province.getProvinceCode());
			db.insert(DBHelper.TABLE_PROVINCE, null, values);
		}
	}

	// �����ݿ��ж�ȡȫ��ʡ����Ϣ
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query(DBHelper.TABLE_PROVINCE, null, null,
				null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex(DBHelper.COLUMN_PROVINCE_NAME)));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex(DBHelper.COLUMN_PROVINCE_CODE)));
				list.add(province);
			} while (cursor.moveToNext());

		}
		return list;
	}

	// ��Cityʵ���洢�����ݿ�
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.COLUMN_CITY_NAME, city.getCityName());
			values.put(DBHelper.COLUMN_CITY_CODE, city.getCitycode());
			values.put(DBHelper.COLUMN_CITY_PROVINCE_ID, city.getProvinceId());
			db.insert(DBHelper.TABLE_CITY, null, values);
		}
	}

	// �����ݿ��ж�ȡĳʡ������Ϣ
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query(DBHelper.TABLE_CITY, null,
				DBHelper.COLUMN_CITY_PROVINCE_ID + " = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex(DBHelper.COLUMN_CITY_NAME)));
				city.setCitycode(cursor.getString(cursor
						.getColumnIndex(DBHelper.COLUMN_CITY_CODE)));
				city.setProvinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());

		}
		return list;
	}

	// ��Countyʵ���洢�����ݿ�
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.COLUMN_COUNTY_NAME, county.getCountyName());
			values.put(DBHelper.COLUMN_COUNTY_CODE, county.getCountyCode());
			values.put(DBHelper.COLUMN_COUNTY_CITY_ID, county.getCityId());
			db.insert(DBHelper.TABLE_COUNTY, null, values);
		}
	}

	// �����ݿ��ж�ȡĳʡ������Ϣ
	public List<County> loadCounties(int cityId) {
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query(DBHelper.TABLE_COUNTY, null,
				DBHelper.COLUMN_COUNTY_CITY_ID + " = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor
						.getColumnIndex(DBHelper.COLUMN_COUNTY_NAME)));
				county.setCountyCode(cursor.getString(cursor
						.getColumnIndex(DBHelper.COLUMN_COUNTY_CODE)));
				county.setCityId(cityId);
				list.add(county);
			} while (cursor.moveToNext());

		}
		return list;
	}
}
