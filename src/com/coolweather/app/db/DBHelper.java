package com.coolweather.app.db;

public final class DBHelper {

	// ���ݿ�����
	public static final String DB_NAME = "cool_weather";

	// ���ݿ�汾
	public static final int VERSION = 1;

	// ʡ���ֶ�
	public static final String PROVINCE_TABLE_NAME = "Province";
	public static final String PROVINCE_NAME = "province_name";
	public static final String PROVINCE_CODE = "province_code";

	// �б��ֶ�
	public static final String CITY_TABLE_NAME = "City";
	public static final String CITY_NAME = "city_name";
	public static final String CITY_CODE = "city_code";
	public static final String CITY_PROVINCE_ID = "province_id";

	// �ر��ֶ�
	public static final String COUNTY_TABLE_NAME = "County";
	public static final String COUNTY_NAME = "county_name";
	public static final String COUNTY_CODE = "county_code";
	public static final String COUNTY_CITY_ID = "city_id";

	// ʡ�ݽ��� SQL
	public static final String CREATE_TABLE_PROVINCT = "create table "
			+ PROVINCE_TABLE_NAME + " ( "
			+ "id integer primary key autoincrement, " + PROVINCE_NAME
			+ " text, " + PROVINCE_CODE + " text)";

	// ���н��� SQL
	public static final String CREATE_TABLE_CITY = "create table "
			+ CITY_TABLE_NAME + " ( "
			+ "id integer primary key autoincrement, " + CITY_NAME + " text, "
			+ CITY_CODE + " text, " + CITY_PROVINCE_ID + " integer)";

	// �ؽ��� SQL
	public static final String CREATE_TABLE_COUNTY = "create table "
			+ COUNTY_TABLE_NAME + " ("
			+ "id integer primary key autoincrement, " + COUNTY_NAME
			+ " text, " + COUNTY_CODE + " text, " + COUNTY_CITY_ID
			+ " integer)";
}
