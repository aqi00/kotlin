package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.grammar.animal.*

import kotlinx.android.synthetic.main.activity_member.*

class MemberActivity : AppCompatActivity() {
    var count = 0
    var animalName = ""
    var animalSex = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_member)

        btn_member_default.setOnClickListener {
            setAnimalInfo()
            var animal = when (count%2) {
                0 -> WildAnimal(animalName)
                else -> WildAnimal(animalName, animalSex)
            }
            tv_class_member.text = "这只${animal.name}是${if (animal.sex==0) "公" else "母"}的"
        }

        btn_member_custom.setOnClickListener {
            setAnimalInfo()
            var animal = when (count%2) {
                0 -> WildAnimalMember(animalName)
                else -> WildAnimalMember(animalName, animalSex)
            }
            tv_class_member.text = "这只${animal.name}是${animal.sexName}的"
        }

        btn_member_function.setOnClickListener {
            setAnimalInfo()
            var animal = when (count%2) {
                0 -> WildAnimalFunction(animalName)
                else -> WildAnimalFunction(animalName, animalSex)
            }
            tv_class_member.text = animal.getDesc("动物园")
        }

        val sexArray:Array<String> = arrayOf("公","母","雄","雌")
        btn_companion_object.setOnClickListener {
            var sexName:String = sexArray[count++%4]
            //伴生对象的WildAnimal名称可以省略掉
            //tv_class_member.text = "\"$sexName\"对应的类型是${WildAnimalCompanion.WildAnimal.judgeSex(sexName)}"
            tv_class_member.text = "\"$sexName\"对应的类型是${WildAnimalCompanion.judgeSex(sexName)}"
        }

        btn_property_constant.setOnClickListener {
            setAnimalInfo()
            var animal = when (count%2) {
                0 -> WildAnimal(animalName)
                else -> WildAnimal(animalName, animalSex)
            }
            tv_class_member.text = "这只${animal.name}是${if (animal.sex==WildAnimalConstant.MALE) "公" else "母"}的"
        }
    }

    fun setAnimalInfo() {
        count++
        animalName = if (count%2==0) "老虎" else "斑马"
        animalSex = if (count%3==0) 0 else 1
    }

}
