/*
 * Copyright (C) 2019 The Jerry xu Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.incoder.weather.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.incoder.weather.model.City;
import org.incoder.weather.model.County;
import org.incoder.weather.model.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * WeatherDB.
 *
 * @author : Jerry xu
 * @date : 2019/3/9 22:05
 */
public class WeatherDB {

    /**
     * 数据库名
     */
    public static final String DB_NAME = "weather_t";

    /**
     * 数据库版本
     */
    public static final int VERSION = 1;

    /**
     * 相关的数据库表名称
     */
    public static final String PROVINCE = "Province";
    public static final String CITY = "City";
    public static final String COUNTY = "County";

    private static WeatherDB sWeatherDB;

    private SQLiteDatabase db;

    private WeatherDB(Context context) {
        WeatherOpenHelper helper = new WeatherOpenHelper(context, DB_NAME, null, VERSION);
        db = helper.getWritableDatabase();
    }


    public synchronized static WeatherDB getInstance(Context context) {
        if (sWeatherDB == null) {
            sWeatherDB = new WeatherDB(context);
        }
        return sWeatherDB;
    }


    /**
     * 将 Province 实例存储到数据库
     *
     * @param province province
     */
    public void saveProvince(Province province) {
        if (province != null) {
            ContentValues values = new ContentValues();
            values.put("province_name", province.getProvinceName());
            values.put("province_code", province.getProvinceCode());
            db.insert("Province", null, values);
        }
    }


    /**
     * 从数据库读取全国所有的省份信息
     *
     * @return 省信息
     */
    public List<Province> loadProvinces() {
        List<Province> list = new ArrayList<>();
        Cursor cursor = db.query(PROVINCE, null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                Province province = new Province();
                province.setId(cursor.getInt(cursor.getColumnIndex("id")));
                province.setProvinceName(cursor.getString(cursor.getColumnIndex("province_name")));
                province.setProvinceCode(cursor.getString(cursor.getColumnIndex("province_code")));
                list.add(province);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 将City实例存储到数据库
     *
     * @param city city
     */
    public void saveCity(City city) {
        if (city != null) {
            ContentValues values = new ContentValues();
            values.put("city_name", city.getCityName());
            values.put("city_code", city.getCityCode());
            values.put("province_id", city.getProvinceId());
            db.insert("City", null, values);
        }
    }


    /**
     * 从数据库读取某个省下所有的城市信息
     *
     * @param provinceId 省id
     * @return 城市信息
     */
    public List<City> loadCities(int provinceId) {
        List<City> list = new ArrayList<>();
        Cursor cursor = db.query(CITY, null, "province_id=?",
                new String[]{String.valueOf(provinceId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                City city = new City();
                city.setId(cursor.getInt(cursor.getColumnIndex("id")));
                city.setCityName(cursor.getString(cursor.getColumnIndex("city_name")));
                city.setCityCode(cursor.getString(cursor.getColumnIndex("city_code")));
                city.setProvinceId(provinceId);
                list.add(city);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

    /**
     * 将County实例存储到数据库
     *
     * @param county county
     */
    public void saveCounty(County county) {
        if (county != null) {
            ContentValues values = new ContentValues();
            values.put("county_name", county.getCountyName());
            values.put("county_code", county.getCountyCode());
            values.put("city_id", county.getCityId());
            db.insert("County", null, values);
        }
    }


    /**
     * 从数据库读取某个城市下所有的县信息
     *
     * @param cityId 市 id
     * @return 县信息
     */
    public List<County> loadCounty(int cityId) {
        List<County> list = new ArrayList<>();
        Cursor cursor = db.query(COUNTY, null, "city_id=?",
                new String[]{String.valueOf(cityId)}, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                County county = new County();
                county.setId(cursor.getInt(cursor.getColumnIndex("id")));
                county.setCountyName(cursor.getString(cursor.getColumnIndex("county_name")));
                county.setCountyCode(cursor.getString(cursor.getColumnIndex("county_code")));
                county.setCityId(cityId);
                list.add(county);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }

}
