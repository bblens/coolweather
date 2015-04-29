package com.coolweather.app.db;

public final class DBHelper {

	// 数据库名称
	public static final String DB_NAME = "cool_weather";

	// 数据库版本
	public static final int VERSION = 1;

	// 省表字段
	public static final String TABLE_PROVINCE = "Province";
	public static final String COLUMN_PROVINCE_NAME = "province_name";
	public static final String COLUMN_PROVINCE_CODE = "province_code";

	// 市表字段
	public static final String TABLE_CITY = "City";
	public static final String COLUMN_CITY_NAME = "city_name";
	public static final String COLUMN_CITY_CODE = "city_code";
	public static final String COLUMN_CITY_PROVINCE_ID = "province_id";

	// 县表字段
	public static final String TABLE_COUNTY = "County";
	public static final String COLUMN_COUNTY_NAME = "county_name";
	public static final String COLUMN_COUNTY_CODE = "county_code";
	public static final String COLUMN_COUNTY_CITY_ID = "city_id";

	// 省份建表 SQL
	public static final String CREATE_TABLE_PROVINCT = "create table "
			+ TABLE_PROVINCE + " ( "
			+ "id integer primary key autoincrement, " + COLUMN_PROVINCE_NAME
			+ " text, " + COLUMN_PROVINCE_CODE + " text)";

	// 城市建表 SQL
	public static final String CREATE_TABLE_CITY = "create table "
			+ TABLE_CITY + " ( "
			+ "id integer primary key autoincrement, " + COLUMN_CITY_NAME + " text, "
			+ COLUMN_CITY_CODE + " text, " + COLUMN_CITY_PROVINCE_ID + " integer)";

	// 县建表 SQL
	public static final String CREATE_TABLE_COUNTY = "create table "
			+ TABLE_COUNTY + " ("
			+ "id integer primary key autoincrement, " + COLUMN_COUNTY_NAME
			+ " text, " + COLUMN_COUNTY_CODE + " text, " + COLUMN_COUNTY_CITY_ID
			+ " integer)";
}
