package com.example.network

import org.json.JSONArray
import org.json.JSONObject

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_json_parse.*

/**
 * Created by ouyangshen on 2017/9/24.
 */
class JsonParseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_json_parse)
        btn_construct_json.setOnClickListener { tv_json.text = jsonStr }
        btn_parser_json.setOnClickListener { tv_json.text = parserJson(jsonStr) }
        btn_traverse_json.setOnClickListener { tv_json.text = traverseJson(jsonStr) }
    }

    //构造json串
    private val jsonStr: String
        get() {
            val obj = JSONObject()
            obj.put("name", "地址信息")
            val array = JSONArray()
            for (i in 0..2) {
                val item = JSONObject()
                item.put("item", "第${i+1}个元素")
                array.put(item)
            }
            obj.put("list", array)
            obj.put("count", array.length())
            obj.put("desc", "这是测试串")
            return obj.toString()
        }

    //解析json串
    private fun parserJson(jsonStr: String?): String {
        val obj = JSONObject(jsonStr)
        var result = "name=${obj.getString("name")}\n" +
                "desc=${obj.getString("desc")}\n" +
                "count=${obj.getInt("count")}\n"
        val listArray = obj.getJSONArray("list")
        //util表示的范围是左闭右开区间。以下语句相当于for (i in 0..listArray.length() - 1)
        for (i in 0 until listArray.length()) {
            val item = listArray.getJSONObject(i)
            result = "${result}\titem=${item.getString("item")}\n"
        }
        return result
    }

    //遍历json串
    private fun traverseJson(jsonStr: String?): String {
        var result = ""
        val obj = JSONObject(jsonStr)
        val it = obj.keys()
        while (it.hasNext()) { // 遍历JSONObject
            var key = it.next().toString()
            result = "${result}key=$key, value=${obj.getString(key)}\n"
        }
        return result
    }

}
