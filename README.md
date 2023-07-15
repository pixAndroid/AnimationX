
# AnimationX 
[![](https://jitpack.io/v/pixAndroid/AnimationX.svg)](https://jitpack.io/#pixAndroid/AnimationX)


Mainly focused on regular animations, such as - Aplha, Translate, Scale, etc.

![cover_bl – 12](https://github.com/pixAndroid/AnimationX/assets/36542546/7fd586c7-2640-4aba-8912-3a1641236e6c)

###DeMo


https://github.com/pixAndroid/AnimationX/assets/36542546/634e7abc-19b3-4795-8932-8f65ee3f895b



### Project Requirements
distributionUrl=https\://services.gradle.org/distributions/gradle-7.4-bin.zip



## Implementation

#### Step 1. Add the JitPack repository to your build.gradle file

```bash
    allprojects {
        repositories {
            maven { url 'https://jitpack.io' }
        }
    }
```
#### Step 2. Add the dependency

```bash
	implementation 'com.github.pixAndroid:AnimationX:2.9'
```
## Examples
### 01 - Smooth ProgressBar Animation
```bash
    //SET MAX PROGRESS 
    int yourMaxValue = 20000;
    progress_bar.setMax(yourMaxValue * 100);
    
    //DO ANIMATION
    AnimationX.smoothProgressAnimation(progress_bar, progress, duration);
```

### 02 - Digit Animation
```bash
     AnimationX.digitCounter(0, 100, text_view);
```

### 03 - Color Chnage Animation
```bash
     AnimationX.text_color_change_effect(MainActivity.this, text_view, 0, Color.RED, 500);
```

### 04 - Spin & Move Animation
```bash
     AnimationX.spin_and_move(view);
```

### 05 - Fade In & Fade Out Animation
```bash
     AnimationX.fade_in(view, 2000);
     AnimationX.fade_out(view, 2000);
```

### 06 - Spin Animation
```bash
     AnimationX.spin_animation(view, duration, repeat_count);
```

### 07 - Alpha Animation
```bash
     AnimationX.alphaAnimation(view, from_alpha, to_alpha,  duration, listener);
```

### 08 - Shake Animation
```bash
     AnimationX.shake_animation(view, from_alpha, to_alpha,  duration, listener);
```


## Badges

[![MIT License](https://img.shields.io/badge/License-MIT-green.svg)](https://choosealicense.com/licenses/mit/)


