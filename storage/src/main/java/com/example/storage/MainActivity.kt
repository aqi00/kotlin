package com.example.storage

import android.os.Bundle
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

/**
 * Created by ouyangshen on 2017/9/10.
 */
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_share_write.setOnClickListener { startActivity<ShareWriteActivity>() }
        btn_share_read.setOnClickListener { startActivity<ShareReadActivity>() }
        btn_login_share.setOnClickListener { startActivity<LoginShareActivity>() }
        btn_sqlite_write.setOnClickListener { startActivity<SQLiteWriteActivity>() }
        btn_sqlite_read.setOnClickListener { startActivity<SQLiteReadActivity>() }
        btn_login_sqlite.setOnClickListener { startActivity<LoginSQLiteActivity>() }
        btn_file_path.setOnClickListener { startActivity<FilePathActivity>() }
        btn_text_write.setOnClickListener { startActivity<TextWriteActivity>() }
        btn_text_read.setOnClickListener { startActivity<TextReadActivity>() }
        btn_image_write.setOnClickListener { startActivity<ImageWriteActivity>() }
        btn_image_read.setOnClickListener { startActivity<ImageReadActivity>() }
        btn_app_write.setOnClickListener { startActivity<AppWriteActivity>() }
        btn_app_read.setOnClickListener { startActivity<AppReadActivity>() }
        btn_menu_option.setOnClickListener { startActivity<MenuOptionActivity>() }
        btn_shopping_cart.setOnClickListener { startActivity<ShoppingCartActivity>() }
    }

}
