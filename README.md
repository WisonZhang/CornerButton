# CornerButton

###效果

![https://github.com/WisonZhang/CornerButton/blob/master/20180310102456.png]()


###Gradle
```
allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
}
dependencies {
	        compile 'com.github.WisonZhang:CornerButton:V1.0'
}
```

###可选属性
|      属性       |        介绍         |
| :-----------: | :---------------: |
|    corner     |       圆角大小        |
| pressed_color | 按下颜色（不设置在正常颜色上变灰） |
| normal_color  |    正常展示颜色，默认白色    |
| stroke_width  |     边框大小，默认为0     |
| stroke_color  |   边框颜色，需设置边框大小    |

###可用方法
setNormalColor
setPressedColor
setStrokeColor
setStrokeWidth
setCorner