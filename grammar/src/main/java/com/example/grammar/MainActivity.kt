package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle

import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btn_variable.setOnClickListener { startActivity<VariableActivity>() }
        btn_array.setOnClickListener { startActivity<ArrayActivity>() }
        btn_string.setOnClickListener { startActivity<StringActivity>() }
        btn_set_of.setOnClickListener { startActivity<SetOfActivity>() }
        btn_list_of.setOnClickListener { startActivity<ListOfActivity>() }
        btn_map_of.setOnClickListener { startActivity<MapOfActivity>() }
        btn_condition.setOnClickListener { startActivity<ConditionActivity>() }
        btn_repeat.setOnClickListener { startActivity<RepeatActivity>() }
        btn_null.setOnClickListener { startActivity<NullActivity>() }
        btn_equal.setOnClickListener { startActivity<EqualActivity>() }
        btn_function.setOnClickListener { startActivity<FunctionActivity>() }
        btn_param.setOnClickListener { startActivity<ParamActivity>() }
        btn_special.setOnClickListener { startActivity<SpecialActivity>() }
        btn_system.setOnClickListener { startActivity<SystemActivity>() }
        btn_class.setOnClickListener { startActivity<ClassActivity>() }
        btn_member.setOnClickListener { startActivity<MemberActivity>() }
        btn_inherit.setOnClickListener { startActivity<InheritActivity>() }
        btn_secret.setOnClickListener { startActivity<SecretActivity>() }
    }

}
