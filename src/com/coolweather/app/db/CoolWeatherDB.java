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

	// 获取CoolWeatherDB实例
	public synchronized static CoolWeatherDB getInstance(Context context) {
		if (coolWeatherDB == null) {
			coolWeatherDB = new CoolWeatherDB(context);
		}
		return coolWeatherDB;
	}

	// 存储省份信息
	public void saveProvince(Province province) {
		if (province != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.PROVINCE_NAME, province.getProvinceName());
			values.put(DBHelper.PROVINCE_CODE, province.getProvinceCode());
			db.insert(DBHelper.PROVINCE_TABLE_NAME, null, values);
		}
	}

	// 从数据库中读取全国省份信息
	public List<Province> loadProvinces() {
		List<Province> list = new ArrayList<Province>();
		Cursor cursor = db.query(DBHelper.PROVINCE_TABLE_NAME, null, null,
				null, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				Province province = new Province();
				province.setId(cursor.getInt(cursor.getColumnIndex("id")));
				province.setProvinceName(cursor.getString(cursor
						.getColumnIndex(DBHelper.PROVINCE_NAME)));
				province.setProvinceCode(cursor.getString(cursor
						.getColumnIndex(DBHelper.PROVINCE_CODE)));
				list.add(province);
			} while (cursor.moveToNext());

		}
		return list;
	}

	// 将City实例存储到数据库
	public void saveCity(City city) {
		if (city != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.CITY_NAME, city.getCityName());
			values.put(DBHelper.CITY_CODE, city.getCitycode());
			values.put(DBHelper.CITY_PROVINCE_ID, city.getProvinceId());
			db.insert(DBHelper.CITY_TABLE_NAME, null, values);
		}
	}

	// 从数据库中读取某省城市信息
	public List<City> loadCities(int provinceId) {
		List<City> list = new ArrayList<City>();
		Cursor cursor = db.query(DBHelper.CITY_TABLE_NAME, null,
				DBHelper.CITY_PROVINCE_ID + " = ?",
				new String[] { String.valueOf(provinceId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				City city = new City();
				city.setId(cursor.getInt(cursor.getColumnIndex("id")));
				city.setCityName(cursor.getString(cursor
						.getColumnIndex(DBHelper.CITY_NAME)));
				city.setCitycode(cursor.getString(cursor
						.getColumnIndex(DBHelper.CITY_CODE)));
				city.setProvinceId(provinceId);
				list.add(city);
			} while (cursor.moveToNext());

		}
		return list;
	}

	// 将County实例存储到数据库
	public void saveCounty(County county) {
		if (county != null) {
			ContentValues values = new ContentValues();
			values.put(DBHelper.COUNTY_NAME, county.getCountyName());
			values.put(DBHelper.COUNTY_CODE, county.getCountyCode());
			values.put(DBHelper.COUNTY_CITY_ID, county.getCityId());
			db.insert(DBHelper.COUNTY_TABLE_NAME, null, values);
		}
	}

	// 从数据库中读取某省城市信息
	public List<County> loadCounties(int cityId) {
		List<County> list = new ArrayList<County>();
		Cursor cursor = db.query(DBHelper.COUNTY_TABLE_NAME, null,
				DBHelper.COUNTY_CITY_ID + " = ?",
				new String[] { String.valueOf(cityId) }, null, null, null);
		if (cursor.moveToFirst()) {
			do {
				County county = new County();
				county.setId(cursor.getInt(cursor.getColumnIndex("id")));
				county.setCountyName(cursor.getString(cursor
						.getColumnIndex(DBHelper.COUNTY_NAME)));
				county.setCountyCode(cursor.getString(cursor
						.getColumnIndex(DBHelper.COUNTY_CODE)));
				county.setCityId(cityId);
				list.add(county);
			} while (cursor.moveToNext());

		}
		return list;
	}
}
