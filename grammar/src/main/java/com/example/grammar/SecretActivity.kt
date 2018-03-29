package com.example.grammar

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.grammar.forest.*

import kotlinx.android.synthetic.main.activity_secret.*

class SecretActivity : AppCompatActivity() {
    var count = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_secret)

        btn_class_nest.setOnClickListener {
            //使用嵌套类时，只能引用外部类的类名，不能调用外部类的构造函数
            val peachBlossom = Tree.Flower("桃花");
            tv_class_secret.text = peachBlossom.getName()
        }

        btn_class_inner.setOnClickListener {
            //使用内部类时，必须调用外部类的构造函数，否则编译器会报错
            val peach = Tree("桃树").Fruit("桃子");
            tv_class_secret.text = peach.getName()
        }

        btn_class_enum.setOnClickListener {
            if (count%2 == 0) {
                //ordinal表示枚举类型的序号，name表示枚举类型的名称
                tv_class_secret.text = when (count++%4) {
                    SeasonType.SPRING.ordinal -> SeasonType.SPRING.name
                    SeasonType.SUMMER.ordinal -> SeasonType.SUMMER.name
                    SeasonType.AUTUMN.ordinal -> SeasonType.AUTUMN.name
                    SeasonType.WINTER.ordinal -> SeasonType.WINTER.name
                    else -> "未知"
                }
            } else {
                tv_class_secret.text = when (count++%4) {
                    //使用自定义属性seasonName表示更个性化的描述
                    SeasonName.SPRING.ordinal -> SeasonName.SPRING.seasonName
                    SeasonName.SUMMER.ordinal -> SeasonName.SUMMER.seasonName
                    SeasonName.AUTUMN.ordinal -> SeasonName.AUTUMN.seasonName
                    SeasonName.WINTER.ordinal -> SeasonName.WINTER.seasonName
                    else -> "未知"
                    //枚举类的构造函数是给枚举类型使用的，外部不能直接调用枚举类的构造函数
                    //else -> SeasonName("未知").name
                }
            }
        }

        btn_class_sealed.setOnClickListener {
            var season = when (count++%4) {
                0 -> SeasonSealed.Spring("春天")
                1 -> SeasonSealed.Summer("夏天")
                2 -> SeasonSealed.Autumn("秋天")
                else -> SeasonSealed.Winter("冬天")
            }
            //密封类是一种严格的枚举类，它的值是一个有限的集合。
            //密封类确保条件分支覆盖了所有的枚举类型，因此不再需要else分支。
            tv_class_secret.text = when (season) {
                is SeasonSealed.Spring -> season.name
                is SeasonSealed.Summer -> season.name
                is SeasonSealed.Autumn -> season.name
                is SeasonSealed.Winter -> season.name
            }
        }

        var lotus = Plant("莲", "莲藕", "莲叶", "莲花", "莲蓬", "莲子")
        //数据类的copy方法不带参数，表示复制一模一样的对象
        var lotus2 = lotus.copy()
        btn_class_data.setOnClickListener {
            lotus2 = when (count++%2) {
                //copy方法带参数，表示指定参数另外赋值
                0 -> lotus.copy(flower="荷花")
                else -> lotus.copy(flower="莲花")
            }
            //数据类自带equals方法，用于判断两个对象是否一样
            var result = if (lotus2.equals(lotus)) "相等" else "不等"
            tv_class_secret.text = "两个植物的比较结果是${result}\n" +
                    "第一个植物的描述是${lotus.toString()}\n" +
                    "第二个植物的描述是${lotus2.toString()}"
        }

        btn_class_generic.setOnClickListener {
            var river = when (count++%4) {
                //模板类(泛型类)声明对象时，要在模板类的类名后面加上“<参数类型>”
                0 -> River<Int>("小溪", 100)
                //如果编译器根据输入参数就能知晓参数类型，也可直接省略“<参数类型>”
                1 -> River("瀑布", 99.9f)
                //当然保守起见，新手最好按规矩添加“<参数类型>”
                2 -> River<Double>("山涧", 50.5)
                //如果你已经是老手了，怎么方便怎么来，Kotlin的设计初衷就是偷懒
                else -> River("大河", "一千")
            }
            tv_class_secret.text = river.getInfo()
        }
    }

}
